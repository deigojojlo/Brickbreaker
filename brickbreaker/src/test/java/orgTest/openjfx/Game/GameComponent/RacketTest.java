// package orgTest.openjfx.Game.GameComponent;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.openjfx.Game.GameClass.Game;
// import org.openjfx.Game.GameComponent.Racket;

// import javafx.scene.input.KeyCode;
// import javafx.scene.input.KeyEvent;
// import javafx.scene.paint.Color;
// import org.testfx.framework.junit5.ApplicationTest;
// import javafx.stage.Stage;

// /**
// * Classe de test pour la classe Racket.
// */
// public class RacketTest extends ApplicationTest {

// private Racket racket;

// @Override
// public void start(Stage stage) {
// // This method is required by ApplicationTest to initialize JavaFX
// }

// /**
// * Initialise les champs nécessaires avant chaque test.
// */
// @BeforeEach
// public void setUp() {
// racket = new Racket(100, 100, KeyCode.RIGHT, KeyCode.LEFT, Color.BLUE, null);
// }

// /**
// * Teste l'initialisation de la raquette.
// */
// @Test
// public void testRacketInitialization() {
// assertEquals(100, racket.getPositionX(), 0);
// assertEquals(100, racket.getPositionY(), 0);
// assertEquals(Color.BLUE, racket.getColor());
// assertEquals(KeyCode.RIGHT, racket.getRightKeyBinding());
// assertEquals(KeyCode.LEFT, racket.getLeftKeyBinding());
// assertEquals(0, racket.getMovementSpeed());
// }

// /**
// * Teste la méthode getSpeed.
// */
// @Test
// public void testGetSpeed() {
// assertEquals(0, racket.getMovementSpeed());
// racket.setMovementSpeed(5);
// assertEquals(5, racket.getMovementSpeed());
// }

// /**
// * Teste la méthode getMaxSpeed.
// */
// @Test
// public void testGetMaxSpeed() {
// assertEquals(2 * Game.speed, racket.getMaximumSpeed());
// }

// /**
// * Teste la méthode setRight.
// */
// @Test
// public void testSetRight() {
// racket.setRightKeyBinding(KeyCode.D);
// assertEquals(KeyCode.D, racket.getRightKeyBinding());
// }

// /**
// * Teste la méthode setLeft.
// */
// @Test
// public void testSetLeft() {
// racket.setLeftKeyBinding(KeyCode.A);
// assertEquals(KeyCode.A, racket.getLeftKeyBinding());
// }

// /**
// * Teste la méthode getRight.
// */
// @Test
// public void testGetRight() {
// assertEquals(KeyCode.RIGHT, racket.getRightKeyBinding());
// }

// /**
// * Teste la méthode getLeft.
// */
// @Test
// public void testGetLeft() {
// assertEquals(KeyCode.LEFT, racket.getLeftKeyBinding());
// }

// /**
// * Teste la méthode setSpeed.
// */
// @Test
// public void testSetSpeed() {
// racket.setMovementSpeed(10);
// assertEquals(10, racket.getMovementSpeed());
// }

// /**
// * Teste la méthode getIsPressed.
// */
// @Test
// public void testGetIsPressed() {
// assertEquals(false, racket.getIsAnyKeyPressed());
// }

// /**
// * Teste la méthode setIsPressed.
// */
// @Test
// public void testSetIsPressed() {
// boolean b = Math.random() < 0.5;
// racket.setPressedState(b);
// assertEquals(b, racket.getIsAnyKeyPressed());
// }

// /**
// * Teste la gestion de l'appui sur la touche droite.
// */
// @Test
// public void testHandleKeyPressedRight() {
// KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT,
// false, false, false, false);
// racket.onKeyPressedForTests(event, 100, 400, false);
// racket.updatePositionForTests(100, 400, false);
// assertTrue(racket.getMovementSpeed() > 0);
// }

// /**
// * Teste la gestion de l'appui sur la touche gauche.
// */
// @Test
// public void testHandleKeyPressedLeft() {
// KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.LEFT,
// false, false, false, false);
// racket.onKeyPressedForTests(event, 100, 400, false);
// racket.updatePositionForTests(100, 400, false);
// assertTrue(racket.getMovementSpeed() < 0);
// }

// /**
// * Teste la gestion du relâchement de la touche droite.
// */
// @Test
// public void testHandleKeyReleased() {
// KeyEvent pressEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "",
// KeyCode.RIGHT, false, false, false, false);
// racket.onKeyPressedForTests(pressEvent, 100, 400, false);
// racket.updatePositionForTests(100, 400, false);
// assertTrue(racket.getMovementSpeed() > 0);

// KeyEvent releaseEvent = new KeyEvent(KeyEvent.KEY_RELEASED, "", "",
// KeyCode.RIGHT, false, false, false, false);
// racket.onKeyReleasedForTests(releaseEvent, 100, 400, false);
// racket.updatePositionForTests(100, 400, false);
// assertEquals(0, racket.getMovementSpeed());
// }

// /**
// * Teste la méthode time.
// */
// @Test
// public void testTime() {
// racket.updatePositionForTests(100, 400, false);
// assertEquals(0, racket.getMovementSpeed());
// }

// /**
// * Teste la méthode moveRacket sans accélération.
// */
// @Test
// public void testMoveRacketNoAcceleration() {
// racket.moveRacket(5, false);
// assertEquals(5, racket.getMovementSpeed());
// }

// /**
// * Teste la méthode moveRacket avec accélération.
// */
// @Test
// public void testMoveRacketWithAcceleration() {
// racket.moveRacket(1, true);
// assertEquals(1, racket.getMovementSpeed());
// racket.moveRacket(1, true);
// assertEquals(2, racket.getMovementSpeed());
// }

// /**
// * Teste la méthode moveRacket avec accélération et vitesse maximale.
// */
// @Test
// public void testMoveRacketWithAccelerationMaxSpeed() {
// racket.setMovementSpeed(racket.getMaximumSpeed() - 1);
// racket.moveRacket(1, true);
// assertEquals(racket.getMaximumSpeed(), racket.getMovementSpeed());
// racket.moveRacket(1, true);
// assertEquals(racket.getMaximumSpeed(), racket.getMovementSpeed());
// }

// /**
// * Teste la méthode moveRacket pour les tests sans accélération.
// */
// @Test
// public void testMoveRacketNoAccelerationForTests() {
// racket.moveForTests(5, false, 100, 400, false);
// assertEquals(5, racket.getMovementSpeed());
// }

// /**
// * Teste la méthode moveRacket pour les tests avec accélération.
// */
// @Test
// public void testMoveRacketWithAccelerationForTests() {
// racket.moveForTests(1, true, 100, 400, false);
// assertEquals(1, racket.getMovementSpeed());
// racket.moveForTests(1, true, 100, 400, false);
// assertEquals(2, racket.getMovementSpeed());
// }

// /**
// * Teste la méthode moveRacket pour les tests avec accélération et vitesse
// * maximale.
// */
// @Test
// public void testMoveRacketWithAccelerationMaxSpeedForTests() {
// racket.setMovementSpeed(racket.getMaximumSpeed() - 1);
// racket.moveForTests(1, true, 100, 400, false);
// assertEquals(racket.getMaximumSpeed(), racket.getMovementSpeed());
// racket.moveForTests(1, true, 100, 400, false);
// assertEquals(racket.getMaximumSpeed(), racket.getMovementSpeed());
// }

// /**
// * Teste la méthode draw.
// */
// @Test
// public void testDraw() {
// racket.drawRacket();
// }

// /**
// * Teste l'inversion des contrôles de la raquette.
// */
// @Test
// public void testInvertControls() {
// KeyCode originalRight = racket.getRightKeyBinding();
// KeyCode originalLeft = racket.getLeftKeyBinding();
// racket.toggleControlDirection();
// assertEquals(originalRight, racket.getLeftKeyBinding());
// assertEquals(originalLeft, racket.getRightKeyBinding());
// }

// /**
// * Teste la méthode setDimensions.
// */
// @Test
// public void testSetDimensions() {
// racket.setRacketDimensions(200, 50);
// assertEquals(200, racket.getWidth());
// assertEquals(50, racket.getHeight());
// }

// /**
// * Teste la méthode adjustSize.
// */
// @Test
// public void testAdjustSize() {
// racket.setRacketDimensions(100, 20);
// racket.adjustRacketSize(50, 10);
// assertEquals(150, racket.getWidth());
// assertEquals(30, racket.getHeight());
// }

// /**
// * Teste la méthode adjustSize avec des dimensions minimales.
// */
// @Test
// public void testAdjustSizeWithMinDimensions() {
// racket.setRacketDimensions(Game.unit * 2, Game.unit);
// racket.adjustRacketSize(-50, -10);
// assertEquals(Game.unit * 2, racket.getWidth());
// assertEquals(Game.unit, racket.getHeight());
// }
// }
