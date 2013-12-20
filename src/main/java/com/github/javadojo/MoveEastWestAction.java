package com.github.javadojo;

import java.awt.*;

public class MoveEastWestAction extends AbstractAction {

    public MoveEastWestAction(Point point, Facing facing) {

        super(point, facing);
    }

    @Override
    public String getRepresentation() {

        return "-";
    }
}
