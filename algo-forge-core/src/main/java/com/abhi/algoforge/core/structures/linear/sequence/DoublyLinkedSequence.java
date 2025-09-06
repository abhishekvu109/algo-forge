package com.abhi.algoforge.core.structures.linear.sequence;

import com.abhi.algoforge.core.constants.CursorMode;
import com.abhi.algoforge.core.structures.common.BiDirectionalCursor;
import com.abhi.algoforge.core.structures.common.Cursor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

@NoArgsConstructor
public class DoublyLinkedSequence<T> implements Sequence<T>, LinkedList<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 || head == null;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public Cursor<T> cursor() {
        return new Cursor<T>() {
            private Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode.getNext() != null;
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
    public void addFirst(T element) {
        if (head == null) {
            this.head = new Node<>(element, null, null);
            this.tail = head;
        } else {
            this.head = new Node<>(element, null, head);
        }
        size++;
    }

    @Override
    public void addLast(T element) {
        if (head == null) {
            this.head = new Node<>(element, null, null);
            this.tail = head;
        } else {
            this.tail = new Node<>(element, tail, null);
            this.tail.getPrev().setNext(this.tail);
        }
        size++;
    }

    @Override
    public T removeFirst() {
        if (head != null) {
            T element = head.getData();
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head.getNext().setPrev(null);
                head = head.getNext();
            }
            size--;
            return element;
        }
        throw new NoSuchElementException();
    }

    @Override
    public T removeLast() {
        if (tail != null) {
            T element = tail.getData();
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.getPrev();
                tail.setNext(null);
            }
            size--;
            return element;
        }
        throw new NoSuchElementException();
    }

    @Override
    public T get(int index) {
        if (head != null) {
            int i = 0;
            Node<T> temp = head;
            while (temp != null) {
                if (i == index) {
                    return temp.getData();
                }
                temp = temp.getNext();
                i++;
            }
        }
        return null;
    }

    @Override
    public void set(int index, T element) {
        if (head != null) {
            int i = 0;
            Node<T> temp = head;
            while (temp != null) {
                if (i == index) {
                    break;
                }
                temp = temp.getNext();
                i++;
            }
            if (temp == null) {
                throw new RuntimeException("The index greater than the current size of the list.");
            }
            temp.setData(element);
        }
    }

    @Override
    public void add(T element) {
        this.addLast(element);
    }

    @Override
    public void addAll(T[] elements) {
        Arrays.stream(elements).forEach(this::addLast);
    }

    @Override
    public void add(int index, T element) {
        if (index > size) {
            throw new RuntimeException("Index is greater than the size of the list.");
        }
        if (head != null) {
            if (index == 0) {
                addFirst(element);
            } else if (index == size) {
                addLast(element);
            } else if (index > 0) {
                int i = 1;
                Node<T> temp = head.getNext();
                while (temp.getNext() != null) {
                    if (i == index) {
                        break;
                    }
                    temp = temp.getNext();
                    i++;
                }
                Node<T> newNode = new Node<>(element, temp.getPrev(), temp);
                temp.getPrev().setNext(newNode);
                temp.setPrev(newNode);
                size++;
            }
        }
    }

    @Override
    public T remove(int index) {

        if (!isEmpty() && index <= size) {
            if (index == 0) {
                return this.removeFirst();
            } else if (index == size - 1) {
                return this.removeLast();
            } else if (index > 0 && index < size) {
                Node<T> temp = head.getNext();
                int i = 1;
                while (temp != null) {
                    if (i == index) {
                        break;
                    }
                    i++;
                    temp = temp.getNext();
                }
                if (temp != null) {
                    T element = temp.getData();
                    temp.getPrev().setNext(temp.getNext());
                    temp.getNext().setPrev(temp.getPrev());
                    size--;
                    return element;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(T element) {
        int indexOf = indexOf(element);
        return indexOf >= 0 && (remove(indexOf) != null);
    }

    @Override
    public int indexOf(T element) {
        if (head != null) {
            Node<T> temp = head;
            int i = 0;
            while (temp != null) {
                if (Objects.equals(element, temp.getData())) {
                    return i;
                }
                i++;
                temp = temp.getNext();
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
                return new BiDirectionalCursorImpl();
            }
            default -> {
                throw new IllegalArgumentException("mode doesn't speak");
            }
        }
    }

    @AllArgsConstructor
    @Data
    @ToString
    private static class Node<T> {
        @ToString.Include
        private T data;
        @ToString.Exclude
        private Node<T> prev;
        @ToString.Exclude
        private Node<T> next;
    }

    private class BiDirectionalCursorImpl implements BiDirectionalCursor<T> {
        private Node<T> currentNode = head;

        @Override
        public boolean hasPrevious() {
            return currentNode.getPrev() != null;
        }

        @Override
        public T previous() {
            if (hasPrevious()) {
                currentNode = currentNode.getPrev();
                return currentNode.getData();
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            currentNode.getPrev().setNext(currentNode.getNext());
            if (currentNode.getNext() != null) {
                currentNode.getNext().setPrev(currentNode.getPrev());
            }
            size--;
        }

        @Override
        public boolean hasNext() {
            return currentNode.getNext() != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                currentNode = currentNode.getNext();
                return currentNode.getData();
            }
            throw new NoSuchElementException();
        }

        @Override
        public void reset() {
            currentNode = head;
        }
    }
}
