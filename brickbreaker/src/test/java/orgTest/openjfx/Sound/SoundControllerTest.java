// package orgTest.openjfx.Sound;

// import org.openjfx.Game.GameClass.Game;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.times;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.MockedStatic;
// import org.mockito.Mockito;
// import org.openjfx.Sound.SoundController;
// import org.openjfx.Sound.Sound;

// import javafx.application.Application;
// import javafx.scene.control.Button;
// import javafx.scene.control.Slider;
// import javafx.scene.image.ImageView;
// import javafx.stage.Stage;

// public class SoundControllerTest {

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
// }

// @Test
// public void testCollisionRacketSound() {
// try (MockedStatic<Sound> mockedSound = Mockito.mockStatic(Sound.class)) {
// SoundController.collisionRacketSound();
// mockedSound.verify(Sound::collisionRacketSound, times(1));
// }
// }

// @Test
// public void testCollisionBrickSound() {
// try (MockedStatic<Sound> mockedSound = Mockito.mockStatic(Sound.class)) {
// SoundController.collisionBrickSound("Brick");
// mockedSound.verify(() -> Sound.collisionBrickSound("Brick"), times(1));
// }
// }

// @Test
// public void testSetSoundButtonImage() {
// Button button = new Button();
// SoundController.setSoundButtonImage(button);
// assertThat(button.getGraphic()).isInstanceOf(ImageView.class);
// }

// @Test
// public void testSoundSliderInit() {
// Slider slider = SoundController.soundSliderInit();
// assertThat(slider).isNotNull();
// assertThat(slider.getValue()).isEqualTo(50.0);
// assertThat(slider.getMaxWidth()).isEqualTo(Game.windowWidth / 4);
// }

// @Test
// public void testSoundButtonInit() {
// Button button = SoundController.soundButtonInit();
// assertThat(button).isNotNull();
// assertThat(button.getGraphic()).isInstanceOf(ImageView.class);
// }
// }
