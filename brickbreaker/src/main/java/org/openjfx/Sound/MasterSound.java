package org.openjfx.Sound;

import org.openjfx.Game.GameClass.Game;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Classe MasterSound gère les réglages audio principaux du jeu.
 */
public class MasterSound {
    /**
     * Bouton principal permettant de contrôler l'état de mise en sourdine du son.
     */
    protected static Button masterSoundButton;

    /**
     * Constructeur qui initialise le bouton de contrôle du son principal.
     *
     * @param anchorPane le panneau parent où le bouton sera ajouté.
     */
    public MasterSound(AnchorPane anchorPane) {
        masterSoundButton = masterSoundButtonInit(anchorPane);
    }

    /**
     * Change l'image de l'état de mise en sourdine du son principal.
     */
    public void toggleMuteImage() {
        if (masterSoundButton != null) {
            masterSoundButton.setGraphic(setImageBtn());
            masterSoundButton.layout();
        }
    }

    /**
     * Définit l'image du bouton de son principal en fonction de l'état de mise en
     * sourdine.
     *
     * @return l'image appropriée pour le bouton.
     */
    public static ImageView setImageBtn() {
        try {
            Image image = new Image(
                    "file:src/main/resources/images/switchMSound" + (Game.muteMaster ? "Off" : "On") + ".png");
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
     * Initialise le bouton de contrôle du son principal.
     *
     * @param anchorPane le panneau parent où le bouton sera ajouté.
     * 
     * @return le bouton configuré pour le contrôle du son principal.
     */
    public static Button masterSoundButtonInit(AnchorPane anchorPane) {
        Button muteSoundButton = new Button("");
        muteSoundButton.setGraphic(setImageBtn());
        anchorPane.getChildren().add(muteSoundButton);
        AnchorPane.setRightAnchor(muteSoundButton, Game.windowWidth * 0.055);
        AnchorPane.setTopAnchor(muteSoundButton, Game.windowHeight * 0.055);
        muteSoundButton.setPrefSize(75, 75);

        muteSoundButton.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        return muteSoundButton;
    }

    /**
     * Retourne le bouton de contrôle du son principal.
     *
     * @return le bouton de contrôle du son principal.
     */
    public static Button getMasterSoundButton() {
        return masterSoundButton;
    }

    /**
     * Initialise le slider de volume du son principal.
     *
     * @return le slider de volume du son principal.
     */
    public static Slider masterSoundSliderInit() {
        Slider sliderMSound = new Slider(0, 100, Game.masterSoundLevel * 100);
        sliderMSound.setShowTickLabels(true);
        sliderMSound.setShowTickMarks(true);
        sliderMSound.setMajorTickUnit(50);
        sliderMSound.setMinorTickCount(5);
        sliderMSound.setBlockIncrement(10);
        sliderMSound.setMaxWidth(Game.windowWidth / 4);
        return sliderMSound;
    }

}
