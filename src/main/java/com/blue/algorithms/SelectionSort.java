package com.blue.algorithms;

import com.blue.datastructures.ASortableList;

public class SelectionSort<T extends Comparable<T>> extends ASort<T> {
    @Override
    public void sort(ASortableList<T> data) {
        this.data = data;
        for (int i = 0; i < data.size(); ++i) {
            T min = data.get(i);
            int minIndex = i;
            for (int j = i + 1; j < data.size(); ++j) {
                if (min.compareTo(data.get(j)) > 0) {
                    min = data.get(j);
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                exchange(i, minIndex);
            }
        }
    }
}
