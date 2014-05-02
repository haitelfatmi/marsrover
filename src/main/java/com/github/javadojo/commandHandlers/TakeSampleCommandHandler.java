/**
 * 
 */
package com.github.javadojo.commandHandlers;

import com.github.javadojo.marsrover.MarsRover;

/**
 * @author Hamza
 *
 */
class TakeSampleCommandHandler implements CommandHandler {

  @Override
  public MarsRover execute(MarsRover rover) {
    return rover.takeSample();
  }

}
