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
public class SouthOrientation extends Orientation {

  private static Orientation instance = new SouthOrientation();

  public static Orientation getInstance() {
    return instance;
  }
  
  private SouthOrientation() {
    // TODO Auto-generated constructor stub
  }

  @Override
  protected MarsRover turnLeft(MarsRover rover) {
    return setOrientation(rover, EastOrientation.getInstance());
  }

  @Override
  protected MarsRover turnRight(MarsRover rover) {
    return setOrientation(rover, WestOrientation.getInstance());
  }

  @Override
  protected void trackMovement(MarsRover rover) {
    Position lastPosition = rover.lastPosition();
    int x = lastPosition.getX();
    int y = lastPosition.getY() - 1;
    Position newPosition = new Position(x, y, ActionSymbol.NORTH_SOUTH_MOVE_SYMBOL);
    rover.addPosition(newPosition);
  }

}
