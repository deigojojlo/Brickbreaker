package org.openjfx.Game.GameComponent.Bricks.Bonus;

import java.util.Random;

import org.openjfx.GUI.GUIGame;
import org.openjfx.Game.GameClass.Scoreboard.ScoreboardController;

import javafx.scene.control.Label;

/**
 * La classe {@code Bonus} représente un bonus aléatoire dans le jeu.
 * Elle gère la création et l'application de bonus sur la partie en cours,
 * qui peuvent offrir des avantages comme des vies supplémentaires,
 * des balles supplémentaires, ou un contrôle inversé.
 * 
 * <p>
 * Un bonus est créé de manière aléatoire lors de l'instanciation de la classe.
 * Si un bonus est généré, il peut être appliqué à l'aide de la méthode
 * {@code applybonus()}.
 * </p>
 * 
 * <h2>Fonctionnement</h2>
 * <ul>
 * <li>Un bonus est généré avec une probabilité de 3 sur 50 à l'instanciation de la classe.</li>
 * <li>Si un bonus est généré, un type de bonus est sélectionné parmi plusieurs options possibles.</li>
 * <li>Le bonus est appliqué à l'aide de la méthode {@code applybonus()}, en modifiant les
 * composants du jeu correspondants (score, balles, etc.).</li>
 * </ul>
 * 
 * <h2>Bonus disponibles</h2>
 * <ul>
 * <li>Ajout d'une vie au joueur.</li>
 * <li>Ajout d'une balle supplémentaire.</li>
 * <li>Inversion des contrôles du joueur.</li>
 * </ul>
 * 
 * @see Aleatoire
 * @see GUIGame
 * @see ScoreboardController
 * @see Bonus_vie
 * @see Bonus_balle
 * @see Bonus_switch
 */
public class Bonus {
    private boolean est_bonus;
    private Aleatoire aleatoire;
    private GUIGame guiGame;
    private ScoreboardController scoreboardController;

    /**
     * Constructeur de la classe {@code Bonus}.
     * Le constructeur détermine si un bonus sera appliqué ou non,
     * basé sur une probabilité aléatoire.
     * 
     * @param aleatoire            Instance de la classe {@code Aleatoire} pour la
     *                             sélection du bonus.
     * @param guiGame              Instance de la classe {@code GUIGame} pour
     *                             l'interface utilisateur du jeu.
     * @param scoreboardController Instance de la classe
     *                             {@code ScoreboardController} pour la gestion du
     *                             score.
     */
    public Bonus(Aleatoire aleatoire, GUIGame guiGame, ScoreboardController scoreboardController) {
        this.guiGame = guiGame;
        this.scoreboardController = scoreboardController;
        this.aleatoire = aleatoire;
        Random r = new Random();
        int n = r.nextInt(50);
        // Un bonus est accordé avec une probabilité de 3 sur 50.
        if (n <= 3) {
            est_bonus = true;
        } else {
            est_bonus = false;
        }
    }

    /**
     * Applique un bonus au jeu si un bonus a été généré.
     * Le type de bonus appliqué est sélectionné de manière aléatoire
     * parmi plusieurs possibilités, incluant l'ajout d'une vie,
     * l'ajout d'une balle, ou l'inversion du contrôle du joueur.
     * 
     * <p>
     * La méthode utilise l'instance de {@code Aleatoire} pour sélectionner
     * le type de bonus. Une fois sélectionné, le bonus est appliqué et
     * un message descriptif est ajouté à l'interface utilisateur via
     * {@code GUIGame}.
     * </p>
     * 
     * Types de bonus
     * <ul>
     * <li>Case 0 : Ajout d'une vie.</li>
     * <li>Case 1 : Ajout d'une balle supplémentaire.</li>
     * <li>Case 2 : Inversion des contrôles.</li>
     * </ul>
     */
    public void applybonus() {
        if (est_bonus) {
            // Sélectionne un bonus à appliquer
            int n = aleatoire.select();
            switch (n) {
                case 0:
                    // Bonus de vie
                    Bonus_vie.apply(scoreboardController);
                    Label bonus_vie = new Label("1 vie supplémentaire");
                    guiGame.addBonusList(bonus_vie);
                    break;
                case 1:
                    // Bonus de balle supplémentaire
                    Bonus_balle.apply(this.guiGame.getLevel());
                    Label bonus_balle = new Label("1 balle supplémentaire");
                    guiGame.addBonusList(bonus_balle);
                    break;
                case 2:
                    // Bonus de contrôle inversé
                    Bonus_switch.apply(this.guiGame.getLevel());
                    Label bonus_switch = new Label("/!\\ contrôle inversé");
                    guiGame.addBonusList(bonus_switch);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Vérifie si un bonus a été généré.
     * 
     * @return {@code true} si un bonus a été généré, sinon {@code false}.
     */
    public boolean est_bonus() {
        return est_bonus;
    }

    /**
     * Définit si un bonus est actif ou non.
     * 
     * @param est_bonus {@code true} pour activer un bonus, sinon {@code false}.
     */
    public void setEst_bonus(boolean est_bonus) {
        this.est_bonus = est_bonus;
    }
}
