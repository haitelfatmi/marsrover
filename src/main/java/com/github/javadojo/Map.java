package com.github.javadojo;

import java.awt.*;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Map {

    private final InfiniteAutosizedList<InfiniteAutosizedList<Character>> map;
    private final Point topRightExcluding = new Point(1, 1);
    private final Point bottomLeft = new Point(0, 0);

    public Map() {
        this.map = new InfiniteAutosizedList<>(
                new ArrayList<InfiniteAutosizedList<Character>>(),
                new InfiniteAutosizedList.Filler<InfiniteAutosizedList<Character>>() {
                    @Override
                    public InfiniteAutosizedList<Character> get() {
                        return new InfiniteAutosizedList<>(new ArrayList<Character>(), ' ');
                    }
                }
        );
    }

    @Override
    public String toString() {
        StringWriter result = new StringWriter();
        try {
            print(result);
        } catch (IOException ignore) {
        } finally {
            try {
                result.close();
            } catch (IOException ignore) {
            }
        }
        return result.toString();

    }

    public void print(Writer out) throws IOException {
        for (int i = topRightExcluding.y - 1; i >= bottomLeft.y; i--) {
            for (int j = bottomLeft.x; j <= topRightExcluding.x - 1; j++) {
                out.write(map.get(i).get(j));
            }
            out.write(System.lineSeparator());
        }
    }

    public void putSymbol(Point position, Character c) {
        resize(position);

        // merge crossing path
        if (getSymbol(position) == '|' && c == '-'
                || getSymbol(position) == '-' && c == '|') {
            c = '+';
        }

        // check if the symbol should override what's already on the map
        if (priority(c) > priority(getSymbol(position))) {
            map.get(position.y).set(position.x, c);
        }
    }

    // we could extract a symbol class and let each symbol know of its priority, but the priority
    // is actually a concern of the map: different maps could decide to print different symbols
    private int priority(Character c) {
        switch (c) {
            case ' ':
                return -1;
            case '-':
                return 0;
            case '|':
                return 0;
            case '+':
                return 1;
            case 'X':
                return 2;
            case 'S':
                return 3;
            case '*':
                return 4;
        }
        return 0;
    }

    private void resize(Point position) {
        topRightExcluding.x = Math.max(position.x + 1, topRightExcluding.x);
        topRightExcluding.y = Math.max(position.y + 1, topRightExcluding.y);
        bottomLeft.x = Math.min(position.x, bottomLeft.x);
        bottomLeft.y = Math.min(position.y, bottomLeft.y);
    }

    public Character getSymbol(Point position) {
        return map.get(position.y).get(position.x);
    }

}
