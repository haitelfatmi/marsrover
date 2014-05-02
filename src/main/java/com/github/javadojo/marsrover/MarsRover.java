/**
 * 
 */

package com.github.javadojo.marsrover;

import java.util.LinkedList;
import java.util.List;

import com.github.javadojo.commandHandlers.CommandInvoker;
import com.github.javadojo.orientations.EastOrientation;
import com.github.javadojo.orientations.NorthOrientation;
import com.github.javadojo.orientations.SouthOrientation;
import com.github.javadojo.orientations.WestOrientation;
import com.github.javadojo.symbols.ActionSymbol;
import com.github.javadojo.utils.PathPrinter;
import com.github.javadojo.utils.Position;

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
 * <dd>path in the west-east orientation</dd>
 * <dt>|</dt>
 * <dd>path in the north-south orientation</dd>
 * <dt>+</dt>
 * <dd>a place where the Mars rover turned or a crossroad</dd>
 * <dt>S</dt>
 * <dd>a place where a sample was taken</dd>
 * </dl>
 * 
 * This class Implement Design patern state. The state here is the Orientation
 * 
 */
public class MarsRover {

  public static final String LINE_SEPARATOR = System.getProperty("line.separator");

  private Orientation orientation;

  private List<Position> positionHistory = new LinkedList<Position>();

  private PathPrinter printer = PathPrinter.getInstance();

  /**
   * 
   * @param operations
   *          sequence as a string
   */
  public MarsRover(String operations) {
    orientation = EastOrientation.getInstance();
    trackInitialPosition();
    CommandInvoker.invoke(this, operations);
  }

  public String path() {
    return printer.printPath(positionHistory);
  }

  public MarsRover turnLeft() {
    return orientation.turnLeft(this);
  }

  public MarsRover turnRight() {
    return orientation.turnRight(this);
  }

  public MarsRover moveForward() {
    return orientation.moveForward(this);
  }

  public MarsRover takeSample() {
    Position lastPosition = lastPosition();
    lastPosition.setSymbol(ActionSymbol.SAMPLE_TAKEN_SYMBOL);
    return this;
  }

  public List<Position> getPositionHistory() {
    return positionHistory;
  }

  public Position lastPosition() {
    return positionHistory.get(positionHistory.size() - 1);
  }

  public void addPosition(Position newPosition) {
    if (positionHistory.contains(newPosition)) {
      int index = positionHistory.indexOf(newPosition);
      if (!positionHistory.get(index).sameSymbolAs(newPosition)) {
        newPosition.setSymbol(ActionSymbol.TURN_MOVE_SYMBOL);
      }
    }
    positionHistory.add(newPosition);
  }
  
  private void trackInitialPosition(){
    Position newPosition = new Position(0, 0, ActionSymbol.INITIAL_POSITION_SYMBOL);
    addPosition(newPosition);
  }

  /**
   * The orientation is a class representing the state of the {@link MarsRover}.
   * This class take part of implementation of design pattern state. There is 4
   * Orientations(States): {@link NorthOrientation}, {@link EastOrientation},
   * {@link WestOrientation} and {@link SouthOrientation}. All those
   * orientations extends {@link Orientation}
   * 
   * @author ait el fatmi
   * 
   */
  public abstract static class Orientation {

    abstract protected MarsRover turnLeft(MarsRover rover);

    abstract protected MarsRover turnRight(MarsRover rover);

    public MarsRover moveForward(MarsRover rover){
      trackMovement(rover);
      return rover;
    }

    abstract protected void trackMovement(MarsRover rover);

    protected MarsRover setOrientation(MarsRover rover, Orientation orientation) {
      rover.orientation = orientation;
      return rover;
    }

  }

}
