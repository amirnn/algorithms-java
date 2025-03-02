package com.blue.datastructures;

import java.util.Random;

public abstract class AList<T> implements IList<T> {

    // should be used only with indexes in the range [0, #numberOfELements)
    @Override
    public void exchange(int source, int target) throws IndexOutOfBoundsException {
        T temp = get(source);
        set(source, get(target));
        set(target, temp);
    }

    // implements knuth's linear shuffle
    @Override
    public void shuffle() {
        final Random r = new Random();
        if (size() <= 1) return;
        for (int i = 1; i < size(); ++i) {
            final int target = r.nextInt(i+1);
            exchange(i, target);
        }
    }
}
