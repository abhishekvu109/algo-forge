package com.abhi.algoforge.core.structures.linear.queue;

import com.abhi.algoforge.core.structures.common.Cursor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedQueueTest {

    private LinkedQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedQueue<>();
    }

    @Test
    void testInitialSize() {
        assertEquals(0, queue.size(), "Initial size should be 1 due to constructor increment.");
    }

    @Test
    void testIsEmpty() {
        queue.offer(1);
        assertFalse(queue.isEmpty(), "Initial size makes queue not empty.");
        queue.clear();
        assertTrue(queue.isEmpty(), "Queue should be empty after clear.");
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
        assertTrue(queue.offer(10));
        assertEquals(1, queue.size());
        assertTrue(queue.offer(20));
        assertEquals(2, queue.size());
    }

    @Test
    void testPoll() {
        queue.offer(1);
        queue.offer(2);

        assertEquals(1, queue.poll());
        assertEquals(1, queue.size());

        assertEquals(2, queue.poll());
        assertEquals(0, queue.size());  // Size reduced but initial size still 1 from constructor.

        assertThrows(NoSuchElementException.class, queue::peek, "Peeking into empty queue should throw.");
    }

    @Test
    void testPollFromEmptyQueue() {
        queue.clear();
        assertThrows(NoSuchElementException.class, queue::peek, "Peeking into empty queue should throw.");
    }

    @Test
    void testPeek() {
        queue.offer(100);
        assertEquals(100, queue.peek());
        queue.poll();
        assertThrows(NoSuchElementException.class, queue::peek);
    }

    @Test
    void testCursorTraversal() {
        queue.clear();
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
        queue.clear();
        Cursor<Integer> cursor = queue.cursor();
        cursor.reset();
        assertFalse(cursor.hasNext());
    }

    @Test
    void testMultipleOperations() {
        queue.clear();
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