package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesTargetTests {

    @Nested
    @DisplayName("Business rule: Total remaining sales target")
    class TotalRemainingSalesTarget {

        @DisplayName("Should calculate the total remaining sales")
        @Test
        void should_calculate_total_value_of_remaining_training_courses() {

            /*
                Number of seats:				5
                Days before training course:	30
                Type:							CSD
                Full price:						4000
                super early bird discount:		 400
                Discounted price:				3600
             */
            Item i1 = new Item(30+1, 50, 5, true, "CSD", 4000);
            /*
                Number of seats:				2
                Days before training course:	9
                Type:							CSD
                Full price:						4000
                Super Early bird discount:		9*30
                Discounted price:				3500
             */
            Item i2 = new Item(9+1, 50, 2, true, "CSD", 4000);
            Item[] items = new Item[]{i1, i2};

            // Act
            DataProcessor.processData(items);

            // Assert
            int expectedRemainingSalesTarget = (3600 * 5) + (4000-9*30) * 2;
            assertEquals(expectedRemainingSalesTarget, DataProcessor.value);

        }
    }

}
