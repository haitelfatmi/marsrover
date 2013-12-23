package com.github.javadojo;

import java.util.EnumMap;

public enum Cursor {
    NULL('\0', -1000),
    HORIZONTAL_MOVEMENT('-', 100), 
    VERTICAL_MOVEMENT('|', 100), 
    CROSSROAD('+', 200), 
    CURRENT_POSITION('*', 300), 
    SAMPLE('S', 400), 
    LANDING_POINT('X', 500), ;

    private static EnumMap<Cursor, EnumMap<Cursor, Cursor>> mergeTable;

    static {
        mergeTable = new EnumMap<>(Cursor.class);

        for (Cursor first : Cursor.values()) {
            EnumMap<Cursor, Cursor> mergeLine = new EnumMap<Cursor, Cursor>(Cursor.class);
            for (Cursor second : Cursor.values()) {
                mergeLine.put(second, max(first, second));
            }
            mergeTable.put(first, mergeLine);
        }

        mergeTable.get(HORIZONTAL_MOVEMENT).put(VERTICAL_MOVEMENT, CROSSROAD);
        mergeTable.get(VERTICAL_MOVEMENT).put(HORIZONTAL_MOVEMENT, CROSSROAD);
    }
    
    private static Cursor max(Cursor first, Cursor second) {
        if(first.getPriority() > second.getPriority()) {
            return first;
        }
        
        return second;
    }

    private final int priority;
    private char symbol;

    private Cursor(char symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public Cursor mergeWith(Cursor oldCursor) {
        return mergeTable.get(this).get(oldCursor);
    }

    private int getPriority() {
        return this.priority;
    }

    public char getSymbol() {
        return symbol;
    }
}
