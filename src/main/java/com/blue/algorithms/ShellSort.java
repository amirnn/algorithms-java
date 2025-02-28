package com.blue.algorithms;

import com.blue.datastructures.ASortableList;

public class ShellSort<T extends Comparable<T>> extends ASort<T> {
    @Override
    public void sort(ASortableList<T> data) {
        this.data = data;
        int size = data.size();
        int h = generateMaximumElementUsingKnuthMethod(size); // log(N)
        while (h >= 1) { // h-sort the array.
            for (int i = h; i < size; i++) {
                for (int j = i; j >= h && (data.get(j).compareTo(data.get(j - h)) < 0); j -= h)
                    exchange(j, j - h);
            }
            h = h / 3;
        }
    }

    // log(N)
    private int generateMaximumElementUsingKnuthMethod(final int size) {
        int h = 1;
        while (h < size / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...
        return h;
    }
}
