package com.workingagile.acsd.specs;

import com.workingagile.acsd.backend.TrainingCourse;
import com.workingagile.acsd.backend.ItemProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EarlyBirdTests {


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
            ItemProcessor processor = new ItemProcessor();
            TrainingCourse csdTrainingCourse = new TrainingCourse(1, "10 January 2024",6, 50, 25, "CSD", 4000, 4000);
            ArrayList<TrainingCourse> courses = new ArrayList<>();
            courses.add(csdTrainingCourse);

            // Act
            List<TrainingCourse> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(3820, updatedTrainingCourses.get(0).currentDiscountedPrice, "proportional discount expected");
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
            ItemProcessor processor = new ItemProcessor();
            TrainingCourse csdTrainingCourse = new TrainingCourse(1,"10 January 2024",5, 50, 25, "CSD", 4000, 4000);
            ArrayList<TrainingCourse> courses = new ArrayList<>();
            courses.add(csdTrainingCourse);

            // Act
            List<TrainingCourse> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(3850, updatedTrainingCourses.get(0).currentDiscountedPrice, "proportional discount expected when enough seats available");
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
            ItemProcessor processor = new ItemProcessor();
            TrainingCourse trainingCourse = new TrainingCourse(1,"10 January 2024",10, 50, 25, trainingCourseType, 4000, 4000);
            ArrayList<TrainingCourse> courses = new ArrayList<>();
            courses.add(trainingCourse);

            // Act
            List<TrainingCourse> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            assertEquals(3800, updatedTrainingCourses.get(0).currentDiscountedPrice, "should apply proportional discount - first day of the interval");
        }

    }

}
