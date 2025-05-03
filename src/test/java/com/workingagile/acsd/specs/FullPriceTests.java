package com.workingagile.acsd.specs;

import com.workingagile.acsd.backend.TrainingCourse;
import com.workingagile.acsd.backend.ItemProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

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
            ItemProcessor processor = new ItemProcessor();
            TrainingCourse trainingCourse = new TrainingCourse(1,"10 January 2024",0, 50, 20, trainingCourseType, 4000, 4000);
            ArrayList<TrainingCourse> courses = new ArrayList<>();
            courses.add(trainingCourse);

            // Act
            List<TrainingCourse> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(4000, updatedTrainingCourses.get(0).currentDiscountedPrice, "expecting the full price");
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
            ItemProcessor processor = new ItemProcessor();
            TrainingCourse trainingCourse = new TrainingCourse(1, "10 January 2024",1, 50, 20, trainingCourseType, 4000, 4000);
            ArrayList<TrainingCourse> courses = new ArrayList<>();
            courses.add(trainingCourse);

            // Act
            List<TrainingCourse> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(4000, updatedTrainingCourses.get(0).currentDiscountedPrice, "expecting the full price");
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
            ItemProcessor processor = new ItemProcessor();
            TrainingCourse trainingCourse = new TrainingCourse(1,"10 January 2024",5, 50, 2, trainingCourseType, 4000, 4000);
            ArrayList<TrainingCourse> courses = new ArrayList<>();
            courses.add(trainingCourse);

            // Act
            List<TrainingCourse> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(4000, updatedTrainingCourses.get(0).currentDiscountedPrice, "expecting the full price");
        }

    }


}
