package org.openjfx.GUI;

import org.openjfx.BrickBreaker;

import javafx.scene.Scene;
import javafx.util.Pair;

/**
 * Contrôleur pour la gestion des interactions liées à l'affichage des
 * Highscores.
 * Gère les actions des boutons et la navigation entre les écrans de sélection
 * des scores et de mode de jeu.
 */
public class HighscoreController {
    private HighscoreView view;
    private BrickBreaker frame;

    /**
     * Constructeur du contrôleur des Highscores.
     * Initialise la vue des Highscores et configure les gestionnaires d'événements
     * pour les boutons.
     * 
     * @param frame La fenêtre principale du jeu (BrickBreaker).
     */
    public HighscoreController(BrickBreaker frame) {
        this.frame = frame;
        this.view = new HighscoreView();

        this.setButtonHandler();
    }

    /**
     * Configure les gestionnaires d'événements pour les boutons de la vue des
     * Highscores.
     * Associe les actions des boutons aux différentes fonctionnalités, comme
     * l'affichage des scores et le changement de mode de jeu.
     */
    private void setButtonHandler() {
        // Action pour le bouton "Home", retourne à l'écran principal.
        this.view.getHomeButton().setOnAction(e -> this.frame.setHome());

        // Action pour le bouton "Back", retourne à l'écran de sélection du mode de jeu.
        this.view.getBackButton().setOnAction(e -> this.view.displayModeSelectionScreen());

        // Action pour le bouton "Multi Player", affiche les scores en mode multijoueur.
        this.view.getMultiButton().setOnAction(e -> {
            this.view.display(true);
            new Thread(() -> {
                // Associe une action pour chaque bouton de niveau en mode multijoueur.
                int[] a = new int[] { 1 };
                this.view.getLevelButtons()
                        .forEach(button -> {
                            int i = a[0]++;
                            button.setOnAction(event -> this.view.displayScore(new Pair<>(i, true)));
                        });
            }).start();
        });

        // Action pour le bouton "Single Player", affiche les scores en mode solo.
        this.view.getSoloButton().setOnAction(e -> {
            this.view.display(false);
            new Thread(() -> {
                this.view.getRushButton()
                        .setOnAction(event -> this.view.displayScore(new Pair<Integer, Boolean>(0, false)));

                // Associe une action pour chaque bouton de niveau en mode solo.
                int[] a = new int[] { 1 };
                this.view.getLevelButtons()
                        .forEach(button -> {
                            int i = a[0]++;
                            button.setOnAction(event -> this.view.displayScore(new Pair<>(i, false)));
                        });
            }).start();
        });
    }

    /**
     * Retourne la scène associée à l'affichage des Highscores.
     * 
     * @return La scène des Highscores.
     */
    public Scene getScene() {
        return this.view.getScene();
    }
}
