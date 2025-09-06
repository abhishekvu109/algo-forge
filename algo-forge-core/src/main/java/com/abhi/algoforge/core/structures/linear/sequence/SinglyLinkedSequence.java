package com.abhi.algoforge.core.structures.linear.sequence;

import com.abhi.algoforge.core.constants.CursorMode;
import com.abhi.algoforge.core.execeptions.structures.linear.sequence.ArrayIndexOutOfBoundException;
import com.abhi.algoforge.core.structures.common.BiDirectionalCursor;
import com.abhi.algoforge.core.structures.common.Cursor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.*;

public class SinglyLinkedSequence<T> implements Sequence<T>, LinkedList<T> {

    private Node<T> head;
    private int size;

    public SinglyLinkedSequence() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
        this.size = 0;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public Cursor<T> cursor() {
        return new Cursor<T>() {
            private Node<T> currentNode;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T element = currentNode.getData();
                    currentNode = currentNode.getNext();
                    return element;
                }
                throw new NoSuchElementException();
            }

            @Override
            public void reset() {
                currentNode = head;
            }
        };
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new NoSuchElementException();
        }
        if (head != null) {
            int i = 0;
            Node<T> temp = head;
            while (temp != null) {
                if (index == i) {
                    return temp.getData();
                }
                i++;
                temp = temp.getNext();
            }

        }
        return null;
    }

    @Override
    public void set(int index, T element) {
        if (index >= size) {
            throw new IllegalArgumentException("Index is greater than the current size of the list.");
        }
        if (head != null) {
            int i = 0;
            Node<T> temp = head;
            while (temp != null) {
                if (index == i) {
                    temp.setData(element);
                    return;
                }
                i++;
                temp = temp.getNext();
            }
        }
    }

    @Override
    public void add(T element) {
        if (head == null) {
            this.head = new Node<T>(element, null);
        } else {
            Node<T> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node<T>(element, null));
        }
        this.size++;
    }

    @Override
    public void addAll(T[] elements) {
        Arrays.stream(elements).forEach(this::add);
    }

    @Override
    public void add(int index, T element) {
        if (head != null) {
            if (index + 1 > size) {
                throw new ArrayIndexOutOfBoundException();
            }
            if (index == 0) {
                this.head = new Node<T>(element, null);
            } else {
                Node<T> temp = head.getNext(), prev = head;
                int i = 1;
                while (temp.getNext() != null && i < index) {
                    prev = temp;
                    temp = temp.getNext();
                    i++;
                }
                prev.setNext(new Node<T>(element, temp));
            }
            this.size++;
        }
    }

    @Override
    public T remove(int index) {
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            if (head != null) {
                int i = 0;
                Node<T> temp = head, prev = null;
                while (temp.getNext() != null && i < size() && i != index) {
                    prev = temp;
                    temp = temp.getNext();
                    i++;
                }
                if (i == index) {
                    T element = temp.getData();
                    prev.setNext(temp.getNext());
                    size--;
                    return element;
                }
            }
        }

        return null;
    }

    @Override
    public boolean remove(T element) {
        if (head == null) {
            return false;
        } else if (Objects.equals(element, head.getData())) {
            size--;
            head = head.getNext();
            return true;
        } else {
            Node<T> temp = head, prev = null;
            while (temp != null) {
                if (Objects.equals(temp.getData(), element)) {
                    if (prev == null) {
                        throw new RuntimeException("Unknown exception has occurred.");
                    }
                    prev.setNext(temp.getNext());
                    size--;
                    return true;
                }
                prev = temp;
                temp = temp.getNext();
            }
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        if (head != null) {
            int index = 0;
            Node<T> temp = head;
            while (temp != null) {
                if (Objects.equals(element, temp.getData())) {
                    return index;
                }
                temp = temp.getNext();
                index++;
            }
        }
        return -1;
    }

    @Override
    public Cursor<T> cursor(CursorMode mode) {
        switch (mode) {
            case FORWARD_ONLY -> {
                return cursor();
            }
            case BI_DIRECTIONAL -> {
                return new BiDirectionalSinglyLinkedListCursor();
            }
            default -> {
                throw new IllegalArgumentException("Incorrect mode selected.");
            }
        }
    }

    @Override
    public void addFirst(T element) {
        head = (head == null) ? new Node<>(element, null) : new Node<>(element, head);
        size++;
    }

    @Override
    public void addLast(T element) {
        this.add(element);
    }

    @Override
    public T removeFirst() {
        if (head != null) {
            T element = head.getData();
            head = head.getNext();
            size--;
            return element;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (head != null) {
            Node<T> temp = head, prev = null;
            T element;
            while (temp.getNext() != null) {
                prev = temp;
                temp = temp.getNext();
            }
            if (prev == null) {
                element = head.getData();
                head = null;
            } else {
                element = temp.getData();
                prev.setNext(null);
            }
            size--;
            return element;
        }
        return null;
    }

    @Data
    @AllArgsConstructor
    @ToString
    private static class Node<T> {
        @ToString.Include
        private T data;

        @ToString.Exclude
        private Node<T> next;
    }

    private class BiDirectionalSinglyLinkedListCursor implements BiDirectionalCursor<T> {

        private Node<T> current = head, prev = null;
        private Map<Node<T>, Node<T>> map;

        public BiDirectionalSinglyLinkedListCursor() {
            if (head != null) {
                map = new LinkedHashMap<>();
                Node<T> temp = head, previous = null;
                map.put(head, null);
                while (temp != null) {
                    previous = temp;
                    temp = temp.getNext();
                    map.put(temp, previous);
                }
            }
        }

        @Override
        public boolean hasPrevious() {
            return prev != null;
        }

        @Override
        public T previous() {
            if (hasPrevious()) {
                T element = prev.getData();
                Node<T> prevOfPrev = map.get(prev);
                current = prev;
                prev = prevOfPrev;
                return element;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (head != null) {
                prev.setNext(current.getNext());
                current = prev.getNext();
                size--;
            }
            throw new RuntimeException("The end of cursor, nothing to delete.");
        }

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T element = current.getData();
                prev = current;
                current = current.getNext();
                return element;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void reset() {
            current = head;
            prev = null;
        }
    }
}
