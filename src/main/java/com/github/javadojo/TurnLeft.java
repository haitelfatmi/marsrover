package com.github.javadojo;

public class TurnLeft extends Operation {

    @Override
    public void execute(MarsRover marsRover, Map map) {
        marsRover.direction = marsRover.direction.left();
        map.putSymbol(marsRover.position, '+');
    }
}
