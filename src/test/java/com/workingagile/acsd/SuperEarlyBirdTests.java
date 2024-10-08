package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
            TrainingCourse i1 = new CSD("10 January 2024",11, 50, 20, true, 4000);
            TrainingCourse[] trainingCourses = new TrainingCourse[]{i1};
            TrainingCourseService trainingCourseService = new TrainingCourseService(trainingCourses);

            // Act
            trainingCourseService.updateCurrentPrices();

            // Assert
            assertEquals(3600, trainingCourseService.getScheduledTrainingCourses()[0]
                    .getCurrentDiscountedPrice());
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
            TrainingCourse i1 = new CSD("10 January 2024",25, 50, 20, true, 4000);
            TrainingCourse[] trainingCourses = new TrainingCourse[]{i1};
            TrainingCourseService trainingCourseService = new TrainingCourseService(trainingCourses);

            // Act
            trainingCourseService.updateCurrentPrices();

            // Assert
            assertEquals(3600, trainingCourseService.getScheduledTrainingCourses()[0]
                    .getCurrentDiscountedPrice());
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
            TrainingCourse i1 = new CSM("10 January 2024",25, 50, 20, true, 4000);
            TrainingCourse[] trainingCourses = new TrainingCourse[]{i1};
            TrainingCourseService trainingCourseService = new TrainingCourseService(trainingCourses);

            // Act
            trainingCourseService.updateCurrentPrices();

            // Assert
            assertEquals(3500, trainingCourseService.getScheduledTrainingCourses()[0]
                    .getCurrentDiscountedPrice());
        }


        @DisplayName("Super Early Bird discount for CSPO training course")
        @Test
        void super_Early_bird_discount_For_CSPO() {

            // Arrange
            /*
                Days before training course:	32
                type:							CSPO
                Full price (no discounts):		4000
                -->
                SuperEarlyBird discount:		 -400
                Discounted price:				3600
             */
            TrainingCourse i1 = new CSPO("10 January 2024",32, 50, 20, true, 4000);
            TrainingCourse[] trainingCourses = new TrainingCourse[]{i1};
            TrainingCourseService trainingCourseService = new TrainingCourseService(trainingCourses);

            // Act
            trainingCourseService.updateCurrentPrices();

            // Assert
            assertEquals(3600, trainingCourseService.getScheduledTrainingCourses()[0]
                    .getCurrentDiscountedPrice());
        }

    }

}
