package com.github.javadojo.marsrover;

import com.github.javadojo.marsrover.directions.Direction;

import java.util.Arrays;

import static com.github.javadojo.MarsRover.LINE_SEPARATOR;
import static com.github.javadojo.marsrover.CharArrays.containsOnly;
import static com.github.javadojo.marsrover.CharArrays.indexOfFirstDifferent;
import static com.github.javadojo.marsrover.CharArrays.indexOfLastDifferent;

public class MatrixMap {

    public static final char BLANK = ' ';
    private char[][] map;

    private final Renderer renderer;

    private int x;
    private int y;

    public MatrixMap(int length) {

        renderer = new Renderer();

        map = new char[length * 2][length * 2];
        x = length;
        y = length;

        for (char[] row : map) {
            Arrays.fill(row, BLANK);
        }

    }


    public MatrixMap step(Direction direction) {

        reDrawCurrentSymbol(direction);

        moveToNextPosition(direction);

        drawRover();

        return this;
    }

    private void drawRover() {
        map[x][y] = renderer.getStartRendition();
    }

    private void moveToNextPosition(Direction direction) {
        x = direction.x(x);
        y = direction.y(y);
    }

    private void reDrawCurrentSymbol(Direction direction) {
        map[x][y] = renderer.getTrailRendition(direction);
    }

    public String trail() {
        StringBuilder trail = new StringBuilder();

        for (int y = maxY(); y >= minY(); y--) {
            for (int x = minX() ; x <= maxX(); x++) {
                trail.append(map[x][y]);
            }
            trail.append(LINE_SEPARATOR);
        }

        return trail.toString();
    }

    private int maxX() {
        int max = map.length - 1;
        while (containsOnly(map[max], BLANK)) {
            max--;
        }
        return max;
    }


    private int minX() {
        int min = 0;
        while (containsOnly(map[min], BLANK)) {
            min++;
        }
        return min;
    }

    private int minY() {
        int min = map.length;
        for (char[] row : map) {
            int x = indexOfFirstDifferent(row, BLANK);
            if (x != -1 && x < min) {
                min = x;
            }
        }
        return min;
    }

    private int maxY() {
        int max = 0;
        for (char[] row : map) {
            int x = indexOfLastDifferent(row, BLANK);
            if ((x != -1) && (x > max)) {
                max = x;
            }
        }
        return max;
    }


    public void drill() {
        renderer.enableDrillRendition();
    }
}

