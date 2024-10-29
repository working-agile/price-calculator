package com.workingagile.acsd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OnDemandSFTests {


    @DisplayName("On demand SF training (OD-SF) courses can be created")
    @Test
    void the_training_course_factory_can_create_ODSF_training_courses() {

        TrainingCourse trainingCourse = null;
        // Act
        try {
            trainingCourse = TrainingCourseFactory
                    .createTrainingCourse("OD-SF", "10 January 2024", 10,
                            20, 19, true, 3500);
        } catch (IllegalArgumentException e) {
            fail("The factory should be able to create a OD-SF training class.");
        }
        // Assert
        assertNotNull(trainingCourse);
        assertEquals("OD-SF", trainingCourse.getDescription());

    }






}