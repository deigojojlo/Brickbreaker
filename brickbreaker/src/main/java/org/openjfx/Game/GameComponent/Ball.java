package org.openjfx.Game.GameComponent;

import org.openjfx.Game.GameClass.Game;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameComponent.Bricks.Brick;
import org.openjfx.Sound.SoundController;

import javafx.scene.paint.Color;

/**
 * Classe représentant la balle
 *
 * Représente une balle dans le jeu, avec sa position, sa vitesse, son
 * accélération et ses interactions avec les raquettes et les briques.
 * Cette classe permet de gérer la physique de la balle (vitesse, accélération,
 * collisions) dans le jeu.
 */
public class Ball extends Component {

    /**
     * La vitesse de la balle selon l'axe X en pixels par tic.
     * Initialisée à la vitesse définie dans le jeu.
     */
    protected double velocityX = Game.speed;

    /**
     * La vitesse de la balle selon l'axe Y en pixels par tic.
     * Initialisée à la vitesse définie dans le jeu, mais dirigée vers le haut.
     */
    protected double velocityY = -Game.speed;

    /**
     * L'accélération de la balle en pixels par tic^2.
     * L'accélération peut être modifiée pour ajuster le comportement du jeu.
     */
    protected double acceleration;

    /**
     * La raquette du joueur 1 dans le jeu.
     * Utilisée pour détecter les collisions avec la balle.
     */
    protected Racket player1Racket;

    /**
     * La raquette du joueur 2 dans le jeu. Elle peut être `null` si le jeu est joué
     * en mode un joueur.
     * Utilisée pour détecter les collisions avec la balle dans les jeux
     * coopératifs.
     */
    protected Racket player2Racket;

    /**
     * La valeur maximale permise pour le carré de la vitesse totale de la balle.
     * Utilisée pour limiter la vitesse de la balle et éviter qu'elle n'atteigne une
     * vitesse trop élevée.
     * Par défaut, cette valeur est fixée à 30.
     */
    protected double maxSpeedSquaredLimit = 30;

    /**
     * Constructeur de la balle.
     * Ce constructeur initialise la position de la balle, ses propriétés graphiques
     * (taille, couleur),
     * et les références aux raquettes des joueurs (P1 et P2) à partir du niveau de
     * jeu spécifié.
     *
     * @param x      La position X initiale de la balle.
     * @param y      La position Y initiale de la balle.
     * @param parent Le niveau actuel du jeu. Il permet de récupérer les raquettes
     *               des joueurs (P1 et P2)
     *               avec lesquelles la balle pourra entrer en collision.
     */
    public Ball(int x, int y, Level parent) {
        super(1, x, y, 0, parent); // Appel au constructeur parent pour initialiser les propriétés de la classe de
                                   // base.
        this.setWidth(12); // Définition de la largeur de la balle.
        this.setHeight(12); // Définition de la hauteur de la balle.
        this.setColor(Color.WHITE); // Définition de la couleur de la balle.
        this.player1Racket = parent.getPlayer1Racket(); // Récupération de la raquette du joueur 1.
        this.player2Racket = parent.getPlayer2Racket(); // Récupération de la raquette du joueur 2.
    }

    /**
     * Constructeur de la balle.
     * Ce constructeur initialise la position, la vitesse, l'accélération, la
     * vitesse maximale et les propriétés graphiques de la balle,
     * ainsi que les références aux raquettes des joueurs (P1 et P2).
     * 
     * @param x            La position X initiale de la balle.
     * @param y            La position Y initiale de la balle.
     * @param vitesse      La vitesse initiale de la balle (appliquée à la fois à la
     *                     direction X et Y). La vitesse initiale en Y est inversée
     *                     pour que la balle parte vers le haut.
     * @param acceleration L'accélération de la balle dans ce niveau de jeu. Elle
     *                     permet d'ajuster la vitesse au fur et à mesure du temps.
     * @param maxSpeed     La vitesse maximale que la balle peut atteindre. Si la
     *                     vitesse maximale est supérieure à la valeur par défaut,
     *                     elle est mise à jour.
     * @param parent       Le niveau actuel du jeu. Il est utilisé pour récupérer
     *                     les raquettes des joueurs (P1 et P2) auxquelles la balle
     *                     pourrait entrer en collision.
     */
    public Ball(int x, int y, double vitesse, double acceleration, double maxSpeed, Level parent) {
        super(1, x, y, 0, parent); // Appel au constructeur parent pour initialiser les propriétés de la classe de
                                   // base.
        velocityX = vitesse; // Définition de la vitesse de la balle en X.
        velocityY = -vitesse; // La vitesse en Y est négative pour que la balle parte vers le haut.
        this.acceleration = acceleration; // Initialisation de l'accélération de la balle.
        if (maxSpeed > this.maxSpeedSquaredLimit) {
            this.maxSpeedSquaredLimit = maxSpeed; // Mise à jour de la vitesse maximale si nécessaire.
        }
        this.setWidth(12); // Définition de la largeur de la balle.
        this.setHeight(12); // Définition de la hauteur de la balle.
        this.setColor(Color.WHITE); // Définition de la couleur de la balle.
        this.player1Racket = parent.getPlayer1Racket(); // Récupération de la raquette du joueur 1.
        this.player2Racket = parent.getPlayer2Racket(); // Récupération de la raquette du joueur 2.
    }

    /**
     * Constructeur de la balle
     * 
     * @param b balle qu'on copie
     */
    public Ball(Ball b) {
        super(1, b.getPositionX(), b.getPositionY(), 0, b.getParentLevel());
        velocityX = b.velocityX;
        velocityY = b.velocityY;
        acceleration = b.acceleration;
        this.maxSpeedSquaredLimit = b.maxSpeedSquaredLimit;
        this.setWidth(b.getWidth());
        this.setHeight(b.getHeight());
        this.setColor(b.getColor());
        this.player1Racket = b.getParentLevel().getPlayer1Racket();
        this.player2Racket = b.getParentLevel().getPlayer2Racket();
    }

    /**
     * Getter pour la vitesse de la balle selon y
     * 
     * @return la vitesse de la balle selon y
     */

    public double getVelocityY() {
        return velocityY;
    }

    /**
     * Getter pour la vitesse de la balle selon x
     * 
     * @return la vitesse de la balle selon x
     */
    public double getVelocityX() {
        return velocityX;
    }

    /**
     * Setter pour les vitesses selon x et y
     * 
     * @param vx nouveau vitesse selon x
     * @param vy nouveau vitesse selon y
     */
    public void setBallSpeed(double vx, double vy) {
        velocityX = vx;
        velocityY = vy;
    }

    /**
     * Setter pour l'accélération
     * 
     * @param a nouveau accélération
     */
    public void setBallAcceleration(double a) { // acceleration en pixel/tic^2
        acceleration = a;
    }

    /**
     * Getter pour l'accélération
     * 
     * @return l'accélération de la balle
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * Met à jour la position de la balle
     * 
     * @param testing a boolean used to indicate if this method is called during
     *                testing (in that case we ignore code dealing with the gui and
     *                sounds)
     */
    /**
     * Met à jour la position de la balle et gère les collisions avec les bords, les
     * raquettes et les briques.
     * Cette méthode calcule également la vitesse de la balle en fonction de
     * l'accélération, et met à jour sa position
     * dans l'espace de jeu. Si un test est effectué (paramètre `testing`),
     * certaines actions comme le dessin de la balle
     * et les sons sont ignorées.
     *
     * @param testing Un booléen indiquant si cette méthode est appelée pendant un
     *                test. Si `true`, le code lié à
     *                l'interface graphique et aux sons est ignoré, permettant ainsi
     *                un test plus rapide sans interférences
     *                visuelles ou sonores.
     * 
     * @return Un booléen indiquant si une collision a eu lieu. Retourne `true` si
     *         la balle a collisionné avec un bord,
     *         une raquette ou une brique, sinon retourne `false`.
     */
    public boolean time(boolean testing) {
        if (!testing) {
            draw(Game.gameBackground); // Si pas en mode test, on redessine le fond du jeu.
        }

        double ballX = super.getPositionX(); // Coordonnée X du coin supérieur gauche de la balle.
        double ballY = super.getPositionY(); // Coordonnée Y du coin supérieur gauche de la balle.

        // Mise à jour de la vitesse selon X et Y si la balle n'a pas atteint la vitesse
        // maximale.
        if (velocityX * velocityX + velocityY * velocityY < maxSpeedSquaredLimit) {
            velocityX = Math.signum(velocityX) * (Math.abs(velocityX) + acceleration);
            velocityY = Math.signum(velocityY) * (Math.abs(velocityY) + acceleration);
        }

        // Calcul des nouvelles coordonnées de la balle en fonction de sa vitesse.
        double nextX = ballX + velocityX;
        double nextY = ballY + velocityY;

        // Test de collision avec les bords de l'écran.
        boolean borderCollision = borderCollisionTest();

        // Test de collision avec la raquette du joueur 1.
        boolean racketP1Collision = racketCollisionTest(player1Racket);
        boolean racketP2Collision = false;
        // Si la collision avec le joueur 1 a lieu, on joue un son si ce n'est pas en
        // mode test.
        if (racketP1Collision && !testing) {
            SoundController.collisionRacketSound();
        } else if (super.getParentLevel().isCooperativeModeEnabled()) {
            // Si en mode coopération, on teste également la collision avec la raquette du
            // joueur 2.
            racketP2Collision = racketCollisionTest(player2Racket);
            if (racketP2Collision && !testing) {
                SoundController.collisionRacketSound();
            }
        }

        // Test de collision avec les briques.
        String classBrick = brickCollisionTest(testing);
        boolean brickCollision = !(classBrick == null);
        // Si une collision avec une brique a eu lieu, on joue un son si ce n'est pas en
        // mode test.
        if (brickCollision && !testing) {
            SoundController.collisionBrickSound(classBrick);
        }

        // Si aucune collision n'a eu lieu, on déplace la balle.
        boolean collision = borderCollision || racketP1Collision || racketP2Collision || brickCollision;
        if (!collision) {
            super.setPositionX(nextX); // Mise à jour de la position X de la balle.
            super.setPositionY(nextY); // Mise à jour de la position Y de la balle.
        }

        // Redessiner la balle si ce n'est pas en mode test.
        if (!testing) {
            drawRacket();
        }

        // Retourne `true` si une collision a eu lieu, sinon retourne `false`.
        return collision;
    }

    /**
     * fonction qui gere la collision avec les briques
     * 
     * @return un String qui indique si une collision a eu place (si il est différen
     *         de null) et sinon est utilisé pour les bruitages
     */
    /**
     * Teste les collisions de la balle avec les briques du niveau. Si une collision
     * a lieu, la direction de la balle
     * est inversée, et la brique est supprimée si ce n'est pas en mode test. Si un
     * test est effectué, cette méthode
     * retourne un message indiquant qu'une brique a été supprimée.
     *
     * Cette méthode vérifie la collision en examinant les points de la balle par
     * rapport aux briques voisines et
     * en inversant la direction de la balle en fonction de la brique touchée.
     * 
     * @param testing Un booléen indiquant si cette méthode est appelée pendant un
     *                test. Si `true`, la brique est
     *                supprimée virtuellement, et un message est retourné. Si
     *                `false`, la brique est réellement supprimée
     *                du niveau.
     * 
     * @return Si une collision avec une brique a eu lieu, retourne le nom de la
     *         classe de la brique touchée.
     *         Si aucune collision n'a eu lieu, retourne `null`.
     */
    protected String brickCollisionTest(boolean testing) {
        // Initialisation de la variable qui contiendra le nom de la brique touchée
        String collidedBrickType = null;

        // Récupération des coordonnées de la balle
        double ballX = super.getPositionX();
        double ballY = super.getPositionY();
        double ballSideLength = super.getWidth(); // Taille du côté de la balle (supposée carrée)

        // Points de collision possibles avec la balle (calculés selon la position et la
        // vitesse)
        double newX = ballX + velocityX;
        double newY = ballY + velocityY;

        // Points représentant les quatre bords de la balle
        double xNorth = newX + ballSideLength / 2;
        double yNorth = newY;
        double xEast = newX + ballSideLength;
        double yEast = newY + ballSideLength / 2;
        double xSouth = newX + ballSideLength / 2;
        double ySouth = newY + ballSideLength;
        double xWest = newX;
        double yWest = newY + ballSideLength / 2;

        // Tableau des briques dans le niveau actuel
        Brick[][] wall = super.getParentLevel().getLevelGrid();

        // Récupération des briques adjacentes à la balle
        Brick[][] adjacentBricks = adjacentBricks(newX, newY, wall);
        if (adjacentBricks.length == 0) {
            return null; // Retourne null si aucune brique adjacente
        }

        // Boucle pour tester les collisions avec chaque brique voisine
        for (int i = 0; i < adjacentBricks.length; i++) {
            for (int j = 0; j < adjacentBricks[i].length; j++) {

                Brick adjacentBrick = adjacentBricks[i][j];

                if (adjacentBrick != null) {
                    // Teste si un des points de la balle touche la brique
                    boolean nCollision = isInComponent(xNorth, yNorth, adjacentBrick);
                    boolean sCollision = isInComponent(xSouth, ySouth, adjacentBrick);
                    boolean eCollision = isInComponent(xEast, yEast, adjacentBrick);
                    boolean wCollision = isInComponent(xWest, yWest, adjacentBrick);

                    boolean collision = nCollision || sCollision || eCollision || wCollision;

                    // Inversion de la direction de la balle selon le point de collision
                    if (eCollision) {
                        velocityX *= -1; // Inversion horizontale
                    }
                    if (nCollision || sCollision) {
                        velocityY *= -1; // Inversion verticale
                    }
                    if (wCollision) {
                        velocityX *= -1; // Inversion horizontale
                    }

                    // Si une collision a eu lieu, suppression de la brique (sauf en mode test)
                    if (collision) {
                        if (!testing) {
                            adjacentBrick.remove(); // Supprime la brique du niveau
                            collidedBrickType = adjacentBrick.getClass().getSimpleName(); // Récupère le nom de la
                                                                                          // classe de la
                            // brique
                        } else {
                            return "brick was removed"; // Retourne un message si en mode test
                        }
                        break; // Sortie de la boucle dès qu'une collision est détectée
                    }
                }
            }
        }

        // Vérification si l'angle de la trajectoire de la balle est trop vertical ou
        // trop horizontal
        limitBallAngle(0.2);

        // Mise à jour des coordonnées de la balle après la collision
        this.setPositionX(ballX);
        this.setPositionY(ballY);

        // Retourne le nom de la classe de la brique touchée ou null si aucune collision
        return collidedBrickType;
    }

    /**
     * Fonction qui nous dit si la point de coordonnées (x,y) est dans le bord d'un
     * Component
     * 
     * @param x         coordonné x du point
     * @param y         coordonné y du point
     * @param component le component qu'on teste
     * @return ub boolean qui indique si une collision a eu place
     */
    protected boolean isInComponent(double x, double y, Component component) {
        double componentX = component.getPositionX();
        double componentY = component.getPositionY();
        double componentWidth = component.getWidth();
        double componentHeight = component.getHeight();

        return componentX <= x && x <= componentX + componentWidth
                && componentY <= y && y <= componentY + componentHeight;
    }

    /**
     * fonction qui gere les collisions ave le bord du fenetre du jeu
     * 
     * @return un boolean qui indique si une collision a eu place
     */
    protected boolean borderCollisionTest() {
        // On récupère les coordonnées et la taille de la balle
        double ballX = super.getPositionX();
        double ballY = super.getPositionY();
        double ballSideLength = super.getWidth();

        // points de collision possible avec le bord du jeu
        double newX = ballX + velocityX;
        double newY = ballY + velocityY;

        // coordonnes des points sur le bord de la balle
        double yNorth = newY;
        double xEast = newX + ballSideLength;
        double ySouth = newY + ballSideLength;
        double xWest = newX;

        // on teste quelques points sur la balle
        boolean nCollision = yNorth < Game.originY;
        boolean sCollision = ySouth > Game.originY + Game.gameHeight;
        boolean wCollision = xWest < Game.originX;
        boolean eCollision = xEast > Game.originX + Game.gameWidth;

        if (eCollision) {
            velocityX *= -1; // Inverser la direction horizontal
        }
        if (wCollision) {
            velocityX *= -1; // Inverser la direction horizontale
        }
        if (nCollision) {
            velocityY *= -1; // Inverser la direction verticale
        }
        if (sCollision) {
            super.getParentLevel().removeBall(this);
        }
        // on verifie que la trajectoire de la balle n'est pas trop vericale ou trop
        // horizontale
        limitBallAngle(0.2);
        return nCollision || wCollision || eCollision || sCollision;
    }

    /**
     * fonction auxiliaire pour brickCollisionTest()
     * 
     * @param ballX coordonné x de la balle
     * @param ballY cordonné y de la balle
     * @param wall  tableau contenant tous les briques du niveau actuel
     * @return un tableau de taille 3x3 contenant le morceau du niveau autour de la
     *         balle
     */
    protected Brick[][] adjacentBricks(double ballX, double ballY, Brick[][] wall) {
        int numberOfBricksAbove = ((int) ballY - Game.originY) / Game.unit;
        int numberOfBricksToTheLeft = ((int) ballX - Game.originX) / Game.unit;
        Brick[][] r = new Brick[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (0 <= numberOfBricksAbove + i - 1
                        && numberOfBricksAbove + i - 1 < wall.length
                        && 0 <= numberOfBricksToTheLeft + j - 1
                        && numberOfBricksToTheLeft + j - 1 < wall[i].length) {

                    r[i][j] = wall[numberOfBricksAbove + i - 1][numberOfBricksToTheLeft + j - 1];
                }
            }
        }
        return r;
    }

    /**
     * fonction qui gere les collisions avec une raquette
     * 
     * @param racket le racket qu'on regarde
     * @return un boolean qui indique si une collision a eu place
     */
    public boolean racketCollisionTest(Racket racket) {

        // on recupére les coordonnées et les dimensions de la balle
        double ballX = super.getPositionX();
        double ballY = super.getPositionY();
        double ballSideLength = super.getWidth();

        // points de collision possible avec le bord du jeu
        double newX = ballX + velocityX;
        double newY = ballY + velocityY;

        // point sur la droite du balle
        double xEast = newX + ballSideLength;
        double yEast = newY + ballSideLength / 2;

        // point sur le bas du balle
        double xSouth = newX + ballSideLength / 2;
        double ySouth = newY + ballSideLength;

        // point sur le gauche du balle
        double xWest = newX;
        double yWest = newY + ballSideLength / 2;

        boolean SCollision = isInComponent(xSouth, ySouth, racket);
        boolean WCollision = isInComponent(xWest, yWest, racket);
        boolean ECollision = isInComponent(xEast, yEast, racket);

        boolean collision = SCollision || WCollision || ECollision;

        if ((SCollision && ECollision) || (SCollision && WCollision)) {
            velocityX *= -1;
            velocityY *= -1;
        } else if (ECollision) {
            velocityX *= -1; // Inverser la direction horizontale
            velocityY = -(Math.abs(velocityY));
        } else if (WCollision) {
            velocityX *= -1; // Inverser la direction horizontale
            velocityY = -(Math.abs(velocityY));
        } else if (SCollision) {
            velocityY *= -1;
        }
        if ((ballX <= Game.originX || ballX + ballSideLength >= Game.originX + Game.gameWidth)
                && (ECollision || WCollision)) {
            ballX = Game.originX;
        }
        return collision;
    }

    /**
     * fonction auxiliaire qui calcule x%n mais donne une valeur entre 0 et n
     * 
     * @param x le nombre qu'on reduit
     * @param n le module
     * @return x%n mais donne une valeur entre 0 et n
     */
    public static double wrapModulo(double x, double n) {
        return ((x % n) + n) % n;
    }

    /**
     * fonction auxiliaire qui limite la trajectoire de la balle pour qu'elle
     * devient pas trop verticale our horizontale
     * 
     * @param epsilon la distance qu'on permet entre l'angle du trajectoireet un
     *                angle verticale/horizontale (en radians)
     */
    protected void limitBallAngle(double epsilon) {
        double ballAngle = (velocityX == 0) ? Math.signum(velocityY) * Math.PI / 2
                : wrapModulo(Math.atan(velocityY / velocityX), 2 * Math.PI);

        double[] keyAngles = { 0, Math.PI / 2, Math.PI, 3 * Math.PI / 2, 2 * Math.PI };

        for (double theta : keyAngles) {

            if (Math.abs(ballAngle - theta) < epsilon) {

                double d1 = Math.abs(ballAngle - (theta + 2 * epsilon));
                double d2 = Math.abs(ballAngle - (theta - 2 * epsilon));

                if (d1 < d2) {
                    ballAngle = (theta + 2 * epsilon) % 2 * Math.PI;
                } else {
                    ballAngle = (theta - 2 * epsilon) % 2 * Math.PI;
                }

                double v = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
                velocityX = v * Math.cos(ballAngle);
                velocityY = v * Math.sin(ballAngle);

            }
        }

    }

    /**
     * fonction qui indique si la balle intersecte un telle component
     * 
     * @param component le component qu'on regarde
     * @return un boolean qui indique si la balle intersecte le component
     */
    protected boolean detectBallIntersection(Component component) {
        double x = super.getPositionX();
        double y = super.getPositionY();
        double ballSideLength = super.getWidth();
        double xBall = x + velocityX;
        double yBall = y + velocityY;
        boolean collision = false;
        collision |= this.isInComponent(xBall, yBall, component);
        collision |= this.isInComponent(xBall + ballSideLength, yBall, component);
        collision |= this.isInComponent(xBall, yBall + ballSideLength, component);
        collision |= this.isInComponent(xBall + ballSideLength, yBall + ballSideLength, component);
        return collision;
    }

    /**
     * dessine la balle sur le canvas
     */
    @Override
    public void drawRacket() {
        Game.graphicsContext.setFill(Color.WHITE);
        Game.graphicsContext.fillOval(this.getPositionX(), this.getPositionY(), this.getWidth(), this.getHeight());
    }

    /**
     * Pour optimiser les performances, on evite de tout redessiner alors on efface
     * juste la position précedente
     * 
     * @param c la couleur du BG
     */
    public void draw(Color c) {
        Game.graphicsContext.setFill(c);
        Game.graphicsContext.fillRect(this.getPositionX(), this.getPositionY(), this.getWidth(), this.getHeight());
    }

}
