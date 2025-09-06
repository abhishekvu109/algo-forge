package com.abhi.algoforge.core.structures.linear.queue;

import com.abhi.algoforge.core.structures.common.Cursor;
import com.abhi.algoforge.core.structures.linear.sequence.DoublyLinkedSequence;

import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Queue<T> {
    private DoublyLinkedSequence<T> data;
    private int size;

    public LinkedQueue() {
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
    public boolean offer(T element) {
        this.data.addLast(element);
        this.size++;
        return true;
    }

    @Override
    public T poll() {
        T element = this.data.removeFirst();
        this.size--;
        return element;
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.data.get(0);
    }
}
