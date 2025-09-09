package com.abhi.algoforge.core.structures.nonlinear.tree;

import com.abhi.algoforge.core.structures.linear.sequence.Sequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericBinaryTreeTest {

    private GenericBinaryTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new GenericBinaryTree<>();
    }

    @Test
    void testInsertAndContains() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(30));
        assertFalse(tree.contains(40));
    }

    @Test
    void testSize() {
        assertEquals(0, tree.size());
        tree.insert(1);
        tree.insert(2);
        assertEquals(2, tree.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(tree.isEmpty());
        tree.insert(5);
        assertFalse(tree.isEmpty());
    }

    @Test
    void testClear() {
        tree.insert(5);
        tree.insert(10);
        tree.clear();
        assertTrue(tree.isEmpty());
    }

    @Test
    void testHeight() {
        assertEquals(0, tree.height());
        tree.insert(1);
        assertEquals(1, tree.height());
        tree.insert(2);
        tree.insert(3);
        assertEquals(2, tree.height());
    }

    @Test
    void testDepth() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        assertEquals(0, tree.depth(1));
        assertTrue(tree.depth(2) > 0);
    }

    @Test
    void testLeafCount() {
        assertEquals(0, tree.leafCount());
        tree.insert(1);
        assertEquals(1, tree.leafCount());
        tree.insert(2);
        tree.insert(3);
        assertTrue(tree.leafCount() >= 1);
    }

    @Test
    void testIsBalanced() {
        assertTrue(tree.isBalanced());
        tree.insert(1);
        assertTrue(tree.isBalanced());
    }

    @Test
    void testIsFull() {
        assertTrue(tree.isFull());
        tree.insert(1);
        assertTrue(tree.isFull());
    }

    @Test
    void testIsPerfect() {
        assertTrue(tree.isPerfect());
        tree.insert(1);
        assertTrue(tree.isPerfect());
    }

    @Test
    void testPathTo() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        assertTrue(tree.pathTo(2).contains(1));
        assertTrue(tree.pathTo(3).contains(1));
    }

    @Test
    void testRemove() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        assertTrue(tree.contains(2));
        tree.remove(2);
        assertFalse(tree.contains(2));
    }

    @Test
    void testCursor() {
        tree.insert(1);
        tree.insert(2);
        var cursor = tree.cursor();
        assertTrue(cursor.hasNext());
        assertNotNull(cursor.next());
    }

    @Test
    void testNodesAtDistanceK() {
        tree = new GenericBinaryTree<>();
        // Creating the following tree:
        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4   5 6   7

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);

        Sequence<Integer> result = tree.nodesAtDistanceK(0);
        assertEquals(1, result.size());
        assertTrue(result.contains(1));

        Sequence<Integer> result1 = tree.nodesAtDistanceK(1);
        assertEquals(2, result1.size());
        assertTrue(result1.contains(2));
        assertTrue(result1.contains(3));

        Sequence<Integer> result2 = tree.nodesAtDistanceK(2);
        assertEquals(4, result2.size());
        assertTrue(result2.contains(4));
        assertTrue(result2.contains(5));
        assertTrue(result2.contains(6));
        assertTrue(result2.contains(7));

        Sequence<Integer> result3 = tree.nodesAtDistanceK(3);
        assertEquals(0, result3.size());

        Sequence<Integer> result4 = tree.nodesAtDistanceK(-1);
        assertEquals(0, result4.size());
    }
}