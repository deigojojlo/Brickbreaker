package orgTest.openjfx.Highscore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.HighscoreTXT.HighScore;

public class HighScoreTXTTest extends HighScore { // pour pouvoir voir et modfier les privates
    private static HashMap<javafx.util.Pair<Integer, Boolean>, LinkedList<javafx.util.Pair<Integer, String>>> sauvegarde;

    @BeforeAll
    public static void before() {
        // objectif ne pas écraser nos sauvegarde
        HighScore.read();
        sauvegarde = HighScore.getHighScores();
        System.out.println("@BeforeClass");
        System.out.println(sauvegarde);
    }

    @AfterAll
    public static void after() {
        HighScore.setHighScores(sauvegarde);
        HighScore.save();
        System.out.println("@AfterClass");
        System.out.println(sauvegarde);
    }

    @BeforeEach
    public void setUp() {
        // Réinitialiser la scoreMap avant chaque test pour éviter l'interférence entre
        // les tests
        HighScore.setHighScores(
                new HashMap<javafx.util.Pair<Integer, Boolean>, LinkedList<javafx.util.Pair<Integer, String>>>());
        ;
    }

    @Test
    public void testAddScore() {
        // Test de l'ajout de scores et vérification de l'ordre décroissant
        javafx.util.Pair<Integer, String> score1 = new javafx.util.Pair<>(500, "Alice");
        javafx.util.Pair<Integer, String> score2 = new javafx.util.Pair<>(300, "Bob");
        javafx.util.Pair<Integer, String> score3 = new javafx.util.Pair<>(800, "Charlie");
        javafx.util.Pair<Integer, Boolean> key1 = new javafx.util.Pair<>(1, false);
        javafx.util.Pair<Integer, Boolean> key2 = new javafx.util.Pair<>(1, false);
        javafx.util.Pair<Integer, Boolean> key3 = new javafx.util.Pair<>(1, false);
        HighScore.addScore(key1, score1);
        HighScore.addScore(key2, score2);
        HighScore.addScore(key3, score3);

        LinkedList<javafx.util.Pair<Integer, String>> scores = HighScore.getHighScores()
                .get(new javafx.util.Pair<Integer, Boolean>(1, false));

        assertNotNull(scores);
        assertEquals(3, scores.size());
        assertEquals(800, scores.get(0).getKey());
        assertEquals("Charlie", scores.get(0).getValue());
        assertEquals(500, scores.get(1).getKey());
        assertEquals("Alice", scores.get(1).getValue());
        assertEquals(300, scores.get(2).getKey());
        assertEquals("Bob", scores.get(2).getValue());
    }

    @Test
    public void testReadHighScores() throws Exception {
        // Crée un fichier temporaire simulant le fichier highscore.txt
        try (FileWriter writer = new FileWriter("src/main/resources/HighscoreTXT/highscore.txt")) {
            writer.write("1 false 500 Alice\n");
            writer.write("1 false 300 Bob\n");
            writer.write("2 true 800 Charlie\n");
            writer.close();
        }

        HighScore.read();

        // Vérifie les résultats
        HashMap<javafx.util.Pair<Integer, Boolean>, LinkedList<javafx.util.Pair<Integer, String>>> scores = HighScore
                .getHighScores();
        assertEquals(2, scores.size());

        LinkedList<javafx.util.Pair<Integer, String>> level1Scores = scores
                .get(new javafx.util.Pair<Integer, Boolean>(1, false));
        assertNotNull(level1Scores);
        assertEquals(2, level1Scores.size());
        assertEquals(500, level1Scores.get(0).getKey());
        assertEquals("Alice", level1Scores.get(0).getValue());
        assertEquals(300, level1Scores.get(1).getKey());
        assertEquals("Bob", level1Scores.get(1).getValue());

        LinkedList<javafx.util.Pair<Integer, String>> level2Scores = scores
                .get(new javafx.util.Pair<Integer, Boolean>(2, true));
        assertNotNull(level2Scores);
        assertEquals(1, level2Scores.size());
        assertEquals(800, level2Scores.get(0).getKey());
        assertEquals("Charlie", level2Scores.get(0).getValue());
    }

    @Test
    public void testSaveHighScores() throws Exception {
        // Ajoute des scores
        HighScore.addScore(new javafx.util.Pair<>(1, false), new javafx.util.Pair<>(500, "Alice"));
        HighScore.addScore(new javafx.util.Pair<>(1, false), new javafx.util.Pair<>(300, "Bob"));
        HighScore.addScore(new javafx.util.Pair<>(2, true), new javafx.util.Pair<>(800, "Charlie"));

        HighScore.save();
        HighScore.read(); // on a tester avant si read fonctionne bien

        assertEquals(2, HighScore.getHighScores().size()); // on a bien alice et bob dans la meme case
        // on lis pour verifier la bonne écriture, sans passer par HighScore.read() qui
        // renvoie la hashmap qu'on a tester juste avant
        LinkedList<String> lines = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/HighscoreTXT/highscore.txt"));) {
            String l;
            while ((l = reader.readLine()) != null) {
                lines.add(l);
            }
            reader.close();
        }

        System.out.println(lines);
        assertEquals(3, lines.size());
        assertTrue(lines.contains("1 false 500 Alice"));
        assertTrue(lines.contains("1 false 300 Bob"));
        assertTrue(lines.contains("2 true 800 Charlie"));
    }

    // Made by valentin
}
