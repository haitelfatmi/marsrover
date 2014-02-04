package com.github.javadojo;

import java.util.ArrayList;
import java.util.List;

public final class PathTracker {

    private List<Spot> path = new ArrayList<Spot>();
    private DirectionHolder directionHolder;

    public PathTracker() {
        this.path.add(new Spot(0, 0).withView(Direction.EST.toString()));
        this.directionHolder = new DirectionHolder(Direction.EST);
    }

    public void registerStep(Step s) {
        directionHolder.alterDirectionIfNeeded(s);
        path.add(directionHolder.nextSpotRelativeTo(getCurrentPosition()));
    }

    public void registerAll(List<Step> steps) {
        for (Step s : steps) {
            registerStep(s);
        }
    }

    public String as2DView() {
        return ViewResolver.resolve(this.path);
    }

   

    private Spot getCurrentPosition() {
        return path.get(path.size() - 1);
    }

   

}
