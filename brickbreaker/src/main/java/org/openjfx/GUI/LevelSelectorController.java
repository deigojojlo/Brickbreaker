package org.openjfx.GUI;

import org.openjfx.BrickBreaker;

import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Contrôleur pour la vue de sélection du niveau.
 * Gère les interactions de l'utilisateur avec les boutons de niveau.
 */
public class LevelSelectorController {
    private LevelSelectorView view;
    private BrickBreaker frame;

    /**
     * Constructeur du contrôleur pour la vue de sélection du niveau.
     * Initialise la vue et configure les gestionnaires d'événements.
     * 
     * @param frame La fenêtre principale du jeu.
     */
    public LevelSelectorController(BrickBreaker frame) {
        this.view = new LevelSelectorView();
        this.frame = frame;

        this.setLevelButtonsHandler();
    }

    /**
     * Configure les gestionnaires d'événements pour les boutons de niveau.
     * Chaque bouton de niveau est associé à une action pour lancer le niveau correspondant.
     */
    private void setLevelButtonsHandler() {
        int i = 0;
        // Boucle pour associer un gestionnaire d'événements à chaque bouton de niveau
        for (Button button : this.view.getLevelButtons()) {
            int effectivlyFinalI = ++i;
            button.setOnAction(e -> frame.setLevel(effectivlyFinalI));  // Lance le niveau sélectionné
        }
        // Gestionnaire d'événements pour le bouton de retour
        this.view.getBackButton().setOnMouseClicked(e -> this.frame.setBackToSelector());
    }

    /**
     * Retourne la scène de sélection du niveau.
     * 
     * @return La scène associée à la vue de sélection du niveau.
     */
    public Scene getScene() {
        return this.view.getScene();
    }
}
