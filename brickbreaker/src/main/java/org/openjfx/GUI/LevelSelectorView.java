package org.openjfx.GUI;

import org.openjfx.Game.GameClass.Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Vue de sélection de niveau du jeu.
 * Permet à l'utilisateur de sélectionner un niveau à jouer.
 */
public class LevelSelectorView {

    private Scene scene;
    private Region spacer;
    private BorderPane levelSelector;
    private AnchorPane top;
    private GridPane grid;
    private VBox center;
    private Button backButton;
    private Button[] levelButtons;

    /**
     * Constructeur de la vue de sélection du niveau.
     * Initialise les éléments de l'interface utilisateur pour la sélection du
     * niveau.
     */
    public LevelSelectorView() {
        /* Initialisation des variables */
        this.levelSelector = new BorderPane();
        this.scene = new Scene(this.levelSelector, Game.windowHeight, Game.windowWidth);
        this.top = new AnchorPane();
        this.grid = new GridPane();
        this.spacer = new Region();
        this.center = new VBox(this.spacer, this.grid);
        this.backButton = new Button("BACK");

        /* Style du spacer */
        spacer.setPrefHeight(75);

        /* Style du bouton retour */
        backButton.setStyle(
                "-fx-background-color :  black  ; -fx-border-radius : 50 ; -fx-background-radius : 50 ; -fx-text-fill: white;");
        backButton.setPrefSize(100, 40);
        backButton.setFont(Game.font);

        /* Déclaration et style du label */
        Label label = new Label("Sélectionnez un niveau");
        label.setFont(new Font(30));
        label.setStyle("-fx-padding : 0 ; -fx-text-fill : white");

        /* Ajout des enfants au top */
        top.getChildren().add(backButton);
        top.getChildren().add(label);

        /* Positionnement des enfants dans le top */
        AnchorPane.setTopAnchor(label, 10.0);
        AnchorPane.setLeftAnchor(label, (Game.windowWidth - 320.0) / 2);

        AnchorPane.setTopAnchor(backButton, 20.0);
        AnchorPane.setLeftAnchor(backButton, 25.0);

        /* Style du top */
        top.setMinHeight(85);
        top.setMinWidth(85);
        top.setPadding(new Insets(10));
        top.setStyle("-fx-background-color : " + Game.topBarColor);

        /* Style du center */
        center.setStyle(String.format("-fx-background-color : %s ; ", Game.backgroundColor));

        /* Style de la fenêtre */
        levelSelector.setTop(top);
        levelSelector.setCenter(center);
        levelSelector.setMinSize(Game.windowWidth, Game.windowWidth);

        /* Création de la grille de boutons */
        levelButtons = new Button[Game.totalLevels];
        for (int i = 0; i < levelButtons.length; i++) {
            ImageView img = new ImageView(
                    new Image(LevelSelectorView.class.getResourceAsStream("/images/Level" + (i + 1) + ".png")));
            levelButtons[i] = new Button();
            levelButtons[i].setStyle(
                    "-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black;");
            levelButtons[i].setGraphic(img);
            grid.add(levelButtons[i], i % 4, i / 4);
        }

        /* Style de la grille */
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap((Game.windowWidth - 200) / 4 - 100);
        grid.setVgap((Game.windowHeight - Game.scoreboardViewHeigh * 2) / 4 - 100);
    }

    /**
     * Retourne la scène de sélection du niveau.
     * 
     * @return La scène de sélection du niveau.
     */
    public Scene getScene() {
        center.setStyle(String.format("-fx-background-color : %s ; ", Game.backgroundColor));
        return scene;
    }

    /**
     * Retourne les boutons des niveaux.
     * 
     * @return Un tableau de boutons pour chaque niveau.
     */
    protected Button[] getLevelButtons() {
        return levelButtons;
    }

    /**
     * Retourne le bouton de retour à l'écran principal.
     * 
     * @return Le bouton de retour.
     */
    protected Button getBackButton() {
        return this.backButton;
    }
}
