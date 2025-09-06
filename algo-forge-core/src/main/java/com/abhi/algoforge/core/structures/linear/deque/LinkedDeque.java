package com.abhi.algoforge.core.structures.linear.deque;

import com.abhi.algoforge.core.structures.common.Cursor;
import com.abhi.algoforge.core.structures.linear.sequence.DoublyLinkedSequence;

import java.util.NoSuchElementException;

public class LinkedDeque<T> implements Deque<T> {
    private DoublyLinkedSequence<T> data;
    private int size;

    public LinkedDeque() {
        this.data = new DoublyLinkedSequence<>();
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        this.data = new DoublyLinkedSequence<>();
        this.size = 0;
    }

    @Override
    public boolean contains(T element) {
        return this.data.contains(element);
    }

    @Override
    public Cursor<T> cursor() {
        return new Cursor<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data.get(currentIndex++);
            }

            @Override
            public void reset() {
                this.currentIndex = 0;
            }
        };
    }

    @Override
    public void offerFirst(T element) {
        this.data.addFirst(element);
        this.size++;
    }

    @Override
    public void offerLast(T element) {
        this.data.addLast(element);
        this.size++;
    }

    @Override
    public T pollFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = this.data.removeFirst();
        this.size--;
        return element;
    }

    @Override
    public T pollLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = this.data.removeLast();
        this.size--;
        return element;
    }

    @Override
    public T peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.data.get(0);
    }

    @Override
    public T peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.data.get(size - 1);
    }

    @Override
    public boolean offer(T element) {
        this.offerLast(element);
        return true;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.pollFirst();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.peekFirst();
    }
}
