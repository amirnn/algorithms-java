package com.blue.algorithms;

import com.blue.datastructures.ASortableList;
import org.jetbrains.annotations.NotNull;

public abstract class ASort<T extends Comparable<T>> {
    protected @NotNull ASortableList<T> data;

    boolean isSorted() {
        for (int i = 0; i < data.size(); ++i) {
            if (data.get(i).compareTo(data.get(i + 1)) >= 0) {
                return false;
            }
        }
        return true;
    }

    void exchange(final int i, final int j) {
        T temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    /**
     * the children will need to only implement this method
     */
    public abstract void sort(ASortableList<T> data);
}
