/**
 * 
 */
package com.github.javadojo.commandHandlers;

import com.github.javadojo.marsrover.MarsRover;

/**
 * @author Hamza
 *
 */
class TurnLeftCommandHandler implements CommandHandler {

  @Override
  public MarsRover execute(MarsRover rover) {
    return rover.turnLeft();
  }

}
