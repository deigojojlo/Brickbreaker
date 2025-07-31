// package orgTest.openjfx.Sound;

// import org.openjfx.Game.GameClass.Game;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.mockConstruction;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.MockedConstruction;
// import org.mockito.MockedStatic;
// import org.mockito.Mockito;
// import org.openjfx.Sound.MasterSound;
// import org.openjfx.Sound.Sound;

// import javafx.application.Application;
// import javafx.scene.control.Button;
// import javafx.scene.control.Slider;
// import javafx.scene.image.ImageView;
// import javafx.scene.media.AudioClip;
// import javafx.stage.Stage;

// public class SoundTest {

// private Sound sound;
// private AudioClip audioClipMock;
// private MockedConstruction<AudioClip> mockedAudioClip;

// @BeforeAll
// public static void initJFX() {
// Thread t = new Thread("JavaFX Init Thread") {
// public void run() {
// Application.launch(TestApp.class, new String[0]);
// }
// };
// t.setDaemon(true);
// t.start();
// }

// public static class TestApp extends Application {
// @Override
// public void start(Stage primaryStage) throws Exception {
// // Ne rien faire ici
// }
// }

// @BeforeEach
// public void setUp() {
// // Mock la construction d'AudioClip
// mockedAudioClip = mockConstruction(AudioClip.class, (mock, context) -> {
// audioClipMock = mock;
// // bloque la méthode play() pour qu'elle ne fasse rien
// doNothing().when(mock).play();
// });
// // Instancie Sound, qui utilisera le AudioClip mocké
// sound = new Sound("music");
// sound.setAudioClip(audioClipMock);
// Sound.setSoundVolume(0.5);
// }

// @AfterEach
// public void tearDown() {
// // Ferme le mock de construction après chaque test
// mockedAudioClip.close();
// }

// @Test
// public void testSetSoundVolume() {
// Sound.setSoundVolume(0.5);
// assertThat(Game.soundLevel).isEqualTo(0.5);
// }

// @Test
// public void testGetAudioClip() {
// assertThat(sound.getAudioClip()).isEqualTo(audioClipMock);
// }

// @Test
// public void testPlay() {
// sound.play();
// verify(audioClipMock, times(1)).play();
// }

// @Test
// public void testSetAudioClip() {
// AudioClip audioClip = new
// AudioClip("file:src/main/resources/sons/music.mp3");
// sound.setAudioClip(audioClip);
// assertThat(sound.getAudioClip()).isEqualTo(audioClip);
// }

// @Test
// public void testSetSoundButtonImage() {
// Button button = new Button();
// Sound.setSoundButtonImage(button);
// assertThat(button.getGraphic()).isInstanceOf(ImageView.class);
// }

// @Test
// public void testCollisionRacketSound() {
// try (MockedStatic<MasterSound> mockedMasterSound =
// Mockito.mockStatic(MasterSound.class)) {
// Game.muteMaster = false;
// Sound.collisionRacketSound();
// verify(audioClipMock, times(1)).play();
// }
// }

// @Test
// public void testCollisionBrickSound() {
// try (MockedStatic<MasterSound> mockedMasterSound =
// Mockito.mockStatic(MasterSound.class)) {
// Game.muteMaster = false;
// Sound.collisionBrickSound("Brick");
// verify(audioClipMock, times(1)).play();
// }
// }

// @Test
// public void testSetImageBtn() {
// ImageView imageView = Sound.setImageBtn();
// assertThat(imageView).isNotNull();
// assertThat(imageView.getImage()).isNotNull();
// }

// @Test
// public void testSoundButtonInit() {
// Button button = Sound.soundButtonInit();
// assertThat(button).isNotNull();
// assertThat(button.getGraphic()).isInstanceOf(ImageView.class);
// }

// @Test
// public void testSoundSliderInit() {
// Slider slider = Sound.soundSliderInit();
// assertThat(slider).isNotNull();
// assertThat(slider.getValue()).isEqualTo(50.0);
// assertThat(slider.getMaxWidth()).isEqualTo(Game.windowWidth / 4);
// }
// }