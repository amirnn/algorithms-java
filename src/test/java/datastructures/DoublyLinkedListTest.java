package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

import com.blue.datastructures.DoublyLinkedList;

class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList<>();
    }

    @Test
    void testIsEmptyOnNewList() {
        assertTrue(list.isEmpty());
    }

    @Test
    void testSizeOnNewList() {
        assertEquals(0, list.size());
    }

    @Test
    void testPushFront() {
        list.pushFront(10);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(10, list.getHead());
        assertEquals(10, list.getTail());
    }

    @Test
    void testPushBack() {
        list.pushBack(20);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(20, list.getHead());
        assertEquals(20, list.getTail());
    }

    @Test
    void testPushMultiple() {
        list.pushFront(10);
        list.pushBack(20);
        list.pushFront(5);
        assertEquals(3, list.size());
        assertEquals(5, list.getHead());
        assertEquals(20, list.getTail());
    }

    @Test
    void testPopFront() {
        list.pushBack(10);
        list.pushBack(20);
        assertEquals(10, list.popFront());
        assertEquals(1, list.size());
        assertEquals(20, list.getHead());
    }

    @Test
    void testPopBack() {
        list.pushBack(10);
        list.pushBack(20);
        assertEquals(20, list.popBack());
        assertEquals(1, list.size());
        assertEquals(10, list.getHead());
    }

    @Test
    void testPopFrontOnEmptyList() {
        assertThrows(NoSuchElementException.class, list::popFront);
    }

    @Test
    void testPopBackOnEmptyList() {
        assertThrows(NoSuchElementException.class, list::popBack);
    }

    @Test
    void testGetElementAtValidIndex() {
        list.pushBack(10);
        list.pushBack(20);
        list.pushBack(30);
        assertEquals(20, list.get(1));
    }

    @Test
    void testGetElementAtInvalidIndex() {
        list.pushBack(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    @Test
    void testPushAtValidIndex() {
        list.pushBack(10);
        list.pushBack(30);
        list.pushAt(1, 20);
        assertEquals(20, list.get(1));
        assertEquals(3, list.size());
    }

    @Test
    void testPushAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.pushAt(5, 100));
    }

    @Test
    void testPopAtValidIndex() {
        list.pushBack(10);
        list.pushBack(20);
        list.pushBack(30);
        assertEquals(20, list.popAt(1));
        assertEquals(2, list.size());
    }

    @Test
    void testPopAtInvalidIndex() {
        list.pushBack(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.popAt(5));
    }

    @Test
    void testSetElementAtValidIndex() {
        list.pushBack(10);
        list.pushBack(20);
        list.set(1, 50);
        assertEquals(50, list.get(1));
    }

    @Test
    void testSetElementAtInvalidIndex() {
        list.pushBack(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, 100));
    }
}
