package com.github.javadojo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.github.javadojo.Facing.EAST;

public class Path {

    private List<AbstractAction> actions = new ArrayList<>();

    private PathRepresentation pathRepresentation = new PathRepresentation();
    private Point pos = new Point();
    private Facing facing = EAST;

    public void moveForward() {

        switch (facing) {

            case EAST:
                pos.x++;
                actions.add(new MoveEastWestAction(pos, facing));
                break;
            case WEST:
                pos.x--;
                actions.add(new MoveEastWestAction(pos, facing));
                break;
            case NORTH:
                pos.y++;
                break;
            case SOUTH:
                pos.y--;
                break;
        }
    }

    public void turnLeft() {

        switch (facing) {

            case EAST:
                facing = Facing.NORTH;
                break;
            case WEST:
                facing = Facing.SOUTH;
                break;
            case NORTH:
                facing = Facing.WEST;
                break;
            case SOUTH:
                facing = Facing.EAST;
                break;
        }
    }

    public void turnRight() {

        switch (facing) {

            case EAST:
                facing = Facing.SOUTH;
                break;
            case WEST:
                facing = Facing.NORTH;
                break;
            case NORTH:
                facing = Facing.EAST;
                break;
            case SOUTH:
                facing = Facing.WEST;
                break;
        }
    }

    public List<AbstractAction> getActions() {

        return actions;
    }

    public void takeSample() {

        actions.add(new TakeSampleAction(pos, facing));
    }
}
