package org.openjfx.Game.GameComponent.Bricks.Bonus;

import org.openjfx.Game.GameClass.Level;

/**
 * La classe {@code Bonus_balle} représente un bonus qui ajoute une balle
 * supplémentaire
 * au jeu. Lorsqu'un joueur obtient ce bonus, une nouvelle balle est ajoutée à
 * la scène
 * de jeu, offrant ainsi plus de possibilités pour interagir avec la raquette et
 * les briques.
 * 
 * <p>
 * Ce bonus est appliqué en appelant la méthode {@code addBall()} sur le niveau
 * actuel du jeu,
 * ce qui entraîne l'ajout d'une balle supplémentaire au jeu.
 * </p>
 */
public class Bonus_balle {

    /**
     * Applique le bonus de balle supplémentaire en ajoutant une balle au niveau du
     * jeu.
     * 
     * @param level Le niveau actuel du jeu. La méthode appelle {@code addBall()}
     *              pour ajouter
     *              une balle au niveau.
     */
    public static void apply(Level level) {
        level.addBall();
    }
}
