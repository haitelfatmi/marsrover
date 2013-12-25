package com.github.javadojo;

public enum Direction {
    NORD(0),EAST(1),SOUTH(2),WEST(3);

    private final int value;
    Direction(int value) {
        this.value = value;
    }

    public static Direction nextDirection(Direction current, RoverCommand command) {
        if (command == RoverCommand.TurnLeft) {
            return direction((current.value -1 + 4) % 4);
        }
        if (command == RoverCommand.TurnRight) {
            return direction((current.value + 1) % 4);
        }
        return current;
    }

    public static Direction direction(int value) {
        switch (value) {
            case 0:
                return NORD;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
        }
        throw new IllegalArgumentException("failed invalid value");
    }
}
