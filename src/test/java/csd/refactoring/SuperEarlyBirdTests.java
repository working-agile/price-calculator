package csd.refactoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperEarlyBirdTests {

    @Nested
    @DisplayName("Business rule: Super Early Bird discounts")
    class SuperEarlyBirdDiscounts {

        @DisplayName("Super Early Bird discount starts before day 10 before training course")
        @Test
        void super_early_bird_discount_on_day_11_before_training_course() {

            // Arrange
            /*
                Days before training course:	11
                type:							CSD
                Full price (no discounts):		4000
                SuperEarlyBird discount:		-400
                -->
                discounted price:				3600

             */
            Item i1 = new Item(11 + 1, 50, 20, true, "CSD", 4000);
            Item[] items = new Item[]{i1};

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(3600, processedItems[0].price);
        }


        @DisplayName("Super Early Bird discount for CSD training course")
        @Test
        void super_Early_bird_discount_For_CSD() {

            // Arrange
            /*
                Days before training course:	25
                type:							CSM
                Full price (no discounts):		4000
                -->
                SuperEarlyBird discount:		 -400
                Discounted price:				3600
             */

            // Arrange
            Item i1 = new Item(25, 50, 20, true, "CSD", 4000);
            Item[] items = new Item[]{i1};

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(3600, processedItems[0].price);
        }

        @DisplayName("Super Early Bird discount for CSM training course")
        @Test
        void super_Early_bird_discount_For_CSM() {

            // Arrange
            /*
                Days before training course:	25
                type:							CSM
                Full price (no discounts):		4000
                -->
                SuperEarlyBird discount:		 -500
                Discounted price:               3500
             */
            Item i1 = new Item(25, 50, 20, true, "CSM", 4000);
            Item[] items = new Item[]{i1};

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(3500, processedItems[0].price);
        }


        @DisplayName("Super Early Bird discount for CSPO training course")
        @Test
        void super_Early_bird_discount_For_CSPO() {

            // Arrange
            /*
                Days before training course:	25
                type:							CSPO
                Full price (no discounts):		4000
                -->
                SuperEarlyBird discount:		 -400
                Discounted price:				3600
             */
            Item i1 = new Item(32, 50, 20, true, "CSPO", 4000);
            Item[] items = new Item[]{i1};
            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(3600, processedItems[0].price);
        }

    }

}
