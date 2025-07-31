package orgTest.openjfx.LevelJSON;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openjfx.LevelJSON.LevelJson;

/**
 * Tests unitaires pour la classe LevelJson.
 */
public class LevelJsonTest {

    /**
     * Test des méthodes getWidth et setWidth.
     */
    @Test
    public void testWidth() {
        LevelJson levelJson = new LevelJson();

        // Définition de la largeur
        int expectedWidth = 800;
        levelJson.setWidth(expectedWidth);

        // Vérification de la valeur
        assertEquals(expectedWidth, levelJson.getWidth(), "La largeur du niveau ne correspond pas.");
    }

    /**
     * Test des méthodes getMaxSpeed et setMaxSpeed.
     */
    @Test
    public void testMaxSpeed() {
        LevelJson levelJson = new LevelJson();

        // Définition de la vitesse maximale
        double expectedMaxSpeed = 5.5;
        levelJson.setMaxSpeed(expectedMaxSpeed);

        // Vérification de la valeur
        assertEquals(expectedMaxSpeed, levelJson.getMaxSpeed(), "La vitesse maximale ne correspond pas.");
    }

    /**
     * Test des méthodes getRate et setRate.
     */
    @Test
    public void testRate() {
        LevelJson levelJson = new LevelJson();

        // Définition du taux de progression
        double expectedRate = 1.2;
        levelJson.setRate(expectedRate);

        // Vérification de la valeur
        assertEquals(expectedRate, levelJson.getRate(), "Le taux de progression ne correspond pas.");
    }

    /**
     * Test des méthodes getAcc et setAcc.
     */
    @Test
    public void testAcc() {
        LevelJson levelJson = new LevelJson();

        // Définition du taux d'accélération
        double expectedAcc = 0.01;
        levelJson.setAcc(expectedAcc);

        // Vérification de la valeur
        assertEquals(expectedAcc, levelJson.getAcc(), "Le taux d'accélération ne correspond pas.");
    }

    /**
     * Test des méthodes getTab et setTab.
     */
    @Test
    public void testTab() {
        LevelJson levelJson = new LevelJson();

        // Création d'un tableau bidimensionnel de test
        int[][] expectedTab = {
                { 1, 2, 0 },
                { 0, 1, 3 },
                { 4, 0, 0 }
        };
        levelJson.setTab(expectedTab);

        // Vérification de la valeur
        assertArrayEquals(expectedTab, levelJson.getTab(), "Le tableau des briques ne correspond pas.");
    }

    /**
     * Test de la méthode toString.
     */
    @Test
    public void testToString() {
        LevelJson levelJson = new LevelJson();

        // Création d'un tableau bidimensionnel de test
        int[][] testTab = {
                { 1, 2, 0 },
                { 0, 1, 3 },
                { 4, 0, 0 }
        };
        levelJson.setTab(testTab);

        // Construction de la chaîne attendue
        String expectedString = "1 2 0 \n0 1 3 \n4 0 0 \n";

        // Vérification du résultat
        assertEquals(expectedString, levelJson.toString(), "La méthode toString ne produit pas le résultat attendu.");
    }
}
