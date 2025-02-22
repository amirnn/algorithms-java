package com.blue.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class DynamicArray<T> implements IList<T> {

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public @NotNull T get(int index) {
        return null;
    }

    @Override
    public @NotNull T getHead() {
        return null;
    }

    @Override
    public @NotNull T getTail() {
        return null;
    }

    @Override
    public void pushFront(@NotNull T item) {

    }

    @Override
    public void pushBack(@NotNull T item) {

    }

    @Override
    public void pushAt(int index, @NotNull T item) {

    }

    @Override
    public @NotNull T popFront() {
        return null;
    }

    @Override
    public @NotNull T popBack() {
        return null;
    }

    @Override
    public @NotNull T popAt(int index) {
        return null;
    }

    @Override
    public void set(int index, @NotNull T value) {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
