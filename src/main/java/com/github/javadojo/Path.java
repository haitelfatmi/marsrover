package com.github.javadojo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Path {

    private final Map<Coordinates, Point> map;
    int northLimit, southLimit, eastLimit, westLimit;
    private Coordinates currentPosition;

    public Path(Coordinates initialPosition, Cursor cursor) {
        map = new HashMap<>();
        currentPosition = initialPosition;

        map.put(initialPosition, Point.valueOf(currentPosition, cursor));
        northLimit = initialPosition.getY();
        southLimit = initialPosition.getY();
        eastLimit = initialPosition.getX();
        westLimit = initialPosition.getX();
    }

    public void mark(Cursor cursor) {
        if(currentPosition.getX() < westLimit) {
            westLimit = currentPosition.getX();
        } else if(currentPosition.getX() > eastLimit) {
            eastLimit = currentPosition.getX();
        }
        
        if(currentPosition.getY() < southLimit) {
            southLimit = currentPosition.getY();
        } else if(currentPosition.getY() > northLimit) {
            northLimit = currentPosition.getY();
        }
        
        insertOrUpdatePoint(cursor);
    }

    private void addPoint(Point point) {
        map.put(point.getCoordinates(), point);
    }
    
    private void insertOrUpdatePoint(Cursor cursor) {
        addPoint(getCurrentPoint().mergeWith(cursor));
    }

    private Point getCurrentPoint() {
        Point point = map.get(currentPosition);
        if(point == null) {
            point = Point.valueOf(currentPosition, Cursor.NULL);
        }
        return point;
    }
    
    public int getWidth() {
        return eastLimit - westLimit + 1;
    }
    
    public int getHeight() {
        return  northLimit - southLimit + 1;        
    }
    
    public Collection<Point> points() {
        return Collections.unmodifiableCollection(map.values());
    }

    public int getWestLimit() {
        return westLimit;
    }

    public int getSouthLimit() {
        return southLimit;
    }
    
    public Coordinates getCurrentPosition() {
        return getCurrentPoint().getCoordinates();
    }

    public void move(Direction direction) {
        currentPosition = currentPosition.moveTowards(direction);
        mark(direction.getCursor());
    }

    public <T> T printOn(Printer<T> printer) {
        Cursor currentPositionCursor = getCurrentPoint().getCursor();
        mark(Cursor.CURRENT_POSITION);
        final T print = printer.print(this);
        mark(currentPositionCursor);
        return print;
    }
    
}
