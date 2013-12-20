package com.github.javadojo;

import java.awt.*;

public class TakeSampleAction extends AbstractAction {

    public TakeSampleAction(Point point, Facing facing) {

        super(point, facing);
    }

    @Override
    public String getRepresentation() {

        return "S";
    }
}
