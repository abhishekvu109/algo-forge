package com.abhi.algoforge.core.structures.common;

public interface Container<T> extends Traversable<T> {
    int size();

    boolean isEmpty();

    void clear();

    boolean contains(T element);
}
