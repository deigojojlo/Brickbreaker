// package orgTest.openjfx.GUI;

// import static org.junit.Assert.assertNotEquals;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.openjfx.BrickBreaker;
// import org.openjfx.GUI.LevelSelectorController;
// import org.openjfx.GUI.LevelSelectorView;
// import org.testfx.framework.junit5.ApplicationTest;

// import javafx.application.Application;
// import javafx.scene.control.Button;
// import javafx.stage.Stage;

// public class LevelSelectorControllerTest {
// private static BrickBreaker mockBrickBreaker;

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
// mockBrickBreaker = new BrickBreaker() {
// @Override
// public void setLevel(int i) {
// };

// @Override
// public void setBackToSelector() {
// };

// };
// }

// @Test
// void create() {
// new LevelSelectorController(mockBrickBreaker); // => test
// setLevelButtonsHandler
// }

// @Test
// void sceneNotNull() {
// LevelSelectorController levelSelectorController = new
// LevelSelectorController(mockBrickBreaker);
// assertNotEquals(null, levelSelectorController.getScene());
// }
// }
