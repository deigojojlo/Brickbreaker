// package orgTest.openjfx.Game.GameClass;

// import org.openjfx.Game.GameClass.Scoreboard.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// public class ScoreboardModelTest {

// public static class InnerScoreboardModelTest extends ScoreboardModel {
// public InnerScoreboardModelTest() {
// super();
// }

// public int getLevel() {
// return super.getLevel();
// }

// public int getScore() {
// return super.getScore();
// }

// public int getTime() {
// return super.getTime();
// }

// public int getSecondes() {
// return super.getSecondes();
// }

// public int getMinutes() {
// return super.getMinutes();
// }

// public int getLife() {
// return super.getLife();
// }

// public void setLevel(int level) {
// super.setLevel(level);
// }

// public void setScore(int score) {
// super.setScore(score);
// }

// public void setTime(int time) {
// super.setTime(time);
// }

// public void setLife(int life) {
// super.setLife(life);
// }

// public void addLevel() {
// super.addLevel();
// }

// public void addScore(int score) {
// super.addScore(score);
// }

// public void addLife() {
// super.addLife();
// }

// public void removeLife() {
// super.removeLife();
// }

// public void clear() {
// super.clear();
// }

// public boolean addTime(int time) {
// return super.addTime(time);
// }
// }

// InnerScoreboardModelTest model;

// @BeforeEach
// public void setUp() {
// model = new InnerScoreboardModelTest();
// }

// @Test
// public void testInitialValues() {
// assertEquals(1, model.getLevel(), "Initial level should be 1");
// assertEquals(0, model.getScore(), "Initial score should be 0");
// assertEquals(0, model.getTime(), "Initial time should be 0");
// assertEquals(0, model.getSecondes(), "Initial secondes should be 0");
// assertEquals(0, model.getMinutes(), "Initial minutes should be 0");
// assertEquals(3, model.getLife(), "Initial life should be 3");
// }

// @Test
// public void testClear() {
// model.setLevel(5);
// model.setScore(1000);
// model.setTime(5000);
// model.setLife(2);
// model.clear();

// assertEquals(1, model.getLevel(), "Level should reset to 1");
// assertEquals(0, model.getScore(), "Score should reset to 0");
// assertEquals(0, model.getTime(), "Time should reset to 0");
// assertEquals(0, model.getSecondes(), "Secondes should reset to 0");
// assertEquals(0, model.getMinutes(), "Minutes should reset to 0");
// assertEquals(3, model.getLife(), "Life should reset to 3");
// }

// @Test
// public void testAddTime_NoThreshold() {
// boolean result = model.addTime(500);
// assertFalse(result, "addTime should return false when time < 1000");
// assertEquals(500, model.getTime(), "Time should be updated correctly");
// assertEquals(0, model.getSecondes(), "Secondes should remain 0");
// assertEquals(0, model.getMinutes(), "Minutes should remain 0");
// }

// @Test
// public void testAddTime_OneSecond() {
// boolean result = model.addTime(1000);
// assertTrue(result, "addTime should return true when time >= 1000");
// assertEquals(0, model.getTime(), "Time should reset after adding 1000");
// assertEquals(1, model.getSecondes(), "Secondes should increment by 1");
// assertEquals(0, model.getMinutes(), "Minutes should remain 0");
// }

// @Test
// public void testAddTime_MultipleSeconds() {
// boolean result = model.addTime(5000);
// assertTrue(result, "addTime should return true when time >= 1000");
// assertEquals(4000, model.getTime(),
// "Time should be the remainder after adding seconds");
// assertEquals(1, model.getSecondes(), "Secondes should increment correctly");
// assertEquals(0, model.getMinutes(), "Minutes should increment correctly");
// }

// @Test
// public void testSetTime() {
// model.setTime(3000);
// assertEquals(3000, model.getTime(), "Time should be set to 3000");
// }

// @Test
// public void testSetLevel() {
// model.setLevel(10);
// assertEquals(10, model.getLevel(), "Level should be set to 10");
// }

// @Test
// public void testSetScore() {
// model.setScore(2500);
// assertEquals(2500, model.getScore(), "Score should be set to 2500");
// }

// @Test
// public void testSetLife() {
// model.setLife(5);
// assertEquals(5, model.getLife(), "Life should be set to 5");
// }

// @Test
// public void testAddLevel() {
// model.addLevel();
// assertEquals(2, model.getLevel(), "Level should increment by 1");
// }

// @Test
// public void testAddScore() {
// model.addScore(150);
// assertEquals(150, model.getScore(), "Score should increase by 150");
// }

// @Test
// public void testAddLife() {
// model.addLife();
// assertEquals(4, model.getLife(), "Life should increment by 1");
// }

// @Test
// public void testRemoveLife() {
// model.removeLife();
// assertEquals(2, model.getLife(), "Life should decrement by 1");
// }

// @Test
// public void testRemoveLife_BelowZero() {
// model.removeLife();
// model.removeLife();
// model.removeLife();
// model.removeLife(); // Life should go to -1
// assertEquals(-1, model.getLife(), "Life should decrement below zero");
// }
// }
