package com.abhi.algoforge.core.structures.linear.sequence;

import com.abhi.algoforge.core.constants.CursorMode;
import com.abhi.algoforge.core.execeptions.structures.linear.sequence.ArrayIndexOutOfBoundException;
import com.abhi.algoforge.core.structures.common.BiDirectionalCursor;
import com.abhi.algoforge.core.structures.common.Cursor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedSequenceTest {

    private SinglyLinkedSequence<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedSequence<>();
    }

    @Test
    void testSizeAndIsEmpty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());

        list.add(10);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());

        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testContainsAndIndexOf() {
        list.add(5);
        list.add(10);
        list.add(15);

        assertTrue(list.contains(10));
        assertFalse(list.contains(99));

        assertEquals(1, list.indexOf(10));
        assertEquals(-1, list.indexOf(100));
    }

    @Test
    void testAddAndGet() {
        list.add(100);
        list.add(200);
        list.add(300);

        assertEquals(100, list.get(0));
        assertEquals(200, list.get(1));
        assertEquals(300, list.get(2));

        assertThrows(NoSuchElementException.class, () -> list.get(3));
    }

    @Test
    void testSet() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.set(1, 20);
        assertEquals(20, list.get(1));

        assertThrows(IllegalArgumentException.class, () -> list.set(5, 99));
    }

    @Test
    void testAddAtIndex() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.add(1, 10);

        assertEquals(1, list.get(0));
        assertEquals(10, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(4, list.size());

        assertThrows(ArrayIndexOutOfBoundException.class, () -> list.add(10, 50));
    }

    @Test
    void testAddFirstAndAddLast() {
        list.addFirst(10);
        list.addLast(20);
        list.addLast(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.remove(0));
        assertEquals(3, list.remove(1));
        assertEquals(2, list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void testRemoveByElement() {
        list.add(5);
        list.add(10);
        list.add(15);

        assertTrue(list.remove(Integer.valueOf(10)));
        assertFalse(list.remove(Integer.valueOf(99)));

        assertEquals(2, list.size());
        assertEquals(5, list.get(0));
        assertEquals(15, list.get(1));
    }

    @Test
    void testRemoveFirstAndRemoveLast() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.removeFirst());
        assertEquals(3, list.removeLast());
        assertEquals(2, list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    void testCursorForwardOnly() {
        list.add(1);
        list.add(2);
        list.add(3);

        Cursor<Integer> cursor = list.cursor(CursorMode.FORWARD_ONLY);
        cursor.reset();

        assertTrue(cursor.hasNext());
        assertEquals(1, cursor.next());

        assertTrue(cursor.hasNext());
        assertEquals(2, cursor.next());

        assertTrue(cursor.hasNext());
        assertEquals(3, cursor.next());

        assertFalse(cursor.hasNext());
        assertThrows(NoSuchElementException.class, cursor::next);
    }

    @Test
    void testBiDirectionalCursorForwardAndBackward() {
        list.add(10);
        list.add(20);
        list.add(30);

        BiDirectionalCursor<Integer> cursor = (BiDirectionalCursor<Integer>) list.cursor(CursorMode.BI_DIRECTIONAL);

        cursor.reset();
        assertTrue(cursor.hasNext());
        cursor.next(); // move to 20
        cursor.next(); // move to 30

        assertFalse(cursor.hasNext());

        assertTrue(cursor.hasPrevious());
        cursor.previous(); // move back to 20

        assertTrue(cursor.hasPrevious());
        cursor.previous(); // move back to 10

        assertFalse(cursor.hasPrevious());
    }
}