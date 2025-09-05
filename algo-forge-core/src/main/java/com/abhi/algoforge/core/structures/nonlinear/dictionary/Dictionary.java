package com.abhi.algoforge.core.structures.nonlinear.dictionary;

import com.abhi.algoforge.core.structures.common.Traversable;

public interface Dictionary<K, V> extends Traversable<Pair<K, V>> {
    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    V get(K key);

    V put(K key, V value);

    V remove(K key);

    void clear();
}
