package com.abhi.algoforge.core.structures.common;

public interface Cursor<T> {
    boolean hasNext();

    T next();

    void reset();
}
