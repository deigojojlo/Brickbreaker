// package orgTest.openjfx;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertFalse;

// import org.junit.jupiter.api.Test;
// import org.openjfx.BrickBreaker;
// import org.openjfx.Game.GameClass.Game;
// import org.openjfx.GUI.GUIGame;
// import org.testfx.framework.junit5.ApplicationTest;

// import javafx.scene.Scene;
// import javafx.scene.control.Label;
// import javafx.scene.layout.AnchorPane;
// import javafx.stage.Stage;

// public class BrickBreakerTest extends ApplicationTest {

// private BrickBreaker brickBreaker;
// public Stage stage;

// @Override
// public void start(Stage stage) {
// // Initialise l'application JavaFX
// brickBreaker = new BrickBreaker();
// try {
// brickBreaker.start(stage);
// this.stage = stage;
// } catch (Exception e) {
// throw new RuntimeException();
// }
// }

// @Test
// public void testStageIsNotNull() {
// // Vérifie que la scène principale est bien initialisée
// assertNotNull(stage, "The primary stage should not be null");
// }

// @Test
// public void testApplicationLoads() {
// // Vérifie que l'application charge correctement une scène
// Scene scene = stage.getScene();
// assertNotNull(scene, "The primary scene should not be null");
// }

// @Test
// public void testTitleIsCorrect() {
// // Vérifie que le titre de la fenêtre est correct
// String title = stage.getTitle();
// assertNotNull(title, "The title should not be null");
// assertEquals("BrickBreaker", title,
// "The title should match 'BrickBreaker'");
// }

// @Test
// public void testUIElements() {
// // Vérifie qu'un élément UI existe dans la scène
// interact(() -> {
// Label label = new Label("Test Label");
// ((AnchorPane) stage.getScene().getRoot()).getChildren().add(label);
// });

// Label label = lookup(".label").queryAs(Label.class);
// assertNotNull(label, "Label should exist in the scene");
// assertEquals("Test Label", label.getText(),
// "The label text should match 'Test Label'");
// }

// @Test
// public void testSetGame() {
// // Vérifie que le jeu s'initialise correctement dans le mode spécifié
// interact(() -> {
// brickBreaker.setGame("edit");
// });

// Scene gameScene = stage.getScene();
// assertNotNull(gameScene, "The game scene should not be null");
// }

// @Test
// public void testSetLevels() {
// // Vérifie que le jeu s'initialise correctement dans le mode spécifié
// for (int i = 0; i < Game.totalLevels; i++) {
// final int level = i;
// interact(() -> {
// brickBreaker.setLevel(level);
// });
// Scene gameScene = stage.getScene();
// assertNotNull(gameScene, "The game scene should not be null");
// }
// }

// @Test
// public void testSetInvalidLevels() {
// // Vérifie que le jeu ne s'initialise pas avec un niveau invalide
// interact(() -> {
// brickBreaker.setLevel(-1);
// });

// Scene gameScene = stage.getScene();
// assertNotNull(gameScene, "The game scene should not be null");

// interact(() -> {
// brickBreaker.setLevel(Game.totalLevels + 1);
// });

// Scene gameScene2 = stage.getScene();
// assertNotNull(gameScene2, "The game scene should not be null");
// }

// @Test
// public void testSetEditor() {
// // Vérifie que l'éditeur s'initialise correctement
// interact(() -> {
// brickBreaker.setEditor();
// });

// Scene editorScene = stage.getScene();
// assertNotNull(editorScene, "The editor scene should not be null");
// }

// @Test
// public void testSetScene() {
// // Vérifie que la scène spécifiée est affichée
// Scene newScene = new Scene(new AnchorPane());
// interact(() -> {
// brickBreaker.setScene(newScene);
// });

// Scene currentScene = stage.getScene();
// assertEquals(newScene, currentScene,
// "The current scene should match the new scene");
// }

// @Test
// public void testSetHome() {
// // Vérifie que l'écran d'accueil est affiché
// interact(() -> {
// brickBreaker.setHome();
// });

// Scene homeScene = stage.getScene();
// assertNotNull(homeScene, "The home scene should not be null");
// }

// @Test
// public void testSetLevelSelector() {
// // Vérifie que la scène de sélection de niveau est affichée
// interact(() -> {
// brickBreaker.setLevelSelector();
// });

// Scene levelSelectorScene = stage.getScene();
// assertNotNull(levelSelectorScene,
// "The level selector scene should not be null");
// }

// @Test
// public void testSetCoop() {
// // Vérifie que le mode coopératif est initialisé et la scène de sélection de
// // niveaux est affichée
// interact(() -> {
// brickBreaker.setCoop();
// });

// Scene coopScene = stage.getScene();
// assertNotNull(coopScene, "The coop scene should not be null");
// }

// @Test
// public void testSetBackToSelector() {
// // Vérifie que la scène de sélection du mode de jeu
// interact(() -> {
// brickBreaker.setBackToSelector();
// });

// Scene selectorScene = stage.getScene();
// assertNotNull(selectorScene, "The selector scene should not be null");
// }

// @Test
// public void testSetSettings() {
// // Vérifie que la scène des paramètres est affichée
// interact(() -> {
// brickBreaker.setSettings();
// });

// Scene settingsScene = stage.getScene();
// assertNotNull(settingsScene, "The settings scene should not be null");
// }

// @Test
// public void testSetMultiplayer() {
// // Vérifie que la scène multijoueur est affichée
// interact(() -> {
// brickBreaker.setMultiplayer();
// });

// Scene multiplayerScene = stage.getScene();
// assertNotNull(multiplayerScene, "The multiplayer scene should not be null");
// }

// @Test
// public void testSetRush() {
// // Vérifie que le mode "Rush" est initialisé et la scène de jeu est affichée
// interact(() -> {
// brickBreaker.setRush();
// });

// Scene rushScene = stage.getScene();
// assertNotNull(rushScene, "The rush scene should not be null");
// }

// @Test
// public void testSetHighscore() {
// // Vérifie que la scène des highscores est affichée
// interact(() -> {
// brickBreaker.setHighscore();
// });

// Scene highscoreScene = stage.getScene();
// assertNotNull(highscoreScene, "The highscore scene should not be null");
// }

// @Test
// public void testSetMusicPlaying() {
// // Vérifie que la méthode setMusicPlaying compile
// interact(() -> {
// brickBreaker.setMusicPlaying(true);
// });

// interact(() -> {
// brickBreaker.setMusicPlaying(false);
// });
// }

// @Test
// public void testUpdateVolume() {
// // Vérifie que la méthode updateVolume compile
// interact(() -> {
// brickBreaker.updateVolume();
// });
// }

// //
// @Test
// public void testUpdateMute() {
// // Vérifie que la méthode updateMute compile
// interact(() -> {
// brickBreaker.updateMute();
// });
// }

// @Test
// public void testIsCoop() {
// // Vérifie si le mode coopératif est activé
// interact(() -> {
// brickBreaker.setCoop();
// });

// assertTrue(brickBreaker.isCoop(), "Coop mode should be enabled");

// interact(() -> {
// brickBreaker.setCoop(false);
// });

// assertFalse(brickBreaker.isCoop(), "Coop mode should be disabled");
// }

// @Test
// public void testSetterCoop() {
// // Vérifie que le setter de coopératif fonctionne correctement
// interact(() -> {
// brickBreaker.setCoop(true);
// });
// assertTrue(brickBreaker.isCoop(), "Coop mode should be enabled");

// interact(() -> {
// brickBreaker.setCoop(false);
// });
// assertFalse(brickBreaker.isCoop(), "Coop mode should be disabled");
// }

// @Test
// public void testGetGuiGame() {
// // Vérifie que l'instance de GUIGame est obtenue correctement
// interact(() -> {
// brickBreaker.setGame("edit");
// });

// GUIGame guiGame = brickBreaker.getGuiGame();
// assertNotNull(guiGame, "The GUIGame instance should not be null");
// }
// }
