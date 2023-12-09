package csd.refactoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumPriceTests {

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

}
