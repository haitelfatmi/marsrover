package com.github.javadojo;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The Mars rover is programmed to drive around Mars.
 * Its programming is very simple. The commands are the following:
 * <dl>
 *     <dt>s</dt>
 *     <dd>drive in a straight line</dd>
 *     <dt>r</dt>
 *     <dd>turn right</dd>
 *     <dt>l</dt>
 *     <dd>turn left</dd>
 * </dl>
 *
 * Note that the Mars rover always land at the <code>X</code> and starts by facing east.
 * 
 * The Mars rover can send a 2D string representation of its path back to Mission Control. The following character are
 * used with the following meanings:
 * <dl>
 *     <dt>X</dt>
 *     <dd>where the Mars rover landed</dd>
 *     <dt>*</dt>
 *     <dd>current position of the Mars rover</dd>
 *     <dt>-</dt>
 *     <dd>path in the west-east direction</dd>
 *     <dt>|</dt>
 *     <dd>path in the north-south direction</dd>
 *     <dt>+</dt>
 *     <dd>a place where the Mars rover turned or a crossroad</dd>
 *     <dt>S</dt>
 *     <dd>a place where a sample was taken</dd>
 * </dl>
 */
public class MarsRover {

    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private final List<Operation> operations = new LinkedList<Operation>();

    final Point position = new Point(0, 0);
    Direction direction = Direction.EAST;


    public MarsRover(String operations) {
        for (char op : operations.toCharArray()) {
            switch (op) {
                case 's':
                    moveForward();
                    break;
                case 'r':
                    turnRight();
                    break;
                case 'l':
                    turnLeft();
                    break;
                case 'S':
                    takeSample();
                    break;
                default:
                    throw new IllegalArgumentException("Operation unknown");
            }
        }
    }

    public String path() {
        Map map = new Map();
        // always print starting position
        map.print(position, 'X');
        for (Operation operation: operations) {
            operation.execute(this, map);
        }
        // print end position
        map.print(position, '*');
        return map.toString();
    }

    public MarsRover turnLeft() {
        operations.add(new TurnLeft());
        return this;
    }

    public MarsRover turnRight() {
        operations.add(new TurnRight());
        return this;
    }

    public MarsRover moveForward() {
        operations.add(new GoStraight());
        return this;
    }

    public MarsRover takeSample() {
        operations.add(new TakeSample());
        return this;
    }
}
