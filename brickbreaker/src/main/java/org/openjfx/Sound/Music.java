package org.openjfx.Sound;

import org.openjfx.Game.GameClass.Game;

import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Classe Music gère la lecture et le contrôle de la musique dans le jeu.
 */
public class Music {
    /**
     * Instance de MediaPlayer utilisée pour gérer la lecture de la musique.
     * Permet de contrôler des fonctionnalités telles que la lecture, la pause
     * et la gestion du volume.
     */
    protected MediaPlayer mediaPlayer;

    /**
     * Indique si la musique associée à cette instance est une musique de jeu.
     * Si true, la musique est liée à un état du jeu actif (par exemple, en jeu).
     * Si false, elle est liée à d'autres contextes (par exemple, menu principal).
     */
    protected boolean isGameMusic;

    /**
     * Constructeur qui initialise le lecteur de médias avec le fichier sonore
     * spécifié.
     *
     * @param soundFile   le nom du fichier sonore sans extension.
     * @param isGameMusic true si la musique est une musique de jeu, false sinon.
     */
    public Music(String soundFile, boolean isGameMusic) {
        try {
            this.mediaPlayer = initMediaPlayer(soundFile);
            this.isGameMusic = isGameMusic;
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement du fichier audio : " + e.getMessage());
        }
    }

    /**
     * Constructeur qui initialise le lecteur de médias avec un lecteur de médias
     * existant (pour les tests).
     *
     * @param mediaPlayer le lecteur de médias à utiliser.
     * @param isGameMusic true si la musique est une musique de jeu, false sinon.
     */
    public Music(MediaPlayer mediaPlayer, boolean isGameMusic) {
        this.mediaPlayer = mediaPlayer;
        this.isGameMusic = isGameMusic;
    }

    /**
     * Initialise une instance de MediaPlayer avec un fichier audio spécifié.
     * 
     * @param soundFile le nom du fichier audio sans extension (par exemple,
     *                  "background_music").
     *                  Le fichier doit être présent dans le dossier
     *                  "src/main/resources/sons" avec l'extension ".mp3".
     * @return une instance de MediaPlayer configurée pour lire le fichier audio.
     * 
     */
    public MediaPlayer initMediaPlayer(String soundFile) {
        Media media = new Media(new File("src/main/resources/sons/" + soundFile + ".mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(javafx.util.Duration.ZERO));
        mediaPlayer.setVolume(Game.musicLevel * Game.masterSoundLevel);
        return mediaPlayer;
    }

    /**
     * Setter de mute.
     * 
     * @param mute le nouveau mute.
     * 
     */
    public void setMute(boolean mute) {
        Game.muteMusic = mute;
    }

    /**
     * Setter de isPlaying.
     * 
     * @param isPlaying le nouveau isPlaying.
     * 
     */
    public void setIsPlaying(boolean isPlaying) {
        Game.isPlayerPlaying = isPlaying;
    }

    /**
     * Setter pour mediaPlayer afin de le rendre accessible pour les tests.
     *
     * @param mediaPlayer l'instance de MediaPlayer à définir.
     */
    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    /**
     * Getter pour mediaPlayer afin de le rendre accessible pour les tests.
     *
     * @return l'instance de MediaPlayer.
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Démarre la lecture de la musique.
     */
    public void play() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.play();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du fichier média : " + e.getMessage());
        }
    }

    /**
     * Arrête la lecture de la musique.
     */
    public void pause() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise en pause du fichier média : " + e.getMessage());
        }
    }

    /**
     * Bascule l'état de mise en sourdine de la musique.
     * 
     * <p>
     * Cette méthode inverse la valeur actuelle de {@link Game#muteMusic},
     * qui détermine si la musique doit être mise en sourdine ou non.
     * </p>
     * 
     * @return la nouvelle valeur de {@link Game#muteMusic} après la bascule :
     *         <ul>
     *         <li><code>true</code> si la musique est maintenant en sourdine.</li>
     *         <li><code>false</code> si la musique n'est plus en sourdine.</li>
     *         </ul>
     */
    public boolean toggleMute() {
        Game.muteMusic = !Game.muteMusic;
        return Game.muteMusic;
    }

    /**
     * Change l'état de lecture de la musique en fonction de l'état de lecture du
     * joueur.
     */
    public void changeMusic() {
        if (Game.isPlayerPlaying && this.isGameMusic) {
            play();
        } else if (!Game.isPlayerPlaying && !this.isGameMusic) {
            play();
        } else {
            pause();
        }
    }

    /**
     * Bascule l'état de lecture de la musique.
     *
     * @param play true pour jouer, false pour mettre en pause.
     * @return true si la musique a été mise en pause, false sinon.
     */
    public boolean togglePlay(boolean play) {
        if ((Game.isPlayerPlaying && play) || (!Game.isPlayerPlaying && !play)) {
            return false;
        } else {
            Game.isPlayerPlaying = play;
            return true;
        }
    }

    /**
     * Rafraîchit l'état de lecture de la musique en fonction de l'état de lecture
     * du
     * joueur.
     *
     * @param isPlayerPlaying true si le joueur est en train de jouer, false sinon.
     */
    public void refresh(boolean isPlayerPlaying) {
        if (Game.muteMaster || Game.muteMusic) {
            pause();
        } else {
            changeMusic();
        }
    }

    /**
     * Met à jour le volume de la musique.
     */
    public void updateVolume() {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(Game.musicLevel * Game.masterSoundLevel);
        }
    }

    /**
     * Met à jour l'état de mise en sourdine de la musique.
     */
    public void updateMute() {
        if (mediaPlayer != null) {
            if (Game.isPlayerPlaying && this.isGameMusic || !Game.isPlayerPlaying && !this.isGameMusic) {
                if (Game.muteMaster || Game.muteMusic) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.play();
                }
            }
        }
    }

    /**
     * Définit l'image du bouton de musique en fonction de l'état de mise en
     * sourdine.
     *
     * @return l'image appropriée pour le bouton.
     */
    public static ImageView setImageBtn() {
        try {
            Image image = new Image("file:src/main/resources/images/Music" + (Game.muteMusic ? "Off" : "On") + ".png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.setPreserveRatio(true);
            return imageView;
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement du fichier image : " + e.getMessage());
            return null;
        }
    }

    /**
     * Initialise le bouton de contrôle de la musique.
     *
     * @return le bouton initialisé.
     */
    public static Button musicButtonInit() {
        Button btn = new Button("");

        btn.setStyle(
                "-fx-border-radius : 15 ; -fx-background-radius : 15 ; -fx-background-color : darkgray  ; -fx-text-fill : black");
        btn.setFont(Game.font);
        btn.setPrefSize(150, 75);

        btn.setGraphic(setImageBtn());
        return btn;
    }

    /**
     * Définit l'image du bouton de musique en fonction de l'état de mise en
     * sourdine.
     *
     * @param musicButton le bouton de musique à mettre à jour.
     */
    public static void setMusicButtonImage(Button musicButton) {
        if (musicButton != null) {
            musicButton.setGraphic(setImageBtn());
            musicButton.layout();
        }
    }

    /**
     * Initialise le curseur de réglage du volume de la musique.
     *
     * @return le curseur initialisé.
     */
    public static Slider musicSliderInit() {
        Slider slider = new Slider(0, 100, Game.musicLevel * 100);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        slider.setMaxWidth(Game.windowWidth / 4);
        return slider;
    }

}
