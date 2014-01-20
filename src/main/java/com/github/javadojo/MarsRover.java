package com.github.javadojo;

/**
 * The Mars rover is programmed to drive around Mars. Its programming is very
 * simple. The commands are the following:
 * <dl>
 * <dt>s</dt>
 * <dd>drive in a straight line</dd>
 * <dt>r</dt>
 * <dd>turn right</dd>
 * <dt>l</dt>
 * <dd>turn left</dd>
 * </dl>
 * 
 * Note that the Mars rover always land at the <code>X</code> and starts by
 * facing east.
 * 
 * The Mars rover can send a 2D string representation of its path back to
 * Mission Control. The following character are used with the following
 * meanings:
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

    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private final DisplayGrid grid;

    public MarsRover(String operations) {
        grid = new DisplayGrid();
        char[] chars = operations.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Operation.valueOf(chars[i]).applyTo(this);
        }
    }

    public String path() {
        return grid.toString();

    }

    public MarsRover turnLeft() {
        grid.turnLeft();
        return this;
    }

    public MarsRover turnRight() {
        grid.turnRight();
        return this;
    }

    public MarsRover moveForward() {
        grid.moveForward();
        return this;
    }

    public MarsRover takeSample() {
        grid.takeSample();
        return this;
    }

    private enum Operation {

        MOVEFORWARD('s') {
            @Override
            void applyTo(MarsRover marsRover) {
                marsRover.moveForward();
            }
        },
        TURNLEFT('l') {
            @Override
            void applyTo(MarsRover marsRover) {
                marsRover.turnLeft();
            }
        },
        TURNRIGHT('r') {
            @Override
            void applyTo(MarsRover marsRover) {
                marsRover.turnRight();
            }
        },
        TAKESAMPLE('S') {
            @Override
            void applyTo(MarsRover marsRover) {
                marsRover.takeSample();
            }
        };

        private char key;

        private Operation(char key) {
            this.key = key;
        }

        static Operation valueOf(char c) {
            for (Operation operation : values()) {
                if (operation.key == c)
                    return operation;
            }
            throw new IllegalArgumentException();
        }

        abstract void applyTo(MarsRover marsRover);
    }

}
