package org.openjfx.LevelJSON;

/**
 * La classe {@code LevelJson} représente une structure de données utilisée pour
 * stocker les informations contenues dans les fichiers JSON décrivant les
 * niveaux du jeu.
 * Elle contient les paramètres nécessaires à l'initialisation et au
 * comportement
 * des niveaux, comme la vitesse maximale, le taux d'accélération, la
 * disposition des briques, etc.
 *
 * Cette classe inclut également des méthodes d'accès (getters et setters) pour
 * manipuler et récupérer les données. Elle est conçue pour être utilisée en
 * conjonction
 * avec des bibliothèques comme Gson, pour la conversion entre les fichiers JSON
 * et les objets Java.
 */
public class LevelJson {
    /** La largeur du niveau en pixels. */
    private int width;

    /** La vitesse maximale de la balle dans le niveau. */
    private double maxSpeed;

    /**
     * Le taux de progression ou d'accélération global du niveau (utilisé pour la
     * gestion du temps).
     */
    private double rate;

    /** Le taux d'accélération de la balle dans le niveau. */
    private double acc;

    /**
     * Tableau bidimensionnel représentant la disposition des briques dans le
     * niveau.
     */
    private int[][] tab;

    /**
     * Retourne une représentation sous forme de chaîne de caractères
     * du tableau des briques du niveau.
     * Chaque ligne du tableau est représentée sur une nouvelle ligne dans la
     * chaîne.
     *
     * @return une chaîne de caractères représentant la disposition des briques
     */
    @Override
    public String toString() {
        String r = "";
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                r += tab[i][j] + " ";
            }
            r += "\n";
        }
        return r;
    }

    /**
     * Obtient la largeur du niveau.
     *
     * @return la largeur du niveau en pixels
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Définit la largeur du niveau.
     *
     * @param width la largeur du niveau en pixels
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Obtient la vitesse maximale de la balle dans le niveau.
     *
     * @return la vitesse maximale de la balle
     */
    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * Définit la vitesse maximale de la balle dans le niveau.
     *
     * @param maxSpeed la vitesse maximale à définir
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Obtient le taux de progression ou d'accélération global du niveau.
     *
     * @return le taux de progression du niveau
     */
    public double getRate() {
        return this.rate;
    }

    /**
     * Définit le taux de progression ou d'accélération global du niveau.
     *
     * @param rate le taux de progression à définir
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * Obtient le taux d'accélération de la balle dans le niveau.
     *
     * @return le taux d'accélération de la balle
     */
    public double getAcc() {
        return this.acc;
    }

    /**
     * Définit le taux d'accélération de la balle dans le niveau.
     *
     * @param acc le taux d'accélération à définir
     */
    public void setAcc(double acc) {
        this.acc = acc;
    }

    /**
     * Obtient la disposition des briques du niveau sous forme de tableau
     * bidimensionnel.
     * Chaque élément du tableau représente une brique ou un espace vide,
     * selon le format défini dans les fichiers JSON.
     *
     * @return le tableau bidimensionnel représentant la disposition des briques
     */
    public int[][] getTab() {
        return this.tab;
    }

    /**
     * Définit la disposition des briques du niveau.
     *
     * @param tab le tableau bidimensionnel représentant la disposition des briques
     */
    public void setTab(int[][] tab) {
        this.tab = tab;
    }
}
