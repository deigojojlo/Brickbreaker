package org.openjfx.Sound;

import org.openjfx.Game.GameClass.Game;

import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;

/**
 * Classe MasterSoundController gère les réglages audio principaux du jeu.
 */
public class MasterSoundController {
    private MasterSound view;
    private MusicController musicControllerGame;
    private MusicController musicControllerBackground;

    /**
     * Constructeur qui initialise les contrôleurs de musique de jeu et de musique
     * de fond.
     *
     * @param anchorPane le panneau parent où les contrôleurs seront ajoutés.
     */
    public MasterSoundController(AnchorPane anchorPane) {
        this.musicControllerBackground = new MusicController("Music", false);
        musicControllerBackground.setMusicPlaying(false);
        this.musicControllerGame = new MusicController("GameMusic", true);
        this.view = new MasterSound(anchorPane);
        setButtonHandler();
    }

    /**
     * Constructeur qui initialise les contrôleurs de musique de jeu et de musique
     * de fond (pour les tests).
     *
     * @param anchorPane   le panneau parent où les contrôleurs seront ajoutés.
     * @param mediaPlayer1 le lecteur de média pour la musique de fond.
     * @param mediaPlayer2 le lecteur de média pour la musique de jeu.
     */
    public MasterSoundController(AnchorPane anchorPane, MediaPlayer mediaPlayer1, MediaPlayer mediaPlayer2) {
        this.musicControllerBackground = new MusicController(mediaPlayer1, false);
        musicControllerBackground.setMusicPlaying(false);
        this.musicControllerGame = new MusicController(mediaPlayer2, true);
        this.view = new MasterSound(anchorPane);
        setButtonHandler();
    }

    /**
     * Bascule l'état de mise en sourdine du son principal.
     */
    public void toggleMute() {
        Game.muteMaster = !Game.muteMaster;
        view.toggleMuteImage();
        this.updateMute();
    }

    /**
     * Met à jour le volume du son principal.
     */
    public void updateVolume() {
        musicControllerGame.updateVolume();
        musicControllerBackground.updateVolume();
    }

    /**
     * Met à jour l'état de mise en sourdine du son principal.
     */
    public void updateMute() {
        musicControllerGame.updateMute();
        musicControllerBackground.updateMute();
    }

    /**
     * Initialise l'action du slider de volume du son principal.
     */
    public void setButtonHandler() {
        MasterSound.masterSoundButton.setOnMouseClicked(e -> toggleMute());
    }

    /**
     * Bascule l'état de lecture de la musique.
     *
     * @param isPlayerPlaying true si la musique est en cours de lecture, false
     *                        sinon.
     */
    public void setMusicPlaying(boolean isPlayerPlaying) {
        musicControllerGame.setMusicPlaying(isPlayerPlaying);
        musicControllerBackground.setMusicPlaying(isPlayerPlaying);
    }

    /**
     * Initialise le slider de volume du son principal.
     *
     * @return le slider de volume du son principal.
     */
    public static Slider mastereSliderInit() {
        return MasterSound.masterSoundSliderInit();
    }

}
