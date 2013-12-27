package com.github.javadojo;

public class TakeSample extends Operation {

    @Override
    public void execute(MarsRover marsRover, Map map) {
        map.putSymbol(marsRover.position, 'S');
    }
}
