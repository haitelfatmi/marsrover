package com.github.javadojo;

enum Direction {

    EAST(0) {
        @Override
        void doMoveForward(DisplayGrid displayGrid) {
            displayGrid.moveHorizentally(true);
        }
    },
    NORTH(1) {
        @Override
        void doMoveForward(DisplayGrid displayGrid) {
            displayGrid.moveVertically(true);
        }
    },
    WEST(2) {
        @Override
        void doMoveForward(DisplayGrid displayGrid) {
            displayGrid.moveHorizentally(false);
        }
    },
    SOUTH(3) {
        @Override
        void doMoveForward(DisplayGrid displayGrid) {
            displayGrid.moveVertically(false);
        }
    };

    private final int order;

    private Direction(int order) {
        this.order = order;
    }

    Direction turnLeft(DisplayGrid displayGrid) {
        displayGrid.turn();
        return valueOf(order + 1);
    }

    Direction turnRight(DisplayGrid displayGrid) {
        displayGrid.turn();
        return valueOf(order - 1);
    }

    Direction moveForward(DisplayGrid displayGrid) {
        doMoveForward(displayGrid);
        return this;
    }

    abstract void doMoveForward(DisplayGrid displayGrid);

    private Direction valueOf(int order) {
        order = ((order % 4) + 4) % 4;
        for (Direction direction : values()) {
            if (direction.order == order)
                return direction;
        }
        throw new IllegalArgumentException("out of bound.");
    }
}
