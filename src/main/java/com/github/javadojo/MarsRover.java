package com.github.javadojo;

import static com.github.javadojo.Direction.*;

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
    
    private Direction direction;
    private Path path;
    private Printer<String> printer;
    
    public MarsRover(String operations) {
        this(operations, new CommandParser(), new StringPrinter());
    }
    
    public MarsRover(String operations, CommandParser commandParser, Printer<String> printer) {
        this(printer);
        //This reference 'escaping' from constructor. Bad practise, but the easy fix (change constructor signature + introduce factory) 
        //involves changing the tests. There are other possible fixes, but the increased complexity is not justified.
        commandParser.executesCommands(operations, this);
    }
    
    private MarsRover(Printer<String> printer) {
        direction = EAST;
        path = new Path(new Coordinates(0,0), Cursor.LANDING_POINT);
        this.printer = printer;
    }

    public String path() {
        return path.printOn(printer);
    }

    public MarsRover turnLeft() {
        direction = direction.turnLeft();
        return turn();
    }

    public MarsRover turnRight() {
        direction = direction.turnRight();
        return turn();
    }

    private MarsRover turn() {
        path.mark(Cursor.CROSSROAD);
        return this;
    }

    public MarsRover moveForward() {
        path.move(direction);
        return this;
    }

    public MarsRover takeSample() {
        path.mark(Cursor.SAMPLE);
        return this;
    }
    
}
