package com.github.javadojo;

class MapDimensions {
    int minX = 0;
    int maxX = 0;
    int minY = 0;
    int maxY = 0;

    int positionX =0;
    int positionY =0;

    Direction direction = Direction.EAST;

    private void updateMaxAndMin() {
        if (positionX < minX) {
            minX = positionX;
        }
        if (positionY < minY) {
            minY = positionY;
        }
        if (positionX > maxX) {
            maxX = positionX;
        }
        if (positionY > maxY) {
            maxY = positionY;
        }
    }


    public void moveCommand(RoverCommand command) {
        final Direction nextDirection = Direction.nextDirection(direction, command);
        if (command == RoverCommand.Forward) {
            switch (direction) {
                case NORD:
                    positionY++;
                    break;
                case EAST:
                    positionX++;
                    break;
                case SOUTH:
                    positionY--;
                    break;
                case WEST:
                    positionX--;
                    break;
            }
            updateMaxAndMin();
        }
        direction = nextDirection;

    }

    public int arrayLenghtX() {
        return maxX - minX + 1;
    }

    public int arrayLenghtY() {
        return maxY - minY + 1;
    }

    public int initialRoverX() {
        return Math.abs(minX);
    }

    public int initialRoverY() {
        return Math.abs(minY);
    }
}
