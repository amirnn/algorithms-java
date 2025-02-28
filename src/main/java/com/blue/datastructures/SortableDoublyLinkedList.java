package com.blue.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortableDoublyLinkedList<T extends Comparable<T>> extends ASortableList<T> {

    private final DoublyLinkedList<T> dlinklist = new DoublyLinkedList<>();

    @Override
    public int size() {
        return dlinklist.size();
    }

    @Override
    public boolean isEmpty() {
        return dlinklist.isEmpty();
    }

    @Override
    public @NotNull T get(int index) throws IndexOutOfBoundsException, NoSuchElementException {
        return dlinklist.get(index);
    }

    @Override
    public @NotNull T head() throws NoSuchElementException {
        return dlinklist.head();
    }

    @Override
    public @NotNull T tail() throws NoSuchElementException {
        return dlinklist.tail();
    }

    @Override
    public void pushFront(@NotNull T item) {
        dlinklist.pushFront(item);
    }

    @Override
    public void pushBack(@NotNull T item) {
        dlinklist.pushBack(item);
    }

    @Override
    public void pushAt(int index, @NotNull T item) throws IndexOutOfBoundsException {
        dlinklist.pushAt(index, item);
    }

    @Override
    public @NotNull T popFront() throws NoSuchElementException {
        return dlinklist.popFront();
    }

    @Override
    public @NotNull T popBack() throws NoSuchElementException {
        return dlinklist.popBack();
    }

    @Override
    public @NotNull T popAt(int index) throws IndexOutOfBoundsException {
        return dlinklist.popAt(index);
    }

    @Override
    public void set(int index, @NotNull T value) throws IndexOutOfBoundsException {
        dlinklist.set(index, value);
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return dlinklist.iterator();
    }
}
