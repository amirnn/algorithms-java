package datastructures;

import com.blue.datastructures.DynamicArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {
    private DynamicArray<Integer> array;

    @BeforeEach
    void setUp() {
        array = new DynamicArray<>();
    }

    @Test
    void testIsEmpty() {
        assertTrue(array.isEmpty());
    }

    @Test
    void testPushBack() {
        array.pushBack(10);
        array.pushBack(20);
        assertEquals(2, array.size());
        assertEquals(10, array.getHead());
        assertEquals(20, array.getTail());
    }

    @Test
    void testPushFront() {
        array.pushFront(10);
        array.pushFront(20);
        assertEquals(2, array.size());
        assertEquals(20, array.getHead());
        assertEquals(10, array.getTail());
    }

    @Test
    void testPopBack() {
        array.pushBack(10);
        array.pushBack(20);
        assertEquals(20, array.popBack());
        assertEquals(1, array.size());
    }

    @Test
    void testPopFront() {
        array.pushBack(10);
        array.pushBack(20);
        assertEquals(10, array.popFront());
        assertEquals(1, array.size());
    }

    @Test
    void testPushAt() {
        array.pushBack(10);
        array.pushBack(30);
        array.pushAt(1, 20);
        assertEquals(3, array.size());
        assertEquals(20, array.get(1));
    }

    @Test
    void testPopAt() {
        array.pushBack(10);
        array.pushBack(20);
        array.pushBack(30);
        assertEquals(20, array.popAt(1));
        assertEquals(2, array.size());
    }

    @Test
    void testResizeOnPush() {
        array.pushBack(1);
        array.pushBack(2);
        array.pushBack(3); // Should trigger buffer expansion
        assertEquals(3, array.size());
        assertEquals(1, array.get(0));
        assertEquals(3, array.get(2));
    }

    @Test
    void testResizeOnPop() {
        array.pushBack(1);
        array.pushBack(2);
        array.pushBack(3);
        array.popBack();
        array.popBack(); // Should trigger buffer shrinking
        assertEquals(1, array.size());
        assertEquals(1, array.getHead());
    }

    @Test
    void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
    }

    @Test
    void testPopFrontEmpty() {
        assertThrows(NoSuchElementException.class, () -> array.popFront());
    }

    @Test
    void testPopBackEmpty() {
        assertThrows(NoSuchElementException.class, () -> array.popBack());
    }
}
