package com.abhi.algoforge.core.structures.nonlinear.bag;

import com.abhi.algoforge.core.structures.common.Container;

public interface Bag<T> extends Container<T> {
    boolean add(T element);

    boolean remove(T element);

    boolean contains(T element);
}
