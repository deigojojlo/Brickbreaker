package org.openjfx.Game.GameClass;

import java.util.LinkedList;

import org.openjfx.GUI.GUIGame;
import org.openjfx.Game.GameClass.Scoreboard.ScoreboardController;
import org.openjfx.Game.GameComponent.Ball;
import org.openjfx.Game.GameComponent.Racket;
import org.openjfx.Game.GameComponent.Bricks.Brick;
import org.openjfx.HighscoreTXT.HighScore;
import org.openjfx.LevelJSON.LevelReader;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * Classe représentant un niveau de jeu.
 * Gère la création, l'évolution et la gestion des objets du jeu (briques,
 * balles, raquettes) dans un niveau.
 */
public class Level {
    // Variables d'instance
    private int remainingBrickCount; // Nombre de briques restantes dans le niveau
    private Brick[][] levelGrid; // Matrice représentant les briques du niveau actuel
    private Racket player1Racket; // Raquette contrôlée par le joueur 1
    private Racket player2Racket; // Raquette contrôlée par le joueur 2 (mode coopération)
    private LinkedList<Ball> ballsInPlay = new LinkedList<>(); // Liste des balles présentes dans le jeu
    private boolean isRushMode; // Indique si le mode Rush (enchaînement de niveaux) est activ
    private double ballMaxSpeed; // Vitesse maximale des balles
    private double ballAccelerationRate; // Accélération des balles
    private boolean isCooperativeMode = false; // Indique si le mode coopération est activé
    private final Object levelLock = new Object(); // Objet utilisé pour la synchronisation des threads

    // Références aux composants principaux du jeu
    private ScoreboardController scoreboardController; // Contrôleur du tableau des scores
    private GUIGame gameUI; // Interface graphique du jeu

    /**
     * Constructeur pour un niveau par défaut avec le mode Rush activé.
     *
     * @param scoreboardController Contrôleur du tableau des scores
     * @param guiGame              Interface graphique du jeu
     */
    public Level(ScoreboardController scoreboardController, GUIGame guiGame) {
        this.scoreboardController = scoreboardController;
        this.gameUI = guiGame;
        this.isRushMode = true;
        this.isCooperativeMode = false;
        scoreboardController.setLevel(1, isCooperativeMode);
        this.createDefaultLevel();
    }

    /**
     * Constructeur pour un niveau spécifique.
     *
     * @param scoreboardController Contrôleur du tableau des scores
     * @param guiGame              Interface graphique du jeu
     * @param level                Numéro du niveau à charger
     * @param isCoop               Indique si le mode coopération est activé
     */
    public Level(ScoreboardController scoreboardController, GUIGame guiGame, int level, boolean isCoop) {
        if (level > Game.totalLevels || level <= 0)
            throw new IllegalArgumentException("entrer non valide");
        this.scoreboardController = scoreboardController;
        this.gameUI = guiGame;
        this.isRushMode = false;
        this.isCooperativeMode = isCoop;
        scoreboardController.setLevel(level, isCoop);
        this.createDefaultLevel();
    }

    /**
     * Constructeur pour un niveau personnalisé (chargé depuis un fichier).
     *
     * @param scoreboardController Contrôleur du tableau des scores
     * @param guiGame              Interface graphique du jeu
     * @param level                Nom du fichier du niveau
     */
    public Level(ScoreboardController scoreboardController, GUIGame guiGame, String level) {
        this.scoreboardController = scoreboardController;
        this.gameUI = guiGame;
        this.isCooperativeMode = false;
        this.isRushMode = false;
        this.initializeCustomLevel(level);
    }

    /**
     * Constructeur vide pour tester la balle
     * 
     * @param racket La raquette du joueur 1 utilisée pour les tests.
     */

    public Level(Racket racket) {
        this.player1Racket = racket;
    }

    /**
     * Initialise un niveau personnalisé depuis un fichier.
     *
     * @param level Nom du fichier représentant le niveau
     */
    public void initializeCustomLevel(String level) {
        scoreboardController.setLevel(-1, isCooperativeMode); // Niveau identifié comme "personnalisé"
        levelGrid = LevelReader.read(level, scoreboardController, gameUI, this); // Charge les briques du niveau
        player1Racket = new Racket(Game.originX + Game.gameWidth / 2 - Game.paddleWidth / 2, 700, Game.p1Right,
                Game.p1Left,
                Color.WHITE, this); // Initialise la raquette du joueur 1
        ballsInPlay = new LinkedList<>();
        ballsInPlay
                .add(new Ball((Game.windowWidth / 2) - (gameUI.bonusDisplayWidth), 600, Game.speed,
                        ballAccelerationRate,
                        ballMaxSpeed, this)); // Ajoute une balle initiale
        gameUI.setHandler(player1Racket); // Définit les événements pour la raquette
        scoreboardController.updateBriqueRestante(this.remainingBrickCount);
    }

    /**
     * Crée un niveau standard avec les paramètres actuels.
     */
    public void createDefaultLevel() {
        levelGrid = LevelReader.read(scoreboardController.getLevel(), isCooperativeMode, scoreboardController, gameUI,
                this);
        int position = Game.originX + Game.gameWidth / (isCooperativeMode ? 4 : 2) - Game.paddleWidth / 2;
        player1Racket = new Racket(position, 700, Game.p1Right, Game.p1Left, Color.BLUE, this); // Initialise la
                                                                                                // raquette du
        // joueur 1
        if (isCooperativeMode) { // Mode coopération
            player2Racket = new Racket(Game.originX + (3 * Game.gameWidth) / 4 - Game.paddleWidth / 2, 700,
                    Game.p2Right,
                    Game.p2Left, Color.RED, this); // Initialise la raquette du joueur 2
            gameUI.setHandler(player1Racket, player2Racket);
        } else {
            player2Racket = null;
            gameUI.setHandler(player1Racket);
        }
        ballsInPlay = new LinkedList<>();
        ballsInPlay
                .add(new Ball((Game.windowWidth / 2) - (gameUI.bonusDisplayWidth), 600, Game.speed,
                        ballAccelerationRate,
                        ballMaxSpeed, this)); // Ajoute une balle initiale
        scoreboardController.updateBriqueRestante(this.remainingBrickCount);
    }

    /**
     * Retourne la matrice représentant le niveau actuel, avec les briques.
     *
     * @return Un tableau à deux dimensions de {@link Brick} représentant les
     *         briques du niveau.
     */
    public Brick[][] getLevelGrid() {
        return levelGrid;
    }

    /**
     * Indique si le mode coopération est activé.
     *
     * @return {@code true} si le mode coopération est activé, {@code false} sinon.
     */
    public boolean isCooperativeModeEnabled() {
        return isCooperativeMode;
    }

    /**
     * Retourne la raquette utilisée par le joueur 1.
     *
     * @return L'objet {@link Racket} représentant la raquette du joueur 1.
     */
    public Racket getPlayer1Racket() {
        return player1Racket;
    }

    /**
     * Retourne la raquette utilisée par le joueur 2 dans le mode coopération.
     *
     * @return L'objet {@link Racket} représentant la raquette du joueur 2, ou
     *         {@code null} si le mode coopération est désactivé.
     */
    public Racket getPlayer2Racket() {
        return player2Racket;
    }

    /**
     * Retourne le nombre de briques restantes dans le niveau.
     *
     * @return Le nombre de briques encore présentes dans le niveau.
     */
    public int getRemainingBrickCount() {
        return remainingBrickCount;
    }

    /**
     * Indique si le mode "Rush" est activé.
     *
     * @return {@code true} si le mode Rush est activé, {@code false} sinon.
     */
    public boolean isRushModeEnabled() {
        return isRushMode;
    }

    /**
     * Retourne la liste des balles présentes dans le jeu.
     * Une copie de la liste est renvoyée pour éviter toute modification
     * concurrente.
     *
     * @return Une copie de la liste des balles ({@link Ball}).
     */
    public LinkedList<Ball> getBallsInPlay() {
        synchronized (levelLock) {
            return new LinkedList<>(ballsInPlay);
        }
    }

    /**
     * Définit la vitesse maximale des balles.
     *
     * @param x Nouvelle vitesse maximale.
     */
    public void setMaximumBallSpeed(double x) {
        ballMaxSpeed = x;
    }

    /**
     * Modifie la touche utilisée pour déplacer la raquette du joueur 1 vers la
     * droite.
     *
     * @param racketRightP1 Nouvelle touche pour déplacer la raquette du joueur 1
     *                      vers la droite.
     */
    public void setRacketRightP1(KeyCode racketRightP1) {
        Game.p1Right = racketRightP1;
    }

    /**
     * Modifie la touche utilisée pour déplacer la raquette du joueur 1 vers la
     * gauche.
     *
     * @param racketLeftP1 Nouvelle touche pour déplacer la raquette du joueur 1
     *                     vers la gauche.
     */
    public void setRacketLeftP1(KeyCode racketLeftP1) {
        Game.p1Left = racketLeftP1;
    }

    /**
     * Modifie la touche utilisée pour déplacer la raquette du joueur 2 vers la
     * droite.
     *
     * @param racketRightP2 Nouvelle touche pour déplacer la raquette du joueur 2
     *                      vers la droite.
     */
    public void setRacketRightP2(KeyCode racketRightP2) {
        Game.p2Right = racketRightP2;
    }

    /**
     * Modifie la touche utilisée pour déplacer la raquette du joueur 2 vers la
     * gauche.
     *
     * @param racketLeftP2 Nouvelle touche pour déplacer la raquette du joueur 2
     *                     vers la gauche.
     */
    public void setRacketLeftP2(KeyCode racketLeftP2) {
        Game.p2Left = racketLeftP2;
    }

    /**
     * Définit la vitesse des balles.
     * La vitesse moyenne est calculée à partir des deux valeurs fournies.
     *
     * @param x Nouvelle vitesse horizontale.
     * @param y Nouvelle vitesse verticale.
     */
    public void setBallSpeed(double x, double y) {
        synchronized (levelLock) {
            for (Ball ball : ballsInPlay) {
                ball.setBallSpeed(x, y);
            }
        }
    }

    /**
     * Définit l'accélération des balles.
     *
     * @param a Nouvelle valeur pour l'accélération.
     */
    public void setBallAccelerationRate(double a) {
        ballAccelerationRate = a;
    }

    /**
     * Définit le nombre de briques restantes dans le niveau.
     *
     * @param n Nouveau nombre de briques restantes.
     */
    public void setRemainingBrickCount(int n) {
        remainingBrickCount = n;
    }

    /**
     * Retourne la touche utilisée pour déplacer la raquette du joueur 1 vers la
     * gauche.
     *
     * @return La touche associée au déplacement gauche de la raquette du joueur 1.
     */
    public KeyCode getPlayer1RacketLeftKey() {
        return Game.p1Left;
    }

    /**
     * Retourne la touche utilisée pour déplacer la raquette du joueur 1 vers la
     * droite.
     *
     * @return La touche associée au déplacement droit de la raquette du joueur 1.
     */
    public KeyCode getPlayer1RacketRightKey() {
        return Game.p1Right;
    }

    /**
     * Retourne la touche utilisée pour déplacer la raquette du joueur 2 vers la
     * droite.
     *
     * @return La touche associée au déplacement droit de la raquette du joueur 2.
     */
    public KeyCode getPlayer2RacketRightKey() {
        return Game.p2Right;
    }

    /**
     * Retourne la touche utilisée pour déplacer la raquette du joueur 2 vers la
     * gauche.
     *
     * @return La touche associée au déplacement gauche de la raquette du joueur 2.
     */
    public KeyCode getPlayer2RacketLeftKey() {
        return Game.p2Left;
    }

    /**
     * Dessine toutes les briques du niveau à l'écran.
     * Les briques identiques consécutives ne sont dessinées qu'une seule fois.
     */
    public void drawLevel() {
        Brick lastBrick = null;
        for (int j = 0; j < levelGrid.length; j++) {
            for (int i = 0; i < levelGrid[j].length; i++) {
                if (levelGrid[j][i] != null && levelGrid[j][i] != lastBrick) {
                    lastBrick = levelGrid[j][i];
                    levelGrid[j][i].drawRacket();
                }
            }
        }
    }

    /**
     * Supprime une brique à une position donnée dans la matrice.
     * Efface également toutes les occurrences consécutives de la même brique.
     * Redessine la zone de jeu et met à jour l'affichage du nombre de briques
     * restantes.
     *
     * @param x Coordonnée x (colonne) de la brique à supprimer.
     * @param y Coordonnée y (ligne) de la brique à supprimer.
     */
    public void remove(int x, int y) {
        synchronized (levelLock) {
            Brick toRemove = levelGrid[y][x];
            int i = 0;
            // Efface toutes les occurrences consécutives de la brique
            while (x + i < levelGrid[0].length && levelGrid[y][x + i] == toRemove) {
                levelGrid[y][x + i] = null;
                i++;
            }
            // Redessine la zone de jeu
            Game.graphicsContext.setFill(gameUI.getBG());
            Game.graphicsContext.fillRect(Game.originX + x * Game.unit, Game.originY + y * Game.unit,
                    toRemove.getWidth(), toRemove.getHeight());
            remainingBrickCount--;
            scoreboardController.updateBriqueRestante(this.remainingBrickCount); // Mise à jour de l'affichage
            if (remainingBrickCount == 0) { // Si plus de briques restantes, le niveau est terminé
                endGame();
            }
        }
    }

    /**
     * Supprime une balle de la liste des balles en jeu.
     * Change la couleur de la balle en arrière-plan pour l'effacer graphiquement.
     * Si aucune balle ne reste, une vie est retirée et le comportement associé est
     * déclenché.
     *
     * @param b La balle ({@link Ball}) à supprimer.
     */
    public void removeBall(Ball b) {
        ballsInPlay.remove(b);
        b.setColor(gameUI.getBG());
        gameUI.removeBonusList();
        if (ballsInPlay.isEmpty()) {
            scoreboardController.removeLife();
            onRemoveLife();
            Platform.runLater(() -> {
                gameUI.clearBonusList();
            });
        }
    }

    /**
     * Réinitialise le niveau et la liste des balles.
     * Efface toutes les balles et définit un niveau vide.
     */
    public void clearLevel() {
        ballsInPlay = new LinkedList<Ball>();
        levelGrid = new Brick[1][1];
    }

    /**
     * Méthode appelée lorsqu'une vie est retirée.
     * Si le joueur n'a plus de vies, le score est enregistré et le statut de fin de
     * partie est défini.
     * Sinon, le statut de changement de niveau est défini et une nouvelle balle est
     * créée.
     */
    private void onRemoveLife() {
        scoreboardController.addScore(-25);
        if (scoreboardController.getLife() == 0) {
            gameUI.setPlay(4); // Statut : fin de partie
            // Enregistrement du score
            HighScore.addScore(isRushMode, isCooperativeMode, scoreboardController.getScore(),
                    scoreboardController.getLevel());
        } else {
            gameUI.setPlay(5); // Statut : changement de niveau
            // Création d'une nouvelle balle
            Ball b = new Ball((Game.windowWidth / 2) - (gameUI.bonusDisplayWidth), 600, Game.speed,
                    ballAccelerationRate,
                    ballMaxSpeed, this);
            ballsInPlay.add(b); // Ajout de la balle à la liste
            b.drawRacket(); // Dessin de la balle pour la visibilité du joueur
            player1Racket.setRacketDimensions(Game.unit * 8, Game.unit); // Réinitialisation de la taille de la raquette
        }
    }

    /**
     * Ajoute une nouvelle balle au jeu en dupliquant une balle existante.
     * La vitesse de la balle ajoutée est inversée par rapport à celle de la balle
     * originale.
     */
    public void addBall() {
        Ball b1 = new Ball(ballsInPlay.getFirst());
        b1.setBallSpeed(((int) b1.getVelocityX()) * -1, ((int) b1.getVelocityY()) * -1);
        ballsInPlay.add(b1);
    }

    /**
     * Méthode appelée périodiquement pour gérer les mises à jour du jeu.
     * Met à jour les positions des raquettes et des balles, puis redessine les
     * éléments du jeu.
     */
    public void updateGameObjects() {
        synchronized (levelLock) {
            player1Racket.updateRacketPosition();
            if (this.isCooperativeMode && player2Racket != null) {
                player2Racket.updateRacketPosition();
            }
            for (Ball ball : new LinkedList<>(ballsInPlay)) {
                if (ball != null) {
                    ball.time(false);
                }
            }
            drawLevel();
            player1Racket.drawRacket();
            if (this.isCooperativeMode && player2Racket != null) {
                player2Racket.drawRacket();
            }
        }
    }

    /**
     * Termine le jeu.
     * Enregistre le score final si le mode Rush est terminé ou que tous les niveaux
     * ont été complétés.
     * Sinon, prépare le niveau suivant dans le mode Rush.
     */
    public void endGame() {
        if (!isRushMode || (isRushMode && Game.totalLevels == scoreboardController.getLevel())) {
            gameUI.setPlay(3); // Statut : fin de partie
            Pair<Integer, String> p = new Pair<>(scoreboardController.getScore(), Game.playerName);
            Pair<Integer, Boolean> key = new Pair<>(scoreboardController.getLevel(), isCooperativeMode);
            HighScore.addScore(key, p);
        } else if (isRushMode) {
            gameUI.setPlay(0); // Préparation du niveau suivant
            scoreboardController.addLevel();
            this.ballsInPlay.forEach(b -> {
                ballsInPlay.remove(b);
                b.setColor(gameUI.getBG());
                b.drawRacket();
            });
            this.createDefaultLevel(); // Création du niveau suivant
            Platform.runLater(() -> {
                gameUI.clearBonusList();
                gameUI.clear();
            });
        }
    }

    /**
     * Methode pour les testes de la balle
     * 
     * @param bricks represente le nouveau level
     */
    public void setLevelGrid(Brick[][] bricks) {
        this.levelGrid = bricks;
    }

}