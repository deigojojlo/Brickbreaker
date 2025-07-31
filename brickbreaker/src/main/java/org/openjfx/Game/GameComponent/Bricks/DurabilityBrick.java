package org.openjfx.Game.GameComponent.Bricks;

import org.openjfx.Game.GameClass.Game;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameClass.Scoreboard.ScoreboardController;
import org.openjfx.Game.GameComponent.Component;
import org.openjfx.Game.GameComponent.Bricks.Bonus.Bonus;

import javafx.scene.image.Image;

/**
 * Classe représentant une brique avec une durabilité.
 * La brique doit être frappée plusieurs fois avant d'être détruite, en fonction
 * de sa durabilité.
 */
public class DurabilityBrick extends Brick {
    /**
     * Durabilité de la brique (nombre de frappes nécessaires pour la détruire).
     */
    private int durability;

    /**
     * Constructeur de la classe DurabilityBrick.
     *
     * @param type                 Le type de la brique (détermine son apparence).
     * @param x                    La position en X de la brique.
     * @param y                    La position en Y de la brique.
     * @param durability           La durabilité de la brique.
     * @param b                    Le bonus associé à la brique.
     * @param scoreboardController Le contrôleur du tableau des scores.
     * @param parent               Le niveau parent auquel appartient la brique.
     */
    public DurabilityBrick(int type, int x, int y, int durability, Bonus b, ScoreboardController scoreboardController,
            Level parent) {
        super(type, x, y, b, scoreboardController, parent);
        this.durability = durability;
        type();
    }

    /**
     * Met à jour l'apparence de la brique en fonction de sa durabilité.
     */
    @Override
    public void update() {
        super.setDecreaseType();
        type();
        drawRacket();
    }

    /**
     * Diminue la durabilité de la brique ou la supprime si elle atteint zéro.
     */
    @Override
    public void remove() {
        if (durability == 0) {
            super.remove();
        } else {
            this.durability--;
            this.update();
        }
    }

    /**
     * Dessine la brique sur le canvas du jeu.
     */
    @Override
    public void drawRacket() {
        Game.graphicsContext.drawImage(this.getImage(), this.getPositionX(), this.getPositionY());
    }

    /**
     * Met à jour le type et l'image de la brique en fonction de sa durabilité.
     */
    public void type() {
        int type = this.getTypeId();
        Image[] image = Component.getLoadedImages();

        this.setHeight(Game.unit);
        this.setWidth(Game.unit * (type % 100 / 10) * (type % 100 / 10));

        if (image[type % 100 + 10] == null) {
            image[type % 100 + 10] = new Image(
                    getClass()
                            .getResourceAsStream("/images/" + this.getClass().getSimpleName() + (type % 100) + ".png"));
        }
        this.setImage(image[type % 100 + 10]);
    }

    /**
     * Retourne la durabilité restant de la brique.
     *
     * @return La durabilité.
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Met à jour l'apparence de la brique en fonction de sa durabilité.
     * Modifié pour les test
     */
    public void updatetest() {
        super.setDecreaseType();
        type();
    }

    /**
     * Diminue la durabilité de la brique ou la supprime si elle atteint zéro.
     * Modifié pour les test
     */
    public void removetest() {
        if (durability == 0) {
            super.remove();
        } else {
            this.durability--;
            this.updatetest();
        }
    }
}
