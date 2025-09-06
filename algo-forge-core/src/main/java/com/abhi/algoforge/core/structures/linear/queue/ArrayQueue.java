package com.abhi.algoforge.core.structures.linear.queue;

import com.abhi.algoforge.core.structures.common.Cursor;
import com.abhi.algoforge.core.structures.linear.sequence.DynamicArraySequence;

import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {
    private final int capacity;
    private DynamicArraySequence<T> data;
    private int size;

    public ArrayQueue() {
        this.capacity = 15;
        this.data = new DynamicArraySequence<>(this.capacity);
        this.size = 0;
    }

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.data = new DynamicArraySequence<>(this.capacity);
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
        this.data = new DynamicArraySequence<>(this.capacity);
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
                return this.currentIndex < size;
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
        this.data.add(element);
        this.size++;
        return true;
    }

    @Override
    public T poll() {
        if (this.data.isEmpty()) {
            throw new RuntimeException("Empty queue.");
        }
        T element = this.data.remove(0);
        this.size--;
        return element;
    }

    @Override
    public T peek() {
        if (this.data.isEmpty()) {
            throw new RuntimeException("Empty queue.");
        }
        return this.data.get(0);
    }
}
