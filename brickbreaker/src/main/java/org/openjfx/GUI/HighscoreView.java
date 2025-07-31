package org.openjfx.GUI;

import java.util.LinkedList;

import org.openjfx.Game.GameClass.Game;
import org.openjfx.HighscoreTXT.HighScore;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Pair;

/**
 * Vue pour afficher les meilleurs scores (Highscores) du jeu.
 * Gère l'affichage des scores, ainsi que les boutons de navigation.
 */
public class HighscoreView {
    private Scene scene;

    private BorderPane highscore;
    private AnchorPane top;
    private VBox center;
    private VBox left;
    private VBox right;
    private Button homeButton;
    private Button backButton;
    private Button soloButton;
    private Button multiButton;
    private Label titlelabel;
    private Button rushButton;
    private LinkedList<Button> levelButtons;

    /**
     * Constructeur de la vue des Highscores.
     * Initialise les composants de l'interface utilisateur et les styles.
     */
    public HighscoreView() {
        this.levelButtons = new LinkedList<>();

        highscore = new BorderPane();
        scene = new Scene(highscore, Game.windowHeight, Game.windowWidth);
        top = new AnchorPane();
        center = new VBox(10);
        left = new VBox(20);
        right = new VBox(20);

        // Initialiser le bouton et le label du titre
        homeButton = new Button("Home");
        titlelabel = new Label("Highscores");
        backButton = new Button("Back");
        rushButton = new Button("Rush");

        // Styliser le top et ajouter les boutons
        top.setMinHeight(Game.scoreboardViewHeigh);
        top.setMinWidth(Game.scoreboardViewHeigh);
        top.setPadding(new Insets(10));
        top.setStyle("-fx-background-color : " + Game.topBarColor);
        top.getChildren().addAll(homeButton, titlelabel);

        // Styliser le bouton home et le titre
        titlelabel.setFont(new Font(40));
        titlelabel.setStyle("-fx-padding: 0; -fx-text-fill: white;");
        top.setStyle("-fx-background-color: " + Game.topBarColor);
        homeButton.setStyle(
                "-fx-background-color :  " + Game.backgroundColor
                        + " ; -fx-border-radius : 50 ; -fx-background-radius : 50 ; -fx-text-fill: white;");
        homeButton.setFont(Game.font);

        AnchorPane.setTopAnchor(homeButton, 20.0);
        AnchorPane.setLeftAnchor(homeButton, 25.0);
        AnchorPane.setTopAnchor(titlelabel, 10.0);
        AnchorPane.setLeftAnchor(titlelabel, (Game.windowWidth - 190) / 2.0);

        // Styliser center left et right
        center.setStyle("-fx-background-color: " + Game.backgroundColor);
        center.setAlignment(Pos.TOP_CENTER);
        left.setStyle("-fx-background-color: " + Game.backgroundColor);
        left.setAlignment(Pos.TOP_CENTER);
        left.setPrefWidth(Game.windowWidth / 7);
        right.setPrefWidth(Game.windowWidth / 7);
        right.setStyle("-fx-background-color: " + Game.backgroundColor);
        right.setAlignment(Pos.CENTER_LEFT);
        center.setPrefWidth(Game.windowWidth * 5 / 7);
        GridPane.setHalignment(titlelabel, HPos.CENTER);
        GridPane.setValignment(titlelabel, VPos.CENTER);

        // Initialisation de highscore
        highscore.setTop(top);
        highscore.setCenter(center);
        highscore.setLeft(left);
        highscore.setRight(right);
        highscore.setMinSize(Game.windowWidth, Game.windowWidth);

        // filler
        left.getChildren().add(new Pane());
        center.getChildren().addAll(new Pane());

        // Ajout des boutons de choix de mode de jeu
        soloButton = new Button("Single Player");
        soloButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        multiButton = new Button("Multi Player");
        multiButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        center.getChildren().addAll(soloButton, multiButton);
    }

    /**
     * Affiche l'écran de sélection du mode de jeu (Solo ou Multi).
     */
    protected void displayModeSelectionScreen() {
        center.getChildren().clear();
        left.getChildren().clear();
        right.getChildren().clear();
        center.getChildren().addAll(new Pane(), soloButton, multiButton);
    }

    /**
     * Retourne la scène associée à l'affichage des Highscores.
     * 
     * @return La scène des Highscores.
     */
    protected Scene getScene() {
        center.setStyle(String.format("-fx-background-color : %s ; ", Game.backgroundColor));
        right.setStyle(String.format("-fx-background-color : %s ; ", Game.backgroundColor));
        left.setStyle(String.format("-fx-background-color : %s ; ", Game.backgroundColor));
        return scene;
    }

    /**
     * Retourne le bouton "Home" permettant de revenir à l'écran principal.
     * 
     * @return Le bouton Home.
     */
    protected Button getHomeButton() {
        return this.homeButton;
    }

    /**
     * Retourne le bouton "Single Player" pour démarrer une partie en solo.
     * 
     * @return Le bouton Solo.
     */
    protected Button getSoloButton() {
        return this.soloButton;
    }

    /**
     * Retourne le bouton "Multi Player" pour démarrer une partie multijoueur.
     * 
     * @return Le bouton Multi.
     */
    protected Button getMultiButton() {
        return this.multiButton;
    }

    /**
     * Retourne le bouton "Back" permettant de revenir à l'écran précédent.
     * 
     * @return Le bouton Back.
     */
    protected Button getBackButton() {
        return this.backButton;
    }

    /**
     * Retourne le bouton "Rush" pour démarrer un mode de jeu rapide.
     * 
     * @return Le bouton Rush.
     */
    protected Button getRushButton() {
        return this.rushButton;
    }

    /**
     * Retourne la liste des boutons des niveaux.
     * 
     * @return La liste des boutons de niveau.
     */
    protected LinkedList<Button> getLevelButtons() {
        return this.levelButtons;
    }

    /**
     * Affiche les boutons des niveaux ainsi que les scores enregistrés pour chaque
     * niveau.
     * 
     * @param gamemode Le mode de jeu sélectionné (false = solo et true = multi).
     */
    protected void display(boolean gamemode) {
        center.getChildren().clear();
        left.getChildren().clear();
        // Ajout du bouton de retour au choix de mode de jeu (multi ou solo)
        backButton.setStyle(
                "-fx-background-color :  black  ; -fx-border-radius : 15 ; -fx-background-radius : 15 ;  -fx-text-fill : white");
        right.getChildren().add(backButton);
        // Ajout des boutons de choix de niveau pour l'affichage des scores
        if (!gamemode) { // Pas de mode Rush possible en Coop
            rushButton.setPrefWidth(100);
            rushButton.setStyle(
                    "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
            left.getChildren().add(rushButton);
        }

        levelButtons.clear();
        for (int i = 0; i < Game.totalLevels; i++) {
            Button levelButton = new Button("Niveau " + (i + 1));
            levelButtons.add(levelButton);
            levelButton.setPrefWidth(100);
            levelButton.setStyle(
                    "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
            left.getChildren().add(levelButton);
        }
    }

    /**
     * Affiche les scores pour un niveau donné.
     * 
     * @param key La paire contenant l'ID du niveau et l'indicateur du mode de jeu.
     */
    protected void displayScore(Pair<Integer, Boolean> key) {
        center.getChildren().clear();
        // filler
        center.getChildren().add(new Pane());
        if (HighScore.getHighScores().containsKey(key)) {
            LinkedList<Pair<Integer, String>> scores = HighScore.getHighScores().get(key);
            for (Pair<Integer, String> elt : scores) {
                Label scoreLabel = new Label(elt.getKey() + " " + elt.getValue());
                scoreLabel.setStyle(String.format("-fx-padding: 0; -fx-text-fill: %s;",
                        Game.backgroundCode == 28 ? "white" : "black"));
                scoreLabel.setFont(new Font(15));
                center.getChildren().add(scoreLabel);
            }
        } else {
            Label noSavedScore = new Label("Pas de scores enregistrés");
            Label playMyGamePleeeeeeease = new Label("À toi de jouer");
            playMyGamePleeeeeeease.setStyle("-fx-padding: 0; -fx-text-fill: white;");
            playMyGamePleeeeeeease.setFont(new Font(15));
            noSavedScore.setStyle("-fx-padding: 0; -fx-text-fill: white;");
            noSavedScore.setFont(new Font(15));
            center.getChildren().addAll(noSavedScore, playMyGamePleeeeeeease);
        }
    }
}
