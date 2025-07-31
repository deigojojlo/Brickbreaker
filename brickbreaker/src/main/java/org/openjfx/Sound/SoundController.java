package org.openjfx.Sound;

import org.openjfx.Game.GameClass.Game;

import javafx.scene.control.Slider;
import javafx.scene.control.Button;

/**
 * Classe SoundController gère les effets sonores du jeu.
 */
public class SoundController {

    /**
     * Joue l'effet sonore de collision avec la raquette.
     */
    public static void collisionRacketSound() {
        Sound.collisionRacketSound();
    }

    /**
     * Joue l'effet sonore de collision avec une brique.
     *
     * @param classBrick la classe de la brique.
     */
    public static void collisionBrickSound(String classBrick) {
        Sound.collisionBrickSound(classBrick);
    }

    /**
     * Définit l'image du bouton des effets sonores en fonction de l'état de mise en
     * sourdine.
     *
     * @param soundButton le bouton à modifier.
     */
    public static void setSoundButtonImage(Button soundButton) {
        Sound.setSoundButtonImage(soundButton);
    }

    /**
     * Initialise le slider de volume des effets sonores.
     *
     * @return le slider de volume des effets sonores.
     */
    public static Slider soundSliderInit() {
        Slider soundSlider = Sound.soundSliderInit();
        soundSlider.valueProperty()
                .addListener((observable, oldValue, newValue) -> Game.soundLevel = newValue.doubleValue() / 100);
        return soundSlider;
    }

    /**
     * Initialise le bouton des effets sonores.
     *
     * @return le bouton des effets sonores.
     */
    public static Button soundButtonInit() {
        return Sound.soundButtonInit();
    }
}
