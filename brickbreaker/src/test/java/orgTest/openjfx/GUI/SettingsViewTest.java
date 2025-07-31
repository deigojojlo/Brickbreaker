/*package orgTest.openjfx.GUI;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openjfx.GUI.SettingsView;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsViewTest {

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

    // Classe interne pour accéder aux méthodes protégées de SettingsView
    private class TestSettingsView extends SettingsView {
        public TestSettingsView() {
            super(new javafx.scene.layout.AnchorPane());
        }

        protected Button getHomeButton() {
            return super.getHomeButton();
        }

        protected Button getRacketRightButtonP1() {
            return super.racketRightButtonP1;
        }

        protected Button getRacketLeftButtonP1() {
            return super.racketLeftButtonP1;
        }

        protected Button getRacketRightButtonP2() {
            return super.racketRightButtonP2;
        }

        protected Button getRacketLeftButtonP2() {
            return super.racketLeftButtonP2;
        }
    }

    @Test
    void create() {
        new SettingsView(new javafx.scene.layout.AnchorPane());
    }

    @Test
    void sceneNotNull() {
        SettingsView settingsView = new SettingsView(new javafx.scene.layout.AnchorPane());
        assertNotNull(settingsView.getScene(), "La scène de SettingsView ne doit pas être nulle.");
    }

    @Test
    void homeButtonIsValid() {
        TestSettingsView settingsView = new TestSettingsView();
        assertNotNull(settingsView.getHomeButton(), "Le bouton Home ne doit pas être nul.");
    }

    @Test
    void racketRightButtonP1IsValid() {
        TestSettingsView settingsView = new TestSettingsView();
        assertNotNull(settingsView.getRacketRightButtonP1(), "Le bouton de raquette droite pour P1 ne doit pas être nul.");
    }

    @Test
    void racketLeftButtonP1IsValid() {
        TestSettingsView settingsView = new TestSettingsView();
        assertNotNull(settingsView.getRacketLeftButtonP1(), "Le bouton de raquette gauche pour P1 ne doit pas être nul.");
    }

    @Test
    void racketRightButtonP2IsValid() {
        TestSettingsView settingsView = new TestSettingsView();
        assertNotNull(settingsView.getRacketRightButtonP2(), "Le bouton de raquette droite pour P2 ne doit pas être nul.");
    }

    @Test
    void racketLeftButtonP2IsValid() {
        TestSettingsView settingsView = new TestSettingsView();
        assertNotNull(settingsView.getRacketLeftButtonP2(), "Le bouton de raquette gauche pour P2 ne doit pas être nul.");
    }
}*/
