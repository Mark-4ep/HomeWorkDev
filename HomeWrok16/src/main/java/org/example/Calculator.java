package org.example;

import java.util.NoSuchElementException;

public class Calculator {
    public int addition(int a, int b) {
        return a + b;
    }

    public int multiplication(int a, int b) {
        notEqualsZero(a,b);
        return a * b;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }

    public int division(int a, int b) {
        notEqualsZero(a,b);
        return a / b;
    }

    public void notEqualsZero(int numberA, int numberB) {
        if(numberA < 0 || numberB < 0){
            throw new ArithmeticException("number is less than zero");
        }
    }
}
