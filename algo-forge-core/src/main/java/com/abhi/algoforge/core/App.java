package com.abhi.algoforge.core;

import com.abhi.algoforge.core.constants.CursorMode;
import com.abhi.algoforge.core.structures.common.BiDirectionalCursor;
import com.abhi.algoforge.core.structures.linear.sequence.DynamicArraySequence;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        DynamicArraySequence<Integer> seq = new DynamicArraySequence<>(5);
        seq.add(10);
        seq.add(20);
        seq.add(30);

        BiDirectionalCursor<Integer> cur = (BiDirectionalCursor<Integer>) seq.cursor(CursorMode.BI_DIRECTIONAL);

        // Forward traversal
        while (cur.hasNext()) {
            System.out.println("Next: " + cur.next());
        }

        // Backward traversal
        while (cur.hasPrevious()) {
            System.out.println("Previous: " + cur.previous());
        }

        // Remove element during traversal
        cur.reset();
        while (cur.hasNext()) {
            int val = cur.next();
            if (val == 20) {
                cur.remove(); // removes 20
            }
        }

        // Print again after removal
        cur.reset();
        while (cur.hasNext()) {
            System.out.println("After removal: " + cur.next());
        }
    }
}
