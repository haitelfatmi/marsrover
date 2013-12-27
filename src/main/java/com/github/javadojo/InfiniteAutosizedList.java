package com.github.javadojo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Note that this class implements the minimum to work correctly in the context of the Mars Rover. Test do not cover
 * other cases, which might or might not work.
 */
public class InfiniteAutosizedList<E> implements List<E> {

    private final List<E> backingList;
    private final Filler<E> filler;
    private int delta = 0;

    public InfiniteAutosizedList(List<E> backingList, E emptyElement) {
        this(backingList, new SimpleFiller<>(emptyElement));
    }

    public InfiniteAutosizedList(List<E> backingList, Filler<E> filler) {
        this.backingList = backingList;
        this.filler = filler;
    }

    @Override
    public int size() {
        return backingList.size();
    }

    @Override
    public boolean isEmpty() {
        return backingList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return backingList.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return backingList.iterator();
    }

    @Override
    public Object[] toArray() {
        return backingList.toArray();
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return backingList.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return backingList.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return backingList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return backingList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return backingList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return backingList.addAll(internalIndexAndResize(index), c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return backingList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return backingList.retainAll(c);
    }

    @Override
    public void clear() {
        delta = 0;
        backingList.clear();
    }

    @Override
    public E get(int index) {
        return backingList.get(internalIndexAndResize(index));
    }

    @Override
    public E set(int index, E element) {
        return backingList.set(internalIndexAndResize(index), element);
    }

    @Override
    public void add(int index, E element) {
        backingList.add(internalIndexAndResize(index), element);
    }

    @Override
    public E remove(int index) {
        return backingList.remove(internalIndexAndResize(index));
    }

    @Override
    public int indexOf(Object o) {
        return internalIndexAndResize(backingList.indexOf(o));
    }

    @Override
    public int lastIndexOf(Object o) {
        return internalIndexAndResize(backingList.lastIndexOf(o));
    }

    @Override
    public ListIterator<E> listIterator() {
        return backingList.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return backingList.listIterator(internalIndexAndResize(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw  new UnsupportedOperationException();
    }

    public int min() {
        return delta;
    }

    private int internalIndexAndResize(int index) {
        // as soon as we access the list, at least the origin needs to exist
        if (backingList.size() == 0) {
            backingList.add(filler.get());
        }

        int nbOfElementToPrepend = Math.max(0, -(index - delta));
        for (int i = 0; i < nbOfElementToPrepend; i++) {
            backingList.add(0, filler.get());
            delta--;
        }

        int nbOfElementToAppend = Math.max(0, index - delta - size() + 1);
        for (int i = 0; i < nbOfElementToAppend; i++) {
            backingList.add(filler.get());
        }
        return index - delta;
    }

    public static interface Filler<T> {
        T get();
    }

    private static final class SimpleFiller<T> implements Filler<T> {
        private final T filler;

        private SimpleFiller(T filler) {
            this.filler = filler;
        }

        @Override
        public T get() {
            return filler;
        }
    }
}
