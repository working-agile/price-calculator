package com.workingagile.acsd;

public class TrainingCourseRepository {

    private final TrainingCourse[] scheduledTrainingCourses;

    public TrainingCourseRepository(TrainingCourse[] trainingCourses) {
        scheduledTrainingCourses = trainingCourses;
    }

    public TrainingCourse[] getScheduledTrainingCourses() {
        return scheduledTrainingCourses.clone();
    }
}
