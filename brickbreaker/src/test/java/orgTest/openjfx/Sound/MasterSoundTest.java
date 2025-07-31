// package orgTest.openjfx.Sound;

// import static org.assertj.core.api.Assertions.assertThat;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.openjfx.Sound.MasterSound;

// import javafx.application.Application;
// import javafx.scene.control.Button;
// import javafx.scene.control.Slider;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.AnchorPane;
// import javafx.stage.Stage;

// public class MasterSoundTest {

// private MasterSound masterSound;

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
// masterSound = new MasterSound(anchorPane);
// }

// @Test
// public void testToggleMuteImage() {
// // État initial
// masterSound.toggleMuteImage();
// assertThat(MasterSound.getMasterSoundButton().getGraphic()).isNotNull();
// }

// @Test
// public void testMasterSoundButtonInit() {
// AnchorPane anchorPane = new AnchorPane();
// Button button = MasterSound.masterSoundButtonInit(anchorPane);
// assertThat(button).isNotNull();
// assertThat(anchorPane.getChildren()).contains(button);
// }

// @Test
// public void testMasterSoundSliderInit() {
// Slider slider = MasterSound.masterSoundSliderInit();
// assertThat(slider).isNotNull();
// assertThat(slider.getValue()).isEqualTo(50.0);
// }

// @Test
// public void testGetMasterSoundButton() {
// Button button = MasterSound.getMasterSoundButton();
// assertThat(button).isNotNull();
// }

// @Test
// public void testSetImageBtn() {
// ImageView imageView = MasterSound.setImageBtn();
// assertThat(imageView).isNotNull();
// assertThat(imageView.getImage()).isNotNull();
// }
// }