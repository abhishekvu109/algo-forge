package com.abhi.algoforge.core.execeptions.structures.linear.sequence;

public class ArrayUnderFlowException extends RuntimeException {
    public ArrayUnderFlowException() {
        super("Array under flow exception.");
    }

    public ArrayUnderFlowException(String message) {
        super(message);
    }
}
