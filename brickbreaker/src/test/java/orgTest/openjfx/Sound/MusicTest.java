// package orgTest.openjfx.Sound;

// import org.openjfx.Game.GameClass.Game;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.openjfx.Sound.Music;

// import javafx.application.Application;
// import javafx.scene.control.Button;
// import javafx.scene.control.Slider;
// import javafx.scene.image.ImageView;
// import javafx.scene.media.MediaPlayer;
// import javafx.stage.Stage;

// public class MusicTest {

// private Music music;
// private MediaPlayer mediaPlayerMock;

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
// mediaPlayerMock = mock(MediaPlayer.class);
// music = new Music(mediaPlayerMock, true);
// music.setMediaPlayer(mediaPlayerMock);
// }

// @AfterEach
// public void tearDown() {
// // Ferme le mock de construction après chaque test
// }

// @Test
// public void testSetMute() {
// music.setMute(true);
// assertThat(Game.muteMusic).isTrue();
// music.setMute(false);
// assertThat(Game.muteMusic).isFalse();
// }

// @Test
// public void testSetIsPlaying() {
// music.setIsPlaying(true);
// assertThat(Game.isPlayerPlaying).isTrue();
// music.setIsPlaying(false);
// assertThat(Game.isPlayerPlaying).isFalse();
// }

// @Test
// public void testSetMediaPlayer() {
// MediaPlayer newMediaPlayerMock = mock(MediaPlayer.class);
// music.setMediaPlayer(newMediaPlayerMock);
// assertThat(music.getMediaPlayer()).isEqualTo(newMediaPlayerMock);
// }

// @Test
// public void testPlay() {
// music.play();
// verify(mediaPlayerMock, times(1)).play();
// }

// @Test
// public void testPause() {
// music.pause();
// verify(mediaPlayerMock, times(1)).pause();
// }

// @Test
// public void testToggleMute() {
// boolean muteState = music.toggleMute();
// assertThat(muteState).isTrue();
// muteState = music.toggleMute();
// assertThat(muteState).isFalse();
// }

// @Test
// public void testChangeMusic() {
// Game.isPlayerPlaying = true;
// music.changeMusic();
// verify(mediaPlayerMock, times(1)).play();

// Game.isPlayerPlaying = false;
// music.changeMusic();
// verify(mediaPlayerMock, times(1)).pause();
// }

// @Test
// public void testTogglePlay() {
// boolean result = music.togglePlay(true);
// assertThat(result).isTrue();
// assertThat(Game.isPlayerPlaying).isTrue();

// result = music.togglePlay(false);
// assertThat(result).isTrue();
// assertThat(Game.isPlayerPlaying).isFalse();
// }

// @Test
// public void testRefresh() {
// Game.muteMaster = true;
// Game.muteMusic = false;
// Game.isPlayerPlaying = true;
// music.refresh(true);
// verify(mediaPlayerMock, times(1)).pause();

// Game.muteMaster = false;
// Game.muteMusic = true;
// Game.isPlayerPlaying = true;
// music.refresh(true);
// verify(mediaPlayerMock, times(2)).pause();

// Game.muteMaster = false;
// Game.muteMusic = false;
// Game.isPlayerPlaying = true;
// music.refresh(true);
// verify(mediaPlayerMock, times(1)).play();
// }

// @Test
// public void testUpdateVolume() {
// music.updateVolume();
// verify(mediaPlayerMock, times(1)).setVolume(Mockito.anyDouble());
// }

// @Test
// public void testUpdateMute() {
// Game.muteMaster = true;
// Game.muteMusic = false;
// Game.isPlayerPlaying = true;
// music.updateMute();
// verify(mediaPlayerMock, times(1)).pause();

// Game.muteMaster = false;
// Game.muteMusic = true;
// Game.isPlayerPlaying = true;
// music.updateMute();
// verify(mediaPlayerMock, times(2)).pause();

// Game.muteMusic = false;
// Game.isPlayerPlaying = true;
// music.updateMute();
// verify(mediaPlayerMock, times(1)).play();
// }

// @Test
// public void testSetImageBtn() {
// ImageView imageView = Music.setImageBtn();
// assertThat(imageView).isNotNull();
// assertThat(imageView.getImage()).isNotNull();
// }

// @Test
// public void testSetMusicButtonImage() {
// Button button = new Button();
// Music.setMusicButtonImage(button);
// assertThat(button.getGraphic()).isInstanceOf(ImageView.class);
// }

// @Test
// public void testMusicButtonInit() {
// Button button = Music.musicButtonInit();
// assertThat(button).isNotNull();
// assertThat(button.getGraphic()).isInstanceOf(ImageView.class);
// }

// @Test
// public void testMusicSliderInit() {
// Slider slider = Music.musicSliderInit();
// assertThat(slider).isNotNull();
// assertThat(slider.getValue()).isEqualTo(50.0);
// assertThat(slider.getMaxWidth()).isEqualTo(Game.windowWidth / 4);
// }
// }