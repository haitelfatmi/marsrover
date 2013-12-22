package com.github.javadojo.marsrover;

public final class CharArrays {

    public static boolean containsOnly(char[] array, char c) {
        for (char current : array) {
            if (current != c) {
                return false;
            }
        }
        return true;
    }

    public static int indexOfFirstDifferent(char[] array, char c) {
        int i = 0;
        for (char current : array) {
            if (current != c) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOfLastDifferent(char[] array, char c) {
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i] != c) {
                return i;
            }
        }
        return -1;
    }

}
