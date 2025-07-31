package org.openjfx.LevelJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.openjfx.GUI.GUIGame;
import org.openjfx.Game.GameClass.Game;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameClass.Time;
import org.openjfx.Game.GameClass.Scoreboard.ScoreboardController;
import org.openjfx.Game.GameComponent.Bricks.Brick;
import org.openjfx.Game.GameComponent.Bricks.DurabilityBrick;
import org.openjfx.Game.GameComponent.Bricks.UnbreakableBrick;
import org.openjfx.Game.GameComponent.Bricks.Bonus.Aleatoire;
import org.openjfx.Game.GameComponent.Bricks.Bonus.Bonus;

import com.google.gson.Gson;

/**
 * Cette classe gère la lecture des fichiers JSON de niveaux et leur
 * conversion en structures de données utilisables dans le jeu.
 */
public class LevelReader {
    private static LevelJson l = new LevelJson();
    private static ScoreboardController scoreboardController;
    private static GUIGame guiGame;
    private static Level level;

    /**
     * Lit le contenu du fichier JSON correspondant au niveau donné et initialise
     * les briques en fonction des données. En cas d'erreur, renvoie {@code null}.
     *
     * @param lvl                     le numéro du niveau à charger
     * @param isCoop                  indique si le mode coopératif est activé
     * @param scoreboardControllerarg le contrôleur du tableau de score
     * @param guiGamearg              l'interface graphique du jeu
     * @param levelarg                l'objet représentant le niveau actuel
     * @return un tableau de briques représentant le niveau, ou {@code null} en cas
     *         d'erreur
     */
    public static Brick[][] read(int lvl, boolean isCoop, ScoreboardController scoreboardControllerarg,
            GUIGame guiGamearg, Level levelarg) {
        scoreboardController = scoreboardControllerarg;
        guiGame = guiGamearg;
        level = levelarg;
        if (lvl > Game.totalLevels || lvl < 1)
            return null;

        Gson gson = new Gson();
        try (InputStream inputStream = LevelReader.class.getResourceAsStream("/LevelJSON/lvl" + lvl + ".json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            l = gson.fromJson(reader, LevelJson.class);
            return initBrick(l);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lit le fichier JSON spécifié par son nom pour un éditeur de niveaux.
     *
     * @param lvl                     le nom du fichier JSON à charger
     * @param scoreboardControllerArg le contrôleur du tableau de score
     * @param guiGameArg              l'interface graphique du jeu
     * @param levelarg                l'objet représentant le niveau actuel
     * @return un tableau de briques représentant le niveau, ou {@code null} en cas
     *         d'erreur
     */
    public static Brick[][] read(String lvl, ScoreboardController scoreboardControllerArg, GUIGame guiGameArg,
            Level levelarg) {
        guiGame = guiGameArg;
        scoreboardController = scoreboardControllerArg;
        level = levelarg;
        Gson gson = new Gson();
        try (InputStream inputStream = LevelReader.class.getResourceAsStream("/LevelJSON/" + lvl + ".json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            l = gson.fromJson(reader, LevelJson.class);
            return initBrick(l);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Initialise les paramètres du niveau en fonction des données de l'objet
     * LevelJson.
     * Configure des propriétés comme la vitesse de la balle, l'accélération, et la
     * largeur du niveau.
     */
    private static void initValue() {
        level.setMaximumBallSpeed(l.getMaxSpeed());
        guiGame.setGameW(l.getWidth());
        level.setBallAccelerationRate(l.getAcc() / 1000.0);
        level.setBallSpeed(Game.speed, Game.speed);
        Time.setRate(l.getRate());
        guiGame.clearBonusList();
    }

    /**
     * Initialise le tableau de briques en fonction des données de l'objet
     * LevelJson.
     * Traite différents types de briques (durables, incassables, avec bonus).
     *
     * @param l l'objet LevelJson contenant les informations de configuration du
     *          niveau
     * @return un tableau de briques représentant le niveau
     */
    private static Brick[][] initBrick(LevelJson l) {
        int[][] tab = l.getTab();
        int length = maxLength(tab);
        int nbBrick = 0;
        Brick[][] currentLevel = new Brick[tab.length][length];

        l.setWidth(Game.unit * length);
        initValue();

        for (int j = 0; j < tab.length; j++) {
            int i = 0;
            int y = 0;
            while (y < tab[j].length && i < length) {
                if (tab[j][y] == 0) {
                    currentLevel[j][i] = null;
                } else {
                    if (tab[j][y] / 100 != 0 && tab[j][y] % 100 / 10 == 2 && tab[j][y] % 10 <= 7) {
                        currentLevel[j][i] = new DurabilityBrick(tab[j][y], Game.originX + i * Game.unit,
                                Game.originY + j * Game.unit, tab[j][y] / 100,
                                new Bonus(new Aleatoire(), guiGame, scoreboardController),
                                scoreboardController, level);
                    } else if (tab[j][y] % 10 == 9) {
                        nbBrick--;
                        currentLevel[j][i] = new UnbreakableBrick(tab[j][y], Game.originX + i * Game.unit,
                                Game.originY + j * Game.unit, scoreboardController, level);
                    } else if ((tab[j][y] / 10 == 1)
                            || (tab[j][y] / 10 == 2 && (tab[j][y] % 10 == 9 || tab[j][y] % 10 <= 4))) {
                        currentLevel[j][i] = new Brick(tab[j][y], Game.originX + i * Game.unit,
                                Game.originY + j * Game.unit, new Bonus(new Aleatoire(), guiGame, scoreboardController),
                                scoreboardController, level);
                    } else {
                        System.out.println(
                                "Erreur lors de la lecture du niveau, le fichier JSON ne respecte pas les formats ou les valeurs acceptées");
                        System.exit(0);
                    }
                    i += duplicate(i, j, currentLevel);
                    nbBrick++;
                }
                i++;
                y++;
            }
        }
        level.setRemainingBrickCount(nbBrick);
        return currentLevel;
    }

    /**
     * Réplique une brique plusieurs fois pour remplir des colonnes adjacentes dans
     * le tableau.
     *
     * @param i  l'indice de colonne de la brique
     * @param j  l'indice de ligne de la brique
     * @param cl le tableau des briques
     * @return le nombre de cases supplémentaires remplies
     */
    private static int duplicate(int i, int j, Brick[][] cl) {
        if (cl[j][i].getTypeId() / 10 == 1) {
            return 0;
        } else if (cl[j][i].getTypeId() % 100 / 10 == 2 || cl[j][i].getTypeId() / 10 != 0) {
            for (int k = 1; k < 4; k++) {
                cl[j][i + k] = cl[j][i];
            }
            return 3;
        }
        return 0;
    }

    /**
     * Calcule la longueur effective d'une ligne dans le tableau en tenant compte
     * des types de briques.
     *
     * @param s une ligne du tableau des briques
     * @return la longueur totale de la ligne
     */
    private static int length(int[] s) {
        int total = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] / 10 == 2 || s[i] / 100 != 0) {
                total += 4;
            } else {
                total += 1;
            }
        }
        return total;
    }

    /**
     * Détermine la longueur maximale parmi toutes les lignes du tableau des
     * briques.
     *
     * @param tab le tableau des briques
     * @return la longueur maximale d'une ligne
     */
    private static int maxLength(int[][] tab) {
        int max = 0;
        for (int[] t : tab) {
            int len = length(t);
            if (len > max)
                max = len;
        }
        return max;
    }
}
