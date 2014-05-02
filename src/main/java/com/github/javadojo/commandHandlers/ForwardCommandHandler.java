/**
 * 
 */
package com.github.javadojo.commandHandlers;

import com.github.javadojo.marsrover.MarsRover;

/**
 * @author Hamza
 *
 */
class ForwardCommandHandler implements CommandHandler {

  @Override
  public MarsRover execute(MarsRover rover) {
    return rover.moveForward();
  }

}
