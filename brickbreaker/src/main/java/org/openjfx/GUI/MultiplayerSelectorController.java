package org.openjfx.GUI;

import org.openjfx.BrickBreaker;

import javafx.scene.Scene;

/**
 * Contrôleur pour la vue de sélection du mode multijoueur.
 * Gère les actions des boutons pour passer d'un mode de jeu à un autre (coop ou accueil).
 */
public class MultiplayerSelectorController {
    private MultiplayerSelectorView view;
    private BrickBreaker frame;

    /**
     * Constructeur du contrôleur de la vue de sélection du mode multijoueur.
     * Initialise la vue et configure les actions des boutons.
     * 
     * @param frame L'instance principale de l'application BrickBreaker.
     */
    public MultiplayerSelectorController(BrickBreaker frame) {
        this.frame = frame;
        this.view = new MultiplayerSelectorView();

        // Initialisation des gestionnaires d'événements pour les boutons
        this.setButtonHandler();
    }

    /**
     * Configure les gestionnaires d'événements pour les boutons de la vue.
     * Associe les actions correspondantes aux boutons Coop et Home.
     */
    private void setButtonHandler() {
        // Lorsque le bouton Coop est cliqué, on passe en mode Coop
        this.view.getCoopButton().setOnAction(e -> this.frame.setCoop());

        // Lorsque le bouton Home est cliqué, on retourne à l'écran d'accueil
        this.view.getHomeButton().setOnAction(e -> this.frame.setHome());
    }

    /**
     * Retourne la scène de la vue de sélection du mode multijoueur.
     * 
     * @return La scène avec les composants de la vue.
     */
    public Scene getScene() {
        return this.view.getScene();
    }
}
