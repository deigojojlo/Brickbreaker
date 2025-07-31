package orgTest.openjfx.Game.GameComponent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameClass.Game;
import org.openjfx.Game.GameComponent.Bricks.Brick;

import org.openjfx.Game.GameComponent.Ball;
import org.openjfx.Game.GameComponent.Component;
import org.openjfx.Game.GameComponent.Racket;

public class BallTest extends Ball {

    private static Level level;
    private static Racket racket;

    public BallTest() {
        super(100, 100, Game.speed, 0.0, 30.0, level);
    }

    @BeforeAll
    public static void setUp() {
        // Setting up the game and level for the tests
        Game.originX = 0;
        Game.originY = 0;
        racket = new Racket(0, 0, null, null, null, level);
        level = new Level(racket);
        level.setLevelGrid(new Brick[10][10]); // Mock the level as per the requirements
        // Initialize ball at position (100, 100) with speed Game.speed = 3 and no
        // acceleration
    }

    @Test
    public void testBallInitialization() {
        // Test if the ball is correctly initialized
        assertEquals(100, this.getPositionX(), "Ball X position should be initialized correctly");
        assertEquals(100, this.getPositionY(), "Ball Y position should be initialized correctly");
        assertEquals(Game.speed, this.getVelocityX(), "Ball speed X should match the game speed");
        assertEquals(-Game.speed, this.getVelocityY(), "Ball speed Y should be the negative of the game speed");
    }

    @Test
    public void testBallSpeed() {
        // Test the ball's speed after setting new speed
        this.setBallSpeed(3, -3);
        assertEquals(3, this.getVelocityX(), "Ball speed X should be 3");
        assertEquals(-3, this.getVelocityY(), "Ball speed Y should be -3");
    }

    @Test
    public void testBallAcceleration() {
        // Test setting acceleration and check if it's applied correctly
        this.setBallAcceleration(0.5);
        assertEquals(0.5, this.getAcceleration(), "Ball acceleration should be set correctly");
    }

    @Test
    public void testTime() {
        // Test the `time()` method which updates ball's position and speed
        this.setPositionX(Game.originX + (Game.gameWidth - this.getWidth()) *
                Math.random());
        this.setPositionX(Game.originX + (Game.gameWidth - this.getHeight()) *
                Math.random());
        double initialX = this.getPositionX();
        double initialY = this.getPositionY();
        boolean collision = this.time(true);

        if (!collision) {
            assertNotEquals(initialX, this.getPositionX(),
                    "Ball X position should be updated after time() is called if no collisions occured");
            assertNotEquals(initialY, this.getPositionY(),
                    "Ball Y position should be updated after time() is called if no collisions occured");
        } else {
            assertEquals(initialX, this.getPositionX(),
                    "Ball X position should be updated after time() is called if a collision occured");
            assertEquals(initialY, this.getPositionY(),
                    "Ball Y position should be updated after time() is called if a collision occured");
        }
    }

    @Test
    public void testBorderCollision() {
        // make sure racket wont interfere
        racket.setPositionX(700);
        racket.setPositionY(700);
        // Test if the ball correctly bounces off the walls
        this.setBallSpeed(Game.speed, -Game.speed);
        double initialSpeedX = this.getVelocityX();
        double initialSpeedY = this.getVelocityY();

        this.setPositionX(Game.gameWidth - 1);
        initialSpeedX = this.getVelocityX();
        if (this.time(true))
            assertEquals(-initialSpeedX, this.getVelocityX(), "Ball speed X should reverse on wall collision");

        this.setPositionX(Game.originX + 1);
        this.setBallSpeed(-Game.speed, initialSpeedY);
        initialSpeedX = this.getVelocityX();
        if (this.time(true))
            assertEquals(-initialSpeedX, this.getVelocityX(), "Ball speed X should reverse on wall collision");

        this.setPositionY(Game.originY + 1);
        this.setBallSpeed(initialSpeedX, -Game.speed);
        initialSpeedY = this.getVelocityY();
        if (this.time(true))
            assertEquals(-initialSpeedY, this.getVelocityY(), "Ball speed Y should reverse on top wall collision");

    }

    @Test
    public void testRacketCollision() {
        // set up Racket for testing
        racket.setPositionX(200.0);
        racket.setPositionY(500.0);

        // Set the ball's position near the racket
        this.setPositionX(racket.getPositionX() - this.getWidth() + 3);
        this.setPositionY(racket.getPositionY() - this.getHeight() + 3);
        this.setBallSpeed(Game.speed, Game.speed);

        boolean collision = this.racketCollisionTest(racket);

        // Test if the ball collides with the racket
        assertTrue(collision);
    }

    @Test
    public void testBrickCollision() {
        // Mock a Brick object for testing
        Brick brick = new Brick(0, 100, 100, level);
        brick.setWidth(2 * Game.unit);
        brick.setHeight(Game.unit);

        int i = (int) Math.floor(brick.getPositionX() / Game.unit);
        int j = (int) Math.floor(brick.getPositionY() / Game.unit);

        level.getLevelGrid()[i][j] = brick; // Place the brick at the correct position
        level.getLevelGrid()[i][j] = brick; // Place the brick at the correct position

        // Test the ball's collision with the brick
        this.setBallSpeed(Game.speed, Game.speed);
        this.setPositionX(100);
        this.setPositionY(100);
        String string = this.brickCollisionTest(true);

        assertTrue(string == "brick was removed");
    }

    @Test
    public void testBallCollisionMethod() {
        // Test if the ball's `ballCollision()` method works correctly
        int x = 192, y = 192;
        this.setPositionX(x);
        this.setPositionY(y);
        int componentX = 200, componentY = 200;
        Component component = new Brick((int) (100 * Math.random()), componentX,
                componentY, null, null, null);
        component.setWidth(Game.unit);
        component.setHeight(Game.unit);
        boolean collision = this.detectBallIntersection(component);
        assertTrue(collision, "Ball should collide with the given component");
    }

    @Test
    public void testAngleLimiting() {
        double v = 3;
        double angle = Math.PI / 2 + 0.02;
        double initialSpeedX = v * Math.cos(angle);
        double initialSpeedY = v * Math.sin(angle);
        this.setBallSpeed(initialSpeedX, initialSpeedY);
        this.limitBallAngle(0.1);
        assert (this.getVelocityX() != initialSpeedX);
        assert (this.getVelocityY() != initialSpeedY);
    }
}
