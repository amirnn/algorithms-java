package com.blue.datastructures;

import com.blue.algorithms.ASort;
import org.jetbrains.annotations.NotNull;

public abstract class ASortableList<T extends Comparable<T>> implements IList<T>{

    public void sort(@NotNull SortSupplier<T> using) {
        ASort<T> sortingAlgorithm = using.get();
        sortingAlgorithm.sort(this);
    }

    @FunctionalInterface
    public interface SortSupplier<T extends Comparable<T>> {
        @NotNull ASort<T> get();
    }
}
