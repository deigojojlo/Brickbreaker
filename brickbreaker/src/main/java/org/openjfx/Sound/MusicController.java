package org.openjfx.Sound;

import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Button;

/**
 * Classe MusicController gère la lecture et le contrôle de la musique dans le
 * jeu.
 */
public class MusicController {
    private Music view;

    /**
     * Constructeur qui initialise le contrôleur de musique avec le fichier sonore
     * spécifié.
     *
     * @param fileName le nom du fichier sonore sans extension (par exemple,
     *                 "Music").
     *                 Le fichier doit se trouver dans le répertoire des ressources.
     * @param isGame   true si la musique est associée à une phase de jeu (en cours
     *                 de partie),
     *                 false si elle est liée à une autre phase (menu, etc.).
     */
    public MusicController(String fileName, boolean isGame) {
        this.view = new Music(fileName, isGame);
    }

    /**
     * Constructeur qui initialise le contrôleur de musique avec le lecteur de
     * média spécifié.
     *
     * @param mediaPlayer le lecteur de média à utiliser pour la musique.
     * @param isGame      true si la musique est associée à une phase de jeu (en
     *                    cours de partie),
     *                    false si elle est liée à une autre phase (menu, etc.).
     */
    public MusicController(MediaPlayer mediaPlayer, boolean isGame) {
        this.view = new Music(mediaPlayer, isGame);
    }

    /**
     * Met à jour l'image du bouton de contrôle de la musique.
     *
     * @param button le bouton de musique à mettre à jour.
     */
    public static void setMusicButtonImage(Button button) {
        Music.setMusicButtonImage(button);
    }

    /**
     * Modifie l'état de lecture de la musique en fonction du statut du joueur.
     *
     * @param isPlayerPlaying true si le joueur est en train de jouer, false sinon.
     */
    public void setMusicPlaying(boolean isPlayerPlaying) {
        this.view.togglePlay(isPlayerPlaying);
        this.view.refresh(isPlayerPlaying);
    }

    /**
     * Met à jour le volume de la musique en fonction des réglages globaux.
     */
    public void updateVolume() {
        this.view.updateVolume();
    }

    /**
     * Met à jour l'état de mise en sourdine de la musique.
     */
    public void updateMute() {
        this.view.updateMute();
    }

    /**
     * Initialise un curseur (slider) permettant de régler le volume de la musique.
     *
     * @return un objet {@link Slider} configuré pour contrôler le volume.
     */
    public static Slider musicSliderInit() {
        return Music.musicSliderInit();
    }

    /**
     * Initialise un bouton pour contrôler la musique.
     *
     * @return un objet {@link Button} configuré pour gérer l'état de lecture et de
     *         sourdine.
     */
    public static Button musicButtonInit() {
        return Music.musicButtonInit();
    }
}
