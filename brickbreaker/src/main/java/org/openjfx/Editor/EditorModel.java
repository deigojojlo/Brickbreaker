package org.openjfx.Editor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Function;

import org.openjfx.Game.GameClass.Game;
import javafx.util.Pair;
import org.openjfx.LevelJSON.LevelJson;

import com.google.gson.Gson;

/**
 * Classe représentant le modèle de données de l'éditeur de niveaux.
 * Contient la configuration de la carte, les paramètres et les méthodes
 * pour manipuler et sauvegarder les données.
 */
public class EditorModel {
    private volatile double acceleration; // Accélération configurée
    private volatile double rate; // Taux de vitesse configuré
    private volatile double maxSpeed; // Vitesse maximale de la balle
    private int[][] map; // Représentation matricielle de la carte

    /**
     * Constructeur par défaut, initialise les paramètres et une carte vide.
     */
    public EditorModel() {
        this.acceleration = 1;
        this.rate = 1;
        this.maxSpeed = 1;
        this.map = new int[20][50]; // Taille par défaut : 20 lignes x 50 colonnes
    }

    /**
     * Incrémente la valeur de la case spécifiée dans la carte, avec une limite.
     * 
     * @param x Colonne de la case à incrémenter
     * @param y Ligne de la case à incrémenter
     */
    public synchronized void click(int x, int y) {
        // Si la valeur de la case est inférieure à 9, on l'incrémente.
        if (map[y][x] % 10 < 9) {
            map[y][x]++;
        }
    }

    /**
     * Sauvegarde l'état actuel de la carte dans un fichier JSON.
     * 
     * @param cases Un HashMap contenant les éléments graphiques et leurs
     *              coordonnées
     */
    public synchronized void save(HashMap<Pair<Integer, Integer>, Noeud> cases) {
        map = new int[20][50]; // Réinitialisation de l'instance, si la sauvegarde change les briques
                               // ont pu changer de place entre temps
        // Fonction de transformation des types de briques
        Function<Integer, Integer> f = x -> x / 10 == 4 ? 20 + x % 10 : x;

        // Met à jour la carte avec les types des briques en fonction de leurs positions
        cases.forEach((p, e) -> {
            if (p.getValue() >= 0 && p.getValue() / Game.unit < map.length && p.getKey() >= 0
                    && p.getKey() / Game.unit < map[p.getValue() / Game.unit].length)
                map[p.getValue() / Game.unit][p.getKey() / Game.unit] = (int) Math.floor(f.apply(e.getType()));
        });

        // Nettoie les espaces inutilisés dans la carte
        map = clearSpace();

        // Prépare l'objet JSON à sauvegarder
        LevelJson save = new LevelJson();
        save.setMaxSpeed(maxSpeed);
        save.setAcc(acceleration);
        save.setRate((int) rate);
        save.setTab(map);

        // Sérialisation en JSON et écriture dans un fichier
        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter("src\\main\\resources\\LevelJSON\\edit.json");
            gson.toJson(save, fileWriter);
            fileWriter.close();
            fileWriter = new FileWriter("build\\resources\\main\\LevelJSON\\edit.json");
            gson.toJson(save, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Nettoie les espaces vides de la carte en supprimant les colonnes inutiles.
     * 
     * @return Une nouvelle carte sans espaces vides
     */
    private synchronized int[][] clearSpace() {
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] newMap = new LinkedList[map.length];
        int max = 0; // Largeur maximale de la carte nettoyée

        // Parcourt chaque ligne pour construire une liste épurée
        for (int i = 0; i < map.length; i++) {
            newMap[i] = new LinkedList<>();
            for (int j = 0; j < map[i].length; j++) {
                newMap[i].add(map[i][j]);
                // Si une brique spéciale (2 colonnes de large) est trouvée, on saute les
                // colonnes supplémentaires
                if (map[i][j] % 100 / 10 == 2) {
                    j += 3; // Saut de 3 colonnes
                }
            }
            // Met à jour la largeur maximale
            if (max <= newMap[i].size()) {
                max = newMap[i].size();
            }
        }

        // Convertit la liste épurée en un tableau 2D
        int[][] finalMap = new int[map.length][max];
        for (int i = 0; i < finalMap.length; i++) {
            if (newMap[i] != null) {
                for (int j = 0; j < newMap[i].size(); j++) {
                    finalMap[i][j] = newMap[i].get(j);
                }
            }
        }
        return finalMap;
    }

    // Getters et Setters pour les attributs du modèle

    /**
     * Retourne la valeur de l'accélération.
     * 
     * @return La valeur actuelle de l'accélération
     */
    public synchronized double getAcceleration() {
        return this.acceleration;
    }

    /**
     * Définit la valeur de l'accélération.
     * 
     * @param acceleration Nouvelle valeur de l'accélération
     */
    public synchronized void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Retourne la valeur du taux de vitesse.
     * 
     * @return Le taux de vitesse actuel
     */
    public synchronized double getRate() {
        return this.rate;
    }

    /**
     * Définit la valeur du taux de vitesse.
     * 
     * @param rate Nouvelle valeur du taux de vitesse
     */
    public synchronized void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * Retourne la largeur configurée.
     * 
     * @return La largeur maximale configurée
     */
    public synchronized double getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * Définit la largeur configurée.
     * 
     * @param width Nouvelle largeur configurée
     */
    public synchronized void setMaxSpeed(double width) {
        this.maxSpeed = width;
    }

    /**
     * Retourne la carte actuelle.
     * 
     * @return La carte actuelle sous forme de matrice d'entiers
     */
    public synchronized int[][] getMap() {
        return this.map;
    }

    /**
     * Définit une nouvelle carte.
     * 
     * @param map Nouvelle carte (matrice) à appliquer
     */
    public synchronized void setMap(int[][] map) {
        this.map = map;
    }
}
