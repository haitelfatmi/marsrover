/**
 * 
 */
package com.github.javadojo.commandHandlers;

import java.util.Map;

import com.github.javadojo.marsrover.MarsRover;

/**
 * @author ait el fatmi
 * 
 */
public class CommandInvoker {

  private static Map<Character, CommandHandler> commandHandlerMap = new DefaultedHashMap();
  static {
    commandHandlerMap.put('s', new ForwardCommandHandler());
    commandHandlerMap.put('r', new TurnRightCommandHandler());
    commandHandlerMap.put('l', new TurnLeftCommandHandler());
    commandHandlerMap.put('S', new TakeSampleCommandHandler());
  }

  public static void invoke(MarsRover rover, String movementString) {
    char[] chars = movementString.toCharArray();
    for (char c : chars) {
      CommandHandler handler = lookupCommandHandler(c);
      handler.execute(rover);
    }
  }

  private static CommandHandler lookupCommandHandler(Character c) {
    return commandHandlerMap.get(c);
  }

}
