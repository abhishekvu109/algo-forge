package com.abhi.algoforge.core.structures.linear.sequence;

import com.abhi.algoforge.core.constants.CursorMode;
import com.abhi.algoforge.core.execeptions.structures.linear.sequence.ArrayIndexOutOfBoundException;
import com.abhi.algoforge.core.structures.common.Cursor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;

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
        return null;
    }

    @Override
    public T get(int index) {
        if (head != null) {
            int i = 0;

        }
        return null;
    }

    @Override
    public void set(int index, T element) {

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
        if (index + 1 > size) {
            throw new ArrayIndexOutOfBoundException();
        }
        if (head == null && index == 0) {
            this.head = new Node<T>(element, null);
        } else {
            Node<T> temp = head;
            int i = 0;
            while (temp.getNext() != null && i < index) {
                temp = temp.getNext();
                i++;
            }
            temp.setNext(new Node<T>(element, null));
        }
        this.size++;
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
        return null;
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
        T data;

        @ToString.Exclude
        Node<T> next;
    }
}
