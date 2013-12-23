package com.github.javadojo;

import java.awt.*;

public abstract class AbstractAction {

    private Point point;

    public AbstractAction(Point point) {

        this.point = point;
    }

    public int getX() {

        return point.x;
    }

    public int getY() {

        return point.y;
    }
}
