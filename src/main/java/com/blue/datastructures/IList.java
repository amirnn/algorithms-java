package com.blue.datastructures;

import org.jetbrains.annotations.NotNull;

import java.lang.Iterable;
import java.util.NoSuchElementException;

public interface IList<T> extends Iterable<T> {
    // getters
    int size();

    boolean isEmpty();

    @NotNull
    T get(int index) throws IndexOutOfBoundsException, NoSuchElementException;

    @NotNull
    T getHead() throws NoSuchElementException;

    @NotNull
    T getTail() throws NoSuchElementException;

    // setters
    void pushFront(@NotNull T item);

    void pushBack(@NotNull T item);

    void pushAt(int index, @NotNull T item) throws IndexOutOfBoundsException;

    @NotNull
    T popFront() throws NoSuchElementException;

    @NotNull
    T popBack() throws NoSuchElementException;

    @NotNull
    T popAt(int index) throws IndexOutOfBoundsException;

    void set(int index,@NotNull T value) throws IndexOutOfBoundsException;
}

