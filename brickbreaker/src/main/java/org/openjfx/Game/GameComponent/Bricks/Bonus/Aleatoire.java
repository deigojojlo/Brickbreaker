package org.openjfx.Game.GameComponent.Bricks.Bonus;

import java.util.Random;

/**
 * Classe permettant de sélectionner aléatoirement un bonus à appliquer.
 * Les probabilités de chaque bonus sont définies par le tableau "repertoire".
 * 
 * <p>
 * Cette classe fournit une méthode principale, {@code select()}, qui permet de
 * sélectionner un bonus en fonction des probabilités associées. Les probabilités
 * sont définies par la distribution des valeurs dans le tableau {@code repertoire}.
 * </p>
 * 
 * <h2>Bonus disponibles</h2>
 * <ul>
 * <li>0 : Vie supplémentaire (probabilité 1/10).</li>
 * <li>1 : Plusieurs balles supplémentaires (probabilité 5/10).</li>
 * <li>2 : Inversion des contrôles du joueur (probabilité 3/10).</li>
 * <li>3 : Modification de la taille de la raquette (probabilité 1/10).</li>
 * </ul>
 * 
 * <p>
 * La classe gère également les accès aux informations statiques du tableau 
 * {@code repertoire} via des accesseurs pour permettre des ajustements si nécessaire.
 * </p>
 */
public class Aleatoire {
    /**
     * Tableau répertoriant les types de bonus disponibles et leur occurrence
     * relative.
     *
     * <ul>
     * <li>0 : Vie en plus (1/10)</li>
     * <li>1 : Plusieurs balles (5/10)</li>
     * <li>2 : Inversion des touches (3/10)</li>
     * <li>3 : Changement de la taille de la raquette (1/10)</li>
     * </ul>
     */
    private static int[] repertoire = new int[] { 0, 1, 2, 1, 2, 1, 2, 1, 1, 3 };

    /**
     * Longueur du tableau "repertoire".
     */
    private static int long_rep = repertoire.length;

    /**
     * Sélectionne aléatoirement un bonus en fonction des probabilités définies dans
     * {@code repertoire}.
     * 
     * <p>
     * Cette méthode utilise une instance de {@code Random} pour générer un index
     * aléatoire dans la plage de valeurs du tableau {@code repertoire}, puis
     * retourne le bonus correspondant.
     * </p>
     * 
     * @return Le type de bonus sélectionné (0, 1, 2, ou 3).
     */
    public int select() {
        Random r = new Random();
        int n = r.nextInt(long_rep);
        return repertoire[n];
    }

    /**
     * Renvoie la longueur actuelle du tableau {@code repertoire}.
     * 
     * @return La longueur du tableau {@code repertoire}.
     */
    public static int getLongrep() {
        return long_rep;
    }

    /**
     * Renvoie le tableau des types de bonus disponibles.
     * 
     * @return Un tableau contenant les types de bonus et leur distribution.
     */
    public static int[] getRepertoire() {
        return repertoire;
    }

}
