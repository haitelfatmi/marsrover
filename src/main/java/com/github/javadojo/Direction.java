package com.github.javadojo;


public enum Direction {
    EST {
        @Override
        Direction rotateToRight() {
            return SOUTH;
        }
        @Override
        Direction rotateToLeft() {
            return NORTH;
        }
        @Override
        Spot nextSpot(Spot s) {
            return s.forwardThroughLongitude();
        }
        @Override
        public String toString() {
            return "-";
        }
        
    }, OUEST {
        @Override
        Direction rotateToRight() {
            return NORTH;
        }
        @Override
        Direction rotateToLeft() {
            return SOUTH;
        }
        @Override
        Spot nextSpot(Spot s) {
            return s.backwardThroughLongitude();
        }
        @Override
        public String toString() {
            return "-";
        }
        
    }, NORTH {
        @Override
        Direction rotateToRight() {
            return EST;
        }
        @Override
        Direction rotateToLeft() {
            return OUEST;
        }
        @Override
        Spot nextSpot(Spot s) {
            return s.forwardThroughLatitude();
        }
        @Override
        public String toString() {
            return "|";
        }
        
    }, SOUTH {
        @Override
        Direction rotateToRight() {
            return OUEST;
        }
        @Override
        Direction rotateToLeft() {
            return EST;
        }
        @Override
        Spot nextSpot(Spot s) {
            return s.forwardThroughLatitude();
        }
        @Override
        public String toString() {
            return "|";
        }
        
    };
    
    abstract Direction rotateToRight();
    abstract Direction rotateToLeft();
    
    abstract Spot nextSpot(Spot s);
}
