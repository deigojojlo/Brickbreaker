package org.openjfx.HighscoreTXT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;

import org.openjfx.Game.GameClass.Game;

import javafx.util.Pair;

/**
 * Classe responsable de la gestion des scores élevés (highscores).
 * Permet de lire, sauvegarder et mettre à jour les scores dans un fichier
 * texte.
 */
public class HighScore {

    /**
     * Map pour stocker les scores par niveau et mode de jeu (coopération ou non).
     * La clé est une paire composée du niveau et d'un booléen représentant si le mode est coopératif.
     * La valeur est une liste liée des scores, chaque score étant une paire contenant le score et le nom du joueur.
     */
    private static HashMap<Pair<Integer, Boolean>, LinkedList<Pair<Integer, String>>> scoreMap = new HashMap<>();

    /**
    * Chemin vers le fichier des highscores. 
    * Ce fichier est utilisé pour sauvegarder et charger les scores.
    */
    public static String pathHighscore = "src/main/resources/HighscoreTXT/highscore.txt";

    /**
    * Flux d'entrée pour accéder aux données du fichier highscore.
    * Permet de lire le fichier de scores enregistré.
    */
    public static InputStream inputStream = HighScore.class.getResourceAsStream(pathHighscore);

    /**
     * Récupère la map contenant tous les highscores.
     * 
     * @return La map des highscores.
     */
    public static HashMap<Pair<Integer, Boolean>, LinkedList<Pair<Integer, String>>> getHighScores() {
        return scoreMap;
    }

    /**
     * Définit une nouvelle map de highscores.
     * 
     * @param sauvegarde La nouvelle map à définir.
     */
    public static void setHighScores(
            HashMap<Pair<Integer, Boolean>, LinkedList<Pair<Integer, String>>> sauvegarde) {
        scoreMap = sauvegarde;
    }

    /**
     * Lit les scores depuis le fichier texte et les stocke dans la map.
     */
    public static void read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathHighscore))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splittedLine = line.split(" ");
                Pair<Integer, Boolean> key = new Pair<>(Integer.parseInt(splittedLine[0]),
                        splittedLine[1].equals("true"));
                Integer score = Integer.parseInt(splittedLine[2]);
                String name = splittedLine[3];

                Pair<Integer, String> scoreWithName = new Pair<>(score, name);
                addScore(key, scoreWithName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sauvegarde les scores actuels de la map dans le fichier texte.
     */
    public static void save() {
        try (FileWriter writer = new FileWriter(pathHighscore)) {
            for (Pair<Integer, Boolean> key : scoreMap.keySet()) {
                LinkedList<Pair<Integer, String>> scoreList = scoreMap.get(key);
                for (Pair<Integer, String> scoreAndName : scoreList) {
                    writer.write(key.getKey() + " " + key.getValue() + " "
                            + scoreAndName.getKey() + " " + scoreAndName.getValue() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute un score à la map pour un niveau et mode donnés. Trie la liste en
     * ordre décroissant des scores.
     * 
     * @param key           La clé représentant le niveau et le mode (coop ou non).
     * @param scoreWithName Le score et le nom du joueur à ajouter.
     */
    public static void addScore(Pair<Integer, Boolean> key, Pair<Integer, String> scoreWithName) {
        scoreMap.computeIfAbsent(key, k -> new LinkedList<>()).add(scoreWithName);
        scoreMap.get(key).sort((pair1, pair2) -> Integer.compare(pair2.getKey(), pair1.getKey()));
    }

    /**
     * Ajoute un score pour un mode spécifique (rush ou coop).
     * 
     * @param isRush Indique si le mode est rush (true) ou non.
     * @param isCoop Indique si le mode est en coopération (true) ou solo (false).
     * @param score  Le score à ajouter.
     * @param level Le niveau ou le score a été effectué.
     */
    public static void addScore(boolean isRush, boolean isCoop, int score, int level) {
        Pair<Integer, String> p = new Pair<>(score, Game.playerName);
        Pair<Integer, Boolean> key = new Pair<>(isRush ? 0 : level, isCoop);
        addScore(key, p);
    }
}
