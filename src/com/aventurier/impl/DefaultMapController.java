package com.aventurier.impl;

import com.aventurier.MapController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DefaultMapController implements MapController {


    // Méthode pour charger le contenu de la carte à partir d'un fichier
    public String loadMapContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier de carte : " + e.getMessage());
            return null; // En cas d'erreur, retourner null
        }
    }

    @Override
    // Méthode pour afficher la carte avec le héros
    public void printMapWithHero(char[][] map, Player player) {
        if (map == null) {
            System.out.println("Erreur : carte invalide !");
            return;
        }

        char[][] mapWithHero = addHeroToMap(map, player);
        for (char[] row : mapWithHero) {
            System.out.println(row);
        }

        // Afficher les coordonnées du joueur en dessous de la carte
        System.out.println("Position du joueur : (" + player.getX() + ", " + player.getY() + ")");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    private char[][] addHeroToMap(char[][] map, Player player) {
        //Création d'une nouvelle matrice pour la carte avec les mêmes dimensions que la carte initiale
        char[][] mapWithHero = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            mapWithHero[i] = map[i].clone();
        }
        mapWithHero[player.getY()][player.getX()] = 'O';
        return mapWithHero;
    }

    @Override
    // Méthode pour déplacer le héros selon les instructions données
    public void moveHero(Player player, String instructions, char[][] map) {

        if (map == null) {
            System.out.println("Erreur : carte invalide !");
            return;
        }

        // Récupérer la largeur de la carte (nombre de colonnes dans la première ligne)
        int mapWidth = map[0].length;
        // Récupérer la hauteur de la carte (nombre de lignes)
        int mapHeight = map.length;

        // Indicateur pour vérifier si au moins un déplacement est possible
        boolean isPossible = false;

        // Indicateur pour vérifier si le message a déjà été affiché
        boolean obstacleMessageDisplayed = false;

        // Parcourir chaque instruction de déplacement
        for (char direction : instructions.toCharArray()) {
            // Sauvegarder les coordonnées actuelles du joueur
            int currentX = player.getX();
            int currentY = player.getY();
            // Déplacer le joueur dans la direction spécifiée par l'instruction
            player.move(direction);

            // Récupérer les nouvelles coordonnées du joueur après le déplacement
            int newX = player.getX();
            int newY = player.getY();

            // Vérifier si les nouvelles coordonnées sont dans les limites de la carte
            if (!isInBounds(newX, newY, mapWidth, mapHeight)) {
                if (!obstacleMessageDisplayed) {
                    System.out.println("Déplacement impossible. Le héros est en dehors des limites de la carte. ☠️");
                    obstacleMessageDisplayed = true; // Mettre l'indicateur à true pour indiquer que le message a été affiché
                }
                player.setX(currentX);
                player.setY(currentY);
            } else if (isObstacle(map, newX, newY)) { // Vérifier si les nouvelles coordonnées sont sur un obstacle
                if (!obstacleMessageDisplayed) {
                    System.out.println("Déplacement impossible. Le héros est sur un obstacle. ☠️");
                    obstacleMessageDisplayed = true; // Mettre l'indicateur à true pour indiquer que le message a été affiché
                }
                player.setX(currentX);
                player.setY(currentY);
            } else {
                isPossible = true; // Un déplacement est possible
            }
        }

        // Si aucun déplacement n'est possible, afficher un message d'erreur
        if (!isPossible) {
            System.out.println();
            System.out.println("-------------------------------------------------");
            System.out.println("Déplacement impossible. ☠️ ☠️ ☠️ ☠️ ☠️");
        } else {
            // Afficher la carte avec le héros après le ou les déplacements
            System.out.println();
            System.out.println("Résultat du test : Carte mise à jour avec déplacement du personnage ");
            System.out.println();
            printMapWithHero(map, player);
        }
    }

    // Méthode qui vérifie si les coordonnées (x, y) sont à l'intérieur des limites de la carte
    private boolean isInBounds(int x, int y, int mapWidth, int mapHeight) {
        return (x >= 0 && x < mapWidth && y >= 0 && y < mapHeight);
    }

    // Méthode pour vérifier si une position est un obstacle sur la carte
    private boolean isObstacle(char[][] map, int x, int y) {
        return (map[y][x] == '#');
    }

    // Méthode pour analyser le contenu de la carte et la transformer en une matrice de caractères
    public char[][] parseMap(String mapContent) {
        if (mapContent == null || mapContent.isEmpty()) {
            return null; // Retourner null si le contenu de la carte est vide
        }
        // Diviser le contenu de la carte en lignes en utilisant le saut de ligne comme séparateur
        String[] lines = mapContent.split("\n");
        // Créer une nouvelle matrice pour stocker la carte, où chaque ligne est un tableau de caractères
        char[][] map = new char[lines.length][];
        // Parcourir chaque ligne du contenu de la carte
        for (int i = 0; i < lines.length; i++) {
            // Convertir chaque ligne en un tableau de caractères et l'ajouter à la matrice de la carte
            map[i] = lines[i].toCharArray();
        }
        return map;
    }
}