package com.workingagile.acsd;

import com.workingagile.acsd.backend.DataProcessor;
import com.workingagile.acsd.backend.Item;
import org.junit.jupiter.api.Test;
import java.util.List;

public class UnitTests {







    @Test
    public void some_unit_test() {

        // Arrange
        DataProcessor processor = DataProcessor.getInstance();
        // TODO insert data

        // Act - call the function
        processor.calculateData(false);

        // Assert
        List<Item> actualResult = processor.list;
        // TODO compare with the expected result

    }


}
