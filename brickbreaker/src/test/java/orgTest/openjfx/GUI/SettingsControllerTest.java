/* package orgTest.openjfx.GUI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openjfx.BrickBreaker;
import org.openjfx.GUI.SettingsController;
import org.openjfx.GUI.SettingsView;
import org.openjfx.Game.GameClass.Game;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingsControllerTest extends ApplicationTest {
    
    private static BrickBreaker mockBrickBreaker;
    private static AnchorPane mockAnchorPane;
    
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
        mockAnchorPane = mock(AnchorPane.class);
    }

    @Test
    void createSettingsController() {
        // Créer un contrôleur de paramètres
        SettingsController controller = new SettingsController(mockBrickBreaker, mockAnchorPane);
        
        // Vérifier que le contrôleur a été créé avec succès
        assertNotNull(controller);
    }

    @Test
    void sceneNotNull() {
        // Créer un contrôleur de paramètres
        SettingsController controller = new SettingsController(mockBrickBreaker, mockAnchorPane);
        
        // Vérifier que la scène associée au contrôleur n'est pas nulle
        assertNotNull(controller.getScene());
    }
}
*/