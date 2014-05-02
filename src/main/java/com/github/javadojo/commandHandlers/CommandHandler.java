/**
 * 
 */
package com.github.javadojo.commandHandlers;

import com.github.javadojo.marsrover.MarsRover;

/**
 * @author Hamza
 *
 */
interface CommandHandler {

  public MarsRover execute(MarsRover rover);
  
}
