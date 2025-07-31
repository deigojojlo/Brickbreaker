/*
 * package orgTest.openjfx.GUI;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals;
 * import static org.junit.jupiter.api.Assertions.assertFalse;
 * import static org.junit.jupiter.api.Assertions.assertNotNull;
 * import static org.junit.jupiter.api.Assertions.assertTrue;
 * import static org.mockito.ArgumentMatchers.any;
 * import static org.mockito.Mockito.mock;
 * import static org.mockito.Mockito.never;
 * import static org.mockito.Mockito.verify;
 * 
 * import java.util.ArrayList;
 * 
 * import org.junit.jupiter.api.BeforeAll;
 * import org.junit.jupiter.api.BeforeEach;
 * import org.junit.jupiter.api.Test;
 * import org.openjfx.BrickBreaker;
 * import org.openjfx.GUI.GUIGame;
 * import org.openjfx.Game.GameClass.Game;
 * import org.openjfx.Game.GameClass.Level;
 * import org.openjfx.Game.GameClass.Scoreboard.ScoreboardController;
 * import org.openjfx.Game.GameComponent.Racket;
 * import org.testfx.framework.junit5.ApplicationTest;
 * 
 * import javafx.application.Application;
 * import javafx.scene.Scene;
 * import javafx.scene.canvas.Canvas;
 * import javafx.scene.control.Label;
 * import javafx.scene.layout.GridPane;
 * import javafx.scene.layout.VBox;
 * import javafx.scene.paint.Color;
 * import javafx.stage.Stage;
 * 
 * public class GUIGameTest {
 * 
 * private static BrickBreaker brickbreakerMock;
 * 
 * public static class TestApp2 extends Application {
 * 
 * @Override
 * public void start(Stage primaryStage) throws Exception {
 * }
 * }
 * 
 * @BeforeAll
 * public static void initJFX() {
 * Thread t = new Thread("JavaFX Init") {
 * public void run() {
 * try {
 * ApplicationTest.launch(TestApp2.class, new String[0]);
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * }
 * };
 * t.setDaemon(true);
 * t.start();
 * }
 * 
 * @BeforeEach
 * public void initialisationMock() {
 * brickbreakerMock = mock(BrickBreaker.class);
 * System.setProperty("java.awt.headless", "true");
 * }
 * 
 * private class TestGUI extends GUIGame {
 * TestGUI() {
 * super(brickbreakerMock);
 * }
 * 
 * public BrickBreaker getFrame() {
 * return super.frame;
 * }
 * 
 * public ScoreboardController getScoreboardController() {
 * return super.scoreboardController;
 * }
 * 
 * public Level getLevel() {
 * return super.level;
 * }
 * 
 * public Canvas getCanvas() {
 * return super.canvas;
 * }
 * 
 * public GridPane getCenter() {
 * return super.center;
 * }
 * 
 * public VBox getBonusDisplay() {
 * return super.bonusDisplay;
 * }
 * 
 * public ArrayList<Label> getBonusList() {
 * return super.bonusList;
 * }
 * 
 * public void togglePlay() {
 * super.togglePlay();
 * }
 * 
 * public int getPlayStatus() {
 * return super.playStatus;
 * }
 * 
 * }
 * 
 * @Test
 * public void initialisationTest() {
 * TestGUI gui = new TestGUI();
 * assertNotNull(gui.getFrame());
 * assertNotNull(gui.getScoreboardController());
 * assertNotNull(gui.getLevel());
 * assertNotNull(gui.getCanvas());
 * assertNotNull(gui.getCenter());
 * assertNotNull(gui.getBonusDisplay());
 * assertNotNull(gui.getBonusList());
 * }
 * 
 * @Test
 * public void gettersAndSettersTest() {
 * TestGUI gui = new TestGUI();
 * 
 * // Test getScene
 * Scene scene = gui.getScene();
 * assertNotNull(scene);
 * 
 * // Test getBG
 * Color bg = gui.getBG();
 * assertNotNull(bg);
 * 
 * // Test setGameW
 * gui.setGameW(800);
 * assertEquals(800, Game.gameWidth);
 * assertTrue(Game.originX >= 0);
 * }
 * 
 * @Test
 * public void addBonusListTest() {
 * TestGUI gui = new TestGUI();
 * Label bonus = new Label("Test Bonus");
 * gui.addBonusList(bonus);
 * 
 * assertTrue(gui.getBonusList().contains(bonus));
 * assertEquals("-fx-font-size: 16px; -fx-text-fill: blue; -fx-alignment : center;"
 * , bonus.getStyle());
 * }
 * 
 * @Test
 * public void removeBonusListTest() {
 * TestGUI gui = new TestGUI();
 * Label bonus = new Label("Test Bonus");
 * gui.addBonusList(bonus);
 * gui.removeBonusList(bonus);
 * 
 * assertFalse(gui.getBonusList().contains(bonus));
 * }
 * 
 * @Test
 * public void removeSpecificBonusTest() {
 * TestGUI gui = new TestGUI();
 * Label bonus = new Label("1 ball supplémentaire");
 * gui.addBonusList(bonus);
 * 
 * gui.removeBonusList();
 * assertFalse(gui.getBonusList().contains(bonus));
 * }
 * 
 * @Test
 * public void clearBonusListTest() {
 * TestGUI gui = new TestGUI();
 * Label bonus1 = new Label("1 ball supplémentaire");
 * Label bonus2 = new Label("/!\\ controle inversé");
 * gui.addBonusList(bonus1);
 * gui.addBonusList(bonus2);
 * 
 * gui.clearBonusList();
 * assertTrue(gui.getBonusList().isEmpty());
 * assertTrue(gui.getBonusDisplay().getChildren().isEmpty());
 * }
 * 
 * @Test
 * public void updateBonusDisplayTest() {
 * TestGUI gui = new TestGUI();
 * Label bonus = new Label("Test Bonus");
 * 
 * // Test ajout
 * gui.addBonusList(bonus);
 * assertTrue(gui.getBonusDisplay().getChildren().contains(bonus));
 * 
 * // Test suppression
 * gui.removeBonusList(bonus);
 * assertFalse(gui.getBonusDisplay().getChildren().contains(bonus));
 * }
 * 
 * @Test
 * public void togglePlayTest() {
 * TestGUI gui = new TestGUI();
 * gui.setPlay(1); // En jeu
 * gui.togglePlay();
 * assertEquals(2, gui.getPlayStatus()); // Passe en pause
 * gui.togglePlay();
 * assertEquals(1, gui.getPlayStatus()); // Retourne en jeu
 * }
 * 
 * @Test
 * public void setPlayTest() {
 * TestGUI gui = new TestGUI();
 * gui.setPlay(3); // Définit l'état à victoire
 * assertEquals(3, gui.getPlayStatus());
 * gui.setPlay(4); // Définit l'état à fin de jeu
 * assertEquals(4, gui.getPlayStatus());
 * }
 * 
 * @Test
 * public void setHandlerTest() {
 * TestGUI gui = new TestGUI();
 * Racket racketMock = mock(Racket.class);
 * gui.setHandler(racketMock);
 * 
 * // Simuler une pression sur une touche (play puis pause)
 * gui.getScene().getOnKeyPressed().handle(new javafx.scene.input.KeyEvent(
 * javafx.scene.input.KeyEvent.KEY_PRESSED, "", "",
 * javafx.scene.input.KeyCode.ESCAPE, false, false,
 * false, false));
 * gui.getScene().getOnKeyPressed().handle(new javafx.scene.input.KeyEvent(
 * javafx.scene.input.KeyEvent.KEY_PRESSED, "", "",
 * javafx.scene.input.KeyCode.ESCAPE, false, false,
 * false, false));
 * assertEquals(2, gui.getPlayStatus()); // État passe à pause (via ESCAPE)
 * 
 * // Simuler un relâchement de touche
 * gui.getScene().getOnKeyReleased().handle(new javafx.scene.input.KeyEvent(
 * javafx.scene.input.KeyEvent.KEY_RELEASED, "", "",
 * javafx.scene.input.KeyCode.ESCAPE, false, false,
 * false, false));
 * }
 * 
 * @Test
 * public void runTest() {
 * TestGUI gui = new TestGUI();
 * gui.setPlay(1); // En jeu
 * gui.run();
 * }
 * 
 * @Test
 * public void clearTest() {
 * TestGUI gui = new TestGUI();
 * gui.clear();
 * // Vérifiez si l'écran est rempli avec la couleur de fond
 * assertEquals(
 * new Color(Game.backgroundCode / 255.0, Game.backgroundCode / 255.0,
 * Game.backgroundCode / 255.0,
 * 1.0),
 * Game.graphicsContext.getFill());
 * }
 * 
 * @Test
 * public void getLevelTest() {
 * TestGUI gui = new TestGUI();
 * assertNotNull(gui.getLevel()); // Vérifie que le niveau n'est pas nul
 * assertEquals(gui.getLevel(), gui.getLevel()); // Vérifie la correspondance
 * }
 * }
 */