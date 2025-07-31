package org.openjfx.GUI;

import org.openjfx.BrickBreaker;
import org.openjfx.Game.GameClass.Game;
import org.openjfx.Sound.MusicController;
import org.openjfx.Sound.SoundController;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

/**
 * Contrôleur pour la vue des paramètres du jeu.
 * Gère les interactions entre la vue et les composants du jeu,
 * notamment le son et les raquettes.
 */
public class SettingsController {
    private SettingsView view;
    private BrickBreaker frame;

    /**
     * Constructeur de la classe SettingsController.
     * Initialise le contrôleur avec la fenêtre du jeu.
     * 
     * @param frame      La fenêtre principale du jeu.
     * @param anchorPane Le Panel d'affichage des settings.
     */
    public SettingsController(BrickBreaker frame, AnchorPane anchorPane) {
        this.frame = frame;
        this.view = new SettingsView(anchorPane);
        initSliders();
        initButtonsSound();
        initButtonsRacket();

        // Initialisation des gestionnaires d'événements pour les boutons
        this.setButtonHandler();
    }

    /**
     * Initialise les sliders de la vue des paramètres.
     */
    public void initSliders() {
        // Configuration des sliders de son
        view.masterSoundSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Game.masterSoundLevel = newValue.doubleValue() / 100;
            frame.updateVolume();
        });
        view.musicSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Game.musicLevel = newValue.doubleValue() / 100;
            frame.updateVolume();
        });
        view.soundSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Game.soundLevel = newValue.doubleValue() / 100;
        });
    }

    /**
     * Initialise les boutons de la vue des paramètres.
     */
    public void initButtonsSound() {
        // Configuration des boutons de son
        view.musicButton.setOnMouseClicked(e -> {
            Game.muteMusic = !Game.muteMusic;
            frame.updateMute();
            MusicController.setMusicButtonImage(view.musicButton);
        });
        view.soundButton.setOnMouseClicked(e -> {
            Game.muteSound = !Game.muteSound;
            SoundController.setSoundButtonImage(view.soundButton);
        });
    }

    /**
     * Initialise les boutons de la vue des paramètres pour les raquettes.
     */
    public void initButtonsRacket() {
        // Configuration des boutons de raquette
        view.racketRightButtonP1.setOnMouseClicked(e -> selectKey(view.racketRightButtonP1, view.labelRightP1, "P1"));
        view.racketLeftButtonP1.setOnMouseClicked(e -> selectKey(view.racketLeftButtonP1, view.labelLeftP1, "P1"));
        view.racketRightButtonP2.setOnMouseClicked(e -> selectKey(view.racketRightButtonP2, view.labelRightP2, "P2"));
        view.racketLeftButtonP2.setOnMouseClicked(e -> selectKey(view.racketLeftButtonP2, view.labelLeftP2, "P2"));
    }

    /**
     * Vérifie si une touche n'est pas déjà utilisée par une raquette.
     *
     * @param code le KeyCode à vérifier.
     * @return vrai si la touche n'est pas déjà utilisée, sinon faux.
     */
    private static boolean isNotAlreadyUsed(KeyCode code) {
        return code != Game.p1Right && code != Game.p1Left
                && code != Game.p2Right && code != Game.p2Left;
    }

    /**
     * Permet de sélectionner une nouvelle touche pour déplacer la raquette.
     *
     * @param button le bouton associé.
     * @param label  le label affichant la touche actuelle.
     * @param player le joueur concerné ("P1" ou "P2").
     */
    public static void selectKey(Button button, Label label, String player) {
        button.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.UNDEFINED) {
                button.setOnKeyPressed(null); // Cancel the key listener
            } else {
                if (button.getText().equals("right") && isNotAlreadyUsed(event.getCode())) {
                    if (player.equals("P1")) {
                        Game.p1Right = event.getCode();
                    } else {
                        Game.p2Right = event.getCode();
                    }
                    label.setText(event.getCode().toString());
                } else if (button.getText().equals("left") && isNotAlreadyUsed(event.getCode())) {
                    if (player.equals("P1")) {
                        Game.p1Left = event.getCode();
                    } else {
                        Game.p2Left = event.getCode();
                    }
                    label.setText(event.getCode().toString());
                }
                button.setOnKeyPressed(null); // Remove the key listener after setting the key
            }
        });
        button.requestFocus(); // Ensure the button has focus to receive key events
    }

    /**
     * Définit les gestionnaires d'événements pour les boutons de la vue des
     * paramètres.
     * En particulier, il gère l'action du bouton "home" pour revenir à l'écran
     * d'accueil.
     */
    private void setButtonHandler() {
        // Lorsque le bouton "home" est cliqué, on revient à l'écran d'accueil.
        this.view.getHomeButton().setOnAction(e -> this.frame.setHome());
    }

    /**
     * Retourne le label de la raquette gauche du joueur 2.
     * 
     * @return Le label représentant la raquette gauche du joueur 2.
     */
    public Label getLabelLeftP2() {
        return this.view.getLabelLeftP2();
    }

    /**
     * Retourne le label de la raquette droite du joueur 2.
     * 
     * @return Le label représentant la raquette droite du joueur 2.
     */
    public Label getLabelRightP2() {
        return this.view.getLabelRightP2();
    }

    /**
     * Retourne le label de la raquette droite du joueur 1.
     * 
     * @return Le label représentant la raquette droite du joueur 1.
     */
    public Label getLabelRightP1() {
        return this.view.getLabelRightP1();
    }

    /**
     * Retourne le label de la raquette gauche du joueur 1.
     * 
     * @return Le label représentant la raquette gauche du joueur 1.
     */
    public Label getLabelLeftP1() {
        return this.view.getLabelLeftP1(); // Erreur dans le code original: devrait retourner le label du joueur 1.
    }

    /**
     * Retourne la scène de la vue des paramètres.
     * 
     * @return La scène contenant la vue des paramètres.
     */
    public Scene getScene() {
        return this.view.getScene();
    }
}
