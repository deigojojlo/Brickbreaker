package org.openjfx.GUI;

import java.util.LinkedList;

import org.openjfx.Game.GameClass.Game;
import org.openjfx.Sound.MasterSoundController;
import org.openjfx.Sound.MusicController;
import org.openjfx.Sound.SoundController;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Classe représentant la vue des paramètres du jeu, permettant de configurer
 * différentes options telles que le son, les raquettes, et le mode
 * sombre/clair.
 */
public class SettingsView {
    /**
     * Scène associée à la vue des paramètres.
     */
    private Scene scene;

    /**
     * Conteneur principal du BorderPane pour l'agencement de la vue.
     */
    private BorderPane settings;

    /**
     * Conteneur vertical pour les éléments au centre de la fenêtre.
     */
    private VBox center;

    /**
     * ScrollPane permettant de faire défiler les options dans la vue des
     * paramètres.
     */
    private ScrollPane scrollPane;

    /**
     * Conteneur pour les éléments en haut de la vue des paramètres.
     */
    private AnchorPane top;

    /**
     * Bouton pour retourner à l'accueil depuis les paramètres.
     */
    private Button homeButton;

    /**
     * Label affichant le titre "Paramètre".
     */
    private Label label;

    /**
     * Labels pour afficher les configurations des raquettes du joueur 1 (droite et
     * gauche).
     */
    protected Label labelRightP1, labelLeftP1;

    /**
     * Labels pour afficher les configurations des raquettes du joueur 2 (droite et
     * gauche).
     */
    protected Label labelRightP2, labelLeftP2;

    /**
     * Boutons pour changer les configurations de la raquette droite et gauche du
     * joueur 1.
     */
    protected Button racketRightButtonP1, racketLeftButtonP1;

    /**
     * Boutons pour changer les configurations de la raquette droite et gauche du
     * joueur 2.
     */
    protected Button racketRightButtonP2, racketLeftButtonP2;

    /**
     * Couleur de texte actuelle pour les labels (initialement "blanc").
     */
    private String textColor = "white";

    /**
     * Sliders pour ajuster les volumes de son : Master, Music et Sound.
     */
    protected Slider masterSoundSlider, musicSlider, soundSlider;

    /**
     * Boutons pour activer/désactiver la musique et les effets sonores.
     */
    protected Button musicButton, soundButton;

    /**
     * Constructeur de la vue des paramètres.
     * 
     * @param anchorPane Le Panel d'affichage des settings.
     */
    public SettingsView(AnchorPane anchorPane) {
        settings = new BorderPane();
        scene = new Scene(settings, Game.windowWidth, Game.windowHeight);
        center = new VBox(50);
        scrollPane = new ScrollPane(center);
        top = new AnchorPane();

        // Initialisation du bouton "home" et du label
        homeButton = new Button("Home");
        homeButton.setFont(Game.font);
        homeButton.setPrefSize(100, 40);
        label = new Label("Paramètre");

        // Ajouter le bouton et le label dans le top
        top.getChildren().addAll(homeButton, label);
        AnchorPane.setTopAnchor(homeButton, 20.0);
        AnchorPane.setLeftAnchor(homeButton, 25.0);
        AnchorPane.setTopAnchor(label, 10.0);
        AnchorPane.setLeftAnchor(label, (Game.windowWidth - 190) / 2.0); // Centrage du label

        // Ajouter le top et le scrollPane dans le BorderPane
        settings.setTop(top);
        settings.setCenter(scrollPane);

        // Configurer les tailles du scrollPane
        scrollPane.setMaxWidth(Game.windowWidth);
        scrollPane.setMinWidth(Game.windowWidth);
        scrollPane.setMaxHeight(Game.windowHeight);
        scrollPane.setMinHeight(Game.windowHeight);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        top.setMinWidth(Game.windowWidth);
        top.setMaxHeight(85);
        top.setMinHeight(85);

        // Styliser le label et le top
        label.setFont(new Font(30));
        label.setStyle("-fx-padding: 0; -fx-text-fill: white;");
        top.setStyle("-fx-background-color: " + Game.topBarColor);
        center.setStyle("-fx-background-color: " + Game.backgroundColor);
        homeButton.setStyle(
                "-fx-background-color :  " + Game.backgroundColor
                        + " ; -fx-border-radius : 50 ; -fx-background-radius : 50 ; -fx-text-fill: white;");
        settings.setStyle("-fx-background-color : " + Game.backgroundColor);

        // Configuration du son (si nécessaire)
        String[] soundLabels = { "Master Sound", "Music", "Sound", "Sound Mute", "Music Mute" };
        LinkedList<Label> soundLabelsList = new LinkedList<>();
        masterSoundSlider = MasterSoundController.mastereSliderInit();
        musicSlider = MusicController.musicSliderInit();
        soundSlider = SoundController.soundSliderInit();
        Slider[] sounSliders = { masterSoundSlider, musicSlider, soundSlider };
        VBox[] soundSliderContainers = { new VBox(20), new VBox(20), new VBox(20) };

        for (int i = 0; i < soundSliderContainers.length; i++) {
            Label soundLabel = new Label(soundLabels[i]);
            soundLabelsList.add(soundLabel);
            soundLabel.setStyle("-fx-padding: 0; -fx-text-fill: white;");
            soundSliderContainers[i].setAlignment(Pos.TOP_CENTER);
            soundSliderContainers[i].getChildren().addAll(soundLabel, sounSliders[i]);
            center.getChildren().add(soundSliderContainers[i]);
        }

        musicButton = MusicController.musicButtonInit();
        soundButton = SoundController.soundButtonInit();
        Button[] musicButtons = { soundButton, musicButton };
        VBox[] soundButtonContainers = { new VBox(20), new VBox(20) };
        for (int i = 0; i < soundButtonContainers.length; i++) {
            Label soundLabel = new Label(soundLabels[i + soundSliderContainers.length]);
            soundLabel.setStyle("-fx-padding: 0; -fx-text-fill: " + textColor);
            soundButtonContainers[i].setAlignment(Pos.TOP_CENTER);
            soundButtonContainers[i].getChildren().addAll(soundLabel, musicButtons[i]);
            center.getChildren().add(soundButtonContainers[i]);
        }

        // Configuration des raquettes (si nécessaire)
        labelRightP1 = new Label(Game.p1Right.toString());
        labelLeftP1 = new Label(Game.p1Left.toString());
        racketRightButtonP1 = racketRightButtonInitP1(labelRightP1);
        racketLeftButtonP1 = racketLeftButtonInitP1(labelLeftP1);
        Button[] racketButtonsP1 = { racketRightButtonP1, racketLeftButtonP1 };

        Label[] racketLabelsP1 = new Label[] { labelRightP1, labelLeftP1 };
        VBox[] racketContainersP1 = { new VBox(20), new VBox(20) };

        for (int i = 0; i < racketContainersP1.length; i++) {
            racketContainersP1[i].setAlignment(Pos.TOP_CENTER);
            racketLabelsP1[i].setStyle("-fx-padding: 0; -fx-text-fill: " + textColor);
            racketContainersP1[i].getChildren().addAll(racketButtonsP1[i], racketLabelsP1[i]);
            center.getChildren().add(racketContainersP1[i]);
        }

        // Configuration des raquettes pour le joueur 2
        labelRightP2 = new Label(Game.p2Right.toString());
        labelLeftP2 = new Label(Game.p2Left.toString());
        racketRightButtonP2 = racketRightButtonInitP2(labelRightP2);
        racketLeftButtonP2 = racketLeftButtonInitP2(labelLeftP2);
        Button[] racketButtonsP2 = { racketRightButtonP2, racketLeftButtonP2 };

        Label[] racketLabelsP2 = new Label[] { labelRightP2, labelLeftP2 };
        VBox[] racketContainersP2 = { new VBox(20), new VBox(20) };

        for (int i = 0; i < racketContainersP2.length; i++) {
            racketContainersP2[i].setAlignment(Pos.TOP_CENTER);
            racketLabelsP2[i].setStyle("-fx-padding: 0; -fx-text-fill: " + textColor);
            racketContainersP2[i].getChildren().addAll(racketButtonsP2[i], racketLabelsP2[i]);
            center.getChildren().add(racketContainersP2[i]);
        }

        // Ajouter un bouton pour le mode sombre
        ToggleButton darkMode = new ToggleButton(Game.backgroundCode == 28 ? "Mode Lumineux" : "Mode Sombre");
        darkMode.setStyle("-fx-padding: 10;");
        darkMode.setOnAction(e -> {
            textColor = Game.backgroundCode != 28 ? "white" : "black";
            darkMode.setText(Game.backgroundCode != 28 ? "Mode Lumineux" : "Mode Sombre");
            Game.backgroundColor = Game.backgroundCode != 28 ? "rgb(28, 28, 30)" : "rgb(255,255,255)";
            Game.backgroundCode = Game.backgroundCode != 28 ? 28 : 255;
            center.setStyle(String.format("-fx-background-color : %s ;", Game.backgroundColor));

            // redefine text color
            for (int i = 0; i < racketContainersP1.length; i++) {
                racketLabelsP1[i].setStyle("-fx-padding: 0; -fx-text-fill: " + textColor);
                racketLabelsP2[i].setStyle("-fx-padding: 0; -fx-text-fill: " + textColor);
            }
            labelRightP1
                    .setStyle("-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-padding: 0; -fx-text-fill: "
                            + textColor);
            labelLeftP1.setStyle("-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-padding: 0; -fx-text-fill: "
                    + textColor);
            labelRightP2
                    .setStyle("-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-padding: 0; -fx-text-fill: "
                            + textColor);
            labelLeftP2.setStyle("-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-padding: 0; -fx-text-fill: "
                    + textColor);
            for (Label lb : soundLabelsList) {
                lb.setStyle("-fx-padding: 0; -fx-text-fill: " + textColor);
            }
        });

        darkMode.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        darkMode.setFont(Game.font);
        darkMode.setPrefSize(150, 75);

        center.setAlignment(Pos.TOP_CENTER);
        center.getChildren().add(darkMode);
        Pane filler = new Pane();
        filler.setMinHeight(250);
        center.getChildren().add(filler);
    }

    /**
     * Retourne la scène de la vue des paramètres.
     * 
     * @return La scène de la vue des paramètres.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Retourne l'étiquette de la raquette droite du joueur 1.
     * 
     * @return L'étiquette de la raquette droite du joueur 1.
     */
    public Label getLabelRightP1() {
        return labelRightP1;
    }

    /**
     * Retourne l'étiquette de la raquette gauche du joueur 1.
     * 
     * @return L'étiquette de la raquette gauche du joueur 1.
     */
    public Label getLabelLeftP1() {
        return labelLeftP1;
    }

    /**
     * Retourne l'étiquette de la raquette droite du joueur 2.
     * 
     * @return L'étiquette de la raquette droite du joueur 2.
     */
    public Label getLabelRightP2() {
        return labelRightP2;
    }

    /**
     * Retourne l'étiquette de la raquette gauche du joueur 2.
     * 
     * @return L'étiquette de la raquette gauche du joueur 2.
     */
    public Label getLabelLeftP2() {
        return labelLeftP2;
    }

    /**
     * Retourne le bouton "Home" de la vue des paramètres.
     * 
     * @return Le bouton "Home".
     */
    protected Button getHomeButton() {
        return this.homeButton;
    }

    /**
     * Initialise le bouton de déplacement vers la droite pour le joueur 1.
     *
     * @param labelRightP1 Le label associé à la raquette droite de joueur 1.
     * 
     * @return le bouton initialisé.
     */
    public static Button racketRightButtonInitP1(Label labelRightP1) {
        Button btn = new Button("right");

        btn.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        btn.setFont(Game.font);
        btn.setPrefSize(150, 75);

        return btn;
    }

    /**
     * Initialise le bouton de déplacement vers la gauche pour le joueur 1.
     *
     * @param labelLeftP1 Le label associé à la raquette gauche de joueur 1.
     * 
     * @return le bouton initialisé.
     */
    public static Button racketLeftButtonInitP1(Label labelLeftP1) {
        Button btn = new Button("left");

        btn.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        btn.setFont(Game.font);
        btn.setPrefSize(150, 75);

        return btn;
    }

    /**
     * Initialise le bouton de déplacement vers la droite pour le joueur 2.
     *
     * @param labelRightP2 Le label associé à la raquette droite de joueur 2.
     * 
     * @return le bouton initialisé.
     */
    public static Button racketRightButtonInitP2(Label labelRightP2) {
        Button btn = new Button("right");

        btn.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        btn.setFont(Game.font);
        btn.setPrefSize(150, 75);

        return btn;
    }

    /**
     * Initialise le bouton de déplacement vers la gauche pour le joueur 2.
     *
     * @param labelLeftP2 Le label associé à la raquette gauche de joueur 2.
     * 
     * @return le bouton initialisé.
     */
    public static Button racketLeftButtonInitP2(Label labelLeftP2) {
        Button btn = new Button("left");

        btn.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        btn.setFont(Game.font);
        btn.setPrefSize(150, 75);

        return btn;
    }
}
