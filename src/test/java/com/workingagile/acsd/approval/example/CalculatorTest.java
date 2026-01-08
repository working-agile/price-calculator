package com.workingagile.acsd.approval.example;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void successful_sum() {

        // Arrange
        Calculator calculator = new Calculator();

        // Act - call the function
        int result = calculator.sum(10, 20);

        // Assert
        Approvals.verify("The expected result is: " + result);

    }


}
