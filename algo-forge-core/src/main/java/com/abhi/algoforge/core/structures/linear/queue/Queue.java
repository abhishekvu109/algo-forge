package com.abhi.algoforge.core.structures.linear.queue;

import com.abhi.algoforge.core.structures.common.Container;

public interface Queue<T> extends Container<T> {
    boolean offer(T element);

    T poll();

    T peek();
}
