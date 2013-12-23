package com.github.javadojo;

/**
 * Using {@link Enum#ordinal()} method is usually considered bad practise (see Effective Java Programming item 31 and 33).
 * This is true when ordinal is used to implicitly map enum order to a concept not logically connected to order.
 * In here {@link Enum#ordinal()} is used to access items' relative order... which seems pretty good in spite of the dogma. 
 *
 */
public enum Direction {
    //Listed clockwise.
    EAST(1, 0, Cursor.HORIZONTAL_MOVEMENT),
    SOUTH(0, -1, Cursor.VERTICAL_MOVEMENT),
    WEST(-1, 0, Cursor.HORIZONTAL_MOVEMENT),
    NORTH(0, 1, Cursor.VERTICAL_MOVEMENT);
    
    private int horizontalIncrement;
    private int verticalIncrement;
    private Cursor cursor;

    Direction (int horizontalIncrement, int verticalIncrement, Cursor cursor) {
        this.horizontalIncrement = horizontalIncrement;
        this.verticalIncrement = verticalIncrement;
        this.cursor = cursor;
    }
    
    public Direction turnLeft() {
        return turnClockwise(-1) ;
    }
    
    public Direction turnRight() {
        return turnClockwise(+1) ;
    }

    private Direction turnClockwise(int units) {
        return Direction.values()[(this.ordinal() + units + Direction.values().length) % Direction.values().length];
    }
    
    public int getHorizontalIncrement() {
        return horizontalIncrement;
    }
    
    public int getVerticalIncrement() {
        return verticalIncrement;
    }

    public Cursor getCursor() {
        return cursor;
    }

}
