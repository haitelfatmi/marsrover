package com.github.javadojo;

import static com.github.javadojo.MarsRover.LINE_SEPARATOR;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class MarsRoverTest {

    @Test
    public void driveEast() {
        MarsRover rover = new MarsRover("s");
        assertThat("X*" + LINE_SEPARATOR, equalTo(rover.path()));
    }

    @Test
    public void driveEastForABitLonger() {
        MarsRover rover = new MarsRover("ssss");
        assertThat("X---*" + LINE_SEPARATOR, equalTo(rover.path()));
    }

    @Test
    public void driveNorth() {
        MarsRover rover = new MarsRover("lsss");
        String expectedPath = new StringBuilder()
                .append("*").append(LINE_SEPARATOR)
                .append("|").append(LINE_SEPARATOR)
                .append("|").append(LINE_SEPARATOR)
                .append("X").append(LINE_SEPARATOR)
                .toString();
        assertThat(expectedPath, equalTo(rover.path()));
    }

    @Test
    public void driveEstThanTurnLeft() {
        MarsRover rover = new MarsRover("sssslssss");
        String expectedPath = new StringBuilder()
                .append("    *").append(LINE_SEPARATOR)
                .append("    |").append(LINE_SEPARATOR)
                .append("    |").append(LINE_SEPARATOR)
                .append("    |").append(LINE_SEPARATOR)
                .append("X---+").append(LINE_SEPARATOR)
                .toString();
        assertThat(expectedPath, equalTo(rover.path()));
    }

    @Test
    public void driveNorthAfterInitialProgrammingToTheEast() {
        MarsRover rover = new MarsRover("ssss")
                .turnLeft()
                .moveForward()
                .moveForward();
        String expectedPath = new StringBuilder()
                .append("    *").append(LINE_SEPARATOR)
                .append("    |").append(LINE_SEPARATOR)
                .append("X---+").append(LINE_SEPARATOR)
                .toString();
        assertThat(expectedPath, equalTo(rover.path()));
    }

    @Test
    public void driveEastThanTakeSampleThanDriveABitMore() {
        MarsRover rover = new MarsRover("sssSsss");
        assertThat("X--S--*" + LINE_SEPARATOR, equalTo(rover.path()));
    }
}
