package org.openjfx.Game.GameComponent;

import org.openjfx.Game.GameClass.Level;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Classe abstraite représentant un composant du jeu (comme une raquette, une
 * balle ou une brique).
 * Cette classe contient les propriétés communes à tous les composants du jeu,
 * telles que la position,
 * la taille, la couleur, l'image, et le type. Elle sert de base pour les
 * classes spécifiques de chaque type de composant.
 */
public abstract class Component {

    /**
     * Largeur du composant (en pixels).
     */
    private double width;

    /**
     * Hauteur du composant (en pixels).
     */
    private double height;

    /**
     * Coordonnée en abscisse (axe X) du composant.
     */
    private double positionX;

    /**
     * Coordonnée en ordonnée (axe Y) du composant.
     */
    private double positionY;

    /**
     * Espace entre les composants (utilisé notamment pour les briques sous forme de
     * rectangle).
     */
    private double spacing;

    /**
     * Type du composant. Ce champ détermine le rôle ou le style du composant.
     * Valeurs possibles :
     * <ul>
     * <li>0 => Raquette</li>
     * <li>1 => Balle</li>
     * <li>x => Brique (par exemple, 1:1 ou 4:1)</li>
     * <li>y => Brique avec points de durabilité</li>
     * </ul>
     */
    private int typeId;

    /**
     * Couleur du composant, si elle existe. Sinon, ce champ est null.
     */
    private Color color;

    /**
     * Image associée au composant, si elle existe. Sinon, ce champ est null.
     */
    private Image image;

    /**
     * Niveau auquel appartient ce composant.
     */
    private Level level;

    /**
     * Tableau contenant les images chargées en mémoire pour une utilisation unique
     * dans le jeu.
     */
    public static Image[] loadedImages = new Image[40];

    /**
     * Constructeur de la classe `Component`.
     * 
     * @param type   Le type du composant (indiquant son rôle ou son style).
     * @param x      La coordonnée en abscisse (X) du composant.
     * @param y      La coordonnée en ordonnée (Y) du composant.
     * @param gap    L'espace entre les composants (utilisé pour les briques).
     * @param parent Le niveau auquel ce composant appartient.
     */
    public Component(int type, double x, double y, double gap, Level parent) {
        this.positionX = x;
        this.positionY = y;
        this.spacing = gap;
        this.typeId = type;
        this.image = null;
        this.level = parent;
    }

    /* Getters */

    /**
     * @return La coordonnée X du composant.
     */
    public double getPositionX() {
        return this.positionX;
    }

    /**
     * @return La coordonnée Y du composant.
     */
    public double getPositionY() {
        return this.positionY;
    }

    /**
     * @return La largeur du composant.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return La hauteur du composant.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return Le type du composant.
     */
    public int getTypeId() {
        return this.typeId;
    }

    /**
     * @return La couleur du composant, si elle existe.
     */
    public Color getColor() {
        return color;
    }

    /* Setters */

    /**
     * Définit la hauteur du composant.
     * 
     * @param h La nouvelle hauteur du composant.
     */
    public void setHeight(double h) {
        this.height = h;
    }

    /**
     * Définit la largeur du composant.
     * 
     * @param w La nouvelle largeur du composant.
     */
    public void setWidth(double w) {
        this.width = w;
    }

    /**
     * Définit la coordonnée X du composant.
     * 
     * @param x La nouvelle coordonnée X du composant.
     */
    public void setPositionX(double x) {
        this.positionX = x;
    }

    /**
     * Définit la coordonnée Y du composant.
     * 
     * @param y La nouvelle coordonnée Y du composant.
     */
    public void setPositionY(double y) {
        this.positionY = y;
    }

    /**
     * Définit la couleur du composant.
     * 
     * @param c La nouvelle couleur du composant.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Réduit le type du composant de 1 (utilisé pour certains composants comme les
     * briques).
     */
    public void setDecreaseType() {
        this.typeId--;
    }

    /**
     * Définit l'ensemble des images utilisées par les composants du jeu.
     * 
     * @param i Le tableau d'images à définir.
     */
    public static void setLoadedImages(Image[] i) {
        loadedImages = i;
    }

    /**
     * Définit l'image associée au composant.
     * 
     * @param im L'image à associer.
     */
    public void setImage(Image im) {
        this.image = im;
    }

    /**
     * @return L'image associée au composant.
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * @return Le tableau d'images utilisées dans le jeu.
     */
    public static Image[] getLoadedImages() {
        return loadedImages;
    }

    /**
     * @return L'espace entre les composants.
     */
    public double getSpacing() {
        return this.spacing;
    }

    /**
     * @return Le niveau auquel appartient ce composant.
     */
    public Level getParentLevel() {
        return this.level;
    }

    /**
     * Méthode abstraite qui permet de dessiner le composant à l'écran.
     */
    public abstract void drawRacket();
}
