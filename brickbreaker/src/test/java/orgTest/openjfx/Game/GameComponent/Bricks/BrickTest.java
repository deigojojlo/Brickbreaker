/*package orgTest.openjfx.Game.GameComponent.Bricks;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.Game.GameClass.ScoreboardController;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameComponent.Bricks.Bonus.Bonus;
import org.openjfx.Game.GameComponent.Bricks.Brick;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

class BrickTest {

    private ScoreboardController scoreboardControllerMock;
    private Level levelMock;
    private Bonus bonusMock;
    private Brick brick;

    @BeforeEach
    void setUp() {
        // Initialisation des mocks
        scoreboardControllerMock = mock(ScoreboardController.class);
        levelMock = mock(Level.class);
        bonusMock = mock(Bonus.class);
        // Activer le mode headless
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    void testConstructorWithBonus() {
        // Création d'une brique de type 21 (par exemple)
        Brick brick = new Brick(21, 10, 10, bonusMock, scoreboardControllerMock, levelMock);

        // Vérification des valeurs initiales
        assertEquals(10, brick.getX());
        assertEquals(10, brick.getY());
        assertEquals(bonusMock, brick.getBonus());
        assertNotNull(brick);
    }

    @Test
    void testConstructorWithoutBonus() {
        // Création d'une brique sans bonus
        Brick brick = new Brick(11, 20, 20, levelMock);

        // Vérification des valeurs initiales
        assertEquals(20, brick.getX());
        assertEquals(20, brick.getY());
        assertNull(brick.getBonus());
    }

    @Test
    void testGetPoint() {
        // Création d'une brique avec 5 points
        Brick brick = new Brick(11, 5, 5, bonusMock, scoreboardControllerMock, levelMock);

        // Vérification du nombre de points
        assertEquals(5, brick.getPoint());
    }

    @Test
    void testRemove() {
        // Création d'une brique avec un bonus et un contrôleur de scoreboard
        Brick brick = new Brick(11, 5, 5, bonusMock, scoreboardControllerMock, levelMock);

        // Appel de la méthode remove et vérification des interactions
        brick.remove();

        // Vérification que la méthode remove de level a été appelée
        verify(levelMock, times(1)).remove(anyInt(), anyInt());

        // Vérification que le score a été mis à jour
        verify(scoreboardControllerMock, times(1)).addScore(5);

        // Vérification que le bonus a été appliqué
        verify(bonusMock, times(1)).applybonus();
    }

    @Test
    public void testDraw() {
        // Création de la brique avec l'image
        Brick brick = new Brick(21, 100, 100, bonusMock, scoreboardControllerMock, levelMock);

        // Mise à jour de la brique et vérification de l'image
        brick.update();
        assertNotNull(brick.getImg());
    }

    @Test
    void testUpdate() {
        // Création d'une brique de type 21
        Brick brick = new Brick(21, 10, 10, bonusMock, scoreboardControllerMock, levelMock);

        // Appel de la méthode update pour mettre à jour l'image
        brick.update();

        // Vérification que l'image a bien été mise à jour (le type 21 devrait
        // correspondre à une image spécifique)
        assertNotNull(brick.getImg());
    }

    @Test
    void testColor() {
        // Test de la couleur d'une brique de type 11 (devrait être bleu)
        Brick brick = new Brick(11, 10, 10, bonusMock, scoreboardControllerMock, levelMock);

        // Vérifier la couleur de la brique
        assertEquals(brick.getColor(), javafx.scene.paint.Color.BLUE);

        // Test de la couleur pour un autre type, par exemple type 4 (devrait être
        // rouge)
        brick = new Brick(14, 10, 10, bonusMock, scoreboardControllerMock, levelMock);
        assertEquals(brick.getColor(), javafx.scene.paint.Color.RED);
    }

    @Test
    void testGettersAndSetters() {
        // Créer une brique avec un type
        Brick brick = new Brick(11, 10, 10, bonusMock, scoreboardControllerMock, levelMock);

        // Tester les setters et getters pour la position
        brick.setX(30);
        brick.setY(40);
        assertEquals(30, brick.getX());
        assertEquals(40, brick.getY());

        // Tester les setters et getters pour la taille
        brick.setW(40);
        brick.setH(50);
        assertEquals(40, brick.getW());
        assertEquals(50, brick.getH());
    }
}*/
