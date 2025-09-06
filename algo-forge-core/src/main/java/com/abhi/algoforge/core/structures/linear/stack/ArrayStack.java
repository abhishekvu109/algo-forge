package com.abhi.algoforge.core.structures.linear.stack;

import com.abhi.algoforge.core.structures.common.Cursor;
import com.abhi.algoforge.core.structures.linear.sequence.DynamicArraySequence;

import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T> {

    private final int capacity;
    private DynamicArraySequence<T> data;
    private int size;


    public ArrayStack() {
        this.capacity = 15;
        this.data = new DynamicArraySequence<>(this.capacity);
        this.size = 0;
    }

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.data = new DynamicArraySequence<>(this.capacity);
        this.size = 0;
    }

    private void isStackFull() {
        if (this.size == this.capacity) {
            throw new RuntimeException("Stack is already full.");
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        this.data = new DynamicArraySequence<>(capacity);
        this.size = 0;
    }

    @Override
    public boolean contains(T element) {
        return this.data.contains(element);
    }

    @Override
    public Cursor<T> cursor() {
        return new Cursor<T>() {
            private int currentIndex = size;

            @Override
            public boolean hasNext() {
                return currentIndex > 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data.get(--currentIndex);
            }

            @Override
            public void reset() {
                this.currentIndex = size;
            }
        };
    }

    @Override
    public void push(T element) {
        isStackFull();
        this.data.add(element);
        this.size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is already empty");
        }

        T element = this.data.remove(this.size - 1);
        size--;
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return this.data.get(this.size - 1);
    }
}
