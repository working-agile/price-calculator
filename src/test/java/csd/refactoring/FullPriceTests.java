package csd.refactoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullPriceTests {

    @Nested
    @DisplayName("Business rule: Full price policy")
    class FullPricePolicy {

        @DisplayName("Full prices on the day of the training course")
        @ParameterizedTest
        @ValueSource(strings = {"CSD", "CSPO", "CSM"})
        void should_have_full_price_on_the_day_of_the_training_class(String trainingCourseType) {

            // Arrange
            /*
                Days before training course:	0
                type:							CSD, CSPO, CSM
                Full price (no discounts):		4000
                -->
                no discount:		 			4000
             */
            Item i1 = new Item(0+1, 50, 20, true, trainingCourseType, 4000);
            Item[] items = new Item[]{i1};

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(4000, processedItems[0].price);
        }

        @DisplayName("Full prices on the day prior to the training course")
        @ParameterizedTest
        @ValueSource(strings = {"CSD", "CSPO", "CSM"})
        void should_have_full_price_on_the_day_prior_to_the_training_class(String trainingCourseType) {

            // Arrange
            /*
                Days before training course:	1
                Full price (no discounts):		4000
                -->
                no discount:		 			4000
             */
            Item i1 = new Item(1+1, 50, 20, true, trainingCourseType, 4000);
            Item[] items = new Item[]{i1};

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(4000, processedItems[0].price);
        }


        @DisplayName("Full prices 5 days prior to the training course if less than seats left")
        @ParameterizedTest
        @ValueSource(strings = {"CSD", "CSPO", "CSM"})
        void full_price_5_days_prior_to_the_training_class_if_less_than_3_seats_left(String trainingCourseType) {

            // Arrange
            /*
                Days before training course:	5
                Seats left:						2
                Full price (no discounts):		4000
                -->
                no discount:		 			4000
             */
            Item i1 = new Item(5+1, 50, 2, true, trainingCourseType, 4000);
            Item[] items = new Item[]{i1};

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(4000, processedItems[0].price);
        }

    }


}
