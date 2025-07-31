/*package orgTest.openjfx.Game.GameComponent.Bricks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameClass.ScoreboardController;
import org.openjfx.Game.GameComponent.Bricks.DurabilityBrick;
import org.openjfx.Game.GameComponent.Bricks.Bonus.Bonus;

import javafx.scene.image.Image;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class DurabilityBrickTest {

    private DurabilityBrick brick;
    private ScoreboardController scoreboardController;
    private Level level;
    private Bonus bonus;

    @BeforeEach
    void setUp() {
        // Initialisation des mocks ou objets nécessaires pour le test
        scoreboardController = mock(ScoreboardController.class);
        level = mock(Level.class);
        bonus = mock(Bonus.class);
        brick = new DurabilityBrick(727, 0, 0, 5, bonus, scoreboardController, level);
    }

    // Test du constructeur
    @Test
    void testConstructor() {
        assertNotNull(brick);
        assertEquals(5, brick.getDurability());
    }

    // Test des getters
    @Test
    void testGetDurability() {
        assertEquals(5, brick.getDurability());
    }

    // Test de la méthode remove()
    @Test
    void testRemoveDecreasesDurability() {
        brick.removetest();
        assertEquals(4, brick.getDurability());
    }

    // Test de la méthode update()
    @Test
    void testUpdate() {
        // Vérification de l'effet de la méthode update()
        brick.updatetest();
        // Tester que la méthode update ne modifie pas l'état de manière indésirable
        assertEquals(5, brick.getDurability());
    }

    // Test de la méthode type()
    @Test
    void testType() {
        brick.type();
        assertNotNull(brick.getImg()); // S'assurer que l'image n'est pas null
    }

    // Test de la mise à jour de l'image avec le type de la brique
    @Test
    void testImageLoading() {
        brick.type();
        assertNotNull(brick.getImg());
    }

    // Test de l'optimisation des images
    @Test
    void testImageCaching() {
        brick.type(); // Charge l'image pour la première fois
        Image firstImage = brick.getImg();
        brick.type(); // Charge à nouveau la même image
        assertSame(firstImage, brick.getImg()); // S'assurer que l'image est mise en cache
    }

}
*/