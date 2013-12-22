package com.github.javadojo;

import static com.github.javadojo.MarsRover.LINE_SEPARATOR;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class MarsRoverTest {

    @Test
    public void driveEast() {
        assertThat(new MarsRover("s").path(), equalTo("X*" + LINE_SEPARATOR));
    }

    @Test
    public void driveEastForABitLonger() {
        assertThat(new MarsRover("ssss").path(), equalTo("X---*" + LINE_SEPARATOR));
    }

    @Test
    public void driveNorth() {
        String expectedPath = new StringBuilder()
                .append("*").append(LINE_SEPARATOR)
                .append("|").append(LINE_SEPARATOR)
                .append("|").append(LINE_SEPARATOR)
                .append("X").append(LINE_SEPARATOR)
                .toString();
        assertThat(new MarsRover("lsss").path(), equalTo(expectedPath));
    }

    @Test
    public void driveEstThanTurnLeft() {
        String expectedPath = new StringBuilder()
                .append("    *").append(LINE_SEPARATOR)
                .append("    |").append(LINE_SEPARATOR)
                .append("    |").append(LINE_SEPARATOR)
                .append("    |").append(LINE_SEPARATOR)
                .append("X---+").append(LINE_SEPARATOR)
                .toString();
        assertThat(new MarsRover("sssslssss").path(), equalTo(expectedPath));
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
        assertThat(rover.path(), equalTo(expectedPath));
    }

    @Test
    public void driveEastThanTakeSampleThanDriveABitMore() {
        assertThat(new MarsRover("sssSsss").path(), equalTo("X--S--*" + LINE_SEPARATOR));
    }
}
