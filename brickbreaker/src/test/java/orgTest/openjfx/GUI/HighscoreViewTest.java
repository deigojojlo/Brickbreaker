/* package orgTest.openjfx.GUI;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openjfx.GUI.HighscoreView;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HighscoreViewTest {

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
    private class TestHighscoreView extends HighscoreView {
        protected Button getHomeButton() {
            return super.getHomeButton();
        }

        protected Button getSoloButton() {
            return super.getSoloButton();
        }

        protected Button getMultiButton() {
            return super.getMultiButton();
        }

        protected Button getBackButton() {
            return super.getBackButton();
        }
        protected Scene getScene(){
            return super.getScene();
        }
    }

    @Test
    void create() {
        new HighscoreView();
    }

    @Test
    void sceneNotNull() {
        TestHighscoreView highscoreView = new TestHighscoreView();
        assertNotNull(highscoreView.getScene(), "La scène de HighscoreView ne doit pas être nulle.");
    }

    @Test
    void homeButtonIsValid() {
        TestHighscoreView highscoreView = new TestHighscoreView();
        assertNotNull(highscoreView.getHomeButton(), "Le bouton Home ne doit pas être nul.");
    }

    @Test
    void soloButtonIsValid() {
        TestHighscoreView highscoreView = new TestHighscoreView();
        assertNotNull(highscoreView.getSoloButton(), "Le bouton Solo ne doit pas être nul.");
    }

    @Test
    void multiButtonIsValid() {
        TestHighscoreView highscoreView = new TestHighscoreView();
        assertNotNull(highscoreView.getMultiButton(), "Le bouton Multi ne doit pas être nul.");
    }

    @Test
    void backButtonIsValid() {
        TestHighscoreView highscoreView = new TestHighscoreView();
        assertNotNull(highscoreView.getBackButton(), "Le bouton Back ne doit pas être nul.");
    }
}
*/