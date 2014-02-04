package com.github.javadojo;

import java.util.List;

public final class ViewResolver {

    public static String resolve(List<Spot> path) {
        String[][] map = new String[path.size()][path.size()];

        adjustToPresentationLayer(path);
        for (Spot spot : path) {
            spot.drawInto(map);
        }
        StringBuilder view = new StringBuilder();
        for (int j = path.size() - 1; j >= 0; j--) {
            for (int i = 0; i < path.size(); i++) {
                view.append((map[i][j] != null ? map[i][j] : " "));
                if (i == path.size() - 1) {
                    view.append(MarsRover.LINE_SEPARATOR);
                }
            }
        }
        return view.toString();
    }

    private static void adjustToPresentationLayer(List<Spot> path) {
        path.get(0).withView("X");
        path.get(path.size() - 1).withView("*");
    }
}
