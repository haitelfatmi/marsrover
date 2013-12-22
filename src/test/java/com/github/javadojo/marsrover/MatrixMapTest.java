package com.github.javadojo.marsrover;

import com.github.javadojo.marsrover.directions.Direction;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MatrixMapTest {

    @Test
    public void forward() {
        System.out.println("forward");
        System.out.println(new MatrixMap(5)
                .step(Direction.EAST)
                .trail());
    }

    @Test
    public void longForward() {
        System.out.println("longForward");
        System.out.println(new MatrixMap(5)
                .step(Direction.EAST)
                .step(Direction.EAST)
                .step(Direction.EAST)
                .trail());
    }

    @Test
    public void longWest() {
        System.out.println("longWest");
        System.out.println(new MatrixMap(5)
                .step(Direction.WEST)
                .step(Direction.WEST)
                .step(Direction.WEST)
                .trail());
    }

    @Test
    public void longNorth() {
        System.out.println("longNorth");
        System.out.println(new MatrixMap(5)
                .step(Direction.NORTH)
                .step(Direction.NORTH)
                .step(Direction.NORTH)
                .trail());
    }

    @Test
    public void longSouth() {
        System.out.println("longSouth");
        System.out.println(new MatrixMap(5)
                .step(Direction.SOUTH)
                .step(Direction.SOUTH)
                .step(Direction.SOUTH)
                .trail());
    }

    @Test
    public void trail() {
        System.out.println("trail");
        System.out.println(new MatrixMap(5)
                .step(Direction.NORTH)
                .step(Direction.NORTH)
                .step(Direction.WEST)
                .step(Direction.WEST)
                .step(Direction.WEST)
                .step(Direction.SOUTH)
                .step(Direction.SOUTH)
                .step(Direction.SOUTH)
                .step(Direction.SOUTH)
                .step(Direction.EAST)
                .step(Direction.EAST)
                .step(Direction.EAST)
                .step(Direction.EAST)
                .step(Direction.EAST)
                .trail());
    }


}
