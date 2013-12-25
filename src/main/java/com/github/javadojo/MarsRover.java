package com.github.javadojo;

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

    private String operations;

    public MarsRover(String operations) {
        this.operations = operations;
    }

    private Drawing drawing;
    public String path() {
        if (drawing != null) {
            return drawing.toStringDrawing();
        }
        final char[] chars = operations.toCharArray();
        if (chars.length == 0) {
            return "X";
        }

        MapDimensions dimensions = new MapDimensions();
        for (char c : chars) {
            dimensions.moveCommand(RoverCommand.toCommand(c));
        }

        drawing = new Drawing(dimensions, Direction.EAST);

        for (char c : chars) {
            drawing.moveRover(RoverCommand.toCommand(c));
        }
        drawing.finish();
        return drawing.toStringDrawing();
    }

    public MarsRover turnLeft() {
        operations = operations + "l";
        cleanUp();
        return this;
    }

    public MarsRover turnRight() {
        operations = operations + "r";
        cleanUp();
        return this;
    }

    public MarsRover moveForward() {
        operations = operations + "s";
        cleanUp();
        return this;
    }

    public MarsRover takeSample() {
        operations = operations + "S";
        cleanUp();
        return this;
    }

    private void cleanUp() {
        drawing = null;
    }
}
