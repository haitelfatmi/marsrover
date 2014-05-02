/**
 * 
 */
package com.github.javadojo.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.github.javadojo.symbols.ActionSymbol;

import static com.github.javadojo.marsrover.MarsRover.LINE_SEPARATOR;

/**
 * @author ait el fatmi
 * 
 */
public final class PathPrinter {

  private static final PathPrinter instance = new PathPrinter();

  public static PathPrinter getInstance() {
    return instance;
  }

  private PathPrinter() {
    // TODO Auto-generated constructor stub
  }

  public String printPath(List<Position> positionsHistory) {
    List<Position> positions = copyOf(positionsHistory);
    changeLastPositionSymbolToActualSymbol(positions);
    detectTurnPoints(positions);
    Collections.sort(positions);
    AbsractPosition[][] positionsArray = convertPositionsListTo2DArray(positions);
    return print(positionsArray);
  }

 /**
  * Return an independent copy of the given list
  **/
  private List<Position> copyOf(List<Position> positionsHistory) {
    List<Position> positions = new LinkedList<>();
    for (Position position : positionsHistory) {
      try {
        positions.add(position.clone());
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
    }
    return positions;
  }

  /**
   * Detect the positions where the mars rover turned
   **/
  private void detectTurnPoints(List<Position> positions) {
    for (int i = 1; i < positions.size() - 1; i++) {
      Position position = positions.get(i);
      Position previousPosition = positions.get(i - 1);
      Position nextPosition = positions.get(i + 1);
      if (position.isTurnPoint(previousPosition, nextPosition))
        position.setSymbol(ActionSymbol.TURN_MOVE_SYMBOL);
    }
  }

  /**
   * Return the 2D representation of positions of the given list
   * Example:
   * Given 5 positions's coordinates of a mars rover: 
   * (-2,3); (1,4); (-4,0); (0,-3); (2,-1) then
   * minX == -4; maxX == -2;
   * minY == -3; maxY == -5;
   * So the 2D array width == -maxX - minX + 1 & height == -maxY - minY + 1
   **/
  private AbsractPosition[][] convertPositionsListTo2DArray(List<Position> positions) {
    Comparator<Position> xComparator = Position.getxComparator();
    Comparator<Position> yComparator = Position.getyComparator();
    int minX = Collections.min(positions, xComparator).getX();
    int minY = Collections.min(positions, yComparator).getY();
    int maxX = Collections.max(positions, xComparator).getX();
    int maxY = Collections.max(positions, yComparator).getY();
    int ecartX = maxX - minX + 1;
    int ecartY = maxY - minY + 1;
    AbsractPosition[][] positions2DArray = new Position[ecartY][ecartX];
    for (Position position : positions) {
      positions2DArray[position.getY() - minY][position.getX() - minX] = position;
    }
    return positions2DArray;
  }

  private String print(AbsractPosition[][] positionsArray) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int row = positionsArray.length - 1; row >= 0; row--) {
      for (int column = 0; column < positionsArray[row].length; column++) {
        AbsractPosition position = positionsArray[row][column];
        position = (position == null) ? NullPosition.getInstanse() : position;//if the position is null replace it with NullPosition object 
        stringBuilder.append(position);
      }
      stringBuilder.append(LINE_SEPARATOR);
    }
    return stringBuilder.toString();
  }

  private void changeLastPositionSymbolToActualSymbol(List<Position> positions) {
    Position lastPosition = positions.get(positions.size() - 1);
    lastPosition.setSymbol(ActionSymbol.ACTUAL_POSITION_SYMBOL);
  }

}
