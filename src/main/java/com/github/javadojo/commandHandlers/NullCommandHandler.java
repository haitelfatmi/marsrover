/**
 * 
 */
package com.github.javadojo.commandHandlers;

import com.github.javadojo.marsrover.MarsRover;

/**
 * @author Hamza
 * 
 */
class NullCommandHandler implements CommandHandler {

  private static final NullCommandHandler INSTANCE = new NullCommandHandler();

  private NullCommandHandler() {
  }

  @Override
  public MarsRover execute(MarsRover rover) {
    throw new IllegalArgumentException("Invalid commande");
  }

  public static NullCommandHandler getInstance() {
    return INSTANCE;
  }

}
