/**
 * 
 */
package com.github.javadojo.commandHandlers;

import java.util.HashMap;

/**
 * @author Hamza
 *
 */
class DefaultedHashMap extends HashMap<Character, CommandHandler> {

  @Override
  public CommandHandler get(Object c) {
    return containsKey(c) ? super.get(c) : NullCommandHandler.getInstance();
  }
  
}
