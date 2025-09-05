package com.abhi.algoforge.core.structures.linear.sequence;

import com.abhi.algoforge.core.constants.CursorMode;
import com.abhi.algoforge.core.structures.common.Container;
import com.abhi.algoforge.core.structures.common.Cursor;

public interface Sequence<T> extends Container<T> {
    T get(int index);

    void set(int index, T element);

    void add(T element);

    void addAll(T[] elements);

    void add(int index, T element);

    T remove(int index);

    boolean remove(T element);

    int indexOf(T element);

    Cursor<T> cursor(CursorMode mode);
}
