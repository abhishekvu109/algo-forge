package com.abhi.algoforge.core.structures.nonlinear.tree;

import com.abhi.algoforge.core.structures.common.Cursor;
import com.abhi.algoforge.core.structures.linear.sequence.Sequence;

public interface BinaryTree<T> extends Tree<T>, BinaryTreeCounting<T> {
    enum TreeCursorStrategy {
        PRE_ORDER, POST_ORDER, IN_ORDER, LEVEL_ORDER, SPIRAL, ZIG_ZAG, ITERATIVE_PRE_ORDER, ITERATIVE_POST_ORDER, ITERATIVE_IN_ORDER
    }


    // --- Basic Operations ---
    void insert(T element);                // Insert an element into the tree

    boolean remove(T element);            // Remove an element from the tree

    boolean contains(T element);          // Check if an element exists in the tree

    void clear();                         // Clear the entire tree

    // --- Traversals ---
    Sequence<T> sequence(TreeCursorStrategy strategy);                 // Traverse the tree in in-order

    // --- Structural Queries ---
    int size();                           // Total number of nodes

    boolean isEmpty();                    // Check if the tree is empty

    int height();                         // Height of the tree

    int depth(T element);                  // Depth of a node


    int leafCount();                       // Count of leaf nodes

    boolean isBalanced();                 // Check if the tree is balanced

    boolean isFull();                     // Check if every node has 0 or 2 children

    boolean isPerfect();                  // Check if tree is perfect

    boolean isComplete();                 // Check if tree is complete

    Sequence<T> pathTo(T element);         // Return the path from root to element

    Sequence<T> nodesAtDistanceK(int k);


    boolean areSiblings(T element1, T element2); // Check if two elements are siblings

    // --- Stream / Iterable Support ---
    Cursor<T> cursor();                    // Return a cursor for iteration
}

enum TraversalStrategy {
    DFS, BFS
}

