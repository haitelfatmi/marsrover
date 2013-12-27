package com.github.javadojo;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InfiniteAutosizedListTest {

    private static final char EMPTY = ' ';
    private InfiniteAutosizedList<Character> list;

    @Before
    public void setUp() throws Exception {
        list = new InfiniteAutosizedList<>(new LinkedList<Character>(), EMPTY);
    }

    @Test
    public void newlyInitializedListIsEmpty() {
        assertThat(list.size(), is(0));
        assertThat("list should be empty", list.isEmpty());
        assertThat(list.min(), is(0));
    }

    @Test
    public void settingInitialElementWorks() {
        list.set(0, 'X');
        assertThat(list.get(0), is('X'));
        assertThat(list.size(), is(1));
        assertThat(list.min(), is(0));
    }

    @Test
    public void addingOneElementIncreaseSizeByOne() {
        list.add('X');
        assertThat(list.get(0), is('X'));
        assertThat("list should not be empty", !list.isEmpty());
        assertThat(list.min(), is(0));
    }

    @Test
    public void addingTwoElementIncreaseSizeByTwo() {
        list.add('X');
        list.add('X');
        assertThat(list.get(0), is('X'));
        assertThat(list.get(1), is('X'));
        assertThat("list should not be empty", !list.isEmpty());
        assertThat(list.min(), is(0));
    }

    @Test
    public void gettingNonExistingElementShouldIncreaseListSize() {
        assertThat(list.get(1), is(EMPTY));
        assertThat(list.size(), is(2));
    }

    @Test
    public void settingElementAtPositionTwoIncreaseSizeByThree() {
        list.set(2, 'X');
        assertThat(list.get(0), is(EMPTY));
        assertThat(list.get(1), is(EMPTY));
        assertThat(list.get(2), is('X'));
        assertThat("list should not be empty", !list.isEmpty());
        assertThat(list.min(), is(0));
    }

    @Test
    public void settingElementsAtMultiplePositions() {
        list.set(-2, '1');
        list.set(0, '2');
        assertThat(list.get(-2), is('1'));
        assertThat(list.get(-1), is(EMPTY));
        assertThat(list.get(0), is('2'));
    }

    @Test
    public void settingElementsAtOtherMultiplePositions() {
        list.set(0, '2');
        list.set(-2, '1');
        assertThat(list.get(-2), is('1'));
        assertThat(list.get(-1), is(EMPTY));
        assertThat(list.get(0), is('2'));
    }

    @Test
    public void addElementInMiddleOfList() {
        list.add('X');
        list.add('X');
        list.add(1, 'Y');
        assertThat(list.get(0), is('X'));
        assertThat(list.get(1), is('Y'));
        assertThat(list.get(2), is('X'));
        assertThat(list.size(), is(3));
        assertThat(list.min(), is(0));
    }

    @Test
    public void settingElementWithNegativeIndexWorks() {
        list.set(-1, 'X');
        assertThat(list.get(0), is(EMPTY));
        assertThat(list.get(-1), is('X'));
        assertThat(list.size(), is(2));
        assertThat(list.min(), is(-1));
    }

}
