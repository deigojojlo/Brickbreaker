package org.openjfx.Game.GameClass.Scoreboard;

/**
 * La classe ScoreboardModel représente le modèle de données du tableau de bord.
 * Elle contient les informations concernant le niveau, le score, le temps, les
 * vies restantes, etc.
 * Ces informations sont utilisées pour maintenir l'état du jeu.
 */
public class ScoreboardModel {
    private int level; // Le niveau du joueur
    private int score; // Le score du joueur
    private int time; // Le temps écoulé en millisecondes
    private int secondes; // Les secondes écoulées
    private int minutes; // Les minutes écoulées
    private int life; // Le nombre de vies restantes

    /**
     * Constructeur de ScoreboardModel. Initialise les valeurs par défaut.
     */
    protected ScoreboardModel() {
        clear();
    }

    /**
     * Réinitialise les données du tableau de bord à leurs valeurs par défaut.
     */
    protected void clear() {
        this.level = 1;
        this.score = 0;
        this.time = 0;
        this.minutes = 0;
        this.secondes = 0;
        this.life = 3;
    }

    /**
     * Ajoute du temps au compteur. Le temps est ajouté en millisecondes.
     * Lorsque le temps atteint ou dépasse 1000 millisecondes, il est converti en
     * secondes et minutes.
     * 
     * @param time Le temps à ajouter en millisecondes.
     * @return true si le temps a été ajouté avec succès, false sinon.
     */
    protected boolean addTime(int time) {
        this.time += time;
        if (this.time >= 1000) {
            this.time = this.time - 1000;
            this.secondes++;
            if (this.secondes == 60) {
                this.secondes = 0;
                this.minutes++;
            }
            return true;
        }
        return false;
    }

    /**
     * Retourne le nombre de minutes écoulées.
     * 
     * @return Le nombre de minutes écoulées.
     */
    protected int getMinutes() {
        return this.minutes;
    }

    /**
     * Retourne le nombre de secondes écoulées.
     * 
     * @return Le nombre de secondes écoulées.
     */
    protected int getSecondes() {
        return this.secondes;
    }

    /**
     * Définit le temps total en millisecondes.
     * 
     * @param time Le temps en millisecondes.
     */
    protected void setTime(int time) {
        this.time = time;
    }

    /**
     * Retourne le niveau actuel du joueur.
     * 
     * @return Le niveau du joueur.
     */
    protected int getLevel() {
        return level;
    }

    /**
     * Retourne le score actuel du joueur.
     * 
     * @return Le score du joueur.
     */
    protected int getScore() {
        return score;
    }

    /**
     * Retourne le temps total écoulé en millisecondes.
     * 
     * @return Le temps total en millisecondes.
     */
    protected int getTime() {
        return time;
    }

    /**
     * Retourne le nombre de vies restantes du joueur.
     * 
     * @return Le nombre de vies restantes.
     */
    protected int getLife() {
        return life;
    }

    /**
     * Définit le niveau du joueur.
     * 
     * @param n Le niveau à définir.
     */
    protected void setLevel(int n) {
        level = n;
    }

    /**
     * Définit le score du joueur.
     * 
     * @param n Le score à définir.
     */
    protected void setScore(int n) {
        score = n;
    }

    /**
     * Définit le nombre de vies restantes du joueur.
     * 
     * @param n Le nombre de vies à définir.
     */
    protected void setLife(int n) {
        life = n;
    }

    /**
     * Incrémente le niveau du joueur de 1.
     */
    protected void addLevel() {
        level++;
    }

    /**
     * Ajoute un certain nombre de points au score du joueur.
     * 
     * @param n Le nombre de points à ajouter.
     */
    protected void addScore(int n) {
        score = score + n >= 0 ? score + n : 0;
    }

    /**
     * Incrémente le nombre de vies restantes du joueur de 1.
     */
    protected void addLife() {
        life++;
    }

    /**
     * Décrémente le nombre de vies restantes du joueur de 1.
     */
    protected void removeLife() {
        life--;
    }
}
