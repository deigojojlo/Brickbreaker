package org.openjfx.Game.GameComponent.Bricks.Bonus;

import org.openjfx.Game.GameClass.Level;
import java.util.Random;

/**
 * La classe {@code Bonus_racket} représente un bonus qui modifie la taille de
 * la raquette du joueur.
 * Lorsqu'un joueur obtient ce bonus, la taille de sa raquette peut être
 * augmentée ou diminuée
 * de manière aléatoire, affectant ainsi le contrôle et la dynamique du jeu.
 * 
 * <p>
 * Le bonus peut soit augmenter soit diminuer la taille des raquettes des
 * joueurs
 * (joueur 1 et joueur 2 si présent) en modifiant leur largeur.
 * </p>
 */
public class Bonus_racket {

    /**
     * Applique le bonus de taille de raquette en augmentant ou diminuant la largeur
     * de la raquette
     * d'un joueur. L'effet est déterminé de manière aléatoire.
     * 
     * @param level Le niveau actuel du jeu. La méthode applique la modification de
     *              taille
     *              aux raquettes des joueurs (P1 et P2 si présent).
     */
    public static void apply(Level level) {
        Random random = new Random();
        boolean increaseSize = random.nextBoolean(); // True pour augmenter, false pour diminuer

        // Si augmenter la taille
        if (increaseSize) {
            // Augmente la taille de la raquette de P1
            level.getPlayer1Racket().adjustRacketSize((int) (level.getPlayer1Racket().getWidth() * 0.2), 0);

            // Si P2 existe, augmente également la taille de sa raquette
            if (level.getPlayer2Racket() != null) {
                level.getPlayer2Racket().adjustRacketSize((int) (level.getPlayer2Racket().getWidth() * 0.2), 0);
            }
        } else {
            // Diminue la taille de la raquette de P1
            level.getPlayer1Racket().adjustRacketSize(-(int) (level.getPlayer1Racket().getWidth() * 0.2), 0);

            // Si P2 existe, diminue également la taille de sa raquette
            if (level.getPlayer2Racket() != null) {
                level.getPlayer2Racket().adjustRacketSize(-(int) (level.getPlayer2Racket().getWidth() * 0.2), 0);
            }
        }
    }
}
