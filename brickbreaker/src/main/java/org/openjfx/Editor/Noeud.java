package org.openjfx.Editor;

import javafx.scene.Node;

/**
 * Représente un noeud dans l'éditeur, composé d'un élément graphique (Node) et
 * d'un type spécifique.
 */
public class Noeud {
    private Node node; // Élément graphique associé au noeud
    private int type; // Type du noeud (ex : brique, objet, etc.)

    /**
     * Constructeur de la classe Noeud.
     *
     * @param node Élément graphique associé au noeud.
     * @param type Type du noeud (entier représentant la catégorie ou la fonction du
     *             noeud).
     */
    public Noeud(Node node, int type) {
        this.node = node;
        this.type = type;
    }

    /**
     * Retourne l'élément graphique associé au noeud.
     *
     * @return L'élément graphique (Node) de ce noeud.
     */
    public Node getNode() {
        return this.node;
    }

    /**
     * Modifie l'élément graphique associé au noeud.
     *
     * @param node L'élément graphique (Node) à associer à ce noeud.
     */
    public void setNode(Node node) {
        this.node = node;
    }

    /**
     * Retourne le type du noeud.
     *
     * @return Un entier représentant le type du noeud.
     */
    public int getType() {
        return this.type;
    }

    /**
     * Modifie le type du noeud.
     *
     * @param type Un entier représentant le nouveau type du noeud.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Retourne une représentation textuelle du noeud.
     *
     * @return Une chaîne de caractères contenant le type du noeud et une
     *         représentation de l'élément graphique.
     */
    @Override
    public String toString() {
        return type + " " + node;
    }
}
