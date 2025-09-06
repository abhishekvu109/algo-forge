package com.abhi.algoforge.core.structures.linear.queue;

import com.abhi.algoforge.core.structures.common.Cursor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    private ArrayQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new ArrayQueue<>();
    }

    @Test
    void testSize() {
        assertEquals(0, queue.size());
        queue.offer(10);
        assertEquals(1, queue.size());
        queue.offer(20);
        assertEquals(2, queue.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.offer(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    void testClear() {
        queue.offer(1);
        queue.offer(2);
        assertFalse(queue.isEmpty());
        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void testContains() {
        queue.offer(1);
        queue.offer(2);
        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
        assertFalse(queue.contains(3));
    }

    @Test
    void testOffer() {
        assertTrue(queue.offer(5));
        assertEquals(1, queue.size());
        assertTrue(queue.offer(10));
        assertEquals(2, queue.size());
    }

    @Test
    void testPoll() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(1, queue.poll());
        assertEquals(2, queue.size());

        assertEquals(2, queue.poll());
        assertEquals(1, queue.size());

        assertEquals(3, queue.poll());
        assertEquals(0, queue.size());
    }

    @Test
    void testPollFromEmptyQueue() {
        assertThrows(RuntimeException.class, queue::poll, "Empty queue.");
    }

    @Test
    void testPeek() {
        queue.offer(100);
        queue.offer(200);
        assertEquals(100, queue.peek());
        queue.poll();
        assertEquals(200, queue.peek());
    }

    @Test
    void testPeekFromEmptyQueue() {
        assertThrows(RuntimeException.class, queue::peek, "Empty queue.");
    }

    @Test
    void testCursorTraversal() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        Cursor<Integer> cursor = queue.cursor();
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
    void testCursorAfterClear() {
        queue.offer(1);
        queue.offer(2);
        queue.clear();

        Cursor<Integer> cursor = queue.cursor();
        cursor.reset();

        assertFalse(cursor.hasNext());
        assertThrows(NoSuchElementException.class, cursor::next);
    }

    @Test
    void testMultipleOperations() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(1, queue.poll());
        queue.offer(4);
        assertEquals(2, queue.peek());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.peek());
        assertEquals(3, queue.poll());
        assertEquals(4, queue.peek());
        assertEquals(4, queue.poll());
        assertTrue(queue.isEmpty());
    }
}