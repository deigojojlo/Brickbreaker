// package orgTest.openjfx.Sound;

// import org.openjfx.Game.GameClass.Game;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.mock;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.openjfx.Sound.MusicController;

// import javafx.application.Application;
// import javafx.scene.control.Button;
// import javafx.scene.control.Slider;
// import javafx.scene.image.ImageView;
// import javafx.scene.media.MediaPlayer;
// import javafx.stage.Stage;

// public class MusicControllerTest {

// private MusicController musicController;

// @BeforeAll
// public static void initJFX() throws InterruptedException {
// Thread t = new Thread("JavaFX Init Thread") {
// public void run() {
// Application.launch(TestApp.class, new String[0]);
// }
// };
// t.setDaemon(true);
// t.start();
// // Attendre que JavaFX soit initialisé
// Thread.sleep(2000);
// }

// public static class TestApp extends Application {
// @Override
// public void start(Stage primaryStage) throws Exception {
// // Ne rien faire ici
// }
// }

// @BeforeEach
// public void setUp() {
// MediaPlayer newMediaPlayerMock = mock(MediaPlayer.class);
// musicController = new MusicController(newMediaPlayerMock, true);
// }

// @Test
// public void testSetMusicButtonImage() {
// Button button = new Button();
// MusicController.setMusicButtonImage(button);
// assertThat(button.getGraphic()).isInstanceOf(ImageView.class);
// }

// @Test
// public void testSetMusicPlaying() {
// musicController.setMusicPlaying(true);
// // Assurez-vous qu'aucune exception n'est levée
// musicController.setMusicPlaying(false);
// }

// @Test
// public void testUpdateVolume() {
// musicController.updateVolume();
// // Assurez-vous qu'aucune exception n'est levée
// }

// @Test
// public void testUpdateMute() {
// musicController.updateMute();
// // Assurez-vous qu'aucune exception n'est levée
// }

// @Test
// public void testMusicButtonInit() {
// Button button = MusicController.musicButtonInit();
// assertThat(button).isNotNull();
// assertThat(button.getGraphic()).isInstanceOf(ImageView.class);
// }

// @Test
// public void testMusicSliderInit() {
// Slider slider = MusicController.musicSliderInit();
// assertThat(slider).isNotNull();
// assertThat(slider.getValue()).isEqualTo(50.0);
// assertThat(slider.getMaxWidth()).isEqualTo(Game.windowWidth / 4);
// }
// }
