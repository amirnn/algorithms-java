package com.blue.datastructures;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> extends AList<T> {

    private Node head = null;
    private Node tail = null;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public @NotNull T get(final int index) throws IndexOutOfBoundsException {
        return getNode(index).data;
    }

    private @NotNull Node getNode(int index) throws IndexOutOfBoundsException, NoSuchElementException {
        checkSizeAndBounds(index);
        Node current;
        if (index <= size / 2) {
            current = head;
            for (int i = 0; i < index; ++i) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; --i) {
                current = current.prev;
            }
        }
        return current;
    }

    private void checkSizeAndBounds(int index) throws IndexOutOfBoundsException, NoSuchElementException {
        if (index == 0 && size() == 1) {
            return;
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
    }

    @Override
    public @NotNull T head() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        return head.data;
    }

    @Override
    public @NotNull T tail() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        return tail.data;
    }

    @Override
    public void pushFront(@NotNull T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            ++size;
            return;
        }
        newNode.setNext(head);
        head.setPrev(newNode);
        head = newNode;
        ++size;
    }

    @Override
    public void pushBack(@NotNull T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            tail = newNode;
            head = newNode;
            ++size;
            return;
        }
        newNode.setPrev(tail);
        tail.setNext(newNode);
        tail = newNode;
        ++size;
    }

    @Override
    public void pushAt(int index, @NotNull T item) {
        checkSizeAndBounds(index);
        if (isEmpty() && index == 0) {
            pushFront(item);
            return;
        }
        Node newNode = new Node(item);
        Node oldNode = getNode(index);
        Node prev = oldNode.getPrev();
        prev.setNext(newNode);
        newNode.setPrev(prev);
        newNode.setNext(oldNode);
        oldNode.setPrev(newNode);
        ++size;
    }

    @Override
    public @NotNull T popFront() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        Node oldNode = head;
        head = head.getNext();
        head.setPrev(null);
        oldNode.setNext(null);
        --size;
        return oldNode.data;
    }

    @Override
    public @NotNull T popBack() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException();
        Node oldNode = tail;
        tail = tail.getPrev();
        tail.setNext(null);
        oldNode.setPrev(null);
        --size;
        return oldNode.data;
    }

    @Override
    public @NotNull T popAt(int index) {
        checkSizeAndBounds(index);
        if (index == 0 && size() == 1) {
            return popFront();
        }
        Node temp = getNode(index);
        T data = temp.data;
        Node prev = temp.getPrev();
        Node next = temp.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        temp.setNext(null);
        temp.setPrev(null);
        --size;
        return data;
    }

    @Override
    public void set(int index, @NotNull T value) throws IndexOutOfBoundsException, NoSuchElementException {
        getNode(index).setData(value);
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class Node {
        private @NotNull T data;
        private Node next = null;
        private Node prev = null;

        public Node(@NotNull T data) {
            this.data = data;
        }

        // getters
        Node getNext() {
            return next;
        }

        // setters
        void setNext(Node next) {
            this.next = next;
        }

        Node getPrev() {
            return prev;
        }

        void setPrev(Node prev) {
            this.prev = prev;
        }

        @NotNull T getData() {
            return data;
        }

        void setData(@NotNull T data) {
            this.data = data;
        }
    }

    class DoublyLinkedListIterator implements Iterator<T> {
        private Node current;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current = current.next;
            return current.data;
        }
    }
}
