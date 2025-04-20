package com.workingagile.acsd.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    @Test
    public void sould_add_amount_to_value() {

        // Arrange
        Calculator calculator = new Calculator(0);

        // Act - call the function
        calculator.add(100);

        // Assert
        int actualValue = calculator.getValue();
        Assertions.assertEquals(100, actualValue);

    }
}
