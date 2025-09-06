package com.abhi.algoforge.core.structures.linear.sequence;

import com.abhi.algoforge.core.constants.CursorMode;
import com.abhi.algoforge.core.structures.common.BiDirectionalCursor;
import com.abhi.algoforge.core.structures.common.Cursor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedSequenceTest {
    private DoublyLinkedSequence<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedSequence<>();
    }

    @Test
    void testAddFirstAndRemoveFirst() {
        list.addFirst(10);
        assertEquals(1, list.size());
        assertEquals(10, list.removeFirst());
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    void testAddLastAndRemoveLast() {
        list.addLast(20);
        list.addLast(30);
        assertEquals(2, list.size());
        assertEquals(30, list.removeLast());
        assertEquals(20, list.removeLast());
        assertTrue(list.isEmpty());
        assertThrows(NoSuchElementException.class, list::removeLast);
    }

    @Test
    void testAddAndGet() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertNull(list.get(5)); // Index out of bounds returns null
    }

    @Test
    void testSet() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.set(1, 99);
        assertEquals(99, list.get(1));
        assertThrows(RuntimeException.class, () -> list.set(5, 100));
    }

    @Test
    void testAddAll() {
        list.addAll(new Integer[]{4, 5, 6});
        assertEquals(3, list.size());
        assertEquals(4, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(6, list.get(2));
    }

    @Test
    void testAddAtIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 10); // Add at index 1
        assertEquals(4, list.size());
        assertEquals(1, list.get(0));
        assertEquals(10, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(3, list.get(3));

        assertThrows(RuntimeException.class, () -> list.add(10, 100));
    }

    @Test
    void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertNull(list.remove(5)); // Index out of bounds
        list.remove(1);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    void testRemoveByElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.remove(2));
        assertEquals(2, list.size());
        assertNull(list.remove(99)); // Element not found
    }

    @Test
    void testIndexOf() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(1, list.indexOf(20));
        assertEquals(-1, list.indexOf(99));
    }

    @Test
    void testClearAndIsEmpty() {
        list.add(1);
        list.add(2);
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testContains() {
        list.add(1);
        list.add(2);
        assertTrue(list.contains(1));
        assertFalse(list.contains(3));
    }

    @Test
    void testCursorForwardOnly() {
        list.add(1);
        list.add(2);
        list.add(3);

        Cursor<Integer> cursor = list.cursor(CursorMode.FORWARD_ONLY);
        cursor.reset();
        int count = 0;
        while (cursor.hasNext()) {
            assertNotNull(cursor.next());
            count++;
        }
        assertEquals(2, count); // Only two transitions possible in forward-only cursor
        assertThrows(NoSuchElementException.class, cursor::next);
    }

    @Test
    void testCursorBiDirectional() {
        list.add(1);
        list.add(2);
        list.add(3);

        BiDirectionalCursor<Integer> cursor = (BiDirectionalCursor<Integer>) list.cursor(CursorMode.BI_DIRECTIONAL);
        cursor.reset();
        assertTrue(cursor.hasNext());
        assertEquals(2, cursor.next());
        assertTrue(cursor.hasNext());
        assertEquals(3, cursor.next());
        assertFalse(cursor.hasNext());

        assertTrue(cursor.hasPrevious());
        assertEquals(2, cursor.previous());
        assertTrue(cursor.hasPrevious());
        assertEquals(1, cursor.previous());
        assertFalse(cursor.hasPrevious());
        assertThrows(NoSuchElementException.class, cursor::previous);
    }

    @Test
    void testCursorReset() {
        list.add(1);
        list.add(2);
        Cursor<Integer> cursor = list.cursor(CursorMode.FORWARD_ONLY);
        cursor.reset();
        assertEquals(1, cursor.next());
        cursor.reset();
        assertEquals(1, cursor.next());
    }

    @Test
    void testRemoveThroughCursor() {
        list.add(1);
        list.add(2);
        list.add(3);

        BiDirectionalCursor<Integer> cursor = (BiDirectionalCursor<Integer>) list.cursor(CursorMode.BI_DIRECTIONAL);
        cursor.reset();
        cursor.next();
        cursor.remove();
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
    }

    @Test
    void testRemoveLastEdgeCase() {
        assertThrows(NoSuchElementException.class, list::removeLast);
    }

    @Test
    void testRemoveFirstEdgeCase() {
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }
}