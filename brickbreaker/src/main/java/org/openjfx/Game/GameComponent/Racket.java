package org.openjfx.Game.GameComponent;

import java.util.LinkedList;

import org.openjfx.Game.GameClass.Game;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Sound.Sound;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe Racket représente la raquette contrôlée par le joueur dans le jeu.
 */
public class Racket extends Component {
    /**
     * Vitesse actuelle de la raquette.
     */
    protected int movementSpeed;

    /**
     * Touche pour déplacer la raquette vers la droite.
     */
    protected KeyCode rightKeyBinding;

    /**
     * Touche pour déplacer la raquette vers la gauche.
     */
    protected KeyCode leftKeyBinding;

    /**
     * Booléen qui indique si la touche gauche est pressée.
     */
    protected boolean isLeftKeyPressed = false;

    /**
     * Booléen qui indique si la touche droite est pressée.
     */
    protected boolean isRightKeyPressed = false;

    /**
     * Booléen qui indique si une touche est pressée actuellement.
     */
    protected boolean isAnyKeyPressed = false;

    /**
     * Valeur de vitesse pour déplacer la raquette vers la gauche.
     */
    protected final int leftMovementDirection = -1;

    /**
     * Valeur de vitesse pour déplacer la raquette vers la droite.
     */
    protected final int rightMovementDirection = 1;

    /**
     * Vitesse maximale de la raquette.
     */
    protected int maximumSpeed;

    /**
     * Touche d'échappement pour quitter le jeu.
     */
    protected static KeyCode escapeKey = KeyCode.ESCAPE;

    /**
     * Constructeur de la raquette.
     *
     * @param x      la position x initiale de la raquette.
     * @param y      la position y initiale de la raquette.
     * @param right  la touche pour déplacer la raquette vers la droite.
     * @param left   la touche pour déplacer la raquette vers la gauche.
     * @param color  la couleur de la raquette.
     * @param parent Le niveau auquel la raquette appartient.
     */
    public Racket(int x, int y, KeyCode right, KeyCode left, Color color, Level parent) {
        super(0, x, y, 0, parent);
        this.movementSpeed = 0;
        this.setColor(color);
        this.setWidth(Game.unit * 8);
        this.setHeight(Game.unit);
        this.rightKeyBinding = right;
        this.leftKeyBinding = left;
        this.maximumSpeed = 2 * Game.speed;
    }

    /**
     * Constructeur de la raquette pour les tests.
     * 
     * @param x        la position x initiale de la raquette.
     * @param y        la position y initiale de la raquette.
     * @param right    la touche pour déplacer la raquette vers la droite.
     * @param left     la touche pour déplacer la raquette vers la gauche.
     * @param color    la couleur de la raquette.
     * @param maxSpeed la vitesse maximale de la raquette.
     * @param parent   Le niveau auquel la raquette appartient.
     */
    public Racket(int x, int y, KeyCode right, KeyCode left, Color color, int maxSpeed, Level parent) {
        super(0, x, y, 0, parent);
        this.movementSpeed = 0;
        this.setColor(color);
        this.setWidth(Game.unit * 8);
        this.setHeight(Game.unit);
        this.rightKeyBinding = right;
        this.leftKeyBinding = left;
        this.maximumSpeed = 2 * maxSpeed;
    }

    /**
     * Obtient la vitesse actuelle de la raquette.
     *
     * @return la vitesse de la raquette.
     */
    public int getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Obtient la vitesse maximale de la raquette.
     *
     * @return la vitesse maximale de la raquette.
     */
    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    /**
     * Définit la touche pour déplacer la raquette vers la droite.
     *
     * @param right le nouveau KeyCode pour la touche droite.
     */
    public void setRightKeyBinding(KeyCode right) {
        this.rightKeyBinding = right;
    }

    /**
     * Définit la touche pour déplacer la raquette vers la gauche.
     *
     * @param left le nouveau KeyCode pour la touche gauche.
     */
    public void setLeftKeyBinding(KeyCode left) {
        this.leftKeyBinding = left;
    }

    /**
     * Obtient la touche assignée pour aller à droite.
     *
     * @return le KeyCode pour la touche droite.
     */
    public KeyCode getRightKeyBinding() {
        return this.rightKeyBinding;
    }

    /**
     * Obtient la touche assignée pour aller à gauche.
     *
     * @return le KeyCode pour la touche gauche.
     */
    public KeyCode getLeftKeyBinding() {
        return this.leftKeyBinding;
    }

    /**
     * Définit la vitesse actuelle de la raquette.
     *
     * @param speed la nouvelle vitesse.
     */
    public void setMovementSpeed(int speed) {
        this.movementSpeed = speed;
    }

    /**
     * Met à jour l'état de pression des touches.
     *
     * @param b vrai si une touche est pressée, faux sinon.
     */
    public void setPressedState(boolean b) {
        isAnyKeyPressed = b;
        moveRacket(0, false);
    }

    /**
     * Getter pour isPressed
     * 
     * @return isPressed
     */
    public boolean getIsAnyKeyPressed() {
        return isAnyKeyPressed;
    }

    /**
     * Met à jour l'état de pression des touches (pour les tests).
     *
     * @param b                   vrai si une touche est pressée, faux sinon.
     * @param originX             la position x d'origine de la raquette.
     * @param gameWidth           la largeur du jeu.
     * @param isCollisionDetected vrai si collision avec une balle, faux sinon.
     */
    private void updatePressedStateForTests(boolean b, int originX, int gameWidth, boolean isCollisionDetected) {
        isAnyKeyPressed = b;
        moveForTests(0, false, originX, gameWidth, isCollisionDetected);
    }

    /**
     * Gère l'événement de pression d'une touche.
     *
     * @param event l'événement de touche pressée.
     */
    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() != escapeKey) {
            if (event.getCode() == leftKeyBinding && !isLeftKeyPressed) {
                isLeftKeyPressed = true;
            }
            if (event.getCode() == rightKeyBinding && !isRightKeyPressed) {
                isRightKeyPressed = true;
            }
            if (event.getCode() == rightKeyBinding && !isAnyKeyPressed) {
                setPressedState(true);
            }
            if (event.getCode() == leftKeyBinding && isAnyKeyPressed) {
                setPressedState(false);
            }
        }
        event.consume();
    }

    /**
     * Gère l'événement de pression d'une touche (pour les tests).
     *
     * @param event               l'événement de touche pressée.
     * @param originX             la position x d'origine de la raquette.
     * @param gameWidth           la largeur du jeu.
     * @param isCollisionDetected vrai si collision avec une balle, faux sinon.
     */
    public void onKeyPressedForTests(KeyEvent event, int originX, int gameWidth, boolean isCollisionDetected) {
        if (event.getCode() != escapeKey) {
            if (event.getCode() == leftKeyBinding && !isLeftKeyPressed) {
                isLeftKeyPressed = true;
            }
            if (event.getCode() == rightKeyBinding && !isRightKeyPressed) {
                isRightKeyPressed = true;
            }
            if (event.getCode() == rightKeyBinding && !isAnyKeyPressed) {
                updatePressedStateForTests(true, originX, gameWidth, isCollisionDetected);
            }
            if (event.getCode() == leftKeyBinding && isAnyKeyPressed) {
                updatePressedStateForTests(false, originX, gameWidth, isCollisionDetected);
            }
        }
        event.consume();
    }

    /**
     * Gère l'événement de relâchement d'une touche.
     *
     * @param event l'événement de touche relâchée.
     */
    public void onKeyReleased(KeyEvent event) {
        if (event.getCode() != escapeKey) {
            if (event.getCode() == leftKeyBinding) {
                isLeftKeyPressed = false;
                setPressedState(true);
            }
            if (event.getCode() == rightKeyBinding) {
                isRightKeyPressed = false;
                setPressedState(false);
            }
        }
    }

    /**
     * Gère l'événement de relâchement d'une touche (pour les tests).
     *
     * @param event               l'événement de touche relâchée.
     * @param originX             la position x d'origine de la raquette.
     * @param gameWidth           la largeur du jeu.
     * @param isCollisionDetected vrai si collision avec une balle, faux sinon.
     */
    public void onKeyReleasedForTests(KeyEvent event, int originX, int gameWidth, boolean isCollisionDetected) {
        if (event.getCode() != escapeKey) {
            if (event.getCode() == leftKeyBinding) {
                isLeftKeyPressed = false;
                updatePressedStateForTests(true, originX, gameWidth, isCollisionDetected);
            }
            if (event.getCode() == rightKeyBinding) {
                isRightKeyPressed = false;
                updatePressedStateForTests(false, originX, gameWidth, isCollisionDetected);
            }
        }
    }

    /**
     * Met à jour la position de la raquette en fonction des touches pressées.
     */
    public void updateRacketPosition() {
        if (isLeftKeyPressed && isRightKeyPressed) {
            moveRacket((isAnyKeyPressed) ? rightMovementDirection : leftMovementDirection, true);
        } else if (isLeftKeyPressed) {
            moveRacket(leftMovementDirection, true);
        } else if (isRightKeyPressed) {
            moveRacket(rightMovementDirection, true);
        } else {
            moveRacket(0, false);
        }
    }

    /**
     * Met à jour la position de la raquette en fonction des touches pressées (pour
     * les tests).
     * 
     * @param originX             la position x d'origine de la raquette.
     * @param gameWidth           la largeur du jeu.
     * @param isCollisionDetected vrai si collision avec une balle, faux sinon.
     */
    public void updatePositionForTests(int originX, int gameWidth, boolean isCollisionDetected) {
        if (isLeftKeyPressed && isRightKeyPressed) {
            moveForTests((isAnyKeyPressed) ? rightMovementDirection : leftMovementDirection, true, originX, gameWidth,
                    isCollisionDetected);
        } else if (isLeftKeyPressed) {
            moveForTests(leftMovementDirection, true, originX, gameWidth, isCollisionDetected);
        } else if (isRightKeyPressed) {
            moveForTests(rightMovementDirection, true, originX, gameWidth, isCollisionDetected);
        } else {
            moveForTests(0, false, originX, gameWidth, isCollisionDetected);
        }
    }

    /**
     * Déplace la raquette à une vitesse donnée avec ou sans accélération.
     *
     * @param speed        la vitesse de déplacement.
     * @param acceleration vrai si accélération, faux sinon.
     */
    public void moveRacket(int speed, boolean acceleration) {
        if (acceleration) {
            this.movementSpeed += (this.movementSpeed < maximumSpeed && this.movementSpeed > -maximumSpeed) ? speed : 0;
        } else {
            this.movementSpeed = speed;
        }

        LinkedList<Ball> activeBalls = null;
        if (super.getParentLevel() != null) {
            activeBalls = super.getParentLevel().getBallsInPlay();
        }
        boolean isCollisionDetected = false;

        double previousXPosition = getPositionX();
        double updatedXPosition = previousXPosition + speed;

        if (activeBalls != null)
            for (Ball ball : activeBalls) {
                isCollisionDetected = ball.racketCollisionTest(this);
                setPositionX(updatedXPosition);
                isCollisionDetected |= ball.racketCollisionTest(this);
                setPositionX(previousXPosition);
                if (isCollisionDetected) {
                    Sound.collisionRacketSound();
                    break;
                }
            }

        if (super.getPositionX() + this.movementSpeed < Game.originX && !isCollisionDetected) { // over to the left
            super.setPositionX(Game.originX);
        } else if (super.getPositionX() + this.movementSpeed + super.getWidth() > Game.originX + Game.gameWidth
                && !isCollisionDetected) { // over to the right
            super.setPositionX(Game.originX + Game.gameWidth - super.getWidth());
        } else if (!isCollisionDetected) {
            super.setPositionX(super.getPositionX() + this.movementSpeed);
        }
    }

    /**
     * Déplace la raquette à une vitesse donnée avec ou sans accélération (pour les
     * tests).
     *
     * @param speed               la vitesse de déplacement.
     * @param acceleration        vrai si accélération, faux sinon.
     * @param originX             la position x d'origine de la raquette.
     * @param gameWidth           la largeur du jeu.
     * @param isCollisionDetected vrai si collision avec une balle, faux sinon.
     */
    public void moveForTests(int speed, boolean acceleration, int originX, int gameWidth, boolean isCollisionDetected) {
        if (acceleration) {
            this.movementSpeed += (this.movementSpeed < maximumSpeed && this.movementSpeed > -maximumSpeed) ? speed : 0;
        } else {
            this.movementSpeed = speed;
        }

        if (super.getPositionX() + this.movementSpeed < originX && !isCollisionDetected) { // over to the left
            super.setPositionX(originX);
        } else if (super.getPositionX() + this.movementSpeed + super.getWidth() > originX + gameWidth
                && !isCollisionDetected) { // over
            // to
            // the
            // right
            super.setPositionX(originX + gameWidth - super.getWidth());
        } else if (!isCollisionDetected) {
            super.setPositionX(super.getPositionX() + this.movementSpeed);
        }

    }

    @Override
    /**
     * Dessine la raquette sur le canvas.
     */
    public void drawRacket() {
        double drawingGap = this.getSpacing();
        if (Game.graphicsContext != null) {
            Game.graphicsContext.setFill(this.getColor());
            Game.graphicsContext.fillRect(this.getPositionX() + drawingGap, this.getPositionY() + drawingGap,
                    this.getWidth() - drawingGap,
                    this.getHeight() - drawingGap);
        }
    }

    /**
     * Inverse les contrôles de la raquette.
     */
    public void toggleControlDirection() {
        KeyCode temp = this.leftKeyBinding;
        this.leftKeyBinding = this.rightKeyBinding;
        this.rightKeyBinding = temp;
    }

    /**
     * Définit les dimensions de la raquette.
     * 
     * @param w La nouvelle largeur de la raquette.
     * @param h La nouvelle hauteur de la raquette.
     */
    public void setRacketDimensions(int w, int h) {
        this.setWidth(w);
        this.setHeight(h);
    }

    /**
     * Ajuste les dimensions de la raquette en fonction des changements de largeur
     * et de hauteur.
     * Si la largeur ou la hauteur deviennent trop petites, elles sont ajustées à
     * une taille minimale.
     * 
     * @param widthChange  Le changement de largeur à appliquer.
     * @param heightChange Le changement de hauteur à appliquer.
     */
    public void adjustRacketSize(int widthChange, int heightChange) {

        int newWidth = (int) this.getWidth() + widthChange;
        int newHeight = (int) this.getHeight() + heightChange;

        final int minWidth = Game.unit * 2; // Largeur minimale
        final int minHeight = Game.unit; // Hauteur minimale

        this.setRacketDimensions(
                Math.max(minWidth, newWidth), // Applique la largeur minimale
                Math.max(minHeight, newHeight) // Applique la hauteur minimale
        );
    }

}
