package com.abhi.algoforge.core.execeptions.structures.linear.sequence;

public class ArrayIndexOutOfBoundException extends RuntimeException {

    public ArrayIndexOutOfBoundException() {
        super("Array index out of bound exception.");
    }

    public ArrayIndexOutOfBoundException(String message) {
        super(message);
    }
}
