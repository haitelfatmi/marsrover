package com.github.javadojo;

enum Direction {

    EAST {
        @Override
        void doMoveForward(DisplayGrid displayGrid) {
            displayGrid.moveHorizentally(true);
        }
    },
    NORTH {
        @Override
        void doMoveForward(DisplayGrid displayGrid) {
            displayGrid.moveVertically(true);
        }
    },
    WEST {
        @Override
        void doMoveForward(DisplayGrid displayGrid) {
            displayGrid.moveHorizentally(false);
        }
    },
    SOUTH {
        @Override
        void doMoveForward(DisplayGrid displayGrid) {
            displayGrid.moveVertically(false);
        }
    };

    Direction turnLeft(DisplayGrid displayGrid) {
        displayGrid.turn();
        return next();
    }

    Direction turnRight(DisplayGrid displayGrid) {
        displayGrid.turn();
        return previous();
    }

    Direction moveForward(DisplayGrid displayGrid) {
        doMoveForward(displayGrid);
        return this;
    }

    abstract void doMoveForward(DisplayGrid displayGrid);

    private Direction next() {
        int nextOrder = ordinal() + 1;
        return valueOf(nextOrder);
    }

    private Direction previous() {
        int previousOrder = ordinal() - 1;
        return valueOf(previousOrder);
    }

    private Direction valueOf(int order) {
        int orderInRange = ((order % 4) + 4) % 4;
        return values()[orderInRange];
    }

}
