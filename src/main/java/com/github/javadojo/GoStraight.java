package com.github.javadojo;

public class GoStraight extends Operation {

    @Override
    public void execute(MarsRover marsRover, Map map) {
        Character pathSegment;
        switch (marsRover.direction) {
            case NORTH:
                marsRover.position.translate(0, 1);
                pathSegment = '|';
                break;
            case EAST:
                marsRover.position.translate(1, 0);
                pathSegment = '-';
                break;
            case SOUTH:
                marsRover.position.translate(0, -1);
                pathSegment = '|';
                break;
            case WEST:
                marsRover.position.translate(-1, 0);
                pathSegment = '-';
                break;
            default:
                throw new IllegalStateException("Unknown direction");
        }
        map.print(marsRover.position, pathSegment);
    }
}
