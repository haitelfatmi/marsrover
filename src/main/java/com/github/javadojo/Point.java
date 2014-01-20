package com.github.javadojo;

import java.util.LinkedHashMap;

class Point {
    final int abscissa;
    final int ordinate;

    Point(int abscissa, int ordinate) {
        this.abscissa = abscissa;
        this.ordinate = ordinate;
    }

    void sample(DisplayGrid grid) {
        changeSymbol(grid, DisplayGrid.SAMPLE);
    }

    void turn(DisplayGrid grid) {
        boolean isNotstartPoint = !grid.isStartPoint(this);
        if (isNotstartPoint)
            changeSymbol(grid, DisplayGrid.TURN);
    }

    Point moveVertically(DisplayGrid grid, boolean isNorth) {
        int newOrdinate = isNorth ? ordinate + 1 : ordinate - 1;
        Point newPoint = new Point(abscissa, newOrdinate);
        String currentSymbol = newPoint.getSymbol(grid);
        if (DisplayGrid.SAMPLE.equals(currentSymbol) || DisplayGrid.START.equals(currentSymbol)) {
            return newPoint;
        } else if (DisplayGrid.HORIZENTAL.equals(currentSymbol)) {
            newPoint.turn(grid);
            return newPoint;
        }
        newPoint.changeSymbol(grid, DisplayGrid.VERTICAL);
        grid.addNewPoint(newPoint);
        return newPoint;
    }

    Point moveHorizentally(DisplayGrid grid, boolean isEast) {
        int newAbscissa = isEast ? abscissa + 1 : abscissa - 1;
        Point newPoint = new Point(newAbscissa, ordinate);
        String current = newPoint.getSymbol(grid);
        if (DisplayGrid.SAMPLE.equals(current) || DisplayGrid.START.equals(current)) {
            return newPoint;
        } else if (DisplayGrid.VERTICAL.equals(current)) {
            newPoint.turn(grid);
            return newPoint;
        }
        newPoint.changeSymbol(grid, DisplayGrid.HORIZENTAL);
        grid.addNewPoint(newPoint);
        return newPoint;
    }

    String getSymbol(DisplayGrid grid) {
        if (grid.path.get(abscissa) == null || grid.path.get(abscissa).get(ordinate) == null) {
            return DisplayGrid.EMPTY;
        }
        return grid.path.get(abscissa).get(ordinate);
    }

    void changeSymbol(DisplayGrid grid, String newSymbol) {
        if (grid.path.get(abscissa) == null) {
            grid.path.put(abscissa, new LinkedHashMap<Integer, String>());
        }
        grid.path.get(abscissa).put(ordinate, newSymbol);

    }

    Point newStartFrom(Point oldStart) {
        int newAbscissa = abscissa < oldStart.abscissa ? abscissa : oldStart.abscissa;
        int newOrdinate = ordinate > oldStart.ordinate ? ordinate : oldStart.ordinate;
        return new Point(newAbscissa, newOrdinate);
    }

    boolean isAbscissaInRange(Point startPoint, int width) {
        return abscissa < startPoint.abscissa || abscissa >= (startPoint.abscissa + width);
    }

    boolean isOrdinateInRange(Point startPoint, int height) {
        return ordinate > startPoint.ordinate || ordinate <= (startPoint.ordinate - height);
    }

}
