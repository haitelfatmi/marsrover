package com.github.javadojo;

public class TakeSample extends Operation {

    @Override
    public void execute(MarsRover marsRover, Map map) {
        map.print(marsRover.position, 'S');
    }
}
