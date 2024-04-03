package com.aventurier;

import com.aventurier.impl.Player;

public interface MapController {

    // Méthode pour afficher la carte avec le héros
    void printMapWithHero(char[][] map, Player player);

    // Méthode pour déplacer le héros selon les instructions données
    void moveHero(Player player, String instructions, char[][] map);

    // Méthode pour analyser le contenu de la carte et la transformer en une matrice de caractères
    char[][] parseMap(String mapContent);

}
