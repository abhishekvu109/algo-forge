package com.abhi.algoforge.core.structures.linear.sequence;

import com.abhi.algoforge.core.constants.CursorMode;
import com.abhi.algoforge.core.structures.common.BiDirectionalCursor;
import com.abhi.algoforge.core.structures.common.Cursor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicArraySequenceTest {

    private DynamicArraySequence<Integer> sequence;

    @BeforeEach
    void setUp() {
        sequence = new DynamicArraySequence<>(5);
    }

    @Test
    void testAddAndGet() {
        sequence.add(10);
        sequence.add(20);
        sequence.add(30);

        assertEquals(3, sequence.size());
        assertEquals(10, sequence.get(0));
        assertEquals(20, sequence.get(1));
        assertEquals(30, sequence.get(2));
    }

    @Test
    void testSet() {
        sequence.add(5);
        sequence.add(15);

        sequence.set(0, 50);
        assertEquals(50, sequence.get(0));
        assertEquals(15, sequence.get(1));
    }

    @Test
    void testRemoveByIndex() {
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);

        int removed = sequence.remove(1);
        assertEquals(2, removed);
        assertEquals(2, sequence.size());
        assertEquals(3, sequence.get(1));
    }

    @Test
    void testRemoveByElement() {
        sequence.add(100);
        sequence.add(200);
        sequence.add(300);

        assertTrue(sequence.remove(Integer.valueOf(200)));
        assertEquals(2, sequence.size());
        assertEquals(100, sequence.get(0));
        assertEquals(300, sequence.get(1));
    }

    @Test
    void testContainsAndIndexOf() {
        sequence.add(42);
        sequence.add(99);

        assertTrue(sequence.contains(42));
        assertEquals(0, sequence.indexOf(42));
        assertEquals(-1, sequence.indexOf(123));
    }

    @Test
    void testClear() {
        sequence.add(1);
        sequence.add(2);

        sequence.clear();
        assertEquals(0, sequence.size());
        assertTrue(sequence.isEmpty());
    }

    @Test
    void testForwardCursor() {
        sequence.add(10);
        sequence.add(20);

        Cursor<Integer> cursor = sequence.cursor(CursorMode.FORWARD_ONLY);

        assertTrue(cursor.hasNext());
        assertEquals(10, cursor.next());
        assertEquals(20, cursor.next());
        assertFalse(cursor.hasNext());

        cursor.reset();
        assertTrue(cursor.hasNext());
        assertEquals(10, cursor.next());
    }

    @Test
    void testBidirectionalCursorForwardAndBackward() {
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);

        BiDirectionalCursor<Integer> cursor =
                (BiDirectionalCursor<Integer>) sequence.cursor(CursorMode.BI_DIRECTIONAL);

        assertTrue(cursor.hasNext());
        assertEquals(1, cursor.next());
        assertEquals(2, cursor.next());

        assertTrue(cursor.hasPrevious());
        assertEquals(2, cursor.previous());
        assertEquals(1, cursor.previous());

        assertFalse(cursor.hasPrevious());
    }

    @Test
    void testBidirectionalCursorRemove() {
        sequence.add(10);
        sequence.add(20);
        sequence.add(30);

        BiDirectionalCursor<Integer> cursor =
                (BiDirectionalCursor<Integer>) sequence.cursor(CursorMode.BI_DIRECTIONAL);

        cursor.next(); // 10
        cursor.remove(); // should remove 10

        assertEquals(2, sequence.size());
        assertEquals(20, sequence.get(0));
    }

    @Test
    void testCursorOutOfBoundsThrowsException() {
        assertThrows(NoSuchElementException.class, () -> {
            BiDirectionalCursor<Integer> cursor =
                    (BiDirectionalCursor<Integer>) sequence.cursor(CursorMode.BI_DIRECTIONAL);
            cursor.previous(); // nothing to move back
        });
    }

    @Test
    void testIndexOutOfBoundsThrowsException() {
        assertThrows(RuntimeException.class, () -> sequence.get(5));
        assertThrows(RuntimeException.class, () -> sequence.remove(-1));
    }
}