package org.openjfx.GUI;

import java.util.ArrayList;

import org.openjfx.BrickBreaker;
import org.openjfx.Game.GameClass.Game;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameClass.Time;
import org.openjfx.Game.GameClass.Scoreboard.ScoreboardController;
import org.openjfx.Game.GameComponent.Racket;
import org.openjfx.Game.GameComponent.Bricks.Bonus.Bonus_switch;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * Classe GUIGame : représente l'interface utilisateur pour le jeu.
 * Elle gère l'affichage graphique, l'initialisation des niveaux
 * et la gestion des bonus.
 */
public class GUIGame {
    /**
     * La variable {@code center} est un conteneur de type {@link GridPane} qui sert
     * à organiser et disposer
     * les différents éléments graphiques au centre de la fenêtre du jeu, tels que
     * le terrain de jeu et autres composants.
     */
    private GridPane center;

    /**
     * La variable {@code canvas} est de type {@link Canvas} et sert à dessiner sur
     * l'écran. Elle représente
     * la surface de dessin principale où les objets du jeu (raquettes, balles,
     * briques) seront rendus.
     */
    private Canvas canvas;

    /**
     * La variable {@code scene} représente la scène principale du jeu, de type
     * {@link Scene}.
     * Elle est utilisée pour définir et gérer tous les éléments de l'interface
     * utilisateur et les événements de saisie.
     */
    private Scene scene;

    /**
     * La variable {@code game} est un conteneur de type {@link BorderPane} utilisé
     * comme structure principale
     * de la scène. Elle organise les éléments en une disposition spécifique : un
     * centre pour le jeu, une barre de score, etc.
     */
    private BorderPane game = new BorderPane();

    /**
     * La variable {@code playStatus} est un entier représentant l'état actuel du
     * jeu.
     * Les valeurs possibles sont :
     * - 0 : En attente d'une touche pour démarrer.
     * - 1 : En cours de jeu.
     * - 2 : Pause.
     * - 3 : Victoire.
     * - 4 : Jeu terminé.
     * - 5 : Perte d'une vie, en attente de confirmation.
     */
    private int playStatus;

    /**
     * La variable {@code bg} est de type {@link Color} et représente la couleur de
     * fond du jeu.
     * Par défaut, elle est définie sur noir (NOIR).
     */
    private Color bg = Color.BLACK;

    /**
     * La variable {@code bonusDisplay} est un conteneur de type {@link VBox}, qui
     * est utilisé pour afficher
     * la liste des bonus actifs dans le jeu. Chaque bonus est représenté par une
     * étiquette (Label).
     */
    private VBox bonusDisplay;

    /**
     * La variable {@code bonusList} est une liste d'objets {@link Label} qui stocke
     * les bonus en cours
     * d'affichage. Chaque élément de la liste représente un bonus spécifique
     * affiché à l'écran.
     */
    private ArrayList<Label> bonusList;

    /**
     * La variable {@code bonusDisplayWidth} détermine la largeur de la zone
     * d'affichage des bonus à gauche de
     * l'écran du jeu. Elle est utilisée pour ajuster la taille de la VBox contenant
     * les bonus.
     */
    public int bonusDisplayWidth = 250;

    /**
     * La variable {@code scoreboardController} est un contrôleur de type
     * {@link ScoreboardController}
     * qui gère l'affichage et la mise à jour du tableau de scores du jeu.
     */
    private ScoreboardController scoreboardController;

    /**
     * La variable {@code frame} représente l'objet principal de la fenêtre du jeu,
     * de type {@link BrickBreaker}.
     * Elle est utilisée pour accéder à l'interface générale et gérer les événements
     * de niveau supérieur.
     */
    private BrickBreaker frame;

    /**
     * La variable {@code level} représente l'objet du niveau en cours, de type
     * {@link Level}.
     * Elle contient toutes les informations concernant l'état du niveau (les
     * briques, les raquettes, les balles, etc.).
     */
    private Level level;

    {
        /* Initialisation du canvas */
        canvas = new Canvas(Game.windowWidth, Game.gameHeight);
        Game.graphicsContext = canvas.getGraphicsContext2D();
        center = new GridPane();
        bonusDisplay = new VBox(15);
        bonusList = new ArrayList<Label>();
        /* Initialisation de la scène */
        scene = new Scene(game, Game.windowWidth, Game.windowHeight);
        Time.createTimeline(() -> this.run());
    }

    /**
     * Constructeur pour le mode de jeu RUSH.
     * 
     * @param frame Fenêtre principale du jeu.
     */
    public GUIGame(BrickBreaker frame) {
        this.frame = frame;
        this.scoreboardController = new ScoreboardController(game, this.frame);
        this.scoreboardController.display();
        this.level = new Level(scoreboardController, this);
        start();
    }

    /**
     * Constructeur pour initialiser le jeu en mode **Campagne** avec un niveau
     * spécifique.
     *
     * @param frame La fenêtre principale du jeu, utilisée pour accéder aux
     *              différentes parties de l'interface.
     * @param lvl   Le numéro du niveau demandé par le joueur. Ce niveau est chargé
     *              et le jeu commence à ce niveau.
     * 
     */
    public GUIGame(BrickBreaker frame, int lvl) {
        this.frame = frame;
        this.scoreboardController = new ScoreboardController(game, this.frame);
        this.scoreboardController.display();
        try {
            this.level = new Level(scoreboardController, this, lvl, frame.isCoop());
        } catch (IllegalArgumentException e) {
            this.frame.setHome();
        }
        start();
    }

    /**
     * Constructeur pour le mode de jeu Campagne avec un niveau chargé depuis un
     * fichier.
     * 
     * @param frame La fenêtre principale du jeu, utilisée pour accéder à
     *              l'interface et gérer les événements du jeu.
     * 
     * @param lvl   Nom du fichier de niveau.
     */
    public GUIGame(BrickBreaker frame, String lvl) {
        this.frame = frame;
        this.scoreboardController = new ScoreboardController(game, this.frame);
        this.scoreboardController.display();
        this.level = new Level(scoreboardController, this, lvl);
        start();
    }

    /**
     * Initialise les composants graphiques du jeu et démarre la timeline.
     */
    protected void start() {
        /* Initialisation du jeu */
        game.setStyle("-fx-background-color : " + Game.backgroundColor);
        Time.start(this);
        Game.gameHeight = Math.max(775, Game.windowHeight - Game.scoreboardViewHeigh);
        playStatus = 0;

        /* Initialisation du centre */
        center.setMaxWidth(Game.windowWidth);
        center.setMinWidth(Game.windowWidth);
        center.setMaxHeight(Game.gameHeight);
        center.setMinHeight(Game.gameHeight);

        /* Initialisation de l'affichage des bonus */
        bonusDisplay.setMaxWidth(bonusDisplayWidth);
        bonusDisplay.setMinWidth(bonusDisplayWidth);
        bonusDisplay.setMaxHeight(Game.gameHeight);
        bonusDisplay.setMinHeight(Game.gameHeight);
        bonusDisplay.setAlignment(Pos.CENTER);

        /* Configuration de la structure principale */
        game.setCenter(center);
        game.setLeft(bonusDisplay);
        center.add(canvas, 0, 0);
        center.requestFocus();
        Time.start(this);
        Time.setRate(1);
    }

    /* Getters */

    /**
     * Retourne la scène principale du jeu.
     * 
     * @return La scène principale.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Retourne la couleur de fond du jeu.
     * 
     * @return La couleur de fond.
     */
    public Color getBG() {
        return bg;
    }

    /**
     * Définit la largeur du terrain de jeu.
     * 
     * @param w Largeur du terrain.
     */
    public void setGameW(int w) {
        Game.gameWidth = w;
        Game.originX = Math.max(0, -bonusDisplayWidth +
                (Game.windowWidth - Game.gameWidth) / 2);
        Game.graphicsContext.setFill(
                new Color(Game.backgroundCode / 255.0, Game.backgroundCode / 255.0, Game.backgroundCode / 255.0, 1.0));
        Game.graphicsContext.fillRect(Game.originX, Game.originY, Game.windowWidth, Game.windowHeight);
    }

    /**
     * Ajoute une étiquette à la liste des bonus affichés.
     * 
     * @param bonusSentence L'étiquette du bonus à ajouter.
     */
    public void addBonusList(Label bonusSentence) {
        if (bonusSentence.getText().equals("/!\\ contrôle inversé")) {
            for (Label label : bonusList) {
                if (label.getText().equals("/!\\ contrôle inversé")) {
                    removeBonusList(label);
                    return;
                }
            }
        }
        bonusSentence.setStyle(String.format("-fx-font-size: 20px; -fx-text-fill: %s; -fx-alignment : center;",
                Game.backgroundCode == 28 ? "white" : "black"));
        bonusList.add(bonusSentence);
        updateBonusDisplay(null);
    }

    /**
     * Supprime une étiquette de la liste des bonus affichés.
     * 
     * @param toRemove L'étiquette du bonus à supprimer.
     */
    public void removeBonusList(Label toRemove) {
        bonusList.remove(toRemove);
        updateBonusDisplay(toRemove);
    }

    /**
     * Supprime l'étiquette du bonus "+1 balle" si elle est présente.
     */
    public void removeBonusList() {
        for (int i = 0; i < bonusList.size(); i++) {
            if (bonusList.get(i).getText().equals("1 balle supplémentaire")) {
                removeBonusList(bonusList.get(i));
                break;
            }
        }
    }

    /**
     * Vide la liste des bonus affichés et révoque les malus si nécessaire.
     */
    public void clearBonusList() {
        bonusDisplay.getChildren().removeAll(bonusList);
        for (int i = 0; i < bonusList.size(); i++) {
            if (bonusList.get(i).getText().equals("/!\\ contrôle inversé"))
                Bonus_switch.apply(this.level); // Révoquer tous les malus de contrôle inversé
        }
        bonusList.clear();
    }

    /**
     * Met à jour l'affichage des bonus dans la VBox.
     * Si la liste des bonus (`bonusList`) contient plus d'éléments que la VBox
     * (`bonusDisplay`),
     * ajoute le dernier élément de la liste.
     * Si un bonus est spécifié en paramètre, il est retiré de la VBox.
     * 
     * @param bonus `null` pour ajouter un élément, ou l'élément à retirer.
     */
    private void updateBonusDisplay(Label bonus) {
        if (bonusList.size() > bonusDisplay.getChildren().size() && bonus == null) {
            bonusDisplay.getChildren().add(bonusList.get(bonusList.size() - 1));
        } else if (bonusList.size() < bonusDisplay.getChildren().size() && bonus != null) {
            bonusDisplay.getChildren().remove(bonus);
        }
    }

    /**
     * Alterne entre l'état "pause" et "en jeu" de manière efficace.
     */
    protected void togglePlay() {
        if (playStatus == 1) {
            playStatus = 2; // Passe en pause
        } else if (playStatus == 2) {
            playStatus = 1; // Passe en mode jeu
        }
    }

    /**
     * Définit l'état actuel du jeu.
     * 
     * @param i L'état souhaité :
     *          - 0 : En attente d'une touche
     *          - 1 : En jeu
     *          - 2 : En pause
     *          - 3 : En attente d'informations (par exemple, après avoir gagné un
     *          niveau)
     *          - 4 : Jeu terminé
     *          - 5 : Perte d'une vie, en attente de confirmation
     */
    public void setPlay(int i) {
        playStatus = i;
    }

    /**
     * Configure les événements clavier pour permettre le déplacement des raquettes.
     * Définit les actions associées à la pression et au relâchement des touches.
     * 
     * @param r Tableau de raquettes à gérer.
     */
    public void setHandler(Racket... r) {
        scene.setOnKeyPressed(e -> {
            if (playStatus == 0 || playStatus == 5) {
                playStatus = 1; // Passe en mode jeu si le statut était "en attente"
            } else if (e.getCode() == KeyCode.ESCAPE) {
                togglePlay(); // Alterne entre pause et jeu
            } else if (e.getCode() == KeyCode.ENTER) {
                level.setRemainingBrickCount(1);
            }
            for (Racket racket : r) {
                racket.onKeyPressed(e); // Gère l'événement de pression de touche pour chaque raquette
            }
            e.consume(); // Empêche la propagation de l'événement
        });
        scene.setOnKeyReleased(e -> {
            for (Racket racket : r) {
                racket.onKeyReleased(e); // Gère l'événement de relâchement de touche pour chaque raquette
            }
            e.consume(); // Empêche la propagation de l'événement
        });
    }

    /**
     * Fonction principale appelée par la classe `Timer`.
     * Effectue différentes actions en fonction de l'état du jeu (`playStatus`).
     */
    public void run() {
        double textPositionX = Game.originX + (Game.gameWidth / 2); // Position centrale du texte
        Game.graphicsContext.setFill(Color.WHITE);
        Game.graphicsContext.setTextAlign(TextAlignment.CENTER);

        if (playStatus == 0) {
            // État : en attente d'une touche pour démarrer
            Game.graphicsContext.setFill(bg);
            Game.graphicsContext.fillRect(Game.originX, Game.originY, Game.gameWidth, Game.gameHeight);
            Game.graphicsContext.setFill(Color.WHITE);
            Game.graphicsContext.fillText("press any key to start", textPositionX, Game.windowHeight / 2);
        } else if (playStatus == 1) {
            // État : en jeu
            Game.graphicsContext.setFill(bg);
            Game.graphicsContext.fillRect(Game.originX, Game.originY, Game.gameWidth, Game.gameHeight);
            Racket racketP1 = this.level.getPlayer1Racket();
            Racket racketP2 = this.level.isCooperativeModeEnabled() ? this.level.getPlayer2Racket() : null;

            if (racketP1 != null) {
                // Dessiner la raquette 1
                Game.graphicsContext.setFill(racketP1.getColor());
                Game.graphicsContext.fillRect(racketP1.getPositionX(), racketP1.getPositionY(), racketP1.getWidth(),
                        racketP1.getHeight());
            }

            if (this.frame.isCoop() && racketP2 != null) {
                // Dessiner la raquette 2 en mode coopératif
                Game.graphicsContext.setFill(racketP2.getColor());
                Game.graphicsContext.fillRect(racketP2.getPositionX(), racketP2.getPositionY(), racketP2.getWidth(),
                        racketP2.getHeight());
            }

            this.scoreboardController.time(); // Met à jour l'affichage du tableau de scores
            this.level.updateGameObjects(); // Met à jour l'état du niveau et les balles
        } else if (playStatus == 2) {
            // État : pause
            Game.graphicsContext.fillText("Paused", textPositionX, Game.windowHeight / 2);
        } else if (playStatus == 3) {
            // État : victoire
            Game.graphicsContext.fillText("Bravo !!!", textPositionX, Game.windowHeight / 2);
        } else if (playStatus == 4) {
            // État : fin du jeu
            Game.graphicsContext.fillText("Game Over !!!", textPositionX, Game.windowHeight / 2);
        } else if (playStatus == 5) {
            // État : perte d'une vie
            Game.graphicsContext.fillText(
                    "You lost a life, " + this.scoreboardController.getLife()
                            + " life(s) remaining. press any key to continue",
                    textPositionX, Game.windowHeight / 2);
        }
    }

    /**
     * Efface l'écran en remplissant toute la fenêtre avec la couleur de fond.
     */
    public void clear() {
        Game.graphicsContext.setFill(
                new Color(Game.backgroundCode / 255.0, Game.backgroundCode / 255.0, Game.backgroundCode / 255.0, 1.0));
        Game.graphicsContext.fillRect(0, 0, Game.windowWidth, Game.windowHeight);
    }

    /**
     * Retourne le niveau actuel.
     * 
     * @return L'objet `Level` représentant le niveau actuel.
     */
    public Level getLevel() {
        return this.level;
    }
}