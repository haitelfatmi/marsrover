package com.github.javadojo;

import java.util.HashMap;
import java.util.Map;

public class PathRepresentation {

    private Map<Class<? extends AbstractAction>, Character> action2representation = new HashMap<>();

    private Character[][] chart = new Character[0][0];

    public PathRepresentation() {

        chart = new Character[][] {{ Character.valueOf('X') }};

        action2representation.put(MoveEastWestAction.class, Character.valueOf('-'));
        action2representation.put(MoveNorthSouthAction.class, Character.valueOf('|'));
        action2representation.put(TakeSampleAction.class, Character.valueOf('S'));
        action2representation.put(TurnLeftAction.class, Character.valueOf('+'));
    }

    public String compute(Path path) {

        int x = 0;
        int y = 0;

        for (AbstractAction action : path.getActions()) {

            x = action.getX();
            y = action.getY();

            resizeIfNecessary(x, y);

            Character cha = action2representation.get(action.getClass());

            chart[y][x] = cha;
        }

        // Final plot has to be overwritten whatever happened
        chart[y][x] = Character.valueOf('*');

        // Some actions may happen on the landing plot
        // They should be overwritten
        chart[0][0] = Character.valueOf('X');

        return getRepresentation();
    }

    private String getRepresentation() {

        StringBuffer buffer = new StringBuffer();

        for (int i = chart.length; i > 0; i--) {

            // Parse lines in reverse order
            // Start from the top to the bottom
            for (int j = 0; j < chart[0].length; j++) {

                Character cha = chart[i - 1][j];

                buffer.append(cha == null ? " " : cha.charValue());
            }

            buffer.append(MarsRover.LINE_SEPARATOR);
        }

        return buffer.toString();
    }

    private void resizeIfNecessary(int x, int y) {

        int width = chart[0].length;
        int height = chart.length;

        // Create a new column if necessary
        if (x >= width) {

            Character newRow[] = new Character[width + 1];

            System.arraycopy(chart[0], 0, newRow, 0, width);

            chart[0] = newRow;
        }

        // Create a new line if necessary
        if (y >= height) {

            Character[][] newChart = new Character[height + 1][width];

            for (int i = 0; i < height; i++) {

                Character newRow[] = new Character[width];

                System.arraycopy(chart[i], 0, newRow, 0, width);

                newChart[i] = newRow;
            }

            newChart[y] = new Character[width];

            chart = newChart;
        }
    }
}
