// package orgTest.openjfx.GUI;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotEquals;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.openjfx.GUI.LevelSelectorView;
// import org.openjfx.Game.GameClass.Game;
// import org.testfx.framework.junit5.ApplicationTest;

// import javafx.application.Application;
// import javafx.scene.control.Button;
// import javafx.stage.Stage;

// public class LevelSelectorViewTest {

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

// private class TestSelector extends LevelSelectorView {
// TestSelector() {
// super();
// }

// Button[] getLevelsButtons() {
// return super.getLevelButtons();
// }

// protected Button getBackButton() {
// return super.getBackButton();
// }

// }

// @Test
// void create() {
// new LevelSelectorView();
// }

// @Test
// void sceneNotNull() {
// LevelSelectorView view = new LevelSelectorView();
// assertNotEquals(null, view.getScene());
// }

// @Test
// void buttonListIsValid() {
// LevelSelectorView view = new TestSelector();
// assertEquals(Game.totalLevels, ((TestSelector)
// view).getLevelsButtons().length);
// }

// @Test
// void backButtonIsValid() {
// LevelSelectorView view = new TestSelector();
// assertNotEquals(null, ((TestSelector) view).getBackButton());
// }
// }
