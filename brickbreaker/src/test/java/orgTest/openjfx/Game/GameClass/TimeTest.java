// package orgTest.openjfx.Game.GameClass;

// import org.openjfx.Game.GameClass.Time;
// import org.testfx.framework.junit5.ApplicationTest;
// import org.openjfx.GUI.GUIGame;
// import javafx.animation.Timeline;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// public class TimeTest extends ApplicationTest {
// private Runnable runnable;
// private GUIGame guiGame;

// // Internal class extending Time
// public static class ExtendedTime extends Time {
// public static Timeline getTimeline() {
// return tl;
// }

// public static void setTimeline(Timeline timeline) {
// tl = timeline;
// }
// }

// @BeforeEach
// public void setUp() {
// runnable = mock(Runnable.class);
// guiGame = mock(GUIGame.class);
// ExtendedTime.setTimeline(null);
// Time.createTimeline(runnable);
// }

// @Test
// public void testCreateTimeline() {
// ExtendedTime.setTimeline(null);
// Time.createTimeline(runnable);
// assertNotNull(ExtendedTime.getTimeline());
// assertEquals(Timeline.Status.STOPPED,
// ExtendedTime.getTimeline().getStatus());
// }

// @Test
// public void testStart() {
// Time.start(guiGame);
// assertEquals(Timeline.Status.RUNNING,
// ExtendedTime.getTimeline().getStatus());
// }

// @Test
// public void testSetRate() {
// double newRate = 2.0;
// Time.setRate(newRate);
// assertEquals(newRate, ExtendedTime.getTimeline().getRate());
// }

// @Test
// public void testStop() {
// Time.start(guiGame);
// Time.stop();
// assertEquals(Timeline.Status.STOPPED,
// ExtendedTime.getTimeline().getStatus());
// }
// }
