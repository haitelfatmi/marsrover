/**
 * 
 */
package com.github.javadojo.symbols;

/**
 * @author ait el fatmi
 * 
 */
public class ActionSymbol {

  private char symbol;

  private int priority;

  private ActionSymbol(char symbol, int priority) {
    this.symbol = symbol;
    this.priority = priority;
  }

  public static final ActionSymbol ACTUAL_POSITION_SYMBOL = new ActionSymbol('*', 0);

  public static final ActionSymbol EAST_WEST_MOVE_SYMBOL = new ActionSymbol('-', 4);

  public static final ActionSymbol INITIAL_POSITION_SYMBOL = new ActionSymbol('X', 2);

  public static final ActionSymbol NORTH_SOUTH_MOVE_SYMBOL = new ActionSymbol('|', 4);

  public static final ActionSymbol SAMPLE_TAKEN_SYMBOL = new ActionSymbol('S', 1);

  public static final ActionSymbol TURN_MOVE_SYMBOL = new ActionSymbol('+', 3);

  public char getSymbol() {
    return symbol;
  }

  public boolean samePririorityAs(ActionSymbol symbol) {
    return this.priority == symbol.priority;
  }

  public int getPriority() {
    return priority;
  }

}
