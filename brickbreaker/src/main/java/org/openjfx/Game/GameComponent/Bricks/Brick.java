package org.openjfx.Game.GameComponent.Bricks;

import java.io.InputStream;

import org.openjfx.Game.GameClass.Game;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameClass.Scoreboard.ScoreboardController;
import org.openjfx.Game.GameComponent.Component;
import org.openjfx.Game.GameComponent.Bricks.Bonus.Bonus;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Classe représentant une brique dans le jeu.
 * Une brique est un composant graphique qui peut être détruit par le joueur,
 * accordant des points et potentiellement un bonus lorsqu'elle est détruite.
 */
public class Brick extends Component {
    /**
     * Points gagnés lorsque la brique est détruite.
     */
    private int point = 5;

    /**
     * Bonus associé à la brique.
     */
    private Bonus bonus;

    /**
     * Contrôleur du tableau des scores.
     */
    private ScoreboardController scoreboardController;

    /**
     * Constructeur de la classe Brick avec un bonus.
     * 
     * <p>
     * Ce constructeur initialise une brique avec un bonus et configure son
     * apparence
     * en fonction de son type. Le type détermine la taille, la couleur ou l'image
     * associée à la brique.
     * </p>
     * 
     * @param type                 Le type de la brique (détermine sa couleur, image
     *                             ou taille).
     * @param x                    La position en X de la brique.
     * @param y                    La position en Y de la brique.
     * @param b                    Le bonus associé à la brique.
     * @param scoreboardController Le contrôleur du tableau des scores.
     * @param parent               Le niveau parent auquel appartient la brique.
     */
    public Brick(int type, int x, int y, Bonus b, ScoreboardController scoreboardController, Level parent) {
        super(type, x, y, 1, parent);
        this.scoreboardController = scoreboardController;
        bonus = b;
        Image[] image = Component.getLoadedImages();

        if (type / 10 == 1 && type % 10 != 9) {
            this.setColor(color());
            this.setWidth(Game.unit);
            this.setHeight(Game.unit);
        } else if ((type % 100 / 10 == 2 || type % 100 % 10 == 9) && (type >= 10 && type % 100 < 30)) {
            int index = type > 100 ? type % 100 + 10 : type;
            InputStream e = getClass()
                    .getResourceAsStream("/images/" + this.getClass().getSimpleName() + type + ".png");
            if (image[index] == null && e != null)
                image[index] = new Image(e);
            this.setImage(image[index]);
            this.setHeight(Game.unit);
            this.setWidth(Game.unit * (type / 10) * (type / 10));
        }
    }

    /**
     * Constructeur de la classe Brick sans bonus.
     * 
     * <p>
     * Ce constructeur initialise une brique sans lui associer de bonus.
     * </p>
     * 
     * @param type   Le type de la brique.
     * @param x      La position en X de la brique.
     * @param y      La position en Y de la brique.
     * @param parent Le niveau parent auquel appartient la brique.
     */
    public Brick(int type, int x, int y, Level parent) {
        super(type, x, y, 1, parent);
    }

    /**
     * Retourne les points associés à la brique.
     * 
     * @return Le nombre de points.
     */
    public int getPoint() {
        return point;
    }

    /**
     * Supprime la brique du niveau et ajoute les points au tableau de scores.
     * Si un bonus est associé à la brique, il est appliqué.
     */
    public void remove() {
        scoreboardController.addScore(this.point); // Ajoute les points de la brique cassée au tableau de scores.
        super.getParentLevel().remove(((int) super.getPositionX() - Game.originX) / Game.unit,
                ((int) super.getPositionY() - Game.originY) / Game.unit);
        if (bonus != null) {
            bonus.applybonus();
        }
    }

    /**
     * Dessine la brique sur le canvas du jeu.
     * 
     * <p>
     * Si la brique est de type basique, elle est dessinée avec une couleur unie.
     * Si la brique utilise une image, celle-ci est affichée à la place.
     * </p>
     */
    @Override
    public void drawRacket() {
        double gap = this.getSpacing();
        if (this.getTypeId() / 10 == 1 && this.getTypeId() % 10 != 9) {
            Game.graphicsContext.setFill(this.color());
            Game.graphicsContext.fillRect(this.getPositionX() + gap, this.getPositionY() + gap, this.getWidth() - gap,
                    this.getHeight() - gap);
        } else if (this.getTypeId() / 10 == 2 || this.getTypeId() % 10 == 9) {
            Game.graphicsContext.drawImage(this.getImage(), this.getPositionX(), this.getPositionY());
        }
    }

    /**
     * Met à jour l'image de la brique en fonction de son type.
     */
    public void update() {
        this.setImage(Component.getLoadedImages()[this.getTypeId()]);
    }

    /**
     * Retourne la couleur de la brique en fonction de son type.
     * 
     * <p>
     * Chaque type de brique est associé à une couleur spécifique.
     * </p>
     * 
     * @return La couleur de la brique.
     */
    protected Color color() {
        switch (this.getTypeId() % 10) {
            case 0:
                return Color.WHITE;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.PURPLE;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.RED;
            case 5:
                return Color.YELLOW;
            case 6:
                return Color.ORANGE;
            case 7:
                return Color.LIGHTSKYBLUE;
            case 8:
                return Color.DARKGRAY;
            case 9:
                return Color.BROWN;
        }
        return null;
    }

    /**
     * Retourne le bonus associé à la brique.
     * 
     * @return Le bonus, ou {@code null} si aucun bonus n'est associé.
     */
    public Bonus getBonus() {
        return bonus;
    }

    /**
     * Retourne la couleur actuelle de la brique.
     * 
     * @return La couleur de la brique.
     */
    public Color getColor() {
        return color();
    }

    /**
     * Retourne le contrôleur du tableau des scores.
     * 
     * @return Le contrôleur du tableau des scores.
     */
    public ScoreboardController getScoreboardController() {
        return scoreboardController;
    }
}
