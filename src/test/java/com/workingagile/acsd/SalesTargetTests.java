package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesTargetTests {

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
            TrainingCourse i1 = new TrainingCourse("10 January 2024",30, 50, 5, true, "CSD", 4000);
            /*
                Number of seats:				2
                Days before training course:	9
                Type:							CSD
                Full price:						4000
                Super Early bird discount:		9*30
                Discounted price:				3500
             */
            TrainingCourse i2 = new TrainingCourse("10 January 2024",9, 50, 2, true, "CSD", 4000);
            TrainingCourse[] trainingCourses = new TrainingCourse[]{i1, i2};
            PriceCalculator_and_SalesTargetCalculator_and_DateMover processor = new PriceCalculator_and_SalesTargetCalculator_and_DateMover(trainingCourses);

            // Act
            processor.moveToNextDayBeforeTrainingCourse_updateCurrentPricesOfTrainingCourses_updateSalesTarget(false);

            // Assert
            int expectedRemainingSalesTarget = (3600 * 5) + (4000-9*30) * 2;
            assertEquals(expectedRemainingSalesTarget, processor.getRemainingSalesTarget());

        }
    }

}
