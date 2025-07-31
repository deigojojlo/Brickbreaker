/*package orgTest.openjfx.Game.GameComponent.Bricks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameClass.ScoreboardController;
import org.openjfx.Game.GameComponent.Bricks.UnbreakableBrick;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UnbreakableBrickTest {

    private UnbreakableBrick brick;
    private ScoreboardController scoreboardController;
    private Level parentLevel;

    @BeforeEach
    void setUp() {
        // Initialisation des objets nécessaires pour les tests
        scoreboardController = mock(ScoreboardController.class);
        parentLevel = mock(Level.class);
        brick = new UnbreakableBrick(1, 10, 20, scoreboardController, parentLevel);
    }

    @Test
    void testConstructorInitializesFieldsCorrectly() {
        // Vérification que le constructeur initialise correctement les champs
        assertEquals(1, brick.getType(), "Le type doit être initialisé correctement.");
        assertEquals(10, brick.getX(), "La position X doit être initialisée correctement.");
        assertEquals(20, brick.getY(), "La position Y doit être initialisée correctement.");
        assertEquals(scoreboardController, brick.getScoreboardController(),
                "Le contrôleur de scores doit être initialisé correctement.");
        assertEquals(parentLevel, brick.getLevel(), "Le niveau parent doit être initialisé correctement.");
    }

    @Test
    void testRemoveDoesNothing() {
        // Appel de la méthode remove
        brick.remove();

        // Vérification que rien ne se passe (aucune exception ou interaction)
        assertDoesNotThrow(() -> brick.remove(), "La méthode remove() ne doit pas lever d'exception.");
    }

    @Test
    void testUpdateDoesNothing() {
        // Appel de la méthode update
        brick.update();

        // Vérification que rien ne se passe (aucune exception ou modification d'état)
        assertDoesNotThrow(() -> brick.update(), "La méthode update() ne doit pas lever d'exception.");
    }
}*/
