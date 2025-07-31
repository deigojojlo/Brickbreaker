package org.openjfx.GUI;

import org.openjfx.Game.GameClass.Game;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 * Vue permettant de sélectionner un mode de jeu dans le jeu BrickBreaker.
 * Propose différents modes de jeu tels que "Campagne" et "Rush".
 */
public class ModeSelectorView {

    private Scene scene;

    private BorderPane modeSelector;
    private AnchorPane top;
    private AnchorPane center;
    private Button homeButton;
    private Button rushButton;
    private Button campaignButton;

    /**
     * Constructeur de la vue de sélection du mode de jeu.
     * Initialise la vue en configurant les éléments graphiques et les styles.
     */
    public ModeSelectorView() {
        start();
    }

    /**
     * Méthode d'initialisation de la vue. Configure les éléments graphiques et leur
     * placement.
     */
    private void start() {
        /* Initialisation des variables */
        modeSelector = new BorderPane();
        scene = new Scene(modeSelector, Game.windowHeight, Game.windowWidth);
        top = new AnchorPane();
        center = new AnchorPane();
        homeButton = new Button("home");
        campaignButton = new Button("campagne");
        rushButton = new Button("rush");

        /* Style des boutons */
        campaignButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        rushButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        homeButton.setStyle(
                "-fx-background-color : " + Game.backgroundColor
                        + " ; -fx-border-radius : 50 ; -fx-background-radius : 50 ; -fx-text-fill: white;");
        homeButton.setPrefSize(100, 40);
        homeButton.setFont(Game.font);
        campaignButton.setFont(Game.font);
        rushButton.setFont(Game.font);
        campaignButton.setPrefSize(150, 75);
        rushButton.setPrefSize(150, 75);

        /* Déclaration et style du label */
        Label label = new Label("Sélectionnez un mode");
        label.setFont(new Font("Arial", 30));
        label.setStyle("-fx-padding : 0 ; -fx-text-fill : white");

        /* Ajout des éléments dans le top */
        top.getChildren().add(homeButton);
        top.getChildren().add(label);

        /* Positionnement des éléments dans le top */
        AnchorPane.setTopAnchor(label, 10.0);
        AnchorPane.setLeftAnchor(label, (Game.windowWidth - 300.0) / 2);

        AnchorPane.setTopAnchor(homeButton, 20.0);
        AnchorPane.setLeftAnchor(homeButton, 25.0);

        /* Style du top */
        top.setMinHeight(85);
        top.setMinWidth(85);
        top.setPadding(new Insets(10));
        top.setStyle("-fx-background-color : " + Game.topBarColor);

        /* Ajout des éléments dans le centre */
        center.getChildren().add(rushButton);
        center.getChildren().add(campaignButton);

        /* Positionnement des éléments dans le centre */
        AnchorPane.setTopAnchor(rushButton, (Game.gameHeight - 12.0) / 2);
        AnchorPane.setLeftAnchor(rushButton, (Game.windowWidth) / 2 - rushButton.getPrefWidth() - 20);
        AnchorPane.setTopAnchor(campaignButton, (Game.gameHeight - 12.0) / 2);
        AnchorPane.setLeftAnchor(campaignButton, (Game.windowWidth) / 2.0 + 20);

        /* Style du centre */
        center.setStyle("-fx-background-color :" + Game.backgroundColor);

        /* Configuration de la fenêtre */
        modeSelector.setTop(top);
        modeSelector.setCenter(center);
        modeSelector.setMinSize(Game.windowWidth, Game.windowWidth);
    }

    /**
     * Retourne la scène de la vue du sélecteur de mode de jeu.
     * 
     * @return La scène configurée pour la sélection du mode de jeu.
     */
    public Scene getScene() {
        center.setStyle(String.format("-fx-background-color : %s ; ", Game.backgroundColor));
        return scene;
    }

    /**
     * Retourne le bouton "Home" permettant de revenir à l'accueil.
     * 
     * @return Le bouton "Home".
     */
    protected Button getHomeButton() {
        return homeButton;
    }

    /**
     * Retourne le bouton "Rush" permettant de sélectionner le mode de jeu Rush.
     * 
     * @return Le bouton "Rush".
     */
    protected Button getRushButton() {
        return rushButton;
    }

    /**
     * Retourne le bouton "Campagne" permettant de sélectionner le mode de jeu
     * Campagne.
     * 
     * @return Le bouton "Campagne".
     */
    protected Button getCampaignButton() {
        return campaignButton;
    }
}
