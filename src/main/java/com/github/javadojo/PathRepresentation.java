package com.github.javadojo;

public class PathRepresentation {

    // X marks the spot
    // Could be refactored as a landing action
    private StringBuffer buffer = new StringBuffer("X");

    public String compute(Path path) {

        for (AbstractAction action : path.getActions()) {

            if (action instanceof TakeSampleAction) {

                buffer.setLength(buffer.length() - 1);
            }

            buffer.append(action.getRepresentation());
        }

        buffer.setLength(buffer.length() - 1);

        buffer.append("*");
        buffer.append(MarsRover.LINE_SEPARATOR);

        return buffer.toString();
    }
}
