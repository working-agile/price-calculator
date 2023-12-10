package csd.refactoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataProcessorTests {

    @Test
    void manual() {

        Item i1 = new Item(15, 30, 10, true, "CSD", 3000);
        Item i2 = new Item(9, 30, 9, true, "CSPO", 4000);
        Item i3 = new Item(8, 30, 4, true, "CSM", 3000);
        Item[] items = new Item[]{i1, i2, i3};

        Item[] processedItems = DataProcessor.processData(items);

        for (int i = 0; i < processedItems.length; i++) {
            System.out.println("Training course price " + processedItems[i].full + " with discount: " + processedItems[i].price + " days left: " + processedItems[i].days);
        }

        System.out.println("Total target still to sell: " + DataProcessor.value);
    }


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
            Item i1 = new Item(25+1, 50, 20, true, "CSD", 4000);
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
            Item i1 = new Item(25+1, 50, 20, true, "CSM", 4000);
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
            Item i1 = new Item(25+1, 50, 20, true, "CSPO", 4000);
            Item[] items = new Item[]{i1};
            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(3600, processedItems[0].price);
        }

    }


    @Nested
    @DisplayName("Business rule: Minimum prices are guaranteed")
    class MinimumPrices {

        @DisplayName("Super Early Bird discount overruled to guarantee minimum price for CSD")
        @Test
        void super_early_bird_discount_overruled_by_minimum_price_for_CSD() {

            // Arrange
            /*
                Days before training course:	25
                type:							CSD
                Full price (no discounts):		1200
                Minimum price CSD:				900
                -->
                SuperEarlyBird discount:		 -400
                Discounted price:               800
             */
            Item i1 = new Item(25+1, 50, 20, true, "CSD", 1200);
            Item[] items = new Item[] { i1 };
            int MINIMUM_PRICE_CSD = 900;

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(MINIMUM_PRICE_CSD, processedItems[0].price);
        }

        @DisplayName("Super Early Bird discount overruled to guarantee minimum price for CSM")
        @Test
        void super_early_bird_discount_overruled_by_minimum_price_for_CSM() {

            // Arrange
            /*
                Days before training course:	25
                type:							CSM
                Full price (no discounts):		1200
                Minimum price CSM:				1000
                -->
                SuperEarlyBird discount:		 -500
                Discounted price:               700
             */
            Item i1 = new Item(25+1, 50, 20, true, "CSM", 1500);
            Item[] items = new Item[] { i1 };
            int MINIMUM_PRICE_CSM = 1000;

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(MINIMUM_PRICE_CSM, processedItems[0].price);
        }

        @DisplayName("Super Early Bird discount overruled to guarantee minimum price for CSPO")
        @Test
        void super_early_bird_discount_overruled_by_minimum_price_for_CSPO() {

            // Arrange
            /*
                Days before training course:	25
                type:							CSPO
                Full price (no discounts):		1500
                Minimum price CSPO:				1200
                -->
                SuperEarlyBird discount:		-400
                Discounted price:               1100
             */
            Item i1 = new Item(25+1, 50, 20, true, "CSPO", 1500);
            Item[] items = new Item[] { i1 };
            int MINIMUM_PRICE_CSPO = 1200;

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(MINIMUM_PRICE_CSPO, processedItems[0].price);
        }

    }



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


        @DisplayName("Full prices 5 days prior to the training course if 3 or less seats left")
        @ParameterizedTest
        @ValueSource(strings = {"CSD", "CSPO", "CSM"})
        void should_have_full_price_5_days_prior_to_the_training_class_if_3_or_less_seats_left(String trainingCourseType) {

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



    @Nested
    @DisplayName("Business rule: Early Bird with proportional discount 10 days before the training course")
    class EarlyBirdProportionalDiscount {

        @DisplayName("Proportional discount when 6 days before the training course")
        @Test
        void should_apply_proportional_discount_from_day_6_for_CSD() {

            // Arrange
            /*
                Days before training course:	6
                Type:							CSD
                Full price:						4000
                Proportional discount:			6*30
                -->
                discounted price:		 		4000-(6*30) = 3820
             */
            Item i1 = new Item(6+1, 50, 25, true, "CSD", 4000);
            Item[] items = new Item[]{i1};

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(3820, processedItems[0].price, "proportional discount expected");
        }

        @DisplayName("Proportional discount when 5 days before the training course and enough seats available")
        @Test
        void should_apply_proportional_discount_day_5_for_CSD_when_enough_seats_available() {

            // Arrange
            /*
                Days before training course:	5
                Type:							CSD
                Full price:						4000
                Proportional discount:			5*30
                -->
                discounted:		 				4000-(5*30) = 3850
             */
            Item i1 = new Item(5+1, 50, 25, true, "CSD", 4000);
            Item[] items = new Item[]{i1};

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(3850, processedItems[0].price, "proportional discount expected when enough seats available");
        }



        @DisplayName("Proportional discount when 10 days before the training course")
        @ParameterizedTest
        @ValueSource(strings = {"CSPO", "CSM"})
        void should_apply_proportional_discount_for_CSM_and_CSPO(String trainingCourseType) {

            // Arrange
            /*
                Days before training course:	10
                Type:							CSPO e CSM
                Full price:						4000
                Proportional discount:			10*30
                -->
                discounted:		 				4000-(10*20) = 3800
             */
            Item i1 = new Item(10+1, 50, 25, true, trainingCourseType, 4000);
            Item[] items = new Item[]{i1};

            // Act
            Item[] processedItems = DataProcessor.processData(items);

            // Assert
            assertEquals(3800, processedItems[0].price, "should apply proportional discount - first day of the interval");
        }

    }




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
