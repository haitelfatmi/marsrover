package com.github.javadojo.marsrover;

import com.github.javadojo.marsrover.directions.Direction;

import static com.github.javadojo.marsrover.directions.Direction.*;

public class Renderer {

    private Direction last;
    private boolean drill;

    public char getTrailRendition(Direction direction) {

        char c = 'X';

        if (last != null) {

            if (direction != last) {

                c = getRendering('+');

            } else {

                if (verticalMove()) {
                    c = getRendering('|');
                }

                if (horizontalMove()) {
                    c = getRendering('-');
                }

            }
        }

        last = direction;

        return c;
    }

    private char getRendering(char c) {

        if (drill) {
            drill = false;
            return 'S';
        }

        return c;
    }

    private boolean horizontalMove() {
        return last == EAST || last == WEST;
    }

    private boolean verticalMove() {
        return last == NORTH || last == SOUTH;
    }

    char getStartRendition() {
        return '*';
    }

    public void enableDrillRendition() {
        drill = true;
    }
}
