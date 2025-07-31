package org.openjfx.Game.GameComponent.Bricks.Bonus;

import org.openjfx.Game.GameClass.Scoreboard.ScoreboardController;

/**
 * La classe {@code Bonus_vie} représente un bonus qui permet d'ajouter une vie
 * supplémentaire au joueur.
 * Elle est utilisée lorsque le bonus de vie est sélectionné dans le jeu.
 * 
 * <p>
 * Le bonus est appliqué en ajoutant une vie au score du joueur via le
 * {@code ScoreboardController}.
 * </p>
 */
public class Bonus_vie {

    /**
     * Applique le bonus de vie en ajoutant une vie au joueur.
     * 
     * @param scoreboardController L'instance du contrôleur de score qui gère les
     *                             vies du joueur.
     *                             La méthode {@code addLife()} est appelée pour
     *                             augmenter le nombre de vies du joueur.
     */
    public static void apply(ScoreboardController scoreboardController) {
        scoreboardController.addLife();
    }
}
