package orgTest.openjfx.Editor;

// malheureusement personne à su nous expliquer le problème avec les tests

// javafx

// import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.LinkedList;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.Timeout;
// import org.openjfx.Editor.EditorView;
// import org.openjfx.Game.GameClass.Game;
// import org.testfx.framework.junit5.ApplicationTest;

// import javafx.application.Application;
// import javafx.application.Platform;
// import javafx.scene.Node;
// import javafx.scene.control.Button;
// import javafx.scene.layout.GridPane;
// import javafx.stage.Stage;
// import javafx.util.Pair;

/**
 * Classe de test pour la classe View.
 * Contient des tests unitaires, d'intégration et de performance.
 */
/*
 * class EditorViewTest {
 * 
 * private EditorView view;
 * 
 * public static class TestApp2 extends Application {
 * 
 * @Override
 * public void start(Stage primaryStage) throws Exception {
 * }
 * }
 * 
 * @BeforeAll
 * public static void initJFX() {
 * Thread t = new Thread("JavaFX Init") {
 * public void run() {
 * try {
 * ApplicationTest.launch(TestApp2.class, new String[0]);
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * }
 * };
 * t.setDaemon(true);
 * t.start();
 * }
 * 
 * @BeforeEach
 * void setUp() {
 * // Initialisation d'une instance de View avant chaque test
 * view = new EditorView();
 * }
 * 
 * // -------------------
 * // Tests unitaires
 * // -------------------
 * 
 * @Test
 * 
 * @DisplayName("Test de l'initialisation des sliders")
 * void testSliderInitialization() {
 * assertNotNull(view.getAccelerationSlider(),
 * "Le slider d'accélération ne doit pas être null");
 * assertNotNull(view.getMaxSpeedSlider(),
 * "Le slider de maxSpeed ne doit pas être null");
 * assertNotNull(view.getRateSlider(),
 * "Le slider de taux ne doit pas être null");
 * 
 * // Vérifie les valeurs par défaut
 * assertEquals(1, view.getAccelerationSlider().getValue(),
 * "La valeur par défaut du slider d'accélération doit être 1");
 * assertEquals(10, view.getMaxSpeedSlider().getValue(),
 * "La valeur par défaut du slider de largeur doit être 10");
 * assertEquals(1, view.getRateSlider().getValue(),
 * "La valeur par défaut du slider de taux doit être 1");
 * }
 * 
 * @Test
 * 
 * @DisplayName("Test d'ajout de briques")
 * void testAddBrick() {
 * Node brick = view.addBrick(11, 50, 50);
 * assertNotNull(brick, "La brique doit être ajoutée correctement");
 * 
 * // Vérifie que la brique est bien enregistrée dans les cases
 * Pair<Integer, Integer> key = new Pair<>(50, 50);
 * assertTrue(view.getGridCases().containsKey(key),
 * "La brique doit être enregistrée dans la HashMap des cases");
 * }
 * 
 * @Test
 * 
 * @DisplayName("Test de suppression de briques")
 * void testRemoveBrick() {
 * Node brick = view.addBrick(11, 50, 50);
 * assertNotNull(brick, "La brique doit être ajoutée avant suppression");
 * 
 * view.removeLabel(brick);
 * 
 * // Vérifie que la brique a bien été supprimée
 * assertFalse(view.getDraggabbleNodes().contains(brick),
 * "La brique ne doit plus être dans la liste des draggable");
 * }
 * 
 * @Test
 * 
 * @DisplayName("Test de l'initialisation de la grille")
 * void testGridInitialization() {
 * view.initializeGrid(10, 5);
 * 
 * GridPane map = (GridPane) view.getGridMap();
 * assertEquals(10 * 5, map.getChildren().size(),
 * "La grille doit contenir le bon nombre de cellules");
 * }
 * 
 * // -------------------
 * // Tests d'intégration
 * // -------------------
 * 
 * @Test
 * 
 * @DisplayName("Test d'intégration : ajout et manipulation de briques")
 * void testIntegrationAddAndManipulateBricks() {
 * view.initializeGrid(10, 5);
 * 
 * // Ajout de plusieurs briques
 * Node brick1 = view.addBrick(11, 50, 50);
 * Node brick2 = view.addBrick(41, 100, 100);
 * 
 * assertNotNull(brick1, "La première brique doit être ajoutée");
 * assertNotNull(brick2, "La deuxième brique doit être ajoutée");
 * 
 * // Vérification dans la liste des briques déplaçables
 * assertTrue(view.getDraggabbleNodes().contains(brick1),
 * "La première brique doit être draggable");
 * assertTrue(view.getDraggabbleNodes().contains(brick2),
 * "La deuxième brique doit être draggable");
 * 
 * // Suppression de l'une des briques
 * view.removeLabel(brick1);
 * assertFalse(view.getDraggabbleNodes().contains(brick1),
 * "La première brique ne doit plus être draggable après suppression");
 * }
 * 
 * @Test
 * 
 * @DisplayName("Test d'intégration : sauvegarde et retour")
 * void testIntegrationSaveAndBackButtons() {
 * Button saveButton = view.getSaveButton();
 * Button backButton = view.getBackButton();
 * 
 * assertNotNull(saveButton, "Le bouton de sauvegarde doit être initialisé");
 * assertNotNull(backButton, "Le bouton retour doit être initialisé");
 * 
 * // Simulation de clics (peut être remplacé par des tests GUI spécifiques)
 * saveButton.fire();
 * backButton.fire();
 * 
 * // Ajout d'un message pour valider qu'ils ne génèrent pas d'erreurs
 * assertDoesNotThrow(() -> {
 * saveButton.fire();
 * backButton.fire();
 * }, "Les boutons doivent fonctionner sans générer d'exceptions");
 * }
 * 
 * // -------------------
 * // Tests de performance
 * // -------------------
 * 
 * @Test
 * 
 * @DisplayName("Test de performance : ajout massif de briques")
 * 
 * @Timeout(3)
 * void testPerformanceAddBricks() {
 * int rows = 50;
 * int cols = 50;
 * 
 * // Initialisation d'une grille plus grande
 * view.initializeGrid(rows, cols);
 * 
 * long startTime = System.currentTimeMillis();
 * 
 * // Ajout d'un grand nombre de briques
 * for (int i = 0; i < rows; i++) {
 * for (int j = 0; j < cols; j++) {
 * view.addBrick(11, i * Game.unit, j * Game.unit);
 * }
 * }
 * 
 * long endTime = System.currentTimeMillis();
 * long duration = endTime - startTime;
 * 
 * System.out.println("Durée de l'ajout massif de briques : " + duration +
 * "ms");
 * 
 * // Vérification des performances : doit prendre moins de 3 secondes pour 2500
 * // briques
 * assertTrue(duration < 3000,
 * "L'ajout massif de briques doit être performant(< 3000 ms)");
 * }
 * 
 * @Test
 * 
 * @DisplayName("Test de performance : suppression massive de briques")
 * void testPerformanceRemoveBricks() {
 * int rows = 20;
 * int cols = 20;
 * 
 * // Initialisation et ajout de briques
 * view.initializeGrid(rows, cols);
 * for (int i = 0; i < rows; i++) {
 * for (int j = 0; j < cols; j++) {
 * view.addBrick(11, i * Game.unit, j * Game.unit);
 * }
 * }
 * 
 * long startTime = System.currentTimeMillis();
 * 
 * // Suppression de toutes les briques
 * LinkedList<Node> draggable = new LinkedList<>(view.getDraggabbleNodes());
 * for (Node brick : draggable) {
 * view.removeLabel(brick);
 * }
 * 
 * long endTime = System.currentTimeMillis();
 * long duration = endTime - startTime;
 * 
 * System.out.println("Durée de la suppression massive de briques : " + duration
 * + " ms");
 * 
 * // Vérification des performances : doit prendre moins de 2 secondes pour 400
 * // briques
 * assertTrue(duration < 2000,
 * "La suppression massive de briques doit être performante (< 2000 ms)");
 * }
 * }
 */
