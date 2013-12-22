package com.github.javadojo.marsrover.directions;

public class North extends Direction {

    public int x(int x) {
        return x;
    }

    public int y(int y) {
        return y + 1;
    }
}
