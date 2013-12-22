package com.github.javadojo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Map {

    private final List<List<Character>> map = new ArrayList<List<Character>>();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (List<Character> line : reverse(map)) {
            for (Character c : line) {
                result.append(c);
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    public void print(Point position, Character c) {
        resizeIfNotContains(position);

        // we never override the starting point (X)
        if (!map.get(position.y).get(position.x).equals('X')) {
            map.get(position.y).set(position.x, c);
        }
    }

    private void resizeIfNotContains(Point position) {
        // check height
        if (position.y >= map.size()) {
            for (int i = map.size(); i <= position.y; i++) {
                map.add(new ArrayList<Character>());
            }
        }
        // check width
        for (List<Character> line : map) {
            if (position.x >= line.size()) {
                for (int i = line.size(); i <= position.x; i++) {
                    line.add(' ');
                }
            }
        }
    }

    private <T> List<T> reverse(List<T> line) {
        // Note: this way of reversing a list is pretty inefficient and ugly. Using Guava would be nicer, but I don't
        // want to add any dependencies. Implementing a "ReverseIterator" would also be nicer, but too many lines of
        // code. I'm lazy.
        List<T> reversedLine = new ArrayList<T>();
        for (T c : line) {
            reversedLine.add(c);
        }
        Collections.reverse(reversedLine);
        return reversedLine;
    }
}
