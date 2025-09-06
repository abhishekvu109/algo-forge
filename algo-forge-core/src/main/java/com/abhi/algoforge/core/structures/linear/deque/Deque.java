package com.abhi.algoforge.core.structures.linear.deque;

import com.abhi.algoforge.core.structures.linear.queue.Queue;

public interface Deque<T> extends Queue<T> {
    void offerFirst(T element);

    void offerLast(T element);

    T pollFirst();

    T pollLast();

    T peekFirst();

    T peekLast();
}
