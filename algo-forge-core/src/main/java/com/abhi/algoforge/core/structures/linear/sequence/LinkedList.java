package com.abhi.algoforge.core.structures.linear.sequence;

public interface LinkedList<T> {
    void addFirst(T element);

    void addLast(T element);

    T removeFirst();

    T removeLast();
}
