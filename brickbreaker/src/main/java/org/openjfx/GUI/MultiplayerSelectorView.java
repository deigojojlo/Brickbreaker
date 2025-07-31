package org.openjfx.GUI;

import org.openjfx.Game.GameClass.Game;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 * Vue permettant de sélectionner le mode multijoueur dans le jeu.
 * Contient les boutons pour choisir le mode coopératif ou compétitif,
 * ainsi qu'un bouton pour revenir à l'écran d'accueil.
 */
public class MultiplayerSelectorView {
    private Scene scene;
    private BorderPane multi;
    private Button homeButton;
    private AnchorPane top;
    private AnchorPane center;

    private Button coopButton;

    /**
     * Constructeur de la vue de sélection du mode multijoueur.
     * Initialise la scène avec les différents éléments de la vue, y compris
     * le bouton "home", le bouton "coop" et le label de titre.
     */
    public MultiplayerSelectorView() {
        multi = new BorderPane();
        scene = new Scene(multi, Game.windowHeight, Game.windowWidth);
        top = new AnchorPane();
        center = new AnchorPane();
        homeButton = new Button("Home");
        coopButton = new Button("Coop");
        // Button btnCompetitive = new Button("Competitive");
        Label label = new Label("Sélectionner un mode de jeu multijoueur");

        // Configuration des tailles et positions des composants
        center.setMaxWidth(Game.windowWidth);
        center.setMinWidth(Game.windowWidth);
        top.setMinHeight(85);
        top.setMaxHeight(85);

        // Ajout des composants dans le BorderPane
        multi.setTop(top);
        multi.setCenter(center);
        top.getChildren().add(label);
        top.getChildren().add(homeButton);

        // Stylisation du label et de la barre supérieure
        label.setFont(new Font(30));
        label.setStyle("-fx-padding : 0 ; -fx-text-fill : white");
        top.setStyle(String.format("-fx-alignment : center; -fx-background-color : %s ; ", Game.topBarColor));

        // Stylisation du bouton Coop
        coopButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        coopButton.setFont(Game.font);
        coopButton.setPrefSize(150, 75);

        // Stylisation du bouton Home
        homeButton.setStyle(
                "-fx-background-color :  " + Game.backgroundColor
                        + " ; -fx-border-radius : 50 ; -fx-background-radius : 50 ; -fx-text-fill: white;");
        homeButton.setPrefSize(100, 40);
        homeButton.setFont(Game.font);

        // Positionnement des boutons et du label avec AnchorPane
        AnchorPane.setTopAnchor(homeButton, 20.0);
        AnchorPane.setLeftAnchor(homeButton, 25.0);

        AnchorPane.setTopAnchor(coopButton, Game.gameHeight / 2.0 - 75 / 2.0);
        AnchorPane.setLeftAnchor(coopButton, Game.windowWidth / 2.0 - 75);

        AnchorPane.setTopAnchor(label, 10.0);
        AnchorPane.setLeftAnchor(label, Game.windowWidth / 2.0 - 270);

        // Ajout du bouton Coop au centre
        center.getChildren().add(coopButton);
        // center.getChildren().add(btnCompetitive); // Commenté pour l'instant
    }

    /**
     * Retourne la scène de la vue de sélection du mode multijoueur.
     * 
     * @return La scène avec les composants de la vue.
     */
    public Scene getScene() {
        center.setStyle(String.format("-fx-background-color : %s", Game.backgroundColor));
        return scene;
    }

    /**
     * Retourne le bouton Coop pour le mode coopératif.
     * 
     * @return Le bouton Coop.
     */
    protected Button getCoopButton() {
        return this.coopButton;
    }

    /**
     * Retourne le bouton Home pour revenir à l'écran d'accueil.
     * 
     * @return Le bouton Home.
     */
    protected Button getHomeButton() {
        return this.homeButton;
    }
}
