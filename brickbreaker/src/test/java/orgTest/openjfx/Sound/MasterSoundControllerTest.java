// package orgTest.openjfx.Sound;

// import org.openjfx.Game.GameClass.Game;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.mock;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.openjfx.Sound.MasterSoundController;

// import javafx.application.Application;
// import javafx.scene.control.Slider;
// import javafx.scene.layout.AnchorPane;
// import javafx.scene.media.MediaPlayer;
// import javafx.stage.Stage;

// public class MasterSoundControllerTest {

// private MasterSoundController masterSoundController;

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
// // Réinitialiser les paramètres avant chaque test si nécessaire
// AnchorPane anchorPane = new AnchorPane();
// MediaPlayer mediaPlayerMock1 = mock(MediaPlayer.class);
// MediaPlayer mediaPlayerMock2 = mock(MediaPlayer.class);
// masterSoundController = new MasterSoundController(anchorPane,
// mediaPlayerMock1, mediaPlayerMock2);
// }

// @Test
// public void testToggleMute() {
// // État initial
// assertThat(Game.muteMaster).isFalse();

// // Bascule la mise en sourdine
// masterSoundController.toggleMute();
// assertThat(Game.muteMaster).isTrue();

// // Bascule à nouveau
// masterSoundController.toggleMute();
// assertThat(Game.muteMaster).isFalse();
// }

// @Test
// public void testUpdateVolume() {
// // Assurez-vous qu'aucune exception n'est levée
// masterSoundController.updateVolume();
// }

// @Test
// public void testUpdateMute() {
// // Assurez-vous qu'aucune exception n'est levée
// masterSoundController.updateMute();
// }

// @Test
// public void testSetButtonHandler() {
// // Assurez-vous qu'aucune exception n'est levée
// masterSoundController.setButtonHandler();
// }

// @Test
// public void testSetMusicPlaying() {
// // Assurez-vous qu'aucune exception n'est levée
// masterSoundController.setMusicPlaying(true);
// masterSoundController.setMusicPlaying(false);
// }

// @Test
// public void testMasterSliderInit() {
// Slider slider = MasterSoundController.mastereSliderInit();
// assertThat(slider).isNotNull();
// assertThat(slider.getValue()).isEqualTo(50.0);
// }
// }
