package com.abhi.algoforge.core.structures.nonlinear.tree;

import com.abhi.algoforge.core.structures.linear.sequence.Sequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class GenericTreeTest {
    private GenericTree<Integer> tree;

    @BeforeEach
    public void setUp() {
        tree = new GenericTree<>(3); // allow max 3 children for testing
    }

    @Test
    public void testAddRoot() {
        assertTrue(tree.isEmpty());
        tree.add(1);
        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
        assertTrue(tree.contains(1));
    }

    @Test
    public void testAddMultipleElements() {
        tree.add(1); // root
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);

        assertEquals(5, tree.size());
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(5));
    }

    @Test
    public void testAddFillsChildrenCorrectly() {
        tree.add(1); // root
        tree.add(2);
        tree.add(3);
        tree.add(4); // should go into the first child’s children

        Sequence<Integer> elements = tree.sequence();
        assertEquals(4, elements.size());
        assertTrue(elements.contains(1));
        assertTrue(elements.contains(2));
        assertTrue(elements.contains(3));
        assertTrue(elements.contains(4));
    }

    @Test
    public void testContainsWithNonExistentElement() {
        tree.add(1);
        tree.add(2);
        assertFalse(tree.contains(5));
    }

    @Test
    public void testSequenceOrder() {
        tree.add(1); // root
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);

        Sequence<Integer> elements = tree.sequence();
        assertNotNull(elements);
        assertEquals(5, elements.size());
        assertTrue(elements.contains(1));
        assertTrue(elements.contains(2));
        assertTrue(elements.contains(3));
        assertTrue(elements.contains(4));
        assertTrue(elements.contains(5));
    }

    @Test
    public void testClearTree() {
        tree.add(1);
        tree.add(2);
        assertFalse(tree.isEmpty());
        tree.clear();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertNull(tree.sequence());
    }

    @Test
    public void testAddWithMultipleLevels() {
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(7);

        assertEquals(7, tree.size());
        Sequence<Integer> elements = tree.sequence();
        assertTrue(elements.contains(1));
        assertTrue(elements.contains(7));
    }
}