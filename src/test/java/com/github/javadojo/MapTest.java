package com.github.javadojo;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class MapTest {

    private Map map;

    @Before
    public void setUp() {
        map = new Map();
    }

    @Test
    public void characterPrintedAtOriginCanBeRetrieved() {
        map.putSymbol(new Point(0, 0), 'X');
        assertThat(map.getSymbol(new Point(0, 0)), is('X'));
    }

    @Test
    public void characterPrintedAtRandomCanBeRetrieved() {
        map.putSymbol(new Point(10, 5), 'X');
        assertThat(map.getSymbol(new Point(10, 5)), is('X'));
    }

    @Test
    public void printLineOnXAxis() {
        Point p = new Point(0, 0);
        map.putSymbol(p, '1');
        p.translate(1, 0);
        map.putSymbol(p, '2');
        p.translate(1, 0);
        map.putSymbol(p, '3');
        assertThat(map.toString(), is("123" + System.lineSeparator()));
    }

    @Test
    public void printLineOnYAxis() {
        Point p = new Point(0, 0);
        map.putSymbol(p, '1');
        p.translate(0, 1);
        map.putSymbol(p, '2');
        p.translate(0, 1);
        map.putSymbol(p, '3');
        String expected = new StringBuffer()
                .append('3').append(System.lineSeparator())
                .append('2').append(System.lineSeparator())
                .append('1').append(System.lineSeparator())
                .toString();
        assertThat(map.toString(), is(expected));
    }

    @Test
    public void printFourCorners() {
        map.putSymbol(new Point(0, 0), '1');
        map.putSymbol(new Point(0, 2), '2');
        map.putSymbol(new Point(3, 2), '3');
        map.putSymbol(new Point(3, 0), '4');
        String expected = new StringBuffer()
                .append("2  3").append(System.lineSeparator())
                .append("    ").append(System.lineSeparator())
                .append("1  4").append(System.lineSeparator())
                .toString();
        assertThat(map.toString(), is(expected));
    }

    @Test
    public void printWithNegativeX() {
        map.putSymbol(new Point(-1, 0), 'X');
        assertThat(map.toString(), is("X " + System.lineSeparator()));
    }

    @Test
    public void printWithNegativeY() {
        map.putSymbol(new Point(0, -1), 'X');
        String expected = new StringBuffer()
                .append(" ").append(System.lineSeparator())
                .append("X").append(System.lineSeparator())
                .toString();
        assertThat(map.toString(), is(expected));
    }

    @Test
    public void printFourCornersWithNegativeCoordinates() {
        map.putSymbol(new Point(0, 0), '1');
        map.putSymbol(new Point(0, -2), '2');
        map.putSymbol(new Point(-3, -2), '3');
        map.putSymbol(new Point(-3, 0), '4');
        String expected = new StringBuffer()
                .append("4  1").append(System.lineSeparator())
                .append("    ").append(System.lineSeparator())
                .append("3  2").append(System.lineSeparator())
                .toString();
        assertThat(map.toString(), is(expected));
    }
}
