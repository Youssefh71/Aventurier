package com.aventurier;

import java.io.IOException;

public interface MapController {

    // Méthode pour afficher la carte avec le héros
    void printMapWithHero(char[][] map, Player player);

    // Méthode pour déplacer le héros selon les instructions données
    void moveHero(Player player, String instructions, char[][] map);

    // Méthode pour analyser le contenu de la carte et la transformer en une matrice de caractères
    char[][] parseMap(String mapContent);

    // Méthode pour charger le contenu de la carte à partir d'un fichier
    String loadMapContent(String filePath) throws IOException;

}
