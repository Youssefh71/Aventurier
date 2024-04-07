package com.aventurier;

public class Player {
        private final Position position;

        public Player(int x, int y) {
                this.position = new Position(x, y);
        }

        // Méthode pour déplacer le joueur
        public void move(char direction) {
                switch (direction) {
                        case Direction.NORTH ->
                                position.setY(position.getY() - 1); // Décrémente la coordonnée y (vers le haut) pour le nord
                        case Direction.SOUTH ->
                                position.setY(position.getY() + 1); // Incrémente la coordonnée y (vers le bas) pour le sud
                        case Direction.EAST ->
                                position.setX(position.getX() + 1); // Incrémente la coordonnée x (vers la droite) pour l'est
                        case Direction.WEST ->
                                position.setX(position.getX() - 1); // Décrémente la coordonnée x (vers la gauche) pour l'ouest
                        default -> throw new IllegalArgumentException("Direction invalide : " + direction);
                }
        }

        public Position getPosition() {
                return position;
        }


        // Méthode pour valider les coordonnées du joueur par rapport à la carte
        public boolean validatePosition(char[][] map) {
                int x = position.getX();
                int y = position.getY();
                return (x < 0 || x >= map[0].length || y < 0 || y >= map.length || map[y][x] == '#');
        }
}

