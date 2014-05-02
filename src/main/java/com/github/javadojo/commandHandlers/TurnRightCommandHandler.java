/**
 * 
 */
package com.github.javadojo.commandHandlers;

import com.github.javadojo.marsrover.MarsRover;

/**
 * @author Hamza
 *
 */
class TurnRightCommandHandler implements CommandHandler {

  @Override
  public MarsRover execute(MarsRover rover) {
    return rover.turnRight();
  }

}
