package com.github.javadojo;

import java.awt.*;

public abstract class AbstractAction {

    private Point point;
    private Facing facing;

    public AbstractAction(Point point, Facing facing) {

        this.facing = facing;
        this.point = point;
    }

    public abstract String getRepresentation();
}
