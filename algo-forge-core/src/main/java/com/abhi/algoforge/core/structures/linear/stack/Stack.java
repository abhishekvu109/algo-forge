package com.abhi.algoforge.core.structures.linear.stack;

import com.abhi.algoforge.core.structures.common.Container;

public interface Stack<T> extends Container<T> {
    void push(T element);

    T pop();

    T peek();
}
