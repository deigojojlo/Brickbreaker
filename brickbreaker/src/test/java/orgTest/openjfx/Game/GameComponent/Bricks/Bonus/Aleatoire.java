/* package orgTest.openjfx.Game.GameComponent.Bricks.Bonus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.Game.GameComponent.Bricks.Bonus.Aleatoire;

class AleatoireTest {

    private Aleatoire aleatoire;

    @BeforeEach
    void setUp() {
        // Initialiser un nouvel objet Aleatoire avant chaque test
        aleatoire = new Aleatoire();
    }

    // Test du constructeur (par défaut)
    @Test
    void testConstructor() {
        // La longueur du tableau 'repertoire' doit être de 10
        assertEquals(10, Aleatoire.getLongrep(), "La longueur du repertoire doit être 10 par défaut.");
    }

    // Test de la méthode select()
    @Test
    void testSelect() {
        int result = aleatoire.select();
        // Vérifier que le résultat est dans la plage attendue (0, 1, 2, ou 3)
        assertTrue(result == 0 || result == 1 || result == 2 || result == 3,
                "Le résultat doit être l'un des types de bonus définis.");
    }

    // Test de la méthode de getter pour 'repertoire'
    @Test
    void testGetRepertoire() {
        int[] expectedRepertoire = new int[] { 0, 1, 2, 1, 2, 1, 2, 1, 1, 3 };
        assertArrayEquals(expectedRepertoire, Aleatoire.getRepertoire(),
                "Le getter 'getRepertoire' doit retourner le tableau 'repertoire' correct.");
    }

    // Test des probabilités de sélection
    @Test
    void testProbabilities() {
        // Nombre d'essais pour tester les probabilités
        int numTests = 10000;
        int count0 = 0, count1 = 0, count2 = 0, count3 = 0;

        for (int i = 0; i < numTests; i++) {
            int result = aleatoire.select();
            switch (result) {
                case 0:
                    count0++;
                    break;
                case 1:
                    count1++;
                    break;
                case 2:
                    count2++;
                    break;
                case 3:
                    count3++;
                    break;
            }
        }

        // Vérification de la répartition approximative
        assertTrue(count0 >= 900 && count0 <= 1100, "Le bonus de vie doit apparaître environ 10% du temps.");
        assertTrue(count1 >= 4500 && count1 <= 5500, "Le bonus de balles doit apparaître environ 50% du temps.");
        assertTrue(count2 >= 2700 && count2 <= 3300, "Le bonus d'inversion doit apparaître environ 30% du temps.");
        assertTrue(count3 >= 900 && count3 <= 1100,
                "Le bonus de taille de raquette doit apparaître environ 10% du temps.");
    }
}*/
