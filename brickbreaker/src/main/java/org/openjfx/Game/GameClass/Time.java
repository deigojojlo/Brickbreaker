package org.openjfx.Game.GameClass;

import org.openjfx.GUI.GUIGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * <p>
 * La classe {@code Time} permet de gérer un timer de manière générique pour le
 * jeu. Elle s'occupe d'appeler de manière récurrente la méthode {@code run()}
 * passée en paramètre. De plus, elle offre la possibilité d'ajuster la vitesse
 * d'exécution ({@code rate}) en fonction du rythme du jeu, qui peut être
 * modifié en fonction du niveau ou du temps écoulé.
 * </p>
 * 
 * Les principales fonctionnalités sont :
 * <ul>
 * <li>Création et gestion d'une instance de {@code Timeline}.</li>
 * <li>Démarrage et arrêt du timer.</li>
 * <li>Changement de la vitesse d'exécution.</li>
 * </ul>
 */
public class Time {
    /**
     * Instance unique de {@code Timeline} utilisée pour gérer le timer.
     */
    protected static Timeline tl;

    /**
     * Crée une nouvelle {@code Timeline} et lui associe un {@code Runnable} à
     * exécuter périodiquement.
     * 
     * @param runnable
     *                 L'action à exécuter à chaque intervalle de temps.
     */
    public static void createTimeline(Runnable runnable) {
        if (tl != null)
            tl.stop();
        tl = new Timeline(
                new KeyFrame(
                        Duration.millis(Game.duration),
                        e -> runnable.run()));
    }

    /**
     * Démarre la {@code Timeline} avec un cycle indéfini.
     * 
     * @param guiGame
     *                Instance de {@link GUIGame} associée à la partie.
     */
    public static void start(GUIGame guiGame) {
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    /**
     * Modifie la vitesse d'exécution du timer.
     * 
     * @param f
     *          La nouvelle valeur de la vitesse (rate).
     */
    public static void setRate(double f) {
        Game.rate = f;
        tl.setRate(f);
    }

    /**
     * Arrête la {@code Timeline} si elle est active.
     */
    public static void stop() {
        if (tl != null)
            tl.stop();
    }
}
