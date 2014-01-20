package com.github.javadojo;

import static java.util.Collections.singletonMap;

import java.util.LinkedHashMap;

class DisplayGrid {

    static final String TURN = "+";
    static final String START = "X";
    static final String SAMPLE = "S";
    static final String VERTICAL = "|";
    static final String CURRENT = "*";
    static final String HORIZENTAL = "-";
    static final String EMPTY = " ";

    private Direction currentDirection;
    private Point currentPoint;
    private final Grid grid;
    final LinkedHashMap<Integer, LinkedHashMap<Integer, String>> path;

    DisplayGrid() {
        currentPoint = new Point(0, 0);
        grid = new Grid(currentPoint, 1, 1);
        currentDirection = Direction.EAST;
        this.path = new LinkedHashMap<>(singletonMap(0, new LinkedHashMap<>(singletonMap(0, START))));
    }

    @Override
    public String toString() {
        /* prepare */
        String backup = currentPoint.getSymbol(this);
        currentPoint.changeSymbol(this, CURRENT);
        String path = grid.print();
        currentPoint.changeSymbol(this, backup);
        return path;
    }

    boolean isStartPoint(Point point) {
        return grid.isStartPoint(point);
    }

    void addNewPoint(Point newPoint) {
        grid.addNewPoint(newPoint);
    }

    /*
     * Operations
     */

    void moveForward() {
        currentDirection = currentDirection.moveForward(this);
    }

    void turnLeft() {
        currentDirection = currentDirection.turnLeft(this);
    }

    void turnRight() {
        currentDirection = currentDirection.turnRight(this);
    }

    void takeSample() {
        sample();
    }

    /*
     * Displays
     */

    void turn() {
        currentPoint.turn(this);
    }

    void moveHorizentally(boolean isEast) {
        currentPoint = currentPoint.moveHorizentally(this, isEast);
    }

    void moveVertically(boolean isNorth) {
        currentPoint = currentPoint.moveVertically(this, isNorth);
    }

    private void sample() {
        currentPoint.sample(this);
    }

    private class Grid {
        private Point startPoint;
        private int height;
        private int width;

        private Grid(Point startPoint, int width, int height) {
            this.startPoint = startPoint;
            this.width = width;
            this.height = height;
        }

        private boolean isStartPoint(Point point) {
            return startPoint.equals(point);
        }

        private String print() {
            StringBuilder sb = new StringBuilder();

            int startOrdinate = startPoint.ordinate;
            int startAbscissa = startPoint.abscissa;

            for (int j = startOrdinate; j > startOrdinate - height; j--) {
                for (int i = startAbscissa; i < startAbscissa + width; i++) {
                    sb.append(new Point(i, j).getSymbol(DisplayGrid.this));
                }
                sb.append(MarsRover.LINE_SEPARATOR);
            }

            return sb.toString();
        }

        private void addNewPoint(Point newPoint) {
            if (isOutGrid(newPoint)) {
                width = isAbscissaOutGrid(newPoint) ? width + 1 : width;
                height = isOrdinateOutGrid(newPoint) ? height + 1 : height;
                startPoint = newPoint.newStartFrom(startPoint);
            }

        }

        private boolean isOutGrid(Point point) {
            return isAbscissaOutGrid(point) || isOrdinateOutGrid(point);
        }

        private boolean isOrdinateOutGrid(Point point) {
            return point.isOrdinateInRange(startPoint, height);
        }

        private boolean isAbscissaOutGrid(Point point) {
            return point.isAbscissaInRange(startPoint, width);
        }
    }

}
