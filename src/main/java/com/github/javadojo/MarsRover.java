package com.github.javadojo;

import com.github.javadojo.marsrover.MatrixMap;
import com.github.javadojo.marsrover.directions.Direction;

import static com.github.javadojo.marsrover.directions.Direction.*;

/**
 * The Mars rover is programmed to drive around Mars.
 * Its programming is very simple. The commands are the following:
 * <dl>
 * <dt>s</dt>
 * <dd>drive in a straight line</dd>
 * <dt>r</dt>
 * <dd>turn right</dd>
 * <dt>l</dt>
 * <dd>turn left</dd>
 * </dl>
 * <p/>
 * Note that the Mars rover always land at the <code>X</code> and starts by facing east.
 * <p/>
 * The Mars rover can send a 2D string representation of its path back to Mission Control. The following character are
 * used with the following meanings:
 * <dl>
 * <dt>X</dt>
 * <dd>where the Mars rover landed</dd>
 * <dt>*</dt>
 * <dd>current position of the Mars rover</dd>
 * <dt>-</dt>
 * <dd>path in the west-east direction</dd>
 * <dt>|</dt>
 * <dd>path in the north-south direction</dd>
 * <dt>+</dt>
 * <dd>a place where the Mars rover turned or a crossroad</dd>
 * <dt>S</dt>
 * <dd>a place where a sample was taken</dd>
 * </dl>
 */
public class MarsRover {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private MatrixMap map;
    private Direction direction;

    public MarsRover(String operations) {

        map = new MatrixMap(10);
        direction = EAST;

        for (Character character : operations.toCharArray()) {
            switch (character) {
                case 's':
                    moveForward();
                    break;
                case 'l':
                    turnLeft();
                    break;
                case 'r':
                    turnRight();
                    break;
                case 'S':
                    takeSample();
                    break;
            }
        }
    }

    public String path() {
        return map.trail();
    }

    public MarsRover turnLeft() {
        direction = direction.left();
        return this;
    }

    public MarsRover turnRight() {
        direction = direction.right();
        return this;
    }

    public MarsRover moveForward() {
        map.step(direction);
        return this;
    }

    public MarsRover takeSample() {
        map.drill();
        return this;
    }
}
