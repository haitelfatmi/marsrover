package com.github.javadojo;

import java.util.Arrays;

public class Drawing {

    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    int roverPositionX;
    int roverPositionY;
    char[][] drawing;
    Direction direction ;

    public Drawing(MapDimensions dimensions, Direction direction) {
        this.roverPositionX = dimensions.initialRoverX();
        this.roverPositionY = dimensions.initialRoverY();
        this.direction = direction;
        this.drawing = new char[dimensions.arrayLenghtY()][dimensions.arrayLenghtX()];
        for(char[] line : drawing) {
            Arrays.fill(line, ' ');
        }
        print('X');
    }

    private void print(char c) {
        char current = getCurrentChar();
        if (current == 'X' || current == 'S') {
            return;
        }
        if ((c == '|' && current == '-') || (c == '-' && current == '|') ) {
            c = '+';
        }
        drawing[roverPositionY][roverPositionX] = c;
    }

    private char getCurrentChar() {
        return drawing[roverPositionY][roverPositionX];
    }


    public void moveRover(RoverCommand command) {
        direction = Direction.nextDirection(direction, command);
        if (command == RoverCommand.Forward) {
            switch (direction) {
                case NORD:
                    roverPositionY++;
                    break;
                case EAST:
                    roverPositionX++;
                    break;
                case SOUTH:
                    roverPositionY--;
                    break;
                case WEST:
                    roverPositionX--;
                    break;
            }
        }
        switch (command) {
            case Forward:
                if (direction == Direction.EAST || direction == Direction.WEST) {
                    print('-');
                }  else {
                    print ('|');
                }
                break;
            case TurnLeft:
            case TurnRight:
                print('+');
                break;
            case TakeSnapShot:
                print('S');
        }
    }


    public String toStringDrawing() {
        StringBuilder sb = new StringBuilder();
        for(int i=drawing.length-1;i >=0 ;i--) {
            sb.append(drawing[i]);
            sb.append(LINE_SEPARATOR);
        }
        return sb.toString();
    }

    public void finish() {
        drawing[roverPositionY][roverPositionX] = '*';
    }
}
