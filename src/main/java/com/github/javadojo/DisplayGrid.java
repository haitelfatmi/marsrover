package com.github.javadojo;

import static java.util.Collections.singletonMap;

import java.util.LinkedHashMap;

public class DisplayGrid {

    private static final String TURN = "+";
    private static final String START = "X";
    private static final String SAMPLE = "S";
    private static final String VERTICAL = "|";
    private static final String CURRENT = "*";
    private static final String HORIZENTAL = "-";
    private static final String EMPTY = " ";

    private Direction currentDirection;
    private Point currentPoint;
    private final Grid grid;
    private final LinkedHashMap<Integer, LinkedHashMap<Integer, String>> symbols;

    DisplayGrid() {
        currentPoint = new Point(0, 0);
        grid = new Grid(currentPoint, 1, 1);
        currentDirection = Direction.EAST;
        this.symbols = new LinkedHashMap<>(singletonMap(0, new LinkedHashMap<>(singletonMap(0, START))));
    }

    @Override
    public String toString() {
        String backup = currentPoint.getSymbol();
        currentPoint.changeSymbol(CURRENT);
        String path = grid.print();
        currentPoint.changeSymbol(backup);
        return path;
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
        currentPoint.turn();
    }

    void moveHorizentally(boolean isEast) {
        currentPoint = currentPoint.moveHorizentally(isEast);
    }

    void moveVertically(boolean isNorth) {
        currentPoint = currentPoint.moveVertically(isNorth);
    }

    private void sample() {
        currentPoint.sample();
    }

    private class Point {
        private final int abscissa;
        private final int ordinate;

        private Point(int abscissa, int ordinate) {
            this.abscissa = abscissa;
            this.ordinate = ordinate;
        }

        public void sample() {
            changeSymbol(SAMPLE);
        }

        private void turn() {
            boolean isNotstartPoint = !grid.isStartPoint(this);
            if (isNotstartPoint)
                changeSymbol(TURN);
        }

        private Point moveVertically(boolean isNorth) {
            int newOrdinate = isNorth ? ordinate + 1 : ordinate - 1;
            Point newPoint = new Point(abscissa, newOrdinate);
            String currentSymbol = newPoint.getSymbol();
            if (SAMPLE.equals(currentSymbol) || START.equals(currentSymbol)) {
                return newPoint;
            } else if (HORIZENTAL.equals(currentSymbol)) {
                newPoint.turn();
                return newPoint;
            }
            newPoint.changeSymbol(VERTICAL);
            grid.updateWith(newPoint);
            return newPoint;
        }

        private Point moveHorizentally(boolean isEast) {
            int newAbscissa = isEast ? abscissa + 1 : abscissa - 1;
            Point newPoint = new Point(newAbscissa, ordinate);
            String current = newPoint.getSymbol();
            if (SAMPLE.equals(current) || START.equals(current)) {
                return newPoint;
            } else if (VERTICAL.equals(current)) {
                newPoint.turn();
                return newPoint;
            }
            newPoint.changeSymbol(HORIZENTAL);
            grid.updateWith(newPoint);
            return newPoint;
        }

        private String getSymbol() {
            if (symbols.get(abscissa) == null || symbols.get(abscissa).get(ordinate) == null) {
                return EMPTY;
            }
            return symbols.get(abscissa).get(ordinate);
        }

        private void changeSymbol(String newSymbol) {
            if (symbols.get(abscissa) == null) {
                symbols.put(abscissa, new LinkedHashMap<Integer, String>());
            }
            symbols.get(abscissa).put(ordinate, newSymbol);

        }

        private Point newStartFrom(Point oldStart) {
            int newAbscissa = abscissa < oldStart.abscissa ? abscissa : oldStart.abscissa;
            int newOrdinate = ordinate > oldStart.ordinate ? ordinate : oldStart.ordinate;
            return new Point(newAbscissa, newOrdinate);
        }

        private boolean isAbscissaInRange(Point startPoint, int width) {
            return abscissa < startPoint.abscissa || abscissa >= (startPoint.abscissa + width);
        }

        private boolean isOrdinateInRange(Point startPoint, int height) {
            return ordinate > startPoint.ordinate || ordinate <= (startPoint.ordinate - height);
        }

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
                    sb.append(new Point(i, j).getSymbol());
                }
                sb.append(MarsRover.LINE_SEPARATOR);
            }

            return sb.toString();
        }

        private void updateWith(Point newPoint) {
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
