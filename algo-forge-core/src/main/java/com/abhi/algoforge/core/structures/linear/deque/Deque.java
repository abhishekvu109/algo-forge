package com.abhi.algoforge.core.structures.linear.deque;

import com.abhi.algoforge.core.structures.linear.queue.Queue;

public interface Deque<T> extends Queue<T> {
    void addFirst(T element);

    void addLast(T element);

    T removeFirst();

    T removeLast();

    T peekFirst();

    T peekLast();
}
