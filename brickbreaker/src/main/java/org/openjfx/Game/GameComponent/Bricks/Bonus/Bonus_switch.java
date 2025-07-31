package org.openjfx.Game.GameComponent.Bricks.Bonus;

import org.openjfx.Game.GameClass.Level;

/**
 * La classe {@code Bonus_switch} représente un bonus qui inverse les contrôles
 * des raquettes des joueurs.
 * Lorsqu'un joueur obtient ce bonus, les contrôles de la raquette sont
 * inversés, ce qui modifie la manière
 * dont le joueur interagit avec le jeu.
 * 
 * <p>
 * Le bonus inverse les contrôles de la raquette de chaque joueur actif dans le
 * niveau actuel du jeu.
 * </p>
 */
public class Bonus_switch {

    /**
     * Applique le bonus de contrôle inversé aux raquettes des joueurs.
     * Inverse les contrôles de la raquette de chaque joueur (P1 et P2).
     * 
     * @param level Le niveau actuel du jeu. La méthode applique l'inversion des
     *              contrôles
     *              aux raquettes de chaque joueur actif (P1 et P2 si présent).
     */
    public static void apply(Level level) {
        // Inversion des contrôles de la raquette du joueur 1
        level.getPlayer1Racket().toggleControlDirection();

        // Si un joueur 2 existe, inverse également les contrôles de sa raquette
        if (level.getPlayer2Racket() != null) {
            level.getPlayer2Racket().toggleControlDirection();
        }
    }
}
