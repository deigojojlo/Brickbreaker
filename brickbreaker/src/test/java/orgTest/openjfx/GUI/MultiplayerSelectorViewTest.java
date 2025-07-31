// package orgTest.openjfx.GUI;

// import static org.junit.Assert.assertNotEquals;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.openjfx.GUI.MultiplayerSelectorView;
// import org.testfx.framework.junit5.ApplicationTest;

// import javafx.application.Application;
// import javafx.scene.control.Button;
// import javafx.stage.Stage;

// public class MultiplayerSelectorViewTest {

// public static class TestApp2 extends Application {
// @Override
// public void start(Stage primaryStage) throws Exception {
// // Ne rien faire ici
// }
// }

// @BeforeAll
// public static void initJFX() {
// Thread t = new Thread("JavaFX Init") {
// public void run() {
// try {
// ApplicationTest.launch(TestApp2.class, new String[0]);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// };
// t.setDaemon(true);
// t.start();
// }

// private class TestMultiView extends MultiplayerSelectorView {
// protected Button getHomeButton() {
// return super.getHomeButton();
// }

// protected Button getCoopButton() {
// return super.getCoopButton();
// }
// }

// @Test
// void create() {
// new MultiplayerSelectorView();
// }

// @Test
// void sceneNotNull() {
// MultiplayerSelectorView multiplayerSelectorView = new
// MultiplayerSelectorView();
// assertNotEquals(null, multiplayerSelectorView.getScene());
// }

// @Test
// void backButtonIsValid() {
// TestMultiView multiplayerSelectorView = new TestMultiView();
// assertNotEquals(null, multiplayerSelectorView.getHomeButton());
// }

// @Test
// void coopButtonIsValid() {
// TestMultiView multiplayerSelectorView = new TestMultiView();
// assertNotEquals(null, multiplayerSelectorView.getCoopButton());
// }
// }
