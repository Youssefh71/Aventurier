package com.aventurier;

import com.aventurier.impl.DefaultMapController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Création d'un joueur avec les coordonnées initiales
        Player hero1 = new Player(3, 0); // Premier test
        Player hero2 = new Player(6, 9); // Deuxième test

        DefaultMapController mapController = new DefaultMapController();

        // Lecture du contenu du fichier
        String mapContent;
        try {
            mapContent = mapController.loadMapContent("data/carte.txt");
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier de carte : " + e.getMessage());
            return; // Arrêter l'exécution si une exception est levée
        }


        if (mapContent != null) {

            char[][] map = mapController.parseMap(mapContent);

            // Vérifier les coordonnées initiales du premier joueur
            if (hero1.validatePosition(map)) {
                System.out.println("Les coordonnées initiales du premier joueur ne sont pas valides.");
                return;
            }

            // Premier test
            System.out.println("Premier test :");
            System.out.println("--------------------------------------------------------------------------------------------");
            // Charger la carte pour le premier test
            char[][] map1 = mapController.parseMap(mapContent);
            // Afficher la carte avec le héros initial pour le premier test
            mapController.printMapWithHero(map1, hero1);
            // Déplacer le héros selon les instructions pour le premier test
            mapController.moveHero(hero1, "SSSSEEEEEENN", map1);

            // Vérifier les coordonnées initiales du deuxième joueur
            if (hero2.validatePosition(map)) {
                System.out.println("Les coordonnées initiales du deuxième joueur ne sont pas valides.");
                return;
            }
            // Deuxième test
            System.out.println("\nDeuxième test :");
            System.out.println("--------------------------------------------------------------------------------------------");
            // Charger la carte pour le deuxième test
            char[][] map2 = mapController.parseMap(mapContent);
            // Afficher la carte avec le héros initial pour le deuxième test
            mapController.printMapWithHero(map2, hero2);
            // Déplacer le héros selon les instructions pour le deuxième test
            mapController.moveHero(hero2, "OONOOOSSO", map2);

        } else {
            System.out.println("Erreur lors de la lecture du fichier de carte");
        }
    }
}
