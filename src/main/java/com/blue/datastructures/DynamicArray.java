package com.blue.datastructures;

import jdk.jshell.spi.ExecutionControl;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<T> implements IList<T> {

    private final int initialBufferSize = 2; // buffer suze
    private final int bufferExtensionScale = 2;
    private final int bufferShrinkScale = 2;
    private final int itemShrinkRatio = 4;
    private int bufferSize = initialBufferSize;
    private T[] data = (T[]) new Object[bufferSize];
    private int numberOfItems = 0;
    private int head = 0;
    private int tail = 0;

    @Override
    public int size() {
        return numberOfItems;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public @NotNull T get(int index) {
        int t = getActualIndex(index);
        assert data[t] != null : "data is null";
        return data[t];
    }

    private int getActualIndex(int index) {
        int indexMod = index % bufferSize;
        int headMod = head % bufferSize;
        int secondMod = (headMod + bufferSize) % bufferSize;
        return indexMod + secondMod;
    }

    private void checkSizeAndBounds(int index) throws IndexOutOfBoundsException, NoSuchElementException {
        if (index == 0 && size() == 1) {
            return;
        }
        if (isEmpty()) {
            throw new NoSuchElementException("Array is empty");
        }
        if (index < 0 || index >= numberOfItems) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
    }

    @Override
    public @NotNull T getHead() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Array is empty");
        }
        return data[getActualIndex(0)];
    }

    @Override
    public @NotNull T getTail() {
        if (isEmpty()) {
            throw new NoSuchElementException("Array is empty");
        }
        return data[getActualIndex(size() - 1)];
    }

    @Override
    public void pushFront(@NotNull T item) {
        if (isBufferFull()) extendBufferAndCopyData();
        if (isEmpty()) {
            data[head] = item;
        } else {
            --head;
            int t = getActualIndex(0);
            data[t] = item;
        }
        numberOfItems++;
    }

    @Override
    public void pushBack(@NotNull T item) {
        if (isBufferFull()) extendBufferAndCopyData();
        if (isEmpty()) {
            data[tail] = item;
        } else {
            ++tail;
            int t = getActualIndex(size());
            data[t] = item;
        }
        numberOfItems++;
    }

    @Override
    public void pushAt(int index, @NotNull T item) throws IndexOutOfBoundsException, NoSuchElementException {
        checkSizeAndBounds(index);
    }

    @Override
    public @NotNull T popFront() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Array is empty");
        if (isBufferNearlyEmpty()) shrinkBufferAndCopyData();
        T temp = data[getActualIndex(0)];
        data[getActualIndex(0)] = null;
        if (size() > 1) ++head;
        --numberOfItems;
        return temp;
    }

    @Override
    public @NotNull T popBack() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Array is empty");
        if (isBufferNearlyEmpty()) shrinkBufferAndCopyData();
        T temp = data[getActualIndex(size() - 1)];
        data[getActualIndex(size() - 1)] = null;
        if (size() > 1) --tail;
        --numberOfItems;
        return temp;
    }

    @Override
    public @NotNull T popAt(int index) throws IndexOutOfBoundsException, NoSuchElementException {
        checkSizeAndBounds(index);
        if (index == 0) {
            return popFront();
        } else if (index == size() - 1) {
            return popBack();
        }
        T temp = get(index);
        data[getActualIndex(index)] = null;
        // exchange with null
        if (index <= size() / 2) {
            for (int i = index; i > 0; --i) {
                exchange(i, i - 1);
            }
            ++head;
        } else {
            for (int i = index; i < size() - 1; ++i) {
                exchange(i, i + 1);
            }
            --tail;
        }
        --numberOfItems;
        return temp;
    }

    private void exchange(int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Override
    public void set(int index, @NotNull T value) throws IndexOutOfBoundsException, NoSuchElementException {
        checkSizeAndBounds(index);
        data[getActualIndex(index)] = value;
    }

    private boolean isBufferFull() {
        return size() >= bufferSize;
    }

    private boolean isBufferNearlyEmpty() {
        return numberOfItems <= bufferSize / itemShrinkRatio;
    }

    /**
     * will extend the buffer to its @param bufferExtensionsScale size and will reset head and tail
     */
    private void extendBufferAndCopyData() {
        int numberOfItems = size();
        T[] newData = (T[]) new Object[bufferExtensionScale * bufferSize];
        for (int i = 0; i < numberOfItems; ++i) {
            newData[i] = data[getActualIndex(i)];
        }
        // update the data
        data = newData;
        // update head and tail
        head = 0;
        tail = numberOfItems - 1;
        // update the size
        bufferSize *= bufferExtensionScale;
    }

    /**
     * will shrink the buffer to its @param bufferShrinkScale size and will reset head and tail
     */
    private void shrinkBufferAndCopyData() {
        int numberOfItems = size();
        int shrinkSize = bufferSize / bufferShrinkScale;
        assert isBufferNearlyEmpty() : "Buffer is not nearly empty";
        T[] newData = (T[]) new Object[shrinkSize];
        for (int i = 0; i < numberOfItems; ++i) {
            newData[i] = data[getActualIndex(i)];
        }
        // update data
        data = newData;
        // update head and tail
        head = 0;
        tail = numberOfItems - 1;
        // update the size
        bufferSize /= bufferShrinkScale;
    }

    void reserve(int reserveSize) {
        if (reserveSize == bufferSize) {
            return;
        }
        if (reserveSize < 2) throw new UnsupportedOperationException("Size of $reserveSize is negative");
        if (reserveSize <= size())
            throw new UnsupportedOperationException("Buffer size must be greater than the current size");
        int powerOfTwo = findClosestPowerOfTwoGreaterOrEqualTo(reserveSize);
        T[] newData = (T[]) new Object[powerOfTwo];
        int numberOfItems = size();
        for (int i = 0; i < numberOfItems; ++i) {
            newData[i] = data[getActualIndex(i)];
        }
        data = newData;
        head = 0;
        tail = numberOfItems - 1;
        bufferSize = powerOfTwo;
    }

    /**
     * Complexity Lg(n)
     */
    private int findClosestPowerOfTwoGreaterOrEqualTo(int number) {
        int factor = 1;
        do {
            factor *= 2;
        } while (factor <= number);
        return factor;
    }

    public void clear() {
        data = (T[]) new Object[initialBufferSize];
        numberOfItems = 0;
        head = 0;
        tail = 0;
        bufferSize = initialBufferSize;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new DynamicArrayIterator();
    }

    public class DynamicArrayIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size() - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            ++currentIndex;
            return data[getActualIndex(currentIndex)];
        }
    }
}
