package com.github.javadojo;

import java.util.HashMap;
import java.util.Map;

public class PathRepresentation {

    private Map<Class<? extends AbstractAction>, Character> action2representation = new HashMap<>();

    private Character[][] chart = new Character[0][0];

    public PathRepresentation() {

        chart = new Character[][] {{Character.valueOf('X')}};

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

        chart[y][x] = Character.valueOf('*');
        chart[0][0] = Character.valueOf('X');

        return getRepresentation();
    }

    private String getRepresentation() {

        StringBuffer buffer = new StringBuffer();

        for (int i = chart.length; i > 0; i--) {

            for (int j = 0; j < chart[0].length; j++) {

                Character cha = chart[i - 1][j];

                if (cha == null) {

                    buffer.append(" ");

                } else {

                    buffer.append(cha.charValue());
                }
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

            Character newRow[] = new Character[x + 1];

            System.arraycopy(chart[0], 0, newRow, 0, width);

            chart[0] = newRow;
        }

        // Create a new line if necessary
        if (y >= height) {

            Character[][] newChart = new Character[chart.length + 1][chart[0].length];

            for (int i = 0; i < chart.length; i++) {

                Character newRow[] = new Character[chart[0].length];

                System.arraycopy(chart[i], 0, newRow, 0, chart[0].length);

                newChart[i] = newRow;
            }

            newChart[y] = new Character[chart[0].length];

            chart = newChart;
        }
    }
}
