package com.workingagile.acsd.specs;

import com.workingagile.acsd.backend.TrainingCourse;
import com.workingagile.acsd.backend.ItemProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesTargetTests {

    @Nested
    @DisplayName("Business rule: Total remaining sales target")
    class TotalRemainingSalesTarget {

        @DisplayName("Should calculate the total remaining sales")
        @Test
        void should_calculate_total_value_of_remaining_training_courses() {

            /*
                Current date:       10 January 2024

                Number of seats:				5
                Days before training course:	41
                Type:							CSD
                Full price:						4000
                Super early bird discount:		 400
                Discounted price:				3600
             */
            TrainingCourse csd1 = new TrainingCourse(1,"20 February 2024",41, 50, 5, "CSD", 4000, 4000);
            /*
                Number of seats:				2
                Days before training course:	9
                Type:							CSD
                Full price:						4000
                Proportional discount:		    9*30
                Discounted price:				3500
             */
            TrainingCourse csd2 = new TrainingCourse(2,"19 January 2024",9, 50, 2, "CSD", 4000, 4000);
            ArrayList<TrainingCourse> courses = new ArrayList<>();
            courses.add(csd1);
            courses.add(csd2);
            ItemProcessor processor = new ItemProcessor();

            // Act
            List<TrainingCourse> updatedTrainingCourses = processor.processItems(courses, false /* just recalculate the current discounted price */);

            // Assert
            int expectedRemainingSalesTarget = (3600 * 5) + (4000-9*30) * 2;

            assertEquals(expectedRemainingSalesTarget, processor.getSalesValue());

        }
    }

}
