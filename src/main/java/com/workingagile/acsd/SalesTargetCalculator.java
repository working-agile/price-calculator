package com.workingagile.acsd;

public class SalesTargetCalculator {

    private int remainingSalesTarget;

    public int getRemainingSalesTarget() {
        return remainingSalesTarget;
    }

    public void updateSalesTarget(TrainingCourse[] scheduledTrainingCourses) {
        remainingSalesTarget = 0;

        for (TrainingCourse trainingCourse: scheduledTrainingCourses) {
            remainingSalesTarget += (trainingCourse.remainingAvailableSeats * trainingCourse.currentDiscountedPrice);
        }
    }


}
