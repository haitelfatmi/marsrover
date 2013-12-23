package com.github.javadojo;

public class Coordinates {

    private int y;
    private int x;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int result = 73;
        result = result * 31 + getX();
        result = result * 31 + getX();
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        
        if(!(obj instanceof Coordinates)) {
            return false;
        }
        
        final Coordinates that = (Coordinates) obj;
        return this.getX() == that.getX() && this.getY() == that.getY();
    }

    public Coordinates moveTowards(Direction direction) {
        return new Coordinates(x + direction.getHorizontalIncrement(), y + direction.getVerticalIncrement());
    }

}
