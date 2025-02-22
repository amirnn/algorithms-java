package com.blue.algorithms;

import com.blue.datastructures.ASortableList;

public class HeapSort<T extends Comparable<T>> extends ASort<T> {

    @Override
    public void sort(ASortableList<T> data) {
        this.data = data;
        // rest
    }
}
