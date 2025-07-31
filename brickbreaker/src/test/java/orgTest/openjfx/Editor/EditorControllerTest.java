/*
 * package orgTest.openjfx.Editor;
 * 
 * import static org.junit.Assert.assertNotEquals;
 * import static org.junit.jupiter.api.Assertions.assertEquals;
 * import java.util.Random;
 * import org.junit.Test;
 * import org.junit.jupiter.api.BeforeAll;
 * import org.openjfx.BrickBreaker;
 * import org.openjfx.Editor.EditorController;
 * import org.testfx.framework.junit5.ApplicationTest;
 * import org.testfx.framework.junit5.Init;
 * import javafx.application.Application;
 * import javafx.scene.Node;
 * import javafx.scene.control.Button;
 * import javafx.scene.control.Label;
 * import javafx.scene.control.Slider;
 * import javafx.scene.layout.Pane;
 * import javafx.stage.Stage;
 * 
 * public class EditorControllerTest {
 * 
 * private static BrickBreaker brickBreaker;
 * 
 * public class EditorControllerTestClass extends EditorController {
 * 
 * EditorControllerTestClass(BrickBreaker frame) {
 * super(frame);
 * }
 * 
 * @Override
 * public void enableDragAndDrop(Node node) {
 * super.enableDragAndDrop(node);
 * }
 * 
 * @Override
 * public String getColor(int i) {
 * return super.getColor(i);
 * }
 * };
 * 
 * public static class TestApp extends Application {
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
 * ApplicationTest.launch(TestApp.class, new String[0]);
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * }
 * };
 * t.setDaemon(true);
 * t.start();
 * brickBreaker = new BrickBreaker() {
 * 
 * @Override
 * public void setHome() {
 * 
 * }
 * };
 * }
 * 
 * @Test
 * void create() {
 * new EditorController(brickBreaker); // => test initEvent
 * }
 * 
 * @Test
 * void enableDragAndDropTest() {
 * new EditorControllerTestClass(brickBreaker).enableDragAndDrop(new Label());
 * new EditorControllerTestClass(brickBreaker).enableDragAndDrop(new Button());
 * new EditorControllerTestClass(brickBreaker).enableDragAndDrop(new Pane());
 * new EditorControllerTestClass(brickBreaker).enableDragAndDrop(new Slider());
 * }
 * 
 * @Test
 * void sceneNotNull() {
 * assertNotEquals(null, new
 * EditorControllerTestClass(brickBreaker).getScene());
 * }
 * 
 * @Test
 * void typeValidColor() {
 * EditorControllerTestClass instance = new
 * EditorControllerTestClass(brickBreaker);
 * assertEquals("blue", instance.getColor(0));
 * assertEquals("purple", instance.getColor(1));
 * assertEquals("green", instance.getColor(2));
 * assertEquals("red", instance.getColor(3));
 * assertEquals("black", instance.getColor(4));
 * assertEquals("white", instance.getColor(5));
 * assertEquals("white", instance.getColor(6));
 * assertEquals("white", instance.getColor(7));
 * assertEquals("white", instance.getColor(8));
 * assertEquals("white", instance.getColor(9));
 * assertEquals("white", instance.getColor(10));
 * assertEquals("blue", instance.getColor(11));
 * assertEquals("purple", instance.getColor(12));
 * assertEquals("green", instance.getColor(13));
 * assertEquals("red", instance.getColor(14));
 * assertEquals("yellow", instance.getColor(15));
 * assertEquals("orange", instance.getColor(16));
 * assertEquals("lightblue", instance.getColor(17));
 * assertEquals("black", instance.getColor(18));
 * assertEquals("brown", instance.getColor(19));
 * assertEquals("blue", instance.getColor(20));
 * }
 * 
 * @Test
 * void typeInvalidColor() {
 * Random rand = new Random();
 * EditorControllerTestClass instance = new
 * EditorControllerTestClass(brickBreaker);
 * for (int i = 0; i < 100; i++) {
 * assertEquals("", instance.getColor(-rand.nextInt(Integer.MAX_VALUE)));
 * }
 * 
 * for (int i = 0; i < 100; i++) {
 * assertEquals("", instance.getColor(rand.nextInt(20, Integer.MAX_VALUE)));
 * }
 * }
 * }
 */