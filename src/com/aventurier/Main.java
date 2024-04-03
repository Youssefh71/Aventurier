package com.aventurier;

import com.aventurier.impl.DefaultMapController;
import com.aventurier.impl.Player;

public class Main {
    public static void main(String[] args) {

        // Création d'un joueur avec les coordonnées initiales
        Player hero1 = new Player(3, 0); // Premier test
        Player hero2 = new Player(6, 9); // Deuxième test

        DefaultMapController mapController = new DefaultMapController();

        // Lecture du contenu du fichier
        String mapContent = mapController.loadMapContent("data/carte.txt");

        if (mapContent != null) {
            // Premier test
            System.out.println("Premier test : Emplacement initiale du personnage");
            System.out.println("--------------------------------------------------------------------------------------------");
            TestRunner.runTest(mapController, mapContent, hero1, "SSSSEEEEEENN");

            // Deuxième test
            System.out.println("\nDeuxième test : Emplacement initiale du personnage");
            System.out.println("--------------------------------------------------------------------------------------------");
            TestRunner.runTest(mapController, mapContent, hero2, "OONOOOSSO");
        } else {
            System.out.println("Erreur lors de la lecture du fichier de carte");
        }
    }

}

