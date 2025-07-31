/* package orgTest.openjfx.GUI;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openjfx.BrickBreaker;
import org.openjfx.GUI.HighscoreController;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HighscoreControllerTest {
    private static BrickBreaker mockBrickBreaker;

    public static class TestApp2 extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            // Ne rien faire ici
        }
    }

    @BeforeAll
    public static void initJFX() {
        Thread t = new Thread("JavaFX Init") {
            public void run() {
                try {
                    ApplicationTest.launch(TestApp2.class, new String[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.setDaemon(true);
        t.start();
        mockBrickBreaker = mock(BrickBreaker.class);
    }

    @Test
    void create() {
        HighscoreController controller = new HighscoreController(mockBrickBreaker);
        assertNotNull(controller); // Vérifie que le contrôleur est créé avec succès.
    }

    @Test
    void sceneNotNull() {
        HighscoreController controller = new HighscoreController(mockBrickBreaker);
        assertNotNull(controller.getScene()); // Vérifie que la scène n'est pas nulle.
    }
}*/
