package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();
    }

    @Test
    void testThatAdditionWorksCorrect() {
        Assertions.assertEquals(9,calculator.addition(4, 5));
    }

    @Test
    void  testThatMultiplicationWorksCorrect() {
        Assertions.assertEquals(20,calculator.multiplication(4, 5));
    }

    @Test
    void testThatSubtractionWorksCorrect(){
        Assertions.assertEquals(5,calculator.subtraction(10, 5));
    }

    @Test
    void testThatDivisionWorksCorrect() {
        Assertions.assertEquals(2,calculator.division(10, 5));
    }

    @Test
    void testThatDivisionOnZeroWorksCorrect() {
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.division(10,0));
    }
}