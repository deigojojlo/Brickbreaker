package org.openjfx.Sound;

import org.openjfx.Game.GameClass.Game;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

/**
 * Classe Sound gère les effets sonores du jeu. Les objets Sound sont créés pour
 * chaque effet sonore à jouer.
 */
public class Sound {
    private AudioClip audioClip;

    /**
     * Constructeur qui initialise l'effet sonore avec le fichier sonore spécifié.
     *
     * @param soundFile le nom du fichier sonore sans extension.
     */
    public Sound(String soundFile) {
        try {
            String soundPath = "file:src/main/resources/sons/" + soundFile + ".mp3";
            audioClip = new AudioClip(soundPath);
            audioClip.setVolume(Game.soundLevel * Game.masterSoundLevel);
        } catch (Exception e) {
            System.err.println("Error loading audio file: " + e.getMessage());
        }
    }

    /**
     * Setter pour le niveau de son afin de le rendre accessible pour les tests.
     *
     * @param soundLevel le niveau de son à définir.
     */
    public static void setSoundVolume(double soundLevel) {
        Game.soundLevel = soundLevel;
    }

    /**
     * Getter pour audioClip afin de le rendre accessible pour les tests.
     *
     * @return l'instance de AudioClip.
     */
    public AudioClip getAudioClip() {
        return audioClip;
    }

    /**
     * Joue l'effet sonore.
     */
    public void play() {
        try {
            audioClip.play();
        } catch (Exception e) {
            System.err.println("Error playing audio file: " + e.getMessage());
        }
    }

    /**
     * Setter pour audioClip afin de le rendre accessible pour les tests.
     *
     * @param audioClip l'instance de AudioClip à définir.
     */
    public void setAudioClip(AudioClip audioClip) {
        this.audioClip = audioClip;
    }

    /**
     * Définit l'image du bouton des effets sonores en fonction de l'état de mise en
     * sourdine.
     *
     * @param soundButton le bouton à modifier.
     */
    public static void setSoundButtonImage(Button soundButton) {
        if (soundButton != null) {
            soundButton.setGraphic(setImageBtn());
            soundButton.layout();
        }
    }

    /**
     * Bascule l'état de mise en sourdine des effets sonores.
     */
    public static void collisionRacketSound() {
        if (!Game.muteSound && !Game.muteMaster) {
            new Sound("Racket").play();
        }
    }

    /**
     * Joue l'effet sonore de collision avec une brique.
     *
     * @param classBrick la classe de la brique.
     */
    public static void collisionBrickSound(String classBrick) {
        if (!Game.muteSound && !Game.muteMaster) {
            if (classBrick.equals("Brick")) {
                classBrick = "DurabilityBrick";
            }
            new Sound(classBrick).play();
        }
    }

    /**
     * Définit l'image du bouton des effets sonores en fonction de l'état de mise en
     * sourdine.
     *
     * @return l'image appropriée pour le bouton.
     */
    public static ImageView setImageBtn() {
        try {
            Image image = new Image("file:src/main/resources/images/Sound" + (Game.muteSound ? "Off" : "On") + ".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.setPreserveRatio(true);
            return imageView;
        } catch (Exception e) {
            System.err.println("Error loading image file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Initialise le bouton de contrôle des effets sonores.
     *
     * @return le bouton initialisé.
     */
    public static Button soundButtonInit() {
        Button btn = new Button("");

        btn.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        btn.setFont(Game.font);
        btn.setPrefSize(150, 75);

        btn.setGraphic(setImageBtn());
        return btn;
    }

    /**
     * Bascule l'état de mise en sourdine des effets sonores.
     * 
     * @return Le slider configuré permettant de régler le volume des effets
     *         sonores.
     * 
     */
    public static Slider soundSliderInit() {
        Slider sliderSound = new Slider(0, 100, Game.soundLevel * 100);
        sliderSound.setShowTickLabels(true);
        sliderSound.setShowTickMarks(true);
        sliderSound.setMajorTickUnit(50);
        sliderSound.setMinorTickCount(5);
        sliderSound.setBlockIncrement(10);
        sliderSound.setMaxWidth(Game.windowWidth / 4);
        return sliderSound;
    }
}