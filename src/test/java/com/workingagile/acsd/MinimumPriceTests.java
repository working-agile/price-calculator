package com.workingagile.acsd;

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
                Minimum price:                  900
             */
            TrainingCourse i1 = new TrainingCourse("10 January 2024",25, 50, 20, true, "CSD", 1200);
            TrainingCourse[] trainingCourses = new TrainingCourse[] { i1 };
            TrainingCourseService processor = new TrainingCourseService(trainingCourses);

            // Act
            processor.moveToNextDayBeforeTrainingCourse_updateCurrentPricesOfTrainingCourses_updateSalesTarget(false);

            // Assert
            assertEquals(900, processor.getScheduledTrainingCourses()[0].currentDiscountedPrice);
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
                Minimum price:                  1000
             */
            TrainingCourse i1 = new TrainingCourse("10 January 2024",25, 50, 20, true, "CSM", 1500);
            TrainingCourse[] trainingCourses = new TrainingCourse[] { i1 };
            TrainingCourseService trainingCourseService = new TrainingCourseService(trainingCourses);

            // Act
            trainingCourseService.updateCurrentPrices();

            // Assert
            assertEquals(1000, trainingCourseService.getScheduledTrainingCourses()[0].currentDiscountedPrice);
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
                Minimum price:                  1200
             */
            TrainingCourse i1 = new TrainingCourse("10 January 2024",25, 50, 20, true, "CSPO", 1500);
            TrainingCourse[] trainingCourses = new TrainingCourse[] { i1 };
            TrainingCourseService trainingCourseService = new TrainingCourseService(trainingCourses);

            // Act
            trainingCourseService.updateCurrentPrices();

            // Assert
            assertEquals(1200, trainingCourseService.getScheduledTrainingCourses()[0].currentDiscountedPrice);
        }

    }

}
