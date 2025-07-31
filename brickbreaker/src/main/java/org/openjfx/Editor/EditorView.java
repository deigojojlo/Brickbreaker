package org.openjfx.Editor;

import java.util.HashMap;
import java.util.LinkedList;

import org.openjfx.Game.GameClass.Game;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

/**
 * Classe EditorView
 * Cette classe représente l'interface graphique de l'éditeur.
 * Elle permet d'interagir avec les composants graphiques pour créer et
 * manipuler des briques.
 */
public class EditorView {

    private Scene scene;
    private Slider accelerationSlider; // curseur pour l'accélération
    private Slider maxSpeedSlider; // curseur pour la vitesse maximale
    private Slider rateSlider; // curseur pour le taux de refraichissement
    private GridPane gridMap; // grille principale
    private BorderPane mainView; // disposition principale
    private Button backButton; // bouton retour
    private Button saveButton; // bouton sauvegarder
    private Button deleteButton; // bouton supprimer
    private HashMap<Pair<Integer, Integer>, Noeud> gridCases; // correspondance entre positions et éléments
    private LinkedList<Label> labelList; // liste des étiquettes
    private LinkedList<Node> draggabbleNodes; // liste des éléments déplaçables
    private Pane draggablePane; // zone des éléments déplaçables
    private Pane sandboxPane; // zone de jeu

    private Label smallBrickLabel; // étiquette pour une petite brique
    private Label largeBrickLabel; // étiquette pour une grande brique
    private Label durableLargeBrickLabel; // étiquette pour une grande brique durable
    private Label unbreakableSmallBrickLabel; // étiquette pour une petite brique incassable
    private Label unbreakableLargeBrickLabel; // étiquette pour une grande brique incassable

    private VBox optionsBox; // conteneur pour les options
    private VBox brickBox; // conteneur pour les briques

    private int initialRow; // position initiale de la brique déplacée (ligne)
    private int initialCol; // position initiale de la brique déplacée (colonne)

    /**
     * Constructeur de la classe EditorView.
     * Initialise les composants de l'interface utilisateur.
     */
    public EditorView() {
        initializeComponents();
    }

    /**
     * Initialise les composants de l'éditeur.
     */
    private void initializeComponents() {
        this.draggabbleNodes = new LinkedList<>();
        this.accelerationSlider = new Slider(1, 5, 1);
        this.maxSpeedSlider = new Slider(1, 50, 10);
        this.rateSlider = new Slider(0, 5, 1);
        this.mainView = new BorderPane();
        this.scene = new Scene(mainView);
        this.labelList = new LinkedList<>();
        this.gridMap = new GridPane();
        this.gridCases = new HashMap<>();

        this.backButton = new Button("Back");
        this.saveButton = new Button("Save");
        this.deleteButton = new Button("Delete");

        AnchorPane topPane = new AnchorPane();

        smallBrickLabel = new Label("Small \nBrick");
        largeBrickLabel = new Label("Large \nBrick");
        unbreakableSmallBrickLabel = new Label("Small \nUnbreakable \nBrick");
        unbreakableLargeBrickLabel = new Label("Large \nUnbreakable \nBrick");
        durableLargeBrickLabel = new Label("Large \nDurable \nBrick");

        Label maxSpeedLabel = new Label("Max Speed");
        Label accelerationLabel = new Label("Acceleration");
        Label rateLabel = new Label("Rate");

        maxSpeedLabel.setStyle("-fx-background-color: #FFFFFF");

        labelList.add(smallBrickLabel);
        labelList.add(largeBrickLabel);
        labelList.add(unbreakableSmallBrickLabel);
        labelList.add(unbreakableLargeBrickLabel);
        labelList.add(durableLargeBrickLabel);
        labelList.add(rateLabel);
        labelList.add(maxSpeedLabel);
        labelList.add(accelerationLabel);

        VBox rateContainer = new VBox(10);
        VBox accelerationContainer = new VBox(10);
        VBox maxSpeedContainer = new VBox(10);

        rateContainer.getChildren().addAll(rateLabel, rateSlider);
        accelerationContainer.getChildren().addAll(accelerationLabel, accelerationSlider);
        maxSpeedContainer.getChildren().addAll(maxSpeedLabel, maxSpeedSlider);

        optionsBox = new VBox(10);
        brickBox = new VBox(10);
        sandboxPane = new Pane();
        draggablePane = new Pane();

        optionsBox.setAlignment(Pos.TOP_CENTER);

        optionsBox.getChildren().addAll(maxSpeedContainer, accelerationContainer, rateContainer, deleteButton);
        brickBox.getChildren().addAll(smallBrickLabel, largeBrickLabel, unbreakableSmallBrickLabel,
                unbreakableLargeBrickLabel, durableLargeBrickLabel);
        topPane.getChildren().addAll(backButton, saveButton);
        sandboxPane = new Pane(gridMap, draggablePane);

        labelList.forEach(label -> {
            label.setPrefSize(150, 100);
            label.setFont(Game.font);
            // label.setStyle("-fx-background-color: #FFFFFF");
        });

        topPane.setStyle("-fx-background-color: " + Game.topBarColor);

        backButton.setStyle(
                "-fx-background-color: " + Game.backgroundColor
                        + "; -fx-border-radius: 50; -fx-background-radius: 50; -fx-text-fill: white");
        backButton.setPrefSize(100, 40);
        backButton.setFont(Game.font);
        saveButton.setStyle(
                "-fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color:" + Game.backgroundColor
                        + " ; -fx-text-fill: white");
        saveButton.setFont(Game.font);
        saveButton.setPrefSize(100, 40);
        deleteButton.setFont(Game.font);
        deleteButton.setPrefSize(100, 40);

        AnchorPane.setTopAnchor(backButton, (75 - backButton.getPrefHeight()) / 2);
        AnchorPane.setLeftAnchor(backButton, 10.0);
        AnchorPane.setTopAnchor(saveButton, (75 - backButton.getPrefHeight()) / 2);
        AnchorPane.setRightAnchor(saveButton, 10.0);

        topPane.setMinSize(Game.windowWidth, 75);
        optionsBox.setMinHeight(Game.windowHeight - 75);
        brickBox.setMinHeight(Game.windowHeight - 75);
        optionsBox.setMinWidth(150);
        brickBox.setMinWidth(150);

        mainView.setTop(topPane);
        mainView.setLeft(optionsBox);
        mainView.setCenter(sandboxPane);
        mainView.setRight(brickBox);
    }

    /**
     * Initialise la grille avec une taille donnée.
     * 
     * @param sizeX Largeur de la grille.
     * @param sizeY Hauteur de la grille.
     */
    public void initializeGrid(int sizeX, int sizeY) {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                Rectangle cell = new Rectangle(i * Game.unit, j * Game.unit, Game.unit - 1, Game.unit - 1);
                cell.setFill(Color.LIGHTGRAY);
                cell.setStroke(Color.BLACK);
                cell.setStrokeWidth(0.5);
                gridMap.add(cell, j, i);
            }
        }
        gridMap.setLayoutX((Game.windowWidth - 300 - sizeX * Game.unit) / 2);
        gridMap.setLayoutY((Game.windowHeight - 75 - 300 - sizeY * Game.unit) / 2);
        draggablePane.setLayoutX((Game.windowWidth - 300 - sizeX * Game.unit) / 2);
        draggablePane.setLayoutY((Game.windowHeight - 75 - 300 - sizeY * Game.unit) / 2);
    }

    /**
     * Ajoute une brique à la position spécifiée.
     * 
     * @param type Type de la brique.
     * @param x    Position x.
     * @param y    Position y.
     * @return Le noeud représentant la brique ajoutée.
     */
    public synchronized Node addBrick(int type, int x, int y) {
        if (gridCases.get(new Pair<>(x, y)) != null)
            return null;

        Label brickLabel = new Label();
        Platform.runLater(() -> {
            brickLabel.setLayoutX(x);
            brickLabel.setLayoutY(y);
            brickLabel.setStyle(
                    "-fx-background-color: white; -fx-padding: 0px; -fx-border-width: 1; -fx-border-color: black;");
            draggablePane.getChildren().add(brickLabel);
        });
        draggabbleNodes.add(brickLabel);
        gridCases.put(new Pair<>(x, y), new Noeud(brickLabel, type));

        Platform.runLater(() -> {
            if (type / 10 == 1) {
                brickLabel.setPrefSize(Game.unit, Game.unit);
                if (type % 10 == 9) {
                    brickLabel.setText("U");
                }
            } else {
                brickLabel.setPrefSize(4 * Game.unit, Game.unit);
                if (type / 100 != 0) {
                    brickLabel.setText("1");
                } else if (type % 10 == 9) {
                    brickLabel.setText("Unbreakable");
                }
            }
        });
        return brickLabel;
    }

        /**
     * Supprime une étiquette (brique) du panneau draggable.
     * 
     * Cette méthode supprime un élément visuel (représenté par un `Node`) du panneau draggable, et l'enlève également de
     * la liste des éléments déplaçables. Elle garantit que l'opération de suppression se fait sur le thread de l'interface
     * utilisateur via `Platform.runLater`.
     * 
     * @param e Le noeud (élément visuel) à supprimer. Si ce noeud est `null`, aucune action n'est effectuée.
     */
    public synchronized void removeLabel(Node e) {
        if (e == null)
            return;
        Platform.runLater(() -> {
            draggablePane.getChildren().remove(e);
        });
        draggabbleNodes.remove(e);
    }
    
    // Getters et setters

    /**
     * Retourne le bouton de suppression.
     * 
     * @return Le bouton de suppression.
     */
    public Button getDeleteButton() {
        return this.deleteButton;
    }

    /**
     * Définit le bouton de suppression.
     * 
     * @param deleteButton Le bouton à définir.
     */
    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    /**
     * Retourne le panneau des éléments déplaçables.
     * 
     * @return Le panneau des éléments déplaçables.
     */
    public Pane getDraggablePane() {
        return this.draggablePane;
    }

    /**
     * Définit le panneau des éléments déplaçables.
     * 
     * @param draggablePane Le panneau à définir.
     */
    public void setDraggablePane(Pane draggablePane) {
        this.draggablePane = draggablePane;
    }

    /**
     * Retourne le panneau de jeu (sandbox).
     * 
     * @return Le panneau de jeu.
     */
    public Pane getSandboxPane() {
        return this.sandboxPane;
    }

    /**
     * Définit le panneau de jeu (sandbox).
     * 
     * @param sandboxPane Le panneau à définir.
     */
    public void setSandboxPane(Pane sandboxPane) {
        this.sandboxPane = sandboxPane;
    }

    /**
     * Retourne le conteneur des options.
     * 
     * @return Le conteneur des options.
     */
    public VBox getOptionsBox() {
        return this.optionsBox;
    }

    /**
     * Définit le conteneur des options.
     * 
     * @param optionsBox Le conteneur à définir.
     */
    public void setOptionsBox(VBox optionsBox) {
        this.optionsBox = optionsBox;
    }

    /**
     * Retourne le conteneur des briques.
     * 
     * @return Le conteneur des briques.
     */
    public VBox getBrickBox() {
        return this.brickBox;
    }

    /**
     * Définit le conteneur des briques.
     * 
     * @param brickBox Le conteneur à définir.
     */
    public void setBrickBox(VBox brickBox) {
        this.brickBox = brickBox;
    }

    /**
     * Retourne le bouton de sauvegarde.
     * 
     * @return Le bouton de sauvegarde.
     */
    public Button getSaveButton() {
        return this.saveButton;
    }

    /**
     * Définit le bouton de sauvegarde.
     * 
     * @param saveButton Le bouton à définir.
     */
    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    /**
     * Retourne la liste des éléments déplaçables.
     * 
     * @return La liste des éléments déplaçables.
     */
    public LinkedList<Node> getDraggabbleNodes() {
        return this.draggabbleNodes;
    }

    /**
     * Définit la liste des éléments déplaçables.
     * 
     * @param draggabbleNodes La liste à définir.
     */
    public void setDraggabbleNodes(LinkedList<Node> draggabbleNodes) {
        this.draggabbleNodes = draggabbleNodes;
    }

    /**
     * Retourne l'étiquette pour une petite brique.
     * 
     * @return L'étiquette pour une petite brique.
     */
    public Label getSmallBrickLabel() {
        return this.smallBrickLabel;
    }

    /**
     * Définit l'étiquette pour une petite brique.
     * 
     * @param smallBrickLabel L'étiquette à définir.
     */
    public void setSmallBrickLabel(Label smallBrickLabel) {
        this.smallBrickLabel = smallBrickLabel;
    }

    /**
     * Retourne l'étiquette pour une grande brique.
     * 
     * @return L'étiquette pour une grande brique.
     */
    public Label getLargeBrickLabel() {
        return this.largeBrickLabel;
    }

    /**
     * Définit l'étiquette pour une grande brique.
     * 
     * @param largeBrickLabel L'étiquette à définir.
     */
    public void setLargeBrickLabel(Label largeBrickLabel) {
        this.largeBrickLabel = largeBrickLabel;
    }

    /**
     * Retourne l'étiquette pour une grande brique durable.
     * 
     * @return L'étiquette pour une grande brique durable.
     */
    public Label getDurableLargeBrickLabel() {
        return this.durableLargeBrickLabel;
    }

    /**
     * Définit l'étiquette pour une grande brique durable.
     * 
     * @param durableLargeBrickLabel L'étiquette à définir.
     */
    public void setDurableLargeBrickLabel(Label durableLargeBrickLabel) {
        this.durableLargeBrickLabel = durableLargeBrickLabel;
    }

    /**
     * Retourne l'étiquette pour une petite brique incassable.
     * 
     * @return L'étiquette pour une petite brique incassable.
     */
    public Label getUnbreakableSmallBrickLabel() {
        return this.unbreakableSmallBrickLabel;
    }

    /**
     * Définit l'étiquette pour une petite brique incassable.
     * 
     * @param unbreakableSmallBrickLabel L'étiquette à définir.
     */
    public void setUnbreakableSmallBrickLabel(Label unbreakableSmallBrickLabel) {
        this.unbreakableSmallBrickLabel = unbreakableSmallBrickLabel;
    }

    /**
     * Retourne l'étiquette pour une grande brique incassable.
     * 
     * @return L'étiquette pour une grande brique incassable.
     */
    public Label getUnbreakableLargeBrickLabel() {
        return this.unbreakableLargeBrickLabel;
    }

    /**
     * Définit l'étiquette pour une grande brique incassable.
     * 
     * @param unbreakableLargeBrickLabel L'étiquette à définir.
     */
    public void setUnbreakableLargeBrickLabel(Label unbreakableLargeBrickLabel) {
        this.unbreakableLargeBrickLabel = unbreakableLargeBrickLabel;
    }

    /**
     * Retourne la position initiale de la brique (ligne).
     * 
     * @return La position initiale de la brique (ligne).
     */
    public int getInitialRow() {
        return this.initialRow;
    }

    /**
     * Définit la position initiale de la brique (ligne).
     * 
     * @param initialRow La position à définir.
     */
    public void setInitialRow(int initialRow) {
        this.initialRow = initialRow;
    }

    /**
     * Retourne la position initiale de la brique (colonne).
     * 
     * @return La position initiale de la brique (colonne).
     */
    public int getInitialCol() {
        return this.initialCol;
    }

    /**
     * Définit la position initiale de la brique (colonne).
     * 
     * @param initialCol La position à définir.
     */
    public void setInitialCol(int initialCol) {
        this.initialCol = initialCol;
    }

    /**
     * Retourne la liste des étiquettes utilisées pour les briques.
     * 
     * @return La liste des étiquettes.
     */
    public LinkedList<Label> getLabelList() {
        return this.labelList;
    }

    /**
     * Définit la liste des étiquettes utilisées pour les briques.
     * 
     * @param labelList La liste à définir.
     */
    public void setLabelList(LinkedList<Label> labelList) {
        this.labelList = labelList;
    }

    /**
     * Définit la scène pour l'éditeur.
     * 
     * @param scene La scène à définir.
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Retourne la vue principale de l'éditeur.
     * 
     * @return La vue principale.
     */
    public BorderPane getMainView() {
        return this.mainView;
    }

    /**
     * Retourne le bouton "Retour".
     * 
     * @return Le bouton "Retour".
     */
    public Button getBackButton() {
        return this.backButton;
    }

    /**
     * Définit le bouton "Retour".
     * 
     * @param backButton Le bouton à définir.
     */
    public void setBackButton(Button backButton) {
        this.backButton = backButton;
    }

    /**
     * Définit la vue principale de l'éditeur.
     * 
     * @param mainView La vue principale à définir.
     */
    public void setMainView(BorderPane mainView) {
        this.mainView = mainView;
    }

    /**
     * Retourne la scène de l'éditeur, avec la mise en forme appliquée.
     * 
     * @return La scène.
     */
    public Scene getScene() {
        optionsBox.setStyle(String.format("-fx-background-color : %s ;",
                String.format("rgb(%1$d,%1$d,%1$d)", Game.backgroundCode == 28 ? 28 : 200)));
        brickBox.setStyle(String.format("-fx-background-color : %s ;",
                String.format("rgb(%1$d,%1$d,%1$d)", Game.backgroundCode == 28 ? 28 : 200)));
        mainView.setStyle(String.format("-fx-background-color : %s ;",
                String.format("rgb(%1$d,%1$d,%1$d)", Game.backgroundCode == 28 ? 50 : 255)));
        labelList.forEach(label -> label.setStyle(String.format(
                "-fx-padding: 0; -fx-text-alignment: center; -fx-alignment: center; -fx-text-fill: %s;",
                Game.backgroundCode == 28 ? "white" : "black")));
        return this.scene;
    }

    /**
     * Retourne le curseur d'accélération.
     * 
     * @return Le curseur d'accélération.
     */
    public Slider getAccelerationSlider() {
        return this.accelerationSlider;
    }

    /**
     * Définit le curseur d'accélération.
     * 
     * @param accelerationSlider Le curseur à définir.
     */
    public void setAccelerationSlider(Slider accelerationSlider) {
        this.accelerationSlider = accelerationSlider;
    }

    /**
     * Retourne le curseur de la vitesse maximale.
     * 
     * @return Le curseur de la vitesse maximale.
     */
    public Slider getMaxSpeedSlider() {
        return this.maxSpeedSlider;
    }

    /**
     * Définit le curseur de la vitesse maximale.
     * 
     * @param maxSpeedSlider Le curseur à définir.
     */
    public void setMaxSpeedSlider(Slider maxSpeedSlider) {
        this.maxSpeedSlider = maxSpeedSlider;
    }

    /**
     * Retourne le curseur du taux de rafraîchissement.
     * 
     * @return Le curseur du taux de rafraîchissement.
     */
    public Slider getRateSlider() {
        return this.rateSlider;
    }

    /**
     * Définit le curseur du taux de rafraîchissement.
     * 
     * @param spawnRateSlider Le curseur à définir.
     */
    public void setRateSlider(Slider spawnRateSlider) {
        this.rateSlider = spawnRateSlider;
    }

     /**
     * Retourne la grille principale.
     * 
     * @return La grille principale.
     */
    public Pane getGridMap() {
        return this.gridMap;
    }

       /**
     * Définit la grille principale.
     * 
     * @param gridMap La grille principale à définir.
     */
    public void setGridMap(GridPane gridMap) {
        this.gridMap = gridMap;
    }

    /**
     * Retourne la carte des cases du grid.
     * 
     * @return La carte des cases du grid.
     */
    public HashMap<Pair<Integer, Integer>, Noeud> getGridCases() {
        return this.gridCases;
    }

    /**
     * Définit la carte des cases du grid.
     * 
     * @param gridCases La carte des cases à définir.
     */
    public void setGridCases(HashMap<Pair<Integer, Integer>, Noeud> gridCases) {
        this.gridCases = gridCases;
    }
}