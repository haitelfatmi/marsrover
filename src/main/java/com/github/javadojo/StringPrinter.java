package com.github.javadojo;

public class StringPrinter implements Printer<String> {

    private static final char BACKGROUND_CHAR = ' ';

    @Override
    public String print(Path path) {
        char[][] pathMap = new char[path.getHeight()][path.getWidth()];
        printBackGround(pathMap);
        printPath(path, pathMap);
        return flush(pathMap);
    }

    private String flush(char[][] pathMap) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < pathMap.length; i++) {
            for (int j = 0; j < pathMap[i].length; j++) {
                buffer.append(pathMap[i][j]); 
            }
            buffer.append(MarsRover.LINE_SEPARATOR);
        }
        
        return buffer.toString();
    }

    private void printPath(Path path, char[][] pathMap) {
        for (Point point : path.points()) {
            int column = xAxisToMatrixColumn(path, point);
            int row = yAxisToMatrixRow(path, point);
            pathMap[row][column] = point.getCursor().getSymbol();
        }
    }

    private int yAxisToMatrixRow(Path path, Point point) {
        return path.getHeight() - (point.getCoordinates().getY() - path.getSouthLimit()) - 1;
    }

    private int xAxisToMatrixColumn(Path path, Point point) {
        return point.getCoordinates().getX() - path.getWestLimit();
    }

    private void printBackGround(char[][] pathMap) {
        for (int i = 0; i < pathMap.length; i++) {
            for (int j = 0; j < pathMap[i].length; j++) {
                pathMap[i][j] = BACKGROUND_CHAR; 
            }
        }
    }

}
