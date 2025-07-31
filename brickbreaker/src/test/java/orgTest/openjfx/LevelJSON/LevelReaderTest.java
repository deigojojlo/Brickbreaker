// package orgTest.openjfx.LevelJSON;

// import java.util.Random;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.openjfx.BrickBreaker;
// import org.openjfx.GUI.GUIGame;
// import org.openjfx.Game.GameClass.Game;
// import org.openjfx.Game.GameClass.Level;
// import org.openjfx.Game.GameClass.ScoreboardController;
// import org.openjfx.Game.GameClass.Time;
// import org.openjfx.HighscoreTXT.HighScore;
// import org.testfx.framework.junit5.ApplicationTest;

// import javafx.application.Application;
// import javafx.scene.layout.BorderPane;
// import javafx.stage.Stage;

// public class LevelReaderTest {
// private ScoreboardController scoreboardController;
// private GUIGame mockGuiGame;
// private BrickBreaker mockBrickBreaker;

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

// @BeforeEach
// public void create() {

// mockGuiGame = Mockito.mock(GUIGame.class);
// mockBrickBreaker = new BrickBreaker() {
// @Override
// public void setHome() {
// System.out.println("setHome() called");
// }
// };
// scoreboardController = new ScoreboardController(new BorderPane(),
// mockBrickBreaker);
// Time.createTimeline(() -> System.out.println("coucou"));
// HighScore.read();
// }

// @Test
// void testReadValidValue() {
// Level level = new Level(scoreboardController, mockGuiGame); // tempt rush
// // tempt campaign
// for (int i = 1; i < Game.totalLevels; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, false);
// }
// for (int i = 1; i < Game.totalLevels; i++) {
// try {
// level = new Level(scoreboardController, mockGuiGame, i, true);
// } catch (IllegalArgumentException e) {
// if (i != 0)
// throw e; // erreur normale car iscoop = true est incompatible avec level = 0
// deplus dans
// // ce cas guigame remet la page d'accueil pur indique l'erreur
// }
// }
// // tempt edit
// level = new Level(scoreboardController, mockGuiGame, "edit");
// }

// @Test
// void testReadInvalidValue() {

// try {
// Level level = new Level(scoreboardController, mockGuiGame, "unvalid");
// throw new RuntimeException();
// } catch (IllegalArgumentException e) {
// // test successfull
// }
// Random rand = new Random();
// Level level;
// for (int i = 1; i < 100; i++) {
// int x = rand.nextInt(Game.totalLevels, Integer.MAX_VALUE);
// x = rand.nextBoolean() ? x : -x;
// try {
// level = new Level(scoreboardController, mockGuiGame, x, rand.nextBoolean());
// throw new RuntimeException(); // dans le cas ou l'instruction precedente ne
// creer pas d'erreur laors il
// // y a un problème
// } catch (IllegalArgumentException e) {
// // si cette exception est levé alors tou va bien
// }
// }
// }
// }