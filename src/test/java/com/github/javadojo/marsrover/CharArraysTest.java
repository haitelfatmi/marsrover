package com.github.javadojo.marsrover;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharArraysTest {

    public static final char EMPTY = 'X';

    @Test
    public void containsOnly() throws Exception {
        char[] c = new char[10];
        for (int i = 0 ; i < c.length ; i++){
            c[i] = EMPTY;
        }
        assertThat(CharArrays.containsOnly(c, EMPTY)).isTrue();
    }

    @Test
    public void indexOfFirstDifferent() throws Exception {
        char[] c = new char[10];
        for (int i = 0 ; i < c.length ; i++){
            c[i] = EMPTY;
        }
        c[7] = 'G';
        assertThat(CharArrays.indexOfFirstDifferent(c, EMPTY)).isEqualTo(7);
    }

    @Test
    public void indexOfLastDifferent() throws Exception {
        char[] c = new char[10];
        for (int i = 0 ; i < c.length ; i++){
            c[i] = EMPTY;
        }
        c[6] = 'G';
        assertThat(CharArrays.indexOfLastDifferent(c, EMPTY)).isEqualTo(6);
    }
}
