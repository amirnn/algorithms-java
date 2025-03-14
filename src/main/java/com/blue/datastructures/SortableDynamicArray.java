package com.blue.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortableDynamicArray<T extends Comparable<T>> extends ASortableList<T> {
    private final DynamicArray<T> array = new DynamicArray<>();

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public @NotNull T get(int index) throws IndexOutOfBoundsException, NoSuchElementException {
        return array.get(index);
    }

    @Override
    public @NotNull T head() throws NoSuchElementException {
        return array.head();
    }

    @Override
    public @NotNull T tail() throws NoSuchElementException {
        return array.tail();
    }

    @Override
    public void pushFront(@NotNull T item) {
        array.pushFront(item);
    }

    @Override
    public void pushBack(@NotNull T item) {
        array.pushBack(item);
    }

    @Override
    public void pushAt(int index, @NotNull T item) throws IndexOutOfBoundsException {
        array.pushAt(index, item);
    }

    @Override
    public @NotNull T popFront() throws NoSuchElementException {
        return array.popFront();
    }

    @Override
    public @NotNull T popBack() throws NoSuchElementException {
        return array.popBack();
    }

    @Override
    public @NotNull T popAt(int index) throws IndexOutOfBoundsException {
        return array.popAt(index);
    }

    @Override
    public void set(int index, @NotNull T value) throws IndexOutOfBoundsException {
        array.set(index, value);
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return array.iterator();
    }
}
