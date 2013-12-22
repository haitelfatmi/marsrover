package com.github.javadojo;

public class TurnRight extends Operation {

    @Override
    public void execute(MarsRover marsRover, Map map) {
        marsRover.direction = marsRover.direction.right();
        map.print(marsRover.position, '+');
    }
}
