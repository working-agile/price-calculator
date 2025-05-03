package com.workingagile.acsd;

import com.workingagile.acsd.backend.TrainingCourseService;
import com.workingagile.acsd.backend.TrainingCourse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;

public class UnitTests {

    @Disabled
    @Test
    public void some_unit_test() {

        // Arrange
        TrainingCourseService processor = new TrainingCourseService();
        // TODO insert data

        // Act - call the function
        processor.calculateCurrentPricesOfTrainingCourses(false);

        // Assert
        List<TrainingCourse> actualResult = processor.getScheduledTrainingCourses();
        // TODO compare with the expected result

    }


}
