// package orgTest.openjfx.GUI;

// import static org.junit.Assert.assertNotEquals;

// import org.junit.Test;
// import org.junit.jupiter.api.BeforeAll;
// import org.openjfx.BrickBreaker;
// import org.openjfx.GUI.MultiplayerSelectorController;
// import org.testfx.framework.junit5.ApplicationTest;

// import javafx.application.Application;
// import javafx.stage.Stage;

// public class MultiplayerSelectorControllerTest {
// static BrickBreaker mockBrickBreaker;

// public static class TestApp2 extends Application {
// @Override
// public void start(Stage primaryStage) throws Exception {
// mockBrickBreaker = new BrickBreaker() {
// @Override
// public void setLevel(int i) {
// };

// @Override
// public void setBackToSelector() {
// };

// };
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

// @Test
// void create() {
// new MultiplayerSelectorController(mockBrickBreaker); // => test
// setButtonHandler
// }

// @Test
// void sceneIsValid() {
// MultiplayerSelectorController multiplayerSelectorController = new
// MultiplayerSelectorController(
// mockBrickBreaker);
// assertNotEquals(null, multiplayerSelectorController.getScene());
// }
// }
