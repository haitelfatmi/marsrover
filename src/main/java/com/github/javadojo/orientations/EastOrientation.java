/**
 * 
 */
package com.github.javadojo.orientations;

import com.github.javadojo.marsrover.MarsRover;
import com.github.javadojo.marsrover.MarsRover.Orientation;
import com.github.javadojo.symbols.ActionSymbol;
import com.github.javadojo.utils.Position;

/**
 * @author ait el fatmi
 * 
 */
public class EastOrientation extends Orientation {

  private static Orientation instance = new EastOrientation();

  public static Orientation getInstance() {
    return instance;
  }

  private EastOrientation(){
  }
 
  @Override
  protected MarsRover turnLeft(MarsRover rover) {
    return setOrientation(rover, NorthOrientation.getInstance());
  }

  @Override
  protected MarsRover turnRight(MarsRover rover) {
    return setOrientation(rover, SouthOrientation.getInstance());
  }

  @Override
  protected void trackMovement(MarsRover rover) {
    Position lastPosition = rover.lastPosition();
    int x = lastPosition.getX() + 1;
    int y = lastPosition.getY();
    Position newPosition = new Position(x, y, ActionSymbol.EAST_WEST_MOVE_SYMBOL);
    rover.addPosition(newPosition);
  }

}
