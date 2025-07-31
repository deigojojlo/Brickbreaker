package org.openjfx;

import org.openjfx.Editor.EditorController;
import org.openjfx.GUI.GUIGame;
import org.openjfx.GUI.HighscoreController;
import org.openjfx.GUI.LevelSelectorController;
import org.openjfx.GUI.ModeSelectorController;
import org.openjfx.GUI.MultiplayerSelectorController;
import org.openjfx.GUI.SettingsController;
import org.openjfx.Game.GameClass.Game;
import org.openjfx.HighscoreTXT.HighScore;
import org.openjfx.Sound.MasterSound;
import org.openjfx.Sound.MasterSoundController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Classe principale du jeu BrickBreaker. Cette classe hérite de {@link Application}
 * et est responsable de l'initialisation des scènes, du contrôle de l'interface utilisateur
 * ainsi que de la gestion des événements et de la navigation entre les différentes parties du jeu.
 */
public class BrickBreaker extends Application {

    /**
     * La scène actuelle de l'application, utilisée pour afficher l'interface graphique.
     */
    protected Scene currentScene;

    /**
     * La fenêtre principale de l'application.
     */
    protected Stage currentStage;

    /**
     * Le label de titre affiché sur l'écran d'accueil.
     */
    protected Label titleLabel;

    /**
     * Le panneau principal utilisé pour organiser les éléments graphiques.
     */
    protected AnchorPane mainPane;

    /**
     * URL de l'image d'arrière-plan utilisée pour la scène principale.
     */
    protected String imageUrl;

    /**
     * Le contrôleur pour la sélection des niveaux du jeu.
     */
    protected LevelSelectorController levelSelectorController;

    /**
     * Le contrôleur pour la sélection du mode de jeu (simple ou multijoueur).
     */
    protected ModeSelectorController modeSelectorController;

    /**
     * Le contrôleur pour la sélection du mode multijoueur.
     */
    protected MultiplayerSelectorController multiplayerSelectorController;

    /**
     * Le contrôleur pour afficher les meilleurs scores.
     */
    protected HighscoreController highscoreController;

    /**
     * Le contrôleur pour les paramètres du jeu.
     */
    protected SettingsController settingsController;

    /**
     * Le contrôleur pour la gestion du son du jeu.
     */
    protected MasterSoundController masterSoundController;

    /**
     * Le contrôleur pour l'éditeur de niveaux.
     */
    protected EditorController editorController;

    /**
     * L'instance du jeu affichée à l'écran, utilisée pour démarrer et gérer le jeu.
     */
    protected GUIGame game;

    /**
     * Indicateur qui détermine si le jeu se joue en mode coopératif.
     */
    protected boolean isCoop;

    /**
     * Méthode principale qui initialise l'application et les éléments graphiques.
     * 
     * @param stage La scène de l'application.
     * @throws Exception Si une erreur survient lors de l'initialisation.
     */
    @Override
    public void start(Stage stage) throws Exception {
        /* Initialisation des variables */
        currentStage = stage;
        mainPane = new AnchorPane();
        Game.screenSize = Screen.getPrimary().getVisualBounds();
        Game.windowWidth = (int) Game.screenSize.getWidth();
        Game.windowHeight = (int) Game.screenSize.getHeight();

        this.levelSelectorController = new LevelSelectorController(this);
        this.modeSelectorController = new ModeSelectorController(this);
        this.multiplayerSelectorController = new MultiplayerSelectorController(this);
        this.highscoreController = new HighscoreController(this);
        this.masterSoundController = new MasterSoundController(mainPane);
        this.editorController = new EditorController(this);
        this.settingsController = new SettingsController(this, mainPane);

        Button singlePlayerButton = new Button("Single Player");
        Button settingsButton = new Button("Settings");
        Button multiplayerButton = new Button("Multiplayer");
        Button highscoreButton = new Button("Highscores");
        Button editorButton = new Button("Editor");
        Button playEditButton = new Button("Play Edit");
        TextField usernameField = new TextField();

        // Définition de la taille des boutons
        singlePlayerButton.setPrefSize(Game.windowWidth * 0.1, Game.windowHeight * 0.1);
        settingsButton.setPrefSize(Game.windowWidth * 0.1, Game.windowHeight * 0.1);
        multiplayerButton.setPrefSize(Game.windowWidth * 0.1, Game.windowHeight * 0.1);
        editorButton.setPrefSize(Game.windowWidth * 0.1, Game.windowHeight * 0.1);
        playEditButton.setPrefSize(Game.windowWidth * 0.1, Game.windowHeight * 0.1);
        highscoreButton.setPrefSize(Game.windowWidth * 0.1, Game.windowHeight * 0.1);
        usernameField.setPrefSize(Game.windowWidth * 0.1, Game.windowHeight * 0.1);

        // Définition des styles des boutons
        singlePlayerButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : rgb(255,48,86) ; -fx-text-fill : black");
        settingsButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray ; -fx-text-fill : black");
        multiplayerButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : rgb(255,187,0) ; -fx-text-fill : black");
        editorButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : rgb(121,212,17) ; -fx-text-fill : black");
        playEditButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : rgb(69,69,207) ; -fx-text-fill : black");
        highscoreButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color :  darkgray; -fx-text-fill : black");
        usernameField.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray ; -fx-prompt-text-fill: rgb(100,100,100)");

        // Définition de la police
        Font font = new Font("Arial", 16);
        singlePlayerButton.setFont(font);
        settingsButton.setFont(font);
        multiplayerButton.setFont(font);
        editorButton.setFont(font);
        playEditButton.setFont(font);
        highscoreButton.setFont(font);
        usernameField.setFont(font);

        usernameField.setPromptText("Username");

        currentScene = new Scene(mainPane, Game.windowWidth, Game.windowHeight);
        titleLabel = new Label("Brick Breaker");

        /* Configuration du son */
        MasterSound.getMasterSoundButton().setOnMouseClicked(e -> masterSoundController.toggleMute());

        /* Ajout des boutons dans la scène */
        mainPane.getChildren().addAll(singlePlayerButton, settingsButton, multiplayerButton, highscoreButton,
                editorButton, playEditButton, usernameField);

        // Positionnement des boutons dans le AnchorPane
        // Boutons des côtés
        AnchorPane.setLeftAnchor(settingsButton, Game.windowWidth * 0.06);
        AnchorPane.setTopAnchor(settingsButton, Game.windowHeight * 0.055);
        AnchorPane.setRightAnchor(highscoreButton, Game.windowWidth * 0.05);
        AnchorPane.setBottomAnchor(highscoreButton, Game.windowHeight * 0.07);
        AnchorPane.setLeftAnchor(usernameField, Game.windowWidth * 0.06);
        AnchorPane.setBottomAnchor(usernameField, Game.windowHeight * 0.07);

        // Boutons du centre
        // colonne 1
        AnchorPane.setBottomAnchor(singlePlayerButton,
                Game.windowHeight * 0.4 - singlePlayerButton.getPrefHeight() + 10);
        AnchorPane.setLeftAnchor(singlePlayerButton,
                Game.windowWidth * 0.5 - singlePlayerButton.getPrefWidth() - 10);
        AnchorPane.setBottomAnchor(editorButton,
                Game.windowHeight * 0.4 - singlePlayerButton.getPrefHeight() * 2 - 10);
        AnchorPane.setLeftAnchor(editorButton,
                Game.windowWidth * 0.5 - singlePlayerButton.getPrefWidth() - 10);
        // colonne 2
        AnchorPane.setBottomAnchor(multiplayerButton,
                Game.windowHeight * 0.4 - singlePlayerButton.getPrefHeight() + 10);
        AnchorPane.setLeftAnchor(multiplayerButton,
                Game.windowWidth * 0.5 + 10);
        AnchorPane.setBottomAnchor(playEditButton,
                Game.windowHeight * 0.4 - singlePlayerButton.getPrefHeight() * 2 - 10);
        AnchorPane.setLeftAnchor(playEditButton,
                Game.windowWidth * 0.5 + 10);

        /* Événements */
        singlePlayerButton.setOnMouseClicked(e -> {
            this.currentStage.setScene(modeSelectorController.getScene());
            isCoop = false;
        });
        settingsButton.setOnMouseClicked(e -> setSettings());
        multiplayerButton.setOnMouseClicked(e -> {
            this.currentStage.setScene(multiplayerSelectorController.getScene());
            setCoop(true);
        });
        highscoreButton.setOnMouseClicked(e -> setHighscore());
        editorButton.setOnMouseClicked(e -> setEditor());
        playEditButton.setOnMouseClicked(e -> {
            setGame("edit");
            setCoop(false);
        });
        usernameField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                Game.playerName = usernameField.getText();
                usernameField.setPromptText(usernameField.getText());
                usernameField.setText("");
            }
        });

        /* Style du AnchorPane */
        imageUrl = getClass().getResource("/images/backGroundImage.JPG").toExternalForm();
        mainPane.setStyle("-fx-background-image: url('" + imageUrl + "');" +
                "-fx-background-size: " + Game.windowWidth + " " + Game.windowHeight + ";" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center;");
        mainPane.setMaxWidth(Game.windowWidth);
        mainPane.setMaxHeight(Game.windowHeight);
        mainPane.setMinWidth(Game.windowWidth);
        mainPane.setMinHeight(Game.windowHeight);
        mainPane.setPrefWidth(Game.windowWidth);
        mainPane.setPrefHeight(Game.windowHeight);

        /* Style du label */
        titleLabel.setFont(new Font(50));
        titleLabel.setStyle("-fx-padding : 0 ; -fx-text-fill : white");

        /* full screen */
        stage.setWidth((int) Screen.getPrimary().getBounds().getMaxX());
        stage.setHeight((int) Screen.getPrimary().getBounds().getMaxY());

        /* Style de la fenêtre */
        stage.setMinHeight(Game.windowHeight);
        stage.setMinWidth(Game.windowWidth);
        stage.setMaximized(true);
        stage.setScene(currentScene);
        stage.setTitle("BrickBreaker");
        stage.show();

        /* Initialisation des highscores */
        HighScore.read();

        /* Action lors de la fermeture de la fenêtre */
        stage.setOnCloseRequest(event -> HighScore.save());

    }

    // Méthodes pour la gestion de différentes scènes du jeu

    /**
     * Initialise le jeu dans le mode spécifié.
     * 
     * @param mode Le mode du jeu à initialiser (par exemple "edit").
     */
    public void setGame(String mode) {
        Platform.runLater(() -> {
            setMusicPlaying(true);
            this.game = new GUIGame(this, mode);
            currentStage.setScene(this.game.getScene());
        });
    }

    /**
     * Initialise l'éditeur et passe à la scène de l'éditeur.
     */
    public void setEditor() {
        Platform.runLater(() -> {
            setMusicPlaying(true);
            currentStage.setScene(this.editorController.getScene());
        });
    }

    /**
     * Retourne à l'écran d'accueil du jeu.
     * 
     * @param scene La scène à afficher.
     */
    public void setScene(Scene scene) {
        Platform.runLater(() -> {
            setMusicPlaying(false);
            this.currentStage.setScene(scene);
        });
    }

    /**
     * Retourne à l'écran d'accueil du jeu.
     */
    public void setHome() {
        Platform.runLater(() -> {
            setMusicPlaying(false);
            this.currentStage.setScene(this.currentScene);
        });
    }

    /**
     * Passe à la scène de sélection de niveau.
     */
    public void setLevelSelector() {
        Platform.runLater(() -> {
            setMusicPlaying(false);
            this.currentStage.setScene(levelSelectorController.getScene());
        });
    }

    /**
     * Initialise le mode coopératif et passe à la sélection de niveaux.
     */
    public void setCoop() {
        Platform.runLater(() -> {
            setCoop(true);
            setMusicPlaying(false);
            this.currentStage.setScene(this.levelSelectorController.getScene());
        });
    }

    /**
     * Retourne à l'écran de sélection du mode de jeu ou de sélection multijoueur.
     */
    public void setBackToSelector() {
        Platform.runLater(() -> {
            if (isCoop) {
                currentStage.setScene(this.multiplayerSelectorController.getScene());
            } else {
                currentStage.setScene(this.modeSelectorController.getScene());
            }
            setMusicPlaying(false);
        });
    }

    /**
     * Initialise les paramètres et passe à la scène des paramètres.
     */
    public void setSettings() {
        Platform.runLater(() -> {
            currentStage.setScene(this.settingsController.getScene());
        });
    }

    /**
     * Initialise le sélecteur multijoueur et passe à la scène multijoueur.
     */
    public void setMultiplayer() {
        Platform.runLater(() -> {
            currentStage.setScene(this.multiplayerSelectorController.getScene());
        });
    }

    /**
     * Lance le mode "Rush" et passe à la scène de jeu.
     */
    public void setRush() {
        Platform.runLater(() -> {
            this.game = new GUIGame(this);
            setMusicPlaying(true);
            currentStage.setScene(this.game.getScene());
        });
    }

    /**
     * Affiche les scores et passe à la scène des highscores.
     */
    public void setHighscore() {
        Platform.runLater(() -> {
            currentStage.setScene(this.highscoreController.getScene());
        });
    }

    /**
     * Passe à la scène de jeu.
     * 
     * @param i Le niveau du jeu.
     */
    public void setLevel(int i) {
        this.game = new GUIGame(this, i);
        setMusicPlaying(true);
        this.currentStage.setScene(game.getScene());
    }

    /**
     * Active ou désactive la musique en fonction de l'état du jeu.
     * 
     * @param game Indique si la musique doit être activée (true) ou désactivée
     *             (false).
     */
    public void setMusicPlaying(boolean game) {
        Platform.runLater(() -> {
            masterSoundController.setMusicPlaying(game);
        });
    }

    /**
     * Met à jour le volume du son.
     */
    public void updateVolume() {
        Platform.runLater(() -> {
            masterSoundController.updateVolume();
        });
    }

    /**
     * Met à jour l'état de la sourdine.
     */
    public void updateMute() {
        Platform.runLater(() -> {
            masterSoundController.updateMute();
        });
    }

    /**
     * Vérifie si le mode coopératif est activé.
     * 
     * @return true si le mode coopératif est activé, sinon false.
     */
    public boolean isCoop() {
        return isCoop;
    }

    /**
     * Active ou désactive le mode coopératif.
     * 
     * @param coop Indique si le mode coopératif doit être activé (true) ou
     *             désactivé (false).
     */
    public void setCoop(boolean coop) {
        isCoop = coop;
    }

    /**
     * Obtient l'instance de GUIGame.
     * 
     * @return L'instance de GUIGame.
     */
    public GUIGame getGuiGame() {
        return this.game;
    }

    /**
     * Méthode principale qui lance l'application.
     * 
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
