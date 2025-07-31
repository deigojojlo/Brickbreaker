package orgTest.openjfx.Editor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openjfx.Editor.Noeud;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

/**
 * Tests unitaires pour la classe Noeud.
 */
public class NoeudTest {

    /**
     * Test du constructeur et des méthodes getNode et getType.
     */
    @Test
    public void testConstructorAndGetters() {
        // Création d'un élément graphique (Node) de test
        Node testNode = new Rectangle(50, 50); // Un rectangle de 50x50
        int testType = 5;

        // Création d'une instance de Noeud
        Noeud noeud = new Noeud(testNode, testType);

        // Vérification des valeurs initiales
        assertEquals(testNode, noeud.getNode(), "Le node associé ne correspond pas.");
        assertEquals(testType, noeud.getType(), "Le type du noeud ne correspond pas.");
    }

    /**
     * Test des méthodes setNode et getNode.
     */
    @Test
    public void testSetNode() {
        // Création d'un élément graphique initial
        Node initialNode = new Rectangle(30, 30);
        Noeud noeud = new Noeud(initialNode, 1);

        // Modification de l'élément graphique
        Node newNode = new Rectangle(100, 100);
        noeud.setNode(newNode);

        // Vérification de la modification
        assertEquals(newNode, noeud.getNode(), "Le node associé n'a pas été mis à jour correctement.");
    }

    /**
     * Test des méthodes setType et getType.
     */
    @Test
    public void testSetType() {
        // Création d'un noeud avec un type initial
        Noeud noeud = new Noeud(new Rectangle(20, 20), 2);

        // Modification du type
        int newType = 10;
        noeud.setType(newType);

        // Vérification de la modification
        assertEquals(newType, noeud.getType(), "Le type du noeud n'a pas été mis à jour correctement.");
    }

    /**
     * Test de la méthode toString.
     */
    @Test
    public void testToString() {
        // Création d'un noeud avec un élément graphique et un type
        Node testNode = new Rectangle(40, 40);
        int testType = 3;
        Noeud noeud = new Noeud(testNode, testType);

        // Vérification du résultat de toString
        String expected = testType + " " + testNode.toString();
        assertEquals(expected, noeud.toString(), "La méthode toString ne produit pas le résultat attendu.");
    }
}
