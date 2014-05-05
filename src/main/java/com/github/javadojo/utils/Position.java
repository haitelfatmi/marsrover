package com.github.javadojo.utils;

import java.util.Comparator;

import com.github.javadojo.symbols.ActionSymbol;

public class Position implements Comparable<Position>, Cloneable, AbsractPosition {

  private int x;

  private int y;

  private ActionSymbol symbole;

  private static Comparator<Position> xComparator;

  private static Comparator<Position> yComparator;

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Position(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  public Position(Integer x, Integer y, ActionSymbol symbol) {
    this(x, y);
    this.symbole = symbol;
  }

  public void setSymbol(ActionSymbol symbol) {
    this.symbole = symbol;
  }

  public ActionSymbol getSymbol() {
    return symbole;
  }

  private int getPriority() {
    return symbole.getPriority();
  }

  public boolean sameSymbolAs(Position position) {
    return this.symbole == position.symbole;
  }

  public boolean sameCoordonateAs(Position position) {
    return this.x == position.x && this.y == position.y;
  }

  public boolean samePririorityAs(Position position) {
    return this.symbole.samePririorityAs(position.symbole);
  }

  private boolean is90DegreesTurnPoint(Position previousPosition, Position nextPosition) {
    return previousPosition.x != nextPosition.x && previousPosition.y != nextPosition.y;
  }

  private boolean is360DegreesTurnPoint(Position previousPosition, Position nextPosition) {
    return previousPosition.x == nextPosition.x && previousPosition.y == nextPosition.y;
  }

  public boolean isTurnPoint(Position previousPosition, Position nextPosition) {
    return is360DegreesTurnPoint(previousPosition, nextPosition) || is90DegreesTurnPoint(previousPosition, nextPosition);
  }

  @Override
  public int compareTo(Position o) {
    if (this.y > o.y)
      return -1;
    if (this.y < o.y)
      return 1;
    if (this.x > o.x)
      return 1;
    if (this.x < o.x)
      return -1;
    if (this.getPriority() > o.getPriority())
      return -1;
    if (this.getPriority() < o.getPriority())
      return 1;
    return 0;
  }

  /*
   * Java effective recommandation: Always override clone() method
   */
  @Override
  public Position clone() throws CloneNotSupportedException {
    Position newPosition = new Position(this.x, this.y);
    newPosition.symbole = this.symbole;
    return newPosition;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Position))
      return false;
    return sameCoordonateAs((Position) obj);
  }

  @Override
  public String toString() {
    return String.valueOf(symbole.getSymbol());
  }

  public static Comparator<Position> getxComparator() {
    if (xComparator == null) {
      xComparator = new Comparator<Position>() {

        @Override
        public int compare(Position o1, Position o2) {
          if (o1.getX() > o2.getX())
            return 1;
          if (o1.getX() < o2.getX())
            return -1;
          return 0;
        }
      };
    }
    return xComparator;
  }

  public static Comparator<Position> getyComparator() {
    if (yComparator == null) {
      yComparator = new Comparator<Position>() {

        @Override
        public int compare(Position o1, Position o2) {
          if (o1.getY() > o2.getY())
            return 1;
          if (o1.getY() < o2.getY())
            return -1;
          return 0;
        }
      };
    }
    return yComparator;
  }

}
