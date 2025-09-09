package com.abhi.algoforge.core.structures.nonlinear.tree;

import com.abhi.algoforge.core.structures.linear.sequence.Sequence;

public interface Tree<T> {

    int size();

    boolean isEmpty();

    void clear();

    void add(T element);

    boolean contains(T element);

    Sequence<T> sequence();
}
