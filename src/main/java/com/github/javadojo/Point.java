package com.github.javadojo;

public class Point {
    
    private Coordinates coordinates;
    private Cursor cursor;
    
    private Point(Coordinates coordinates, Cursor cursor) {
        this.coordinates = coordinates;
        this.cursor = cursor;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    
    public Cursor getCursor() {
        return cursor;
    }

    public Point mergeWith(Cursor otherCursor) {
        return valueOf(coordinates, otherCursor.mergeWith(cursor));
    }
    
    public static Point valueOf(Coordinates coordinates, Cursor cursor) {
        return new Point(coordinates, cursor);
    }

}
