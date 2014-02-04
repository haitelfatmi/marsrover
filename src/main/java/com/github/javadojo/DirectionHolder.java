package com.github.javadojo;

public final class DirectionHolder {

    private Direction direction;
    private Direction previousDirection;
    private boolean isAltered;

    public DirectionHolder(Direction direction) {
        this.direction = direction;
    }

    public void alterDirectionIfNeeded(Step s) {
        if(!((s == Step.TURN_RIGHT) || (s == Step.TURN_LEFT))){
            return;
        }
        markAsAltered();
        if (s == Step.TURN_RIGHT) {
            direction = direction.rotateToRight();
        } else if (s == Step.TURN_LEFT) {
            direction = direction.rotateToLeft();
        }
    }

    private void markAsAltered() {
        this.previousDirection = this.direction;
        this.isAltered = true;        
    }

    Direction getDirection() {
        return direction;
    }

    public Spot nextSpotRelativeTo(Spot s) {
        if(this.isAltered){
            return  fireCurrentAlteration(s);
        }
        return direction.nextSpot(s).withView(direction.toString());
    }

    private Spot fireCurrentAlteration(Spot s) {
        this.isAltered = false;
        return previousDirection.nextSpot(s).withView("+");
    }
}
