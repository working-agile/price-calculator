package com.workingagile.acsd;

import com.workingagile.acsd.backend.DataProcessor;
import com.workingagile.acsd.backend.Item;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;

public class UnitTests {

    @Disabled
    @Test
    public void some_unit_test() {

        // Arrange
        DataProcessor processor = new DataProcessor();
        // TODO insert data

        // Act - call the function
        processor.calculateData(false);

        // Assert
        List<Item> actualResult = processor.getList();
        // TODO compare with the expected result

    }


}
