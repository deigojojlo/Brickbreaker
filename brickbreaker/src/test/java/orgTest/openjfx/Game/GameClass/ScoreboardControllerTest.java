// package orgTest.openjfx.Game.GameClass;

// import org.openjfx.BrickBreaker;
// import org.openjfx.Game.GameClass.Scoreboard.*;
// import org.openjfx.Game.GameClass.Game;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.testfx.framework.junit5.ApplicationTest;

// import javafx.scene.layout.BorderPane;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// class ScoreboardControllerTest extends ApplicationTest {

// private static class MockScoreboardModel extends ScoreboardModel {
// private int minutes;
// private int secondes;
// private int level;
// private int score;
// private int time;
// private int life;

// @Override
// public int getMinutes() {
// return minutes;
// }

// @Override
// public int getSecondes() {
// return secondes;
// }

// @Override
// public boolean addTime(int time) {
// this.time += time;
// return super.addTime(time);
// }

// @Override
// public int getLevel() {
// return level;
// }

// @Override
// public int getScore() {
// return score;
// }

// @Override
// public int getTime() {
// return time;
// }

// @Override
// public int getLife() {
// return life;
// }

// @Override
// public void setLevel(int level) {
// this.level = level;
// }

// @Override
// public void setScore(int score) {
// this.score = score;
// }

// @Override
// public void setLife(int life) {
// this.life = life;
// }

// @Override
// public void addLevel() {
// this.level++;
// }

// @Override
// public void addScore(int score) {
// this.score += score;
// }

// @Override
// public void addLife() {
// this.life++;
// }

// @Override
// public void removeLife() {
// this.life--;
// }
// }

// private static class MockScoreboardView extends ScoreboardView {
// private boolean displayCalled;
// private int updatedMinutes;
// private int updatedSecondes;
// private int updatedLevel;
// private int updatedScore;
// private int updatedLife;
// private int updatedBriqueRestante;

// public MockScoreboardView(BorderPane w) {
// super(w);
// }

// @Override
// public void display() {
// displayCalled = true;
// }

// @Override
// public void updateTime(int minutes, int secondes) {
// this.updatedMinutes = minutes;
// this.updatedSecondes = secondes;
// }

// @Override
// public void updateLevel(int level) {
// this.updatedLevel = level;
// }

// @Override
// public void updateScore(int score) {
// this.updatedScore = score;
// }

// @Override
// public void updateLife(int life) {
// this.updatedLife = life;
// }

// @Override
// public void updateBriqueRestante(int nbBrick) {
// this.updatedBriqueRestante = nbBrick;
// }
// }

// private MockScoreboardModel model;
// private MockScoreboardView view;
// private ScoreboardController controller;

// @BeforeEach
// void setUp() {
// model = new MockScoreboardModel();
// view = new MockScoreboardView(new BorderPane());
// controller = new ScoreboardController(new BorderPane(), new BrickBreaker(),
// view, model);
// }

// @Test
// void testDisplay() {
// controller.display();
// assertEquals(true, view.displayCalled);
// }

// @Test
// void testTime() {
// // Setup
// Game.rate = 60.0;
// model.minutes = 2;
// model.secondes = 0;

// // Execute
// controller.time();

// // Verify
// assertEquals(2, model.getMinutes());
// assertEquals(0, model.getSecondes());
// assertEquals(2, view.updatedMinutes);
// assertEquals(0, view.updatedSecondes);
// }

// @Test
// void testGetLevel() {
// model.level = 5;
// int level = controller.getLevel();
// assertEquals(5, level);
// }

// @Test
// void testGetScore() {
// model.score = 100;
// int score = controller.getScore();
// assertEquals(100, score);
// }

// @Test
// void testGetTime() {
// model.time = 120;
// int time = controller.getTime();
// assertEquals(120, time);
// }

// @Test
// void testGetLife() {
// model.life = 3;
// int life = controller.getLife();
// assertEquals(3, life);
// }

// @Test
// void testSetLevel() {
// controller.setLevel(2, true);
// assertEquals(2, model.getLevel());
// assertEquals(2, view.updatedLevel);
// }

// @Test
// void testSetScore() {
// controller.setScore(150);
// assertEquals(150, model.getScore());
// assertEquals(150, view.updatedScore);
// }

// @Test
// void testSetLife() {
// controller.setLife(4);
// assertEquals(4, model.getLife());
// assertEquals(4, view.updatedLife);
// }

// @Test
// void testAddLevel() {
// model.level = 1;
// controller.addLevel();
// assertEquals(2, model.getLevel());
// assertEquals(2, view.updatedLevel);
// }

// @Test
// void testAddScore() {
// model.score = 50;
// controller.addScore(50);
// assertEquals(100, model.getScore());
// assertEquals(100, view.updatedScore);
// }

// @Test
// void testAddLife() {
// model.life = 1;
// controller.addLife();
// assertEquals(2, model.getLife());
// assertEquals(2, view.updatedLife);
// }

// @Test
// void testRemoveLife() {
// model.life = 3;
// controller.removeLife();
// assertEquals(2, model.getLife());
// assertEquals(2, view.updatedLife);
// }

// @Test
// void testUpdateBriqueRestante() {
// controller.updateBriqueRestante(10);
// assertEquals(10, view.updatedBriqueRestante);
// }
// }
