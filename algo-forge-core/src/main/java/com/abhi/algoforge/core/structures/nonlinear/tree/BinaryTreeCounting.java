package com.abhi.algoforge.core.structures.nonlinear.tree;

public interface BinaryTreeCounting<T> {
    int count(T element); // occurrences of value
    int count(T element,TraversalStrategy strategy); // occurrences of value

    int internalNodeCount(); // Number of nodes with at least one child (non-leaf).
    int internalNodeCount(TraversalStrategy strategy); // Number of nodes with at least one child (non-leaf).

    int singleChildCount(); // Number of nodes with exactly one child.
    int singleChildCount(TraversalStrategy strategy); // Number of nodes with exactly one child.

    int countAtLevel(int level); // Number of nodes at a given level.
    int countAtLevel(int level,TraversalStrategy strategy); // Number of nodes at a given level.

    int countNodesInRange(int minDepth, int maxDepth); // Count nodes within certain levels.
    int countNodesInRange(int minDepth, int maxDepth,TraversalStrategy strategy); // Count nodes within certain levels.

    int maxLevelNodes(); //  Number of nodes at the last level.
}
