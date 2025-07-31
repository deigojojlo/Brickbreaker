package org.openjfx.Game.GameClass.Scoreboard;

import org.openjfx.Game.GameClass.Game;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * La classe ScoreboardView gère l'affichage du tableau de bord dans le jeu.
 * Elle affiche des informations telles que le niveau, le score, le temps,
 * les vies restantes, le score le plus élevé et le nombre de briques restantes.
 * Elle inclut également un bouton pour revenir à l'écran d'accueil du jeu.
 */
public class ScoreboardView {
    /**
     * Le tableau de bord est la zone située en haut du jeu (GUIGame).
     */
    protected BorderPane window;

    /**
     * La grille qui contient tous les éléments du tableau de bord
     */
    protected GridPane pane;

    /**
     * Hauteur du tableau de bord. Cette valeur définit la hauteur
     * du tableau de bord dans l'interface graphique du jeu.
     */
    protected int scoreboardHeight = 85;

    /**
     * Label pour afficher le texte "Level"
     */
    protected Label levelLabelText;

    /**
     * Label pour afficher la valeur du niveau actuel du joueur.
     * Ce label affiche un chiffre ou un texte qui représente le niveau
     * dans lequel le joueur se trouve.
     */
    protected Label levelLabelValue;

    /**
     * Label pour afficher le texte "Score".
     */
    protected Label scoreLabelText;

    /**
     * Label pour afficher la valeur du score actuel du joueur.
     * Ce label affiche un chiffre représentant le score accumulé
     * par le joueur pendant la partie.
     */
    protected Label scoreLabelValue;

    /**
     * Label pour afficher le texte "Time"
     */
    protected Label timeLabelText;

    /**
     * Label pour afficher la valeur du temps écoulé pendant la partie.
     * Ce label affiche le temps sous forme de minutes et secondes,
     * indiquant combien de temps le joueur a joué.
     */
    protected Label timeLabelValue;

    /**
     * Label pour afficher le texte "Life"
     */
    protected Label lifeLabelText;

    /**
     * Label pour afficher la valeur du nombre de vies restantes du joueur.
     * Ce label affiche un chiffre représentant le nombre de vies
     * que le joueur a encore avant de perdre la partie.
     */
    protected Label lifeLabelValue;

    /**
     * Label pour afficher le texte "High Score".
     */
    protected Label highscoreLabelText;

    /**
     * Label pour afficher la valeur du score le plus élevé enregistré.
     * Ce label affiche le score maximum jamais atteint dans le jeu.
     */
    protected Label highscoreLabelValue;

    /**
     * Label pour afficher le texte "Brique restante"
     */
    protected Label remainingBricksText;

    /**
     * Label pour afficher la valeur du nombre de briques restantes à détruire.
     * Ce label affiche un chiffre représentant combien de briques il reste
     * avant que le joueur ait terminé le niveau ou la partie.
     */
    protected Label remainingBricksValue;

    /**
     * Bouton permettant de revenir à l'écran d'accueil du jeu.
     * Ce bouton est utilisé pour quitter la partie en cours et revenir
     * au menu principal.
     */
    protected Button homeButton;

    /**
     * Constructeur de ScoreboardView
     * 
     * @param w La fenêtre principale (BorderPane) qui contient le tableau de bord.
     */
    public ScoreboardView(BorderPane w) {
        window = w;
        pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setMinSize(1000, scoreboardHeight);
        pane.setMaxHeight(scoreboardHeight);
        window.setTop(pane);

        // Labels pour les différentes informations du jeu
        // home
        homeButton = new Button("home");
        // level
        levelLabelText = new Label("Level");
        levelLabelValue = new Label("1");
        levelLabelText.setStyle("-fx-text-fill : rgb(255,255,255)");
        levelLabelValue.setStyle("-fx-text-fill : rgb(255,255,255)");
        // score
        scoreLabelText = new Label("Score");
        scoreLabelValue = new Label("0");
        scoreLabelText.setStyle("-fx-text-fill : rgb(255,255,255)");
        scoreLabelValue.setStyle("-fx-text-fill : rgb(255,255,255)");
        // life
        lifeLabelText = new Label("Life");
        lifeLabelValue = new Label("3");
        lifeLabelText.setStyle("-fx-text-fill : rgb(255,255,255)");
        lifeLabelValue.setStyle("-fx-text-fill : rgb(255,255,255)");
        // highscore
        highscoreLabelText = new Label("high score");
        highscoreLabelValue = new Label();
        highscoreLabelText.setStyle("-fx-text-fill : rgb(255,255,255)");
        highscoreLabelValue.setStyle("-fx-text-fill : rgb(255,255,255)");
        // time
        timeLabelText = new Label("Time");
        timeLabelValue = new Label("0");
        timeLabelText.setStyle("-fx-text-fill : rgb(255,255,255)");
        timeLabelValue.setStyle("-fx-text-fill : rgb(255,255,255)");
        // nbBriques
        remainingBricksText = new Label("brique restante");
        remainingBricksValue = new Label("0");
        remainingBricksText.setStyle("-fx-text-fill : rgb(255,255,255)");
        remainingBricksValue.setStyle("-fx-text-fill : rgb(255,255,255)");

        for (int i = 0; i <= 7; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(14.29); // chaque colonne prend 14.29% de la largeur de la fenetre
            pane.getColumnConstraints().add(columnConstraints);
        }
    }

    /**
     * Retourne la hauteur du tableau de bord.
     * 
     * @return La hauteur du tableau de bord.
     */
    public int getScoreboardHeight() {
        return scoreboardHeight;
    }

    /**
     * Met à jour le score affiché sur le tableau de bord.
     * 
     * @param score Le score à afficher.
     */
    protected void setScore(int score) {
        scoreLabelValue.setText("" + score);
    }

    /**
     * Met à jour le nombre de vies affiché sur le tableau de bord.
     * 
     * @param life Le nombre de vies à afficher.
     */
    protected void setLife(int life) {
        lifeLabelValue.setText("" + life);
    }

    /**
     * Met à jour le niveau affiché sur le tableau de bord.
     * 
     * @param level Le niveau à afficher.
     */
    protected void setLevel(int level) {
        if (level == -1)
            levelLabelValue.setText("player's level");
        else
            levelLabelValue.setText("" + level);
    }

    /**
     * Met à jour le nombre de briques restantes affiché sur le tableau de bord.
     * 
     * @param remainingBrickCount Le nombre de briques restantes à afficher.
     */
    public void updateRemainingBricks(int remainingBrickCount) {
        remainingBricksValue.setText("" + remainingBrickCount);
    }

    /**
     * Met à jour l'affichage du temps sur le tableau de bord.
     * 
     * @param minutes Les minutes à afficher.
     * @param seconds Les secondes à afficher.
     */
    protected void setTime(int minutes, int seconds) {
        if (seconds >= 10) {
            timeLabelValue.setText("" + minutes + ":" + seconds);
        } else {
            timeLabelValue.setText("" + minutes + ":" + "0" + seconds);
        }
    }

    /**
     * Retourne le bouton "home" permettant de revenir à l'écran d'accueil.
     * 
     * @return Le bouton "home".
     */
    protected Button getHomeButton() {
        return this.homeButton;
    }

    /**
     * Affiche tous les éléments du tableau de bord dans la fenêtre.
     */
    public void display() {
        pane.setStyle("-fx-background-color: " + Game.topBarColor + "; -fx-hgap : 100; -fx-alignment : center;");
        pane.setPrefSize(1000, 100);

        /* Style du bouton */
        homeButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : black  ; -fx-text-fill : white");
        homeButton.setFont(Game.font);
        homeButton.setPrefSize(100, 40);

        // Ajout des éléments à la grille
        pane.add(homeButton, 0, 0, 1, 2);
        pane.add(levelLabelText, 1, 0);
        pane.add(levelLabelValue, 1, 1);
        GridPane.setHalignment(levelLabelValue, HPos.CENTER);
        GridPane.setHalignment(levelLabelText, HPos.CENTER);

        pane.add(scoreLabelText, 2, 0);
        pane.add(scoreLabelValue, 2, 1);
        GridPane.setHalignment(scoreLabelValue, HPos.CENTER);
        GridPane.setHalignment(scoreLabelText, HPos.CENTER);

        pane.add(lifeLabelText, 3, 0);
        pane.add(lifeLabelValue, 3, 1);
        GridPane.setHalignment(lifeLabelValue, HPos.CENTER);
        GridPane.setHalignment(lifeLabelText, HPos.CENTER);

        pane.add(timeLabelText, 4, 0);
        pane.add(timeLabelValue, 4, 1);
        GridPane.setHalignment(timeLabelValue, HPos.CENTER);
        GridPane.setHalignment(timeLabelText, HPos.CENTER);

        pane.add(highscoreLabelText, 5, 0);
        GridPane.setHalignment(highscoreLabelValue, HPos.CENTER);
        GridPane.setHalignment(highscoreLabelText, HPos.CENTER);

        pane.add(remainingBricksText, 6, 0);
        pane.add(remainingBricksValue, 6, 1);
        GridPane.setHalignment(remainingBricksValue, HPos.CENTER);
        GridPane.setHalignment(remainingBricksText, HPos.CENTER);

        pane.add(highscoreLabelValue, 5, 1);
    }

    /**
     * Retourne le label affichant le score élevé.
     * 
     * @return Le label du score élevé.
     */
    protected Label getHighScoreLabelValue() {
        return this.highscoreLabelValue;
    }
}
