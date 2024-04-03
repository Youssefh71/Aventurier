package com.aventurier;

import com.aventurier.impl.DefaultMapController;
import com.aventurier.impl.Player;

public class TestRunner {

    // Méthode pour exécuter un test
    public static void runTest(DefaultMapController mapController, String mapContent, Player hero, String instructions) {
        // Charger la carte pour le test
        char[][] map = mapController.parseMap(mapContent);

        if (map != null) {
            // Afficher la carte avec le héros initial
            mapController.printMapWithHero(map, hero);

            // Déplacer le héros selon les instructions
            mapController.moveHero(hero, instructions, map);

        } else {
            System.out.println("Erreur : Impossible de charger la carte.");
        }
    }
}
