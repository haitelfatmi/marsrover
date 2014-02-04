package com.github.javadojo;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<Step> steps = StepAdapter.adapt("sssslssss");
        PathTracker tracker = new PathTracker();
        tracker.registerAll(steps);
        System.out.println(tracker.as2DView());
    }

}
