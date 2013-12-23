package com.github.javadojo;

/**
 * The Path rover is programmed to drive around Path.
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
 * Note that the Path rover always land at the <code>X</code> and starts by facing east.
 *
 * The Path rover can send a 2D string representation of its pathRepresentation back to Mission Control. The following character are
 * used with the following meanings:
 * <dl>
 *     <dt>X</dt>
 *     <dd>where the Path rover landed</dd>
 *     <dt>*</dt>
 *     <dd>current position of the Path rover</dd>
 *     <dt>-</dt>
 *     <dd>pathRepresentation in the west-east direction</dd>
 *     <dt>|</dt>
 *     <dd>pathRepresentation in the north-south direction</dd>
 *     <dt>+</dt>
 *     <dd>a place where the Path rover turned or a crossroad</dd>
 *     <dt>S</dt>
 *     <dd>a place where a sample was taken</dd>
 * </dl>
 */
public class MarsRover {

    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Path path = new Path();

    public MarsRover(String operations) {

        for (Character command: operations.toCharArray()) {

            switch(command) {

                case 's':

                    moveForward();
                    break;

                case 'S':

                    takeSample();
                    break;

                case 'l':

                    turnLeft();
                    break;

                case 'r':

                    turnRight();
                    break;
            }
        }
    }

    public String path() {

        return path.getRepresentation();
    }

    public MarsRover turnLeft() {

        path.turnLeft();

        return this;
    }

    public MarsRover turnRight() {

        path.turnRight();

        return this;
    }

    public MarsRover moveForward() {

        path.moveForward();

        return this;
    }

    public MarsRover takeSample() {

        path.takeSample();

        return this;
    }
}
