package com.aventurier.impl;

import com.aventurier.Movement;

public class Player implements Movement {
        private int x; // Coordonnée x du joueur
        private int y; // Coordonnée y du joueur

        public Player(int x, int y) {
                this.x = x;
                this.y = y;
        }

        // Méthode pour déplacer le joueur
        @Override
        public void move(char direction) {
                switch (direction) {
                        case 'N' -> y--; // Décrémente la coordonnée y (vers le haut) pour le nord
                        case 'S' -> y++; // Incrémente la coordonnée y (vers le bas) pour le sud
                        case 'E' -> x++; // Incrémente la coordonnée x (vers la droite) pour l'est
                        case 'O' -> x--; // Décrémente la coordonnée x (vers la gauche) pour l'ouest
                        default -> throw new IllegalArgumentException("Direction invalide : " + direction);
                }
        }


        public int getX() {
                return x;
        }

        public int getY() {
                return y;
        }

        public void setX(int x) {
                this.x = x;
        }

        public void setY(int y) {
                this.y = y;
        }
}