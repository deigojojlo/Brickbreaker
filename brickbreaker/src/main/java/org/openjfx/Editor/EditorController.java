package org.openjfx.Editor;

import org.openjfx.BrickBreaker;
import org.openjfx.Game.GameClass.Game;
import javafx.util.Pair;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;

/**
 * Le contrôleur de l'éditeur qui gère la logique des interactions avec
 * l'interface graphique de l'éditeur de niveaux.
 * Il permet à l'utilisateur de manipuler les briques et d'organiser leur
 * placement dans la grille.
 * Ce contrôleur fait le lien entre la vue et le modèle, tout en traitant les
 * événements associés aux manipulations.
 * Le contrôleur gère également les positions de glissement (drag-and-drop) des
 * briques dans la grille,
 * ainsi que l'ajout, la suppression et la modification des briques sur la carte
 * du jeu.
 */
public class EditorController {

    /**
     * Vue associée au contrôleur, permettant d'interagir avec l'interface
     * graphique.
     * Cette vue gère l'affichage de l'éditeur et des éléments manipulés par
     * l'utilisateur.
     */
    private EditorView view;

    /**
     * Modèle contenant les données de l'application, incluant la logique métier et
     * la gestion des éléments.
     * Il gère les données de la carte du jeu et les propriétés associées.
     */
    private EditorModel model;

    /**
     * Fenêtre principale de l'application (classe BrickBreaker), utilisée pour
     * gérer les transitions entre les écrans.
     * Permet de revenir à l'écran principal ou d'afficher d'autres interfaces de
     * l'application.
     */
    private BrickBreaker frame;

    /**
     * Dernière position valide de l'objet (sur l'axe X) pendant le déplacement.
     * Cette valeur est utilisée pour restreindre les mouvements de l'objet à une
     * position valide.
     */
    private double lastPermittedX;

    /**
     * Dernière position valide de l'objet (sur l'axe Y) pendant le déplacement.
     * Cette valeur est mise à jour au fur et à mesure du déplacement de l'objet et
     * garantit qu'il reste dans les limites de la grille.
     */
    private double lastPermittedY;

    /**
     * Position initiale de l'objet avant de commencer à le déplacer, en coordonnées
     * X.
     * Elle sert à calculer les déplacements relatifs pendant un drag-and-drop.
     */
    private double initialX;

    /**
     * Position initiale de l'objet avant de commencer à le déplacer, en coordonnées
     * Y.
     * Elle est utilisée pour déterminer les mouvements relatifs et la position de
     * départ pendant le déplacement.
     */
    private double initialY;

    /**
     * Initialise le contrôleur et la vue. Cette méthode est appelée une seule fois
     * pour éviter de recréer les instances lorsque l'utilisateur revient à l'écran
     * principal.
     * 
     * @param frame La fenêtre principale (BrickBreaker) de l'application.
     *              Elle est utilisée pour revenir à l'écran principal et gérer les
     *              transitions d'écran.
     */
    public EditorController(BrickBreaker frame) {
        if (view != null) {
            return; // Si déjà initialisé, on ne fait rien
        }
        this.view = new EditorView();
        this.model = new EditorModel();
        this.frame = frame;
        // Initialise la grille en fonction des dimensions de la carte du modèle
        view.initializeGrid(model.getMap()[0].length, model.getMap().length);
        initEvent(); // Initialise les événements
    }

    /**
     * Initialise les gestionnaires d'événements pour les composants de l'interface.
     */
    public void initEvent() {
        // Retourne à l'écran principal
        view.getBackButton().setOnAction(e -> frame.setHome());

        // Écoute les changements sur le slider d'accélération
        view.getAccelerationSlider().valueProperty()
                .addListener((observable, oldValue, newValue) -> model.setAcceleration(newValue.doubleValue()));

        // Écoute les changements sur le slider de largeur
        view.getMaxSpeedSlider().valueProperty()
                .addListener((observable, oldValue, newValue) -> model.setMaxSpeed(newValue.doubleValue()));

        // Écoute les changements sur le slider de vitesse
        view.getRateSlider().valueProperty()
                .addListener((observable, oldValue, newValue) -> model.setRate(newValue.doubleValue()));

        // Gère le bouton de sauvegarde
        view.getSaveButton().setOnAction(e -> model.save(view.getGridCases()));

        // Active le drag-and-drop pour chaque élément draggable
        view.getDraggabbleNodes().forEach(e -> enableDragAndDrop(e));

        // Gère la suppression de la brique
        view.getDeleteButton()
                .setOnAction(
                        e -> {
                            Noeud n = view.getGridCases().get(new Pair<>((int) lastPermittedX, (int) lastPermittedY));
                            if (n != null)
                                view.removeLabel(n.getNode());
                            view.getGridCases().remove(new Pair<>((int) lastPermittedX, (int) lastPermittedY));
                        });

        // Position de départ pour l'ajout de briques
        int addX = (int) (Game.unit * (model.getMap()[0].length) / 2.0);
        int addY = Game.unit * (model.getMap().length);

        // Ajout de briques spécifiques avec leur gestionnaire d'événements
        view.getSmallBrickLabel().setOnMouseClicked(e -> enableDragAndDrop(view.addBrick(10, addX, addY)));
        view.getLargeBrickLabel().setOnMouseClicked(e -> enableDragAndDrop(view.addBrick(40, addX, addY)));
        view.getUnbreakableSmallBrickLabel().setOnMouseClicked(e -> enableDragAndDrop(view.addBrick(19, addX, addY)));
        view.getUnbreakableLargeBrickLabel().setOnMouseClicked(e -> enableDragAndDrop(view.addBrick(49, addX, addY)));
        view.getDurableLargeBrickLabel().setOnMouseClicked(e -> enableDragAndDrop(view.addBrick(127, addX, addY)));

    }

    /**
     * Active le drag-and-drop pour un élément de l'interface.
     *
     * @param label L'élément graphique à rendre déplaçable.
     */
    protected void enableDragAndDrop(Node label) {
        if (label == null)
            return;

        // Offsets pour calculer la position relative du label pendant le drag
        final double[] offsetX = new double[1];
        final double[] offsetY = new double[1];

        // Gestion du clic initial pour démarrer le drag
        label.setOnMousePressed(event -> {
            offsetX[0] = event.getSceneX() - label.getLayoutX();
            offsetY[0] = event.getSceneY() - label.getLayoutY();

            Noeud last = view.getGridCases().get(new Pair<>((int) lastPermittedX, (int) lastPermittedY));
            if (last != null)
                addOrUpdateStyle(last.getNode(), "-fx-border-width", "0");
            lastPermittedX = Math.round((event.getSceneX() - offsetX[0]) / Game.unit) * Game.unit;
            lastPermittedY = Math.round((event.getSceneY() - offsetY[0]) / Game.unit) * Game.unit;
            initialX = lastPermittedX;
            initialY = lastPermittedY;

            Noeud n = view.getGridCases().get(new Pair<>((int) initialX, (int) initialY));
            if (n != null) {
                addOrUpdateStyle(n.getNode(), "-fx-border-width", "2px");
                addOrUpdateStyle(n.getNode(), "-fx-border-color", "darkgrey");
            }

            // Si clic droit, change le type ou les propriétés de la brique
            if (event.getButton() == MouseButton.SECONDARY) {
                if (n.getType() / 10 == 4) {
                    if (n.getType() % 10 == 9) {
                        return;
                    } else {
                        n.setType(40 + (n.getType() % 10 + 1) % 5);
                    }
                } else if (n.getType() / 100 != 0) {
                    if (n.getType() / 100 == 7) {
                        n.setType(100 + n.getType() % 100);
                    } else {
                        n.setType(n.getType() + 100);
                    }
                    ((Label) n.getNode()).setText("" + n.getType() / 100);
                } else {
                    if (n.getType() % 10 == 9) {
                        return;
                    } else {
                        n.setType(10 + ((n.getType() % 10 + 1) % 9));
                    }
                }
                // Applique une nouvelle couleur au style CSS
                Platform.runLater(() -> {
                    addOrUpdateStyle(n.getNode(), "-fx-background-color", getColor(n.getType()));
                });
            } else if (event.getButton() == MouseButton.MIDDLE) {
                // Supprime le label au clic molette
                Platform.runLater(() -> {
                    view.removeLabel(n.getNode());
                    view.getGridCases().remove(new Pair<>((int) initialX, (int) initialY));
                });
            }
        });

        // Gestion du déplacement de la brique
        label.setOnMouseDragged(event -> {
            if (!event.isPrimaryButtonDown())
                return;

            double newX = event.getSceneX() - offsetX[0];
            double newY = event.getSceneY() - offsetY[0];

            // Rend le déplacement "snappable" à la grille
            double snappedX = Math.round(newX / Game.unit) * Game.unit;
            double snappedY = Math.round(newY / Game.unit) * Game.unit;
            Label l = (Label) label;

            boolean inBounds = snappedX >= 0 && snappedY >= 0 &&
                    snappedX < model.getMap()[0].length * Game.unit &&
                    snappedY < model.getMap().length * Game.unit;

            // Vérifie si la position est valide
            boolean nothing = (l.getPrefWidth() == 4 * Game.unit)
                    ? right41(label, (int) snappedX, (int) snappedY)
                    : oneOne(label, (int) snappedX, (int) snappedY);
            boolean nothing8 = left41(label, (int) snappedX, (int) snappedY);

            if (inBounds && nothing8 && nothing) {
                label.setLayoutX(snappedX);
                label.setLayoutY(snappedY);
                lastPermittedX = snappedX;
                lastPermittedY = snappedY;
            }
        });

        // Gestion du relâchement de la souris après un drag
        label.setOnMouseReleased(event -> {
            if (event.getButton() != MouseButton.PRIMARY)
                return;

            Noeud n = view.getGridCases().get(new Pair<>((int) initialX, (int) initialY));

            Platform.runLater(() -> {
                label.setLayoutX(lastPermittedX);
                label.setLayoutY(lastPermittedY);
                view.getGridCases().put(new Pair<>((int) lastPermittedX, (int) lastPermittedY), n);
                if (lastPermittedX != initialX || lastPermittedY != initialY)
                    view.getGridCases().remove(new Pair<>((int) initialX, (int) initialY));
            });
        });
    }

    /**
     * Retourne la scène de l'éditeur.
     * 
     * @return La scène de l'éditeur sous forme d'un objet {@link Scene}.
     */
    public Scene getScene() {
        return view.getScene();
    }

    /**
     * Retourne la couleur associée à un type de brique.
     *
     * @param type le type de la brique
     * @return la couleur associé
     */
    protected String getColor(int type) {
        if (type / 10 == 1) {
            switch (type % 10) {
                case 0:
                    return "white";
                case 1:
                    return "blue";
                case 2:
                    return "purple";
                case 3:
                    return "green";
                case 4:
                    return "red";
                case 5:
                    return "yellow";
                case 6:
                    return "orange";
                case 7:
                    return "lightblue";
                case 8:
                    return "black";
                case 9:
                    return "brown";
            }
        } else {
            switch (type % 10) {
                case 0:
                    return "blue";
                case 1:
                    return "purple";
                case 2:
                    return "green";
                case 3:
                    return "red";
                case 4:
                    return "black ";
                default:
                    return "white";
            }
        }
        return "";
    }

    /**
     * Vérifie si un espace est libre à gauche pour une brique 4x1.
     */
    private boolean left41(Node e, int x, int y) {
        for (int i = 0; i < 4; i++) {
            if (view.getGridCases().get(new Pair<>(x - i * Game.unit, y)) != null) {
                if (((Label) view.getGridCases().get(new Pair<>(x - i * Game.unit, y)).getNode()).getPrefWidth() == 80.0
                        && ((Label) view.getGridCases().get(new Pair<>(x - i * Game.unit, y)).getNode()) != (Label) e)
                    return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si un espace est libre à droite pour une brique 4x1.
     */
    private boolean right41(Node e, int x, int y) {
        if (x > (model.getMap()[0].length - 4) * Game.unit)
            return false;
        for (int i = 0; i < 4; i++) {
            Noeud n = view.getGridCases().get(new Pair<>(x + i * Game.unit, y));
            if (n != null && n.getNode() != (Label) e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si une cellule est libre pour une brique 1x1.
     */
    private boolean oneOne(Node e, int x, int y) {
        return view.getGridCases().get(new Pair<>(x, y)) == null
                || view.getGridCases().get(new Pair<>(x, y)).getNode() == e;
    }

    /**
     * Ajoute ou modifie une propriété de style
     * 
     * @param node     le noeud à modifier
     * @param property la propriété impacté
     * @param value    la nouvelle valeur
     */
    public static void addOrUpdateStyle(Node node, String property, String value) {
        String oldStyle = node.getStyle();
        String newStyle = property + ": " + value + ";";

        if (oldStyle.contains(property)) {
            // Remplace l'ancienne propriété
            oldStyle = oldStyle.replaceAll(property + ": [^;]+;", newStyle);
        } else {
            // Ajoute la nouvelle propriété
            oldStyle += " " + newStyle;
        }

        node.setStyle(oldStyle);
    }
}
