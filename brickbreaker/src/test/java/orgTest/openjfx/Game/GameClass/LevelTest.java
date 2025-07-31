// package orgTest.openjfx.Game.GameClass;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.openjfx.BrickBreaker;
// import org.openjfx.GUI.GUIGame;
// import org.openjfx.Game.GameClass.Game;
// import org.openjfx.Game.GameClass.Level;
// import org.openjfx.Game.GameClass.Time;
// import org.openjfx.Game.GameClass.Scoreboard.ScoreboardController;
// import org.openjfx.HighscoreTXT.HighScore;
// import org.testfx.framework.junit5.ApplicationTest;

// import javafx.application.Application;
// import javafx.scene.canvas.Canvas;
// import javafx.scene.layout.BorderPane;
// import javafx.stage.Stage;

// public class LevelTest {
// // à noté les tests de contruction et de lecture de Level sont testé par
// // levelReader

// private ScoreboardController scoreboardController;
// private GUIGame mockGuiGame;
// private BrickBreaker mockBrickBreaker;

// public static class TestApp3 extends Application {
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
// ApplicationTest.launch(TestApp3.class, new String[0]);
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
// void racket2notCreated() {
// Level level = new Level(scoreboardController, mockGuiGame); // rush => solo

// assertEquals(null, level.getPlayer2Racket());

// for (int i = 1; i < Game.totalLevels + 1; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, false);
// assertEquals(null, level.getPlayer2Racket());
// }

// level = new Level(scoreboardController, mockGuiGame, "edit");
// assertEquals(null, level.getPlayer2Racket());
// }

// @Test
// void racket2Created() {
// Level level;
// for (int i = 1; i < Game.totalLevels + 1; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, true);
// assertNotEquals(null, level.getPlayer2Racket());
// }
// }

// @Test
// void racket1Created() {
// Level level = new Level(scoreboardController, mockGuiGame); // rush => solo
// assertNotEquals(null, level.getPlayer1Racket());

// for (int i = 1; i < Game.totalLevels + 1; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, false);
// assertNotEquals(null, level.getPlayer1Racket());
// level = new Level(scoreboardController, mockGuiGame, i, true);
// assertNotEquals(null, level.getPlayer1Racket());
// }
// level = new Level(scoreboardController, mockGuiGame, "edit");
// assertNotEquals(null, level.getPlayer1Racket());
// }

// @Test
// void currentLevelNotNull() {
// Level level = new Level(scoreboardController, mockGuiGame); // rush => solo
// assertNotEquals(null, level.getLevelGrid());
// for (int i = 1; i < Game.totalLevels + 1; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, true);
// assertNotEquals(null, level.getLevelGrid());
// level = new Level(scoreboardController, mockGuiGame, i, false);
// assertNotEquals(null, level.getLevelGrid());
// }
// // level = new Level(scoreboardController, mockGuiGame, "edit");
// // assertNotEquals(null, level.getLevel());
// }

// @Test
// void nbOfBrick() {
// Level level = new Level(scoreboardController, mockGuiGame); // rush => solo
// assertNotEquals(null, level.getLevelGrid());
// for (int i = 1; i < Game.totalLevels + 1; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, true);
// if (level.getRemainingBrickCount() < 0)
// throw new RuntimeException();
// level = new Level(scoreboardController, mockGuiGame, i, false);
// if (level.getRemainingBrickCount() < 0)
// throw new RuntimeException();
// }

// level = new Level(scoreboardController, mockGuiGame, "edit");
// if (level.getRemainingBrickCount() < 0)
// throw new RuntimeException();
// }

// @Test
// void isRush() {
// Level level = new Level(scoreboardController, mockGuiGame); // rush => solo
// assertEquals(true, level.isRushModeEnabled());
// for (int i = 1; i < Game.totalLevels; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, true);
// assertEquals(false, level.isRushModeEnabled());
// level = new Level(scoreboardController, mockGuiGame, i, false);
// assertEquals(false, level.isRushModeEnabled());
// }

// level = new Level(scoreboardController, mockGuiGame, "edit");
// assertEquals(false, level.isRushModeEnabled());
// }

// @Test
// void isCoop() {
// Level level = new Level(scoreboardController, mockGuiGame); // rush => solo
// assertEquals(false, level.isCooperativeModeEnabled());
// for (int i = 1; i < Game.totalLevels; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, true);
// assertEquals(true, level.isCooperativeModeEnabled());
// level = new Level(scoreboardController, mockGuiGame, i, false);
// assertEquals(false, level.isCooperativeModeEnabled());
// }

// level = new Level(scoreboardController, mockGuiGame, "edit");
// assertEquals(false, level.isCooperativeModeEnabled());
// }

// @Test
// void ballList() {
// Level level = new Level(scoreboardController, mockGuiGame); // rush => solo
// assertNotEquals(null, level.getBallsInPlay());
// assertNotEquals(0, level.getBallsInPlay());
// for (int i = 1; i < Game.totalLevels; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, true);
// assertNotEquals(null, level.getBallsInPlay());
// assertNotEquals(0, level.getBallsInPlay());
// level = new Level(scoreboardController, mockGuiGame, i, false);
// assertNotEquals(null, level.getBallsInPlay());
// assertNotEquals(0, level.getBallsInPlay());
// }

// level = new Level(scoreboardController, mockGuiGame, "edit");
// assertNotEquals(null, level.getBallsInPlay());
// assertNotEquals(0, level.getBallsInPlay());
// }

// @Test
// void removeBall() {
// Game.graphicsContext = (new Canvas(Integer.MAX_VALUE,
// Integer.MAX_VALUE)).getGraphicsContext2D();
// scoreboardController.setLife(Game.totalLevels * 2 + 10);
// Level level = new Level(scoreboardController, mockGuiGame); // rush => solo
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(1, level.getBallsInPlay().size());
// for (int i = 1; i < Game.totalLevels; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, true);
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(1, level.getBallsInPlay().size());
// level = new Level(scoreboardController, mockGuiGame, i, false);
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(1, level.getBallsInPlay().size());
// }

// level = new Level(scoreboardController, mockGuiGame, "edit");
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(1, level.getBallsInPlay().size());

// scoreboardController.setLife(1);
// level = new Level(scoreboardController, mockGuiGame); // rush => solo
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(0, level.getBallsInPlay().size());
// for (int i = 1; i < Game.totalLevels; i++) {
// scoreboardController.setLife(1);
// level = new Level(scoreboardController, mockGuiGame, i, true);
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(0, level.getBallsInPlay().size());
// scoreboardController.setLife(1);
// level = new Level(scoreboardController, mockGuiGame, i, false);
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(0, level.getBallsInPlay().size());
// }

// scoreboardController.setLife(1);
// level = new Level(scoreboardController, mockGuiGame, "edit");
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(0, level.getBallsInPlay().size());

// }

// @Test
// void addBall() {
// Game.graphicsContext = (new Canvas(Integer.MAX_VALUE,
// Integer.MAX_VALUE)).getGraphicsContext2D();
// scoreboardController.setLife(Game.totalLevels * 2 + 10);
// Level level = new Level(scoreboardController, mockGuiGame); // rush => solo
// level.addBall();
// assertEquals(2, level.getBallsInPlay().size());
// for (int i = 1; i < Game.totalLevels; i++) {
// level = new Level(scoreboardController, mockGuiGame, i, true);
// level.addBall();
// assertEquals(2, level.getBallsInPlay().size());
// level = new Level(scoreboardController, mockGuiGame, i, false);
// level.addBall();
// assertEquals(2, level.getBallsInPlay().size());
// }

// level = new Level(scoreboardController, mockGuiGame, "edit");
// level.addBall();
// assertEquals(2, level.getBallsInPlay().size());

// scoreboardController.setLife(1);
// level = new Level(scoreboardController, mockGuiGame); // rush => solo
// level.addBall();
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(1, level.getBallsInPlay().size());
// for (int i = 1; i < Game.totalLevels; i++) {
// scoreboardController.setLife(1);
// level = new Level(scoreboardController, mockGuiGame, i, true);
// level.addBall();
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(1, level.getBallsInPlay().size());
// scoreboardController.setLife(1);
// level = new Level(scoreboardController, mockGuiGame, i, false);
// level.addBall();
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(1, level.getBallsInPlay().size());
// }

// scoreboardController.setLife(1);
// level = new Level(scoreboardController, mockGuiGame, "edit");
// level.addBall();
// level.removeBall(level.getBallsInPlay().getFirst());
// assertEquals(1, level.getBallsInPlay().size());

// }
// }
