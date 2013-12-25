package com.github.javadojo;

public enum RoverCommand {
    Forward('s'), TurnLeft('l'), TurnRight('r'), TakeSnapShot('S');

    private final char representation;
    private RoverCommand(char rep) {
        this.representation = rep;
    }
    public static RoverCommand toCommand(char command) {
        switch (command) {
            case 's':
                return Forward;
            case 'l':
                return TurnLeft;
            case 'r':
                return TurnRight;
            case 'S':
                return TakeSnapShot;
        }
        throw new IllegalArgumentException("failed to get matching command");
    }
}
