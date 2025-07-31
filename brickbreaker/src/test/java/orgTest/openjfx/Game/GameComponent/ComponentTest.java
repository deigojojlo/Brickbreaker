package orgTest.openjfx.Game.GameComponent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameComponent.Component;
import org.openjfx.Game.GameComponent.Bricks.Brick;

public class ComponentTest {
    private static Level level;

    @BeforeAll
    public static void avantClasse() {
        System.out.println("@BeforeClass");
    }

    @AfterAll
    public static void apresClasse() {
        System.out.println("@AfterClass");
    }

    @Test
    public void allTypeIsAccept() {
        for (int i = 0; i < 100; i++) {
            new Brick(i, 0, 0, level);
        }
        setDecreaseType_shouldReturnType_less1();
    }

    @Test
    public void setDecreaseType_shouldReturnType_less1() {
        Component c = null;
        c = new Brick(20, 0, 0, null);
        for (int i = 0; i < 100; i++) {
            c = new Brick(i, 0, 0, null);
            int type = c.getTypeId();
            c.setDecreaseType();
            assertEquals(type - 1, c.getTypeId());
        }
    }

}
