package com.workingagile.acsd.specs;

import com.workingagile.acsd.backend.Item;
import com.workingagile.acsd.backend.ItemProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
                Current date:   10. January 2024

                Days before training course:	11
                type:							CSD
                Full price (no discounts):		4000
                SuperEarlyBird discount:		-400
                -->
                discounted price:				3600

             */
            Item trainingCourse = new Item(1,"21 January 2024",11, 50, 20,"CSD", 4000, 4000);
            ArrayList<Item> courses = new ArrayList<>();
            courses.add(trainingCourse);
            ItemProcessor processor = new ItemProcessor();

            // Act
            List<Item> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(3600, updatedTrainingCourses.get(0).curr);
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
            Item trainingCourse = new Item(1,"10 January 2024",25, 50, 20, "CSD", 4000, 4000);
            ArrayList<Item> courses = new ArrayList<>();
            courses.add(trainingCourse);
            ItemProcessor processor = new ItemProcessor();

            // Act
            List<Item> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(3600, updatedTrainingCourses.get(0).curr);
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
            Item trainingCourse = new Item(1,"10 January 2024",25, 50, 20, "CSM", 4000, 4000);
            ArrayList<Item> courses = new ArrayList<>();
            courses.add(trainingCourse);
            ItemProcessor processor = new ItemProcessor();

            // Act
            List<Item> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(3500, updatedTrainingCourses.get(0).curr);

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
            Item trainingCourse = new Item(1,"10 January 2024",32, 50, 20, "CSPO", 4000, 4000);
            ArrayList<Item> courses = new ArrayList<>();
            courses.add(trainingCourse);
            ItemProcessor processor = new ItemProcessor();

            // Act
            List<Item> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(3600, updatedTrainingCourses.get(0).curr);
        }

    }

}
