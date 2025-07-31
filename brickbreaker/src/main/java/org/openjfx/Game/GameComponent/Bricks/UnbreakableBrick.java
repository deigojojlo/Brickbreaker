package org.openjfx.Game.GameComponent.Bricks;

import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameClass.Scoreboard.ScoreboardController;

/**
 * Classe représentant une brique incassable.
 * Cette brique ne peut pas être détruite ni modifiée, et reste présente tout au
 * long du jeu.
 */
public class UnbreakableBrick extends Brick {

    /**
     * Constructeur de la classe UnbreakableBrick.
     *
     * @param type                 Le type de la brique (détermine son apparence).
     * @param x                    La position en X de la brique.
     * @param y                    La position en Y de la brique.
     * @param scoreboardController Le contrôleur du tableau des scores.
     * @param parent               Le niveau parent auquel appartient la brique.
     */
    public UnbreakableBrick(int type, int x, int y, ScoreboardController scoreboardController, Level parent) {
        super(type, x, y, null, scoreboardController, parent);
    }

    /**
     * Méthode vide car cette brique est incassable.
     * Aucune action n'est entreprise lorsqu'une tentative est faite pour la
     * retirer.
     */
    @Override
    public void remove() {
        // Cette brique est incassable, donc remove() est intentionnellement vide.
    }

    /**
     * Méthode vide car cette brique ne possède aucune variante et reste inchangée.
     */
    @Override
    public void update() {
    }

}
