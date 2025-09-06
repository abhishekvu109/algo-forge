package com.abhi.algoforge.core.structures.linear.deque;

import com.abhi.algoforge.core.structures.common.Cursor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedDequeTest {

    private LinkedDeque<Integer> deque;

    @BeforeEach
    void setUp() {
        deque = new LinkedDeque<>();
    }

    @Test
    void testInitialState() {
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    void testOfferFirstAndPollFirst() {
        deque.offerFirst(1);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());

        assertEquals(1, deque.pollFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    void testOfferLastAndPollLast() {
        deque.offerLast(1);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());

        assertEquals(1, deque.pollLast());
        assertTrue(deque.isEmpty());
    }

    @Test
    void testOfferAndPoll() {
        deque.offer(1);
        deque.offer(2);
        assertEquals(2, deque.size());

        assertEquals(1, deque.poll());
        assertEquals(1, deque.size());
        assertEquals(2, deque.poll());
        assertTrue(deque.isEmpty());
    }

    @Test
    void testPeekFirstAndPeekLast() {
        deque.offerFirst(1);
        deque.offerLast(2);

        assertEquals(1, deque.peekFirst());
        assertEquals(2, deque.peekLast());
    }

    @Test
    void testPeekAndPollExceptions() {
        assertThrows(NoSuchElementException.class, deque::pollFirst);
        assertThrows(NoSuchElementException.class, deque::pollLast);
        assertThrows(NoSuchElementException.class, deque::peekFirst);
        assertThrows(NoSuchElementException.class, deque::peekLast);
        assertThrows(NoSuchElementException.class, deque::poll);
        assertThrows(NoSuchElementException.class, deque::peek);
    }

    @Test
    void testClear() {
        deque.offerFirst(1);
        deque.offerLast(2);
        assertEquals(2, deque.size());

        deque.clear();
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    void testContains() {
        deque.offerFirst(1);
        deque.offerLast(2);

        assertTrue(deque.contains(1));
        assertTrue(deque.contains(2));
        assertFalse(deque.contains(3));
    }

    @Test
    void testCursorTraversal() {
        deque.offerFirst(1);
        deque.offerLast(2);
        deque.offerLast(3);

        Cursor<Integer> cursor = deque.cursor();
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
    void testMultipleOperations() {
        assertTrue(deque.isEmpty());

        deque.offerFirst(1);
        deque.offerLast(2);
        deque.offerFirst(0);
        assertEquals(3, deque.size());

        assertEquals(0, deque.pollFirst());
        assertEquals(2, deque.pollLast());
        assertEquals(1, deque.peekFirst());
        assertEquals(1, deque.peekLast());

        assertEquals(1, deque.poll());
        assertTrue(deque.isEmpty());
    }
}