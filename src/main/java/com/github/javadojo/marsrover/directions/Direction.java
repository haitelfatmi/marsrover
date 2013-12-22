package com.github.javadojo.marsrover.directions;

public abstract class Direction {

    public static final Direction NORTH;
    public static final Direction EAST;
    public static final Direction SOUTH;
    public static final Direction WEST;

    static {

        NORTH = new North();
        EAST = new East();
        SOUTH = new South();
        WEST = new West();

        NORTH.setLeft(WEST);
        NORTH.setRight(EAST);

        EAST.setLeft(NORTH);
        EAST.setRight(SOUTH);

        SOUTH.setLeft(EAST);
        SOUTH.setRight(WEST);

        WEST.setLeft(SOUTH);
        WEST.setRight(NORTH);
    }

    private Direction left;
    private Direction right;

    public Direction left() {
        return left;
    }

    private void setLeft(Direction left) {
        this.left = left;
    }

    public Direction right() {
        return right;
    }

    private void setRight(Direction right) {
        this.right = right;
    }

    public abstract int x(int x);
    public abstract int y(int y);

}
