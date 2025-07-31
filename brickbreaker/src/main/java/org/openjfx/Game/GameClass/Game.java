package org.openjfx.Game.GameClass;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Cette classe {@code Game} centralise les données globales et constantes du jeu.
 * Elle permet de faciliter l'accès aux paramètres généraux, graphiques, et de configuration.
 */
public class Game {

    // Constantes générales
    /** Le nombre total de niveaux dans le jeu. */
    public static final int totalLevels = 10;

    /** La dimension d'une unité dans le jeu (en pixels). */
    public static final int unit = 20;

    /** La durée d'une action pour le timer (en millisecondes). */
    public static final int duration = 10;

    // Dimensions et tailles
    /** La largeur d'une raquette (en pixels). */
    public static final int paddleWidth = 8 * unit;

    /** La hauteur d'une raquette (en pixels). */
    public static final int paddleHeight = unit;

    /** La hauteur de la zone d'affichage du panneau des scores (en pixels). */
    public static final int scoreboardViewHeigh = 85;

    /** La largeur de la fenêtre du jeu (en pixels). */
    public static int windowWidth;

    /** La hauteur de la fenêtre du jeu (en pixels). */
    public static int windowHeight;

    /** La largeur de la zone de jeu (en pixels). */
    public static int gameWidth = 100;

    /** La hauteur jouable de la zone de jeu (en pixels). */
    public static int gameHeight = Math.max(775, windowHeight - scoreboardViewHeigh);

    /** La position X d'origine où commence la zone de jeu. */
    public static int originX = windowWidth - gameWidth;

    /** La position Y d'origine où commence la zone de jeu. */
    public static int originY;

    // Configuration du jeu
    /** La vitesse du jeu (utilisée pour ajuster le rythme). */
    public static int speed = 3;

    /** La vitesse d'exécution des actions liées au timer. */
    public static double rate;

    // Graphismes et affichage
    /** Le contexte graphique utilisé pour dessiner sur le canevas. */
    public static GraphicsContext graphicsContext;

    /** Les dimensions de l'écran en 2D (utilisé pour adapter la fenêtre). */
    public static Rectangle2D screenSize;

    /** La police utilisée pour afficher les textes. */
    public static Font font = new Font("Arial", 16);

    // Couleurs et thèmes
    /** La couleur de la barre supérieure (top bar) du jeu. */
    public static String topBarColor = "rgb(69,69,207)";

    /** La couleur de fond de l'écran (en RGB). */
    public static String backgroundColor = "rgb(28, 28, 30)";

    /** Le code RGB associé à la couleur de fond. */
    public static int backgroundCode = 28;

    /** La couleur de l'arrière-plan de la zone de jeu. */
    public static final Color gameBackground = Color.BLACK;

    // Joueur
    /** Le pseudo du joueur. */
    public static String playerName = "marceauleboss";

    // Contrôles
    /** Touche pour déplacer la raquette du joueur 1 vers la gauche. */
    public static KeyCode p1Left = KeyCode.LEFT;

    /** Touche pour déplacer la raquette du joueur 1 vers la droite. */
    public static KeyCode p1Right = KeyCode.RIGHT;

    /** Touche pour déplacer la raquette du joueur 2 vers la gauche. */
    public static KeyCode p2Left = KeyCode.Z;

    /** Touche pour déplacer la raquette du joueur 2 vers la droite. */
    public static KeyCode p2Right = KeyCode.E;

    // Paramètres sonores et de lecture
    /** Indicateur pour savoir si le joueur est en train de jouer. */
    public static boolean isPlayerPlaying = false;

    /** Indicateur pour savoir si le son principal est en mode muet. */
    public static boolean muteMaster = false;

    /** Indicateur pour savoir si la musique est en mode muet. */
    public static boolean muteMusic = false;

    /** Indicateur pour savoir si les effets sonores sont en mode muet. */
    public static boolean muteSound = false;

    /** Le niveau du volume principal du jeu (entre 0 et 1). */
    public static double masterSoundLevel = 0.5;

    /** Le niveau du volume de la musique (entre 0 et 1). */
    public static double musicLevel = 0.5;

    /** Le niveau du volume des effets sonores (entre 0 et 1). */
    public static double soundLevel = 0.5;
}
