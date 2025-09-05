package com.abhi.algoforge.core.structures.common;

public interface BiDirectionalCursor<T> extends Cursor<T> {
    boolean hasPrevious();

    T previous();

    void remove();
}
