package org.openjfx.GUI;

import org.openjfx.BrickBreaker;

import javafx.scene.Scene;

/**
 * Contrôleur pour la vue de sélection du mode de jeu.
 * Gère les événements des boutons pour naviguer entre les différents modes de jeu.
 */
public class ModeSelectorController {
    private ModeSelectorView view;
    private BrickBreaker frame;

    /**
     * Constructeur du contrôleur de la sélection du mode de jeu.
     * 
     * @param frame L'instance de la classe BrickBreaker, utilisée pour naviguer dans l'application.
     */
    public ModeSelectorController(BrickBreaker frame) {
        this.frame = frame;
        this.view = new ModeSelectorView();
        this.setButtonHandler();
    }

    /**
     * Méthode pour configurer les événements des boutons dans la vue de sélection du mode de jeu.
     * Associe chaque bouton à une action spécifique.
     */
    private void setButtonHandler() {
        /* Définir les événements des boutons */
        this.view.getHomeButton().setOnMouseClicked(e -> this.frame.setHome());
        this.view.getCampaignButton().setOnMouseClicked(e -> this.frame.setLevelSelector());
        this.view.getRushButton().setOnMouseClicked(e -> this.frame.setRush());
    }

    /**
     * Retourne la scène associée à la vue de sélection du mode de jeu.
     * 
     * @return La scène de la vue de sélection du mode de jeu.
     */
    public Scene getScene() {
        return this.view.getScene();
    }
}
