package com.github.javadojo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.github.javadojo.Facing.EAST;

public class Path {

    private List<AbstractAction> actions = new ArrayList<>();
    private PathRepresentation representation = new PathRepresentation();
    private Point position = new Point();
    private Facing facing = EAST;

    public void moveForward() {

        switch (facing) {

            case EAST:
                position.x++;
                actions.add(new MoveEastWestAction((Point) position.clone()));
                break;
            case WEST:
                position.x--;
                actions.add(new MoveEastWestAction((Point) position.clone()));
                break;
            case NORTH:
                position.y++;
                actions.add(new MoveNorthSouthAction((Point) position.clone()));
                break;
            case SOUTH:
                position.y--;
                actions.add(new MoveNorthSouthAction((Point) position.clone()));
                break;
        }
    }

    public void turnLeft() {

        switch (facing) {

            case EAST:
                facing = Facing.NORTH;
                actions.add(new TurnLeftAction((Point) position.clone()));
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

        actions.add(new TakeSampleAction((Point) position.clone()));
    }

    public String getRepresentation() {

        return representation.compute(this);
    }
}
