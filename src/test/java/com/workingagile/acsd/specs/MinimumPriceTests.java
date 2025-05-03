package com.workingagile.acsd.specs;

import com.workingagile.acsd.backend.TrainingCourse;
import com.workingagile.acsd.backend.ItemProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
            ItemProcessor processor = new ItemProcessor();
            TrainingCourse trainingCourse = new TrainingCourse(1, "10 January 2024",25, 50, 20, "CSD", 1200, 1200);
            ArrayList<TrainingCourse> courses = new ArrayList<>();
            courses.add(trainingCourse);

            // Act
            List<TrainingCourse> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(900, updatedTrainingCourses.get(0).curr, "expecting the minimum price");
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
            ItemProcessor processor = new ItemProcessor();
            TrainingCourse trainingCourse = new TrainingCourse(1,"10 January 2024",25, 50, 20, "CSM", 1200, 1200);
            ArrayList<TrainingCourse> courses = new ArrayList<>();
            courses.add(trainingCourse);

            // Act
            List<TrainingCourse> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(1000, updatedTrainingCourses.get(0).curr, "expecting the minimum price");
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
            ItemProcessor processor = new ItemProcessor();
            TrainingCourse trainingCourse = new TrainingCourse(1,"10 January 2024",25, 50, 20, "CSPO", 1500, 1500);
            ArrayList<TrainingCourse> courses = new ArrayList<>();
            courses.add(trainingCourse);

            // Act
            List<TrainingCourse> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(1200, updatedTrainingCourses.get(0).curr, "expecting the minimum price");
        }

    }

}
