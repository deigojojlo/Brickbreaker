/* package orgTest.openjfx.GUI;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openjfx.GUI.ModeSelectorView;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ModeSelectorViewTest {

    public static class TestApp extends Application {
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
                    ApplicationTest.launch(TestApp.class, new String[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.setDaemon(true);
        t.start();
    }

    // Classe interne pour accéder aux méthodes protégées
    private class TestModeSelectorView extends ModeSelectorView {
        protected Button getHomeButton() {
            return super.getHomeButton();
        }

        protected Button getRushButton() {
            return super.getRushButton();
        }

        protected Button getCampaignButton() {
            return super.getCampaignButton();
        }
    }

    @Test
    void create() {
        new ModeSelectorView();
    }

    @Test
    void sceneNotNull() {
        ModeSelectorView modeSelectorView = new ModeSelectorView();
        assertNotNull(modeSelectorView.getScene(), "La scène de ModeSelectorView ne doit pas être nulle.");
    }

    @Test
    void homeButtonIsValid() {
        TestModeSelectorView modeSelectorView = new TestModeSelectorView();
        assertNotNull(modeSelectorView.getHomeButton(), "Le bouton Home ne doit pas être nul.");
    }

    @Test
    void rushButtonIsValid() {
        TestModeSelectorView modeSelectorView = new TestModeSelectorView();
        assertNotNull(modeSelectorView.getRushButton(), "Le bouton Rush ne doit pas être nul.");
    }

    @Test
    void campaignButtonIsValid() {
        TestModeSelectorView modeSelectorView = new TestModeSelectorView();
        assertNotNull(modeSelectorView.getCampaignButton(), "Le bouton Campagne ne doit pas être nul.");
    }
}
    */
