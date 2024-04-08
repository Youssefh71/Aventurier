package com.aventurier.impl;

import com.aventurier.MapController;
import com.aventurier.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DefaultMapController implements MapController {

    private static final char HERO_SYMBOL = 'O';
    private static final char OBSTACLE_SYMBOL = '#';

    private static final String ERROR_INVALID_MAP = "Erreur : carte invalide !";
    private static final String ERROR_MOVE_OUT_OF_BOUNDS = "Déplacement impossible. Le héros est en dehors des limites de la carte. ☠️";
    private static final String ERROR_MOVE_ON_OBSTACLE = "Déplacement impossible. Le héros est sur un obstacle. ☠️";
    private static final String ERROR_NO_MOVEMENT_POSSIBLE = "Déplacement impossible. ☠️ ☠️ ☠️ ☠️ ☠️";
    @Override
    // Méthode pour charger le contenu de la carte à partir d'un fichier
    public String loadMapContent(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    @Override
    // Méthode pour afficher la carte avec le héros
    public void printMapWithHero(char[][] map, Player player) {
        if (map == null) {
            System.out.println(ERROR_INVALID_MAP);
            return;
        }


        char[][] mapWithHero = addHeroToMap(map, player);
        for (char[] row : mapWithHero) {
            System.out.println(row);
        }

        // Afficher les coordonnées du joueur en dessous de la carte
        System.out.println("Position du joueur : (" + player.getPosition().getX() + ", " + player.getPosition().getY() + ")");
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    private char[][] addHeroToMap(char[][] map, Player player) {
        char[][] mapWithHero = copyMap(map);
        mapWithHero[player.getPosition().getY()][player.getPosition().getX()] = HERO_SYMBOL;
        return mapWithHero;
    }

    private char[][] copyMap(char[][] map) {
        char[][] copiedMap = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            copiedMap[i] = map[i].clone();
        }
        return copiedMap;
    }

    @Override
    // Méthode pour déplacer le héros selon les instructions données
    public void moveHero(Player player, String instructions, char[][] map) {

        if (!isValidMap(map)) {
            System.out.println(ERROR_INVALID_MAP);
            return;
        }

        // Récupérer la largeur de la carte (nombre de colonnes dans la première ligne)
        int mapWidth = map[0].length;

        // Récupérer la hauteur de la carte (nombre de lignes)
        int mapHeight = map.length;

        // Indicateur pour vérifier si au moins un déplacement est possible
        boolean isPossible = false;

        // Parcourir chaque instruction de déplacement
        for (char direction : instructions.toCharArray()) {
            // Sauvegarder les coordonnées actuelles du joueur
            int currentX = player.getPosition().getX();
            int currentY = player.getPosition().getY();
            // Déplacer le joueur dans la direction spécifiée par l'instruction
            player.move(direction);

            // Récupérer les nouvelles coordonnées du joueur après le déplacement
            int newX = player.getPosition().getX();
            int newY = player.getPosition().getY();

            // Vérifier si les nouvelles coordonnées sont dans les limites de la carte
            if (!isInBounds(newX, newY, mapWidth, mapHeight)) {
                System.out.println(ERROR_MOVE_OUT_OF_BOUNDS);
                // Réinitialiser la position du joueur aux coordonnées actuelles
                player.getPosition().setX(currentX);
                player.getPosition().setY(currentY);
                return; // Sortir de la méthode car le déplacement est impossible
            } else if (isObstacle(map, newX, newY)) { // Vérifier si les nouvelles coordonnées sont sur un obstacle
                System.out.println(ERROR_MOVE_ON_OBSTACLE);

                // Réinitialiser la position du joueur aux coordonnées actuelles
                player.getPosition().setX(currentX);
                player.getPosition().setY(currentY);
                return; // Sortir de la méthode car le déplacement est impossible
            } else {
                isPossible = true; // Un déplacement est possible
            }
        }

        // Si aucun déplacement n'est possible, afficher un message d'erreur
        if (!isPossible) {
            System.out.println();
            System.out.println("-------------------------------------------------");
            System.out.println(ERROR_NO_MOVEMENT_POSSIBLE);
        } else {
            // Afficher la carte avec le héros après le ou les déplacements
            System.out.println();
            System.out.println("Résultat du test : Carte mise à jour avec déplacement du personnage ");
            System.out.println();
            printMapWithHero(map, player);
        }
    }

    private boolean isValidMap(char[][] map) {
        return map != null && map.length > 0 && map[0].length > 0;
    }

    // Méthode qui vérifie si les coordonnées (x, y) sont à l'intérieur des limites de la carte
    private boolean isInBounds(int x, int y, int mapWidth, int mapHeight) {
        return (x >= 0 && x < mapWidth && y >= 0 && y < mapHeight);
    }

    // Méthode pour vérifier si une position est un obstacle sur la carte
    private boolean isObstacle(char[][] map, int x, int y) {
        return (map[y][x] == OBSTACLE_SYMBOL);
    }

    // Méthode pour analyser le contenu de la carte et la transformer en une matrice de caractères
    public char[][] parseMap(String mapContent) {
        try {
            if (mapContent == null || mapContent.isEmpty()) {
                throw new IllegalArgumentException("Le contenu de la carte est vide ou null.");
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
        } catch (Exception e) {
            System.out.println("Erreur lors de la conversion de la carte en une matrice de caractères : " + e.getMessage());
            return null; // Retourner null en cas d'erreur
        }
    }
}
