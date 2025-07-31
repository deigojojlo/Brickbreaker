// package orgTest.openjfx.Game.GameClass;

// import org.openjfx.Game.GameClass.Scoreboard.ScoreboardView;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.lang.reflect.Method;
// import javafx.application.Platform;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.layout.BorderPane;

// public class ScoreboardViewTest {

// static class TestScoreboardView extends ScoreboardView {

// private ScoreboardView scoreboardView;

// public TestScoreboardView() {
// super(new BorderPane());
// scoreboardView = this;
// }

// public String getLevelValue() {
// return super.levelLabelValue.getText();
// }

// public String getTimeValue() {
// return super.timeLabelValue.getText();
// }

// public String getLifeValue() {
// return super.lifeLabelValue.getText();
// }

// public String getScoreValue() {
// return super.scoreLabelValue.getText();
// }

// public void setScore(int i) {
// super.setScore(i);
// }

// public void setTime(int i, int j) {
// super.setTime(i, j);
// }

// public void setLevel(int i) {
// super.setLevel(i);
// }

// public void setLife(int i) {
// super.setLife(i);
// }

// public String getRemainingBricksValue() {
// return super.remainingBricksValue.getText();
// }

// public Button getHomeButton() {
// return super.getHomeButton();
// }
// }

// private TestScoreboardView testView;

// @BeforeAll
// public static void initToolkit() {
// Platform.startup(() -> {
// });
// }

// @BeforeEach
// public void setup() throws Exception {
// testView = new TestScoreboardView();
// }

// @Test
// public void testInitialValues() {
// try {
// assertEquals(85, testView.getScoreboardHeight());
// assertEquals("1", testView.getLevelValue());
// assertEquals("3", testView.getLifeValue());
// assertEquals("0", testView.getTimeValue());
// } catch (Exception e) {
// throw new RuntimeException();
// }
// }

// @Test
// public void testUpdateScore() {
// try {
// int newScore = 100;
// this.testView.setScore(newScore);
// assertEquals(String.valueOf(newScore),
// testView.getScoreValue(),
// "Score should be " + newScore);
// } catch (Exception e) {
// e.printStackTrace();
// throw new RuntimeException();
// }
// }

// @Test
// public void testUpdateLife() {
// try {
// int newLife = 2;
// testView.setLife(newLife);
// assertEquals(String.valueOf(newLife),
// testView.getLifeValue(), "Life should be " + newLife);
// } catch (Exception e) {
// throw new RuntimeException();
// }
// }

// @Test
// public void testUpdateLevel() {
// try {
// int newLevel = 3;
// this.testView.setLevel(newLevel);
// assertEquals(String.valueOf(newLevel),
// testView.getLevelValue(),
// "Level should be " + newLevel);
// } catch (Exception e) {
// throw new RuntimeException();
// }
// }

// @Test
// public void testUpdateTime() {
// try {
// testView.setTime(5, 30);
// assertEquals("5:30", testView.getTimeValue(),
// "Time should be 5:30");

// testView.setTime(10, 5);
// assertEquals("10:05", testView.getTimeValue(),
// "Time should be 10:05");
// } catch (Exception e) {
// throw new RuntimeException();
// }
// }

// @Test
// public void testUpdateRemainingBricks() {
// try {
// int remainingBricks = 50;
// testView.updateRemainingBricks(remainingBricks);
// assertEquals(String.valueOf(remainingBricks),
// testView.getRemainingBricksValue(),
// "Remaining bricks should be " + remainingBricks);
// } catch (Exception e) {
// throw new RuntimeException();
// }
// }

// @Test
// public void testHomeButton() {
// try {
// assertTrue(testView.getHomeButton() != null,
// "Home button should not be null");
// } catch (Exception e) {
// throw new RuntimeException();
// }
// }

// }