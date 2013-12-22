package com.github.javadojo.marsrover.directions;

public class West extends Direction {

    public int x(int x) {
        return x - 1;
    }

    public int y(int y) {
        return y;
    }
}
