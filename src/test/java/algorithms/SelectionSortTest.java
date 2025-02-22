package algorithms;

import com.blue.algorithms.ASort;
import com.blue.datastructures.ASortableList;
import com.blue.datastructures.DynamicArray;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

import com.blue.datastructures.SortableDynamicArray;
import com.blue.algorithms.SelectionSort;

class SelectionSortTest {
    @Test
    void testSortingRandomList() {
        SelectionSort<Integer> sorter = new SelectionSort<>();
        SortableDynamicArray<Integer> list = new SortableDynamicArray<>();
        List<Integer> input = Arrays.asList(5, 3, 8, 1, 2);
        input.forEach(list::pushBack);

        sorter.sort(list);

        List<Integer> expected = Arrays.asList(1, 2, 3, 5, 8);
        for ( int i = 0; i < list.size(); ++i ) {
            assertEquals(expected.get(i), list.get(i));
        }
    }

    @Test
    void testSortingAlreadySorted() {
        SelectionSort<Integer> sorter = new SelectionSort<>();
        SortableDynamicArray<Integer> list = new SortableDynamicArray<>();
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        input.forEach(list::pushBack);
        sorter.sort(list);
        for ( int i = 0; i < list.size(); ++i ) {
            assertEquals(input.get(i), list.get(i));
        }
    }

    @Test
    void testSortingEmptyList() {
        SelectionSort<Integer> sorter = new SelectionSort<>();
        SortableDynamicArray<Integer> list = new SortableDynamicArray<>();

        sorter.sort(list);

        assertTrue(list.isEmpty());
    }

    @Test
    void testSortingSingleElementList() {
        SelectionSort<Integer> sorter = new SelectionSort<>();
        SortableDynamicArray<Integer> list = new SortableDynamicArray<>();
        list.pushBack(42);

        sorter.sort(list);

        var l = List.of(42);
        for ( int i = 0; i < list.size(); ++i ) {
            assertEquals(l.get(i), list.get(i));
        }

    }

    @Test
    void testSortingWithDuplicates() {
        SelectionSort<Integer> sorter = new SelectionSort<>();
        SortableDynamicArray<Integer> list = new SortableDynamicArray<>();

        List<Integer> input = Arrays.asList(4, 2, 4, 1, 2);
        input.forEach(list::pushBack);
        // sorter.sort(list);

        list.sort(new ASortableList.SortSupplier<>() {
            @Override
            public @NotNull ASort<Integer> get() {
                return new SelectionSort<>();
            }
        });

        List<Integer> expected = Arrays.asList(1, 2, 2, 4, 4);
        for ( int i = 0; i < list.size(); ++i ) {
            assertEquals(expected.get(i), list.get(i));
        }
    }
}
