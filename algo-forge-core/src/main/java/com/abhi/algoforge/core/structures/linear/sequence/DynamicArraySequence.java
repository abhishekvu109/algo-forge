package com.abhi.algoforge.core.structures.linear.sequence;


import com.abhi.algoforge.core.constants.AppConstants;
import com.abhi.algoforge.core.constants.CursorMode;
import com.abhi.algoforge.core.execeptions.structures.linear.sequence.ArrayIndexOutOfBoundException;
import com.abhi.algoforge.core.execeptions.structures.linear.sequence.ArrayUnderFlowException;
import com.abhi.algoforge.core.structures.common.BiDirectionalCursor;
import com.abhi.algoforge.core.structures.common.Cursor;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class DynamicArraySequence<T> implements Sequence<T> {


    private Object[] data;

    private int size;

    private int CAPACITY;

    public DynamicArraySequence() {
        this.CAPACITY = AppConstants.DynamicArrayList.INIT_CAPACITY;
        this.size = 0;
        this.data = new Object[CAPACITY];
    }

    public DynamicArraySequence(int CAPACITY) {
        this.CAPACITY = CAPACITY;
        this.size = 0;
        this.data = new Object[CAPACITY];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        this.data = new Object[CAPACITY];
        this.size = 0;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public Cursor<T> cursor() {
        return new DynamicArraySequenceCursor();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) this.data[index];
    }

    @Override
    public void set(int index, T element) {
        checkIndex(index);
        data[index] = element;
    }

    @Override
    public void add(T element) {
        ensureCapacity(this.size + 1);
        this.data[size++] = element;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        ensureCapacity(size + 1);
        Object[] temp = new Object[data.length];
        int i = 0, j = 0;
        while (j < data.length) {
            if (i == index) {
                temp[i] = element;
                i++;
            } else {
                temp[i] = data[j];
                j++;
            }
        }
        this.size++;
        this.data = temp;
    }

    @Override
    public void addAll(T[] elements) {
        Arrays.stream(elements).forEach(element -> {
            this.add(element);
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);
        if (index == this.size) {
            Object element = data[index];
            this.data[index] = null;
            this.size--;
            return (T) element;
        } else {
            Object[] temp = new Object[data.length];
            T element = (T) data[index];
            int j = 0;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    continue;
                }
                temp[j++] = data[i];
            }
            size--;
            data = temp;
            return element;
        }
    }

    @Override
    public boolean remove(T element) {
        if (size == 0)
            return false;
        Object[] temp = new Object[data.length];
        int j = 0;
        boolean found = false;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                count++;
                found = true;
            } else {
                temp[j] = data[i];
                j++;
            }
        }
        data = temp;
        size = size - count;
        return found;
    }

    @Override
    public int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(element)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index > this.size) {
            throw new ArrayIndexOutOfBoundException();
        }
        if (index < 0) {
            throw new ArrayUnderFlowException();
        }
    }

    private void ensureCapacity(int newSize) {
        if (newSize > data.length) {
            CAPACITY = CAPACITY * 2;
            Object[] temp = new Object[Math.max(CAPACITY, newSize)];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
        }
    }

    @Override
    public Cursor<T> cursor(CursorMode mode) {
        switch (mode) {
            case FORWARD_ONLY -> {
                return new DynamicArraySequenceCursor();
            }
            case BI_DIRECTIONAL -> {
                return new DynamicArraySequenceBiDirectionalCursor();
            }
            default -> {
                throw new IllegalArgumentException("Unsupported mode: " + mode);
            }
        }
    }

    private class DynamicArraySequenceBiDirectionalCursor implements BiDirectionalCursor<T> {
        private int currentIndex = 0;
        private int lastIndex = -1;

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            T element = (T) data[lastIndex];
            currentIndex = lastIndex;
            lastIndex--;
            return element;
        }

        @Override
        public void remove() {
            if (currentIndex >= 0 && currentIndex <= size) {
                int j = 0;
                Object[] temp = new Object[data.length];
                for (int i = 0; i < data.length; i++) {
                    if (i != currentIndex - 1) {
                        temp[j] = data[i];
                        j++;
                    }
                }
                size--;
                data = temp;
                if (currentIndex > size) {
                    currentIndex--;
                    lastIndex--;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T element = (T) data[currentIndex];
            lastIndex = currentIndex;
            currentIndex++;
            return element;
        }

        @Override
        public void reset() {
            currentIndex = 0;
            lastIndex = -1;
        }
    }

    private class DynamicArraySequenceCursor implements Cursor<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (currentIndex > size) {
                throw new ArrayIndexOutOfBoundException();
            }
            return (T) data[currentIndex++];
        }


        @Override
        public void reset() {
            currentIndex = 0;
        }
    }
}
