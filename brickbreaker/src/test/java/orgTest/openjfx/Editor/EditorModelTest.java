package orgTest.openjfx.Editor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.Editor.EditorModel;

class EditorModelTest {

    private EditorModel model;

    @BeforeEach
    void setUp() {
        model = new EditorModel();
    }

    @Test
    void testInitialValues() {
        assertEquals(1.0, model.getAcceleration(), "Default acceleration should be 1.0");
        assertEquals(1.0, model.getRate(), "Default rate should be 1.0");
        assertEquals(1.0, model.getMaxSpeed(), "Default max speed should be 1.0");
        assertNotNull(model.getMap(), "Map should not be null");
        assertEquals(20, model.getMap().length, "Default map should have 20 rows");
        assertEquals(50, model.getMap()[0].length, "Default map should have 50 columns");
    }

    @Test
    void testClick() {
        int[][] map = model.getMap();

        // Check initial value at a specific position
        int initialValue = map[5][5];
        assertEquals(0, initialValue, "Initial value at (5, 5) should be 0");

        // Perform click operation
        model.click(5, 5);
        int afterClickValue = model.getMap()[5][5];
        assertEquals(1, afterClickValue, "Value at (5, 5) should be incremented to 1");

        // Perform multiple clicks
        for (int i = 0; i < 9; i++) {
            model.click(5, 5);
        }
        assertEquals(9, model.getMap()[5][5], "Value at (5, 5) should not exceed 9");

        // Clicking beyond the limit should not increase the value
        model.click(5, 5);
        assertEquals(9, model.getMap()[5][5], "Value at (5, 5) should remain 9 after max clicks");
    }

    @Test
    void testSetAndGetAttributes() {
        model.setAcceleration(2.5);
        assertEquals(2.5, model.getAcceleration(), "Acceleration should be updated to 2.5");

        model.setRate(3.0);
        assertEquals(3.0, model.getRate(), "Rate should be updated to 3.0");

        model.setMaxSpeed(5.0);
        assertEquals(5.0, model.getMaxSpeed(), "Max speed should be updated to 5.0");
    }

    @Test
    void testSetAndGetMap() {
        int[][] newMap = new int[10][10];
        model.setMap(newMap);
        assertSame(newMap, model.getMap(), "The map should be updated to the new instance");
        assertEquals(10, model.getMap().length, "The new map should have 10 rows");
        assertEquals(10, model.getMap()[0].length, "The new map should have 10 columns");
    }

}
