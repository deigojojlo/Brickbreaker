package org.openjfx.Game.GameClass.Scoreboard;

import org.openjfx.BrickBreaker;
import org.openjfx.HighscoreTXT.HighScore;
import org.openjfx.Game.GameClass.Game;
import org.openjfx.Game.GameClass.Time;

import javafx.scene.layout.BorderPane;
import javafx.util.Pair;

/**
 * Le contrôleur de la vue du tableau de scores, qui gère les interactions avec
 * le modèle de données
 * du tableau de scores et met à jour la vue en fonction des événements du jeu.
 * 
 * Cette classe agit comme un intermédiaire entre la vue (`ScoreboardView`), le
 * modèle (`ScoreboardModel`),
 * et le reste de l'application en traitant les entrées de l'utilisateur et en
 * mettant à jour l'interface utilisateur.
 */
public class ScoreboardController {
    private ScoreboardView view;
    private ScoreboardModel model;

    private BrickBreaker frame;

    /**
     * Constructeur du contrôleur de tableau de scores.
     * 
     * @param parent Le parent du panneau principal pour la vue du tableau de
     *               scores.
     * @param frame  L'instance de la fenêtre principale du jeu.
     */
    public ScoreboardController(BorderPane parent, BrickBreaker frame) {
        this.frame = frame;
        this.model = new ScoreboardModel();
        this.view = new ScoreboardView(parent);

        setButtonHandler();
    }

    /**
     * Constructeur avec en paramètre parent, frame, view et model pour les tests.
     * 
     * @param parent Le parent du panneau principal pour la vue du tableau de
     *               scores.
     * @param frame  L'instance de la fenêtre principale du jeu.
     * @param view   La vue associée au tableau de scores.
     * @param model  Le modèle de données pour le tableau de scores.
     */
    public ScoreboardController(BorderPane parent, BrickBreaker frame, ScoreboardView view, ScoreboardModel model) {
        this.frame = frame;
        this.model = model;
        this.view = view;

        setButtonHandler();
    }

    /**
     * Affiche la vue du tableau de scores.
     */
    public void display() {
        this.view.display();
    }

    /**
     * Met à jour le temps affiché dans le tableau de scores.
     * Le temps est mis à jour en fonction du jeu.
     */
    public void time() {
        this.model.addTime((int) (Math.floor(Game.duration / Game.rate)));
        this.view.setTime(this.model.getMinutes(), this.model.getSecondes());
    }

    /**
     * Retourne le niveau actuel du jeu.
     * 
     * @return Le niveau actuel.
     */
    public int getLevel() {
        return this.model.getLevel();
    }

    /**
     * Retourne le score actuel du jeu.
     * 
     * @return Le score actuel.
     */
    public int getScore() {
        return this.model.getScore();
    }

    /**
     * Retourne le temps écoulé depuis le début du jeu.
     * 
     * @return Le temps écoulé.
     */
    public int getTime() {
        return this.model.getTime();
    }

    /**
     * Retourne le nombre de vies restantes.
     * 
     * @return Le nombre de vies restantes.
     */
    public int getLife() {
        return this.model.getLife();
    }

    /**
     * Met à jour le niveau dans le modèle et la vue.
     * 
     * @param n      Le niveau à définir.
     * @param isCoop Indique si le jeu est en mode coopération.
     */
    public void setLevel(int n, boolean isCoop) {
        this.model.setLevel(n);
        this.view.setLevel(this.model.getLevel());
        this.setHighscoreLabelValue(isCoop);
    }

    /**
     * Met à jour le score dans le modèle et la vue.
     * 
     * @param n Le score à définir.
     */
    public void setScore(int n) {
        this.model.setScore(n);
        this.view.setScore(this.model.getScore());
    }

    /**
     * Met à jour le nombre de vies restantes dans le modèle et la vue.
     * 
     * @param n Le nombre de vies à définir.
     */
    public void setLife(int n) {
        this.model.setLife(n);
        this.view.setLife(this.model.getLife());
    }

    /**
     * Ajoute un niveau au jeu et met à jour la vue.
     */
    public void addLevel() {
        this.model.addLevel();
        this.view.setLevel(this.model.getLevel());
    }

    /**
     * Ajoute des points au score et met à jour la vue.
     * 
     * @param n Le nombre de points à ajouter.
     */
    public void addScore(int n) {
        this.model.addScore(n);
        this.view.setScore(this.model.getScore());
    }

    /**
     * Ajoute une vie au joueur et met à jour la vue.
     */
    public void addLife() {
        this.model.addLife();
        this.view.setLife(this.model.getLife());
    }

    /**
     * Retire une vie au joueur et met à jour la vue.
     */
    public void removeLife() {
        this.model.removeLife();
        this.view.setLife(this.model.getLife());
    }

    /**
     * Met à jour le nombre de briques restantes dans la vue.
     * 
     * @param nbBrick Le nombre de briques restantes.
     */
    public void updateBriqueRestante(int nbBrick) {
        this.view.updateRemainingBricks(nbBrick);
    }

    /**
     * Met à jour la valeur du meilleur score dans la vue, en fonction du niveau et
     * du mode de jeu (coopératif ou non).
     * 
     * @param isCoop Indique si le mode coopératif est activé.
     */
    protected void setHighscoreLabelValue(boolean isCoop) {
        Pair<Integer, String> p;
        if (this.model.getLevel() == 0) { // is rush
            p = HighScore.getHighScores().get(new Pair<Integer, Boolean>(0, isCoop)).get(0);
        } else {
            if (HighScore.getHighScores()
                    .containsKey(new Pair<Integer, Boolean>(this.model.getLevel(), isCoop))) {
                p = HighScore.getHighScores()
                        .get(new Pair<Integer, Boolean>(this.model.getLevel(), isCoop))
                        .get(0);
            } else {
                p = new Pair<Integer, String>(0, "Pas de scores enregistrés !");
            }
        }
        this.view.getHighScoreLabelValue()
                .setText(p.getValue().toString() + " " + (p.getKey() == 0 ? "" : p.getKey().toString()));
    }

    /**
     * Définit le gestionnaire d'événements pour le bouton "Home".
     * Lorsque le bouton est cliqué, la fenêtre principale du jeu est réinitialisée.
     */
    protected void setButtonHandler() {
        this.view.getHomeButton().setOnAction(e -> {
            this.frame.setHome();
            Time.stop();
        });
    }
}
