package com.abhi.algoforge.core.structures.linear.stack;

import com.abhi.algoforge.core.structures.common.Cursor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    private LinkedStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new LinkedStack<>();
    }

    @Test
    void testPushAndSize() {
        assertEquals(0, stack.size());
        stack.push(10);
        assertEquals(1, stack.size());
        stack.push(20);
        assertEquals(2, stack.size());
    }

    @Test
    void testPushUntilFull() {
        LinkedStack<Integer> smallStack = new LinkedStack<>(3);
        smallStack.push(1);
        smallStack.push(2);
        smallStack.push(3);
        assertThrows(RuntimeException.class, () -> smallStack.push(4), "Stack is already full.");
    }

    @Test
    void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(100);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testClear() {
        stack.push(5);
        stack.push(10);
        assertEquals(2, stack.size());
        stack.clear();
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testContains() {
        stack.push(1);
        stack.push(2);
        assertTrue(stack.contains(1));
        assertTrue(stack.contains(2));
        assertFalse(stack.contains(3));
    }

    @Test
    void testPeek() {
        stack.push(5);
        stack.push(10);
        assertEquals(10, stack.peek());
        stack.push(20);
        assertEquals(20, stack.peek());
    }

    @Test
    void testPeekOnEmptyStack() {
        assertThrows(RuntimeException.class, stack::peek);
    }

    @Test
    void testPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.size());

        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());

        assertEquals(1, stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    void testPopOnEmptyStack() {
        assertThrows(RuntimeException.class, stack::pop, "Stack is already empty.");
    }

    @Test
    void testCursorTraversal() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Cursor<Integer> cursor = stack.cursor();
        cursor.reset();

        assertTrue(cursor.hasNext());
        assertEquals(3, cursor.next());
        assertTrue(cursor.hasNext());
        assertEquals(2, cursor.next());
        assertTrue(cursor.hasNext());
        assertEquals(1, cursor.next());
        assertFalse(cursor.hasNext());
        assertThrows(RuntimeException.class, cursor::next);
    }

    @Test
    void testCursorAfterClear() {
        stack.push(1);
        stack.push(2);
        stack.clear();

        Cursor<Integer> cursor = stack.cursor();
        cursor.reset();

        assertFalse(cursor.hasNext());
        assertThrows(RuntimeException.class, cursor::next);
    }

    @Test
    void testCustomCapacityStack() {
        LinkedStack<String> customStack = new LinkedStack<>(2);
        customStack.push("A");
        customStack.push("B");
        assertThrows(RuntimeException.class, () -> customStack.push("C"));
    }
}