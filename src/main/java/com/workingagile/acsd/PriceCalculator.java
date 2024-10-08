package com.workingagile.acsd;

public class PriceCalculator {

    public void updateCurrentPrices(TrainingCourse[] scheduledTrainingCourses) {

        for (TrainingCourse trainingCourse: scheduledTrainingCourses) {

            if (fullPricePolicyApplies(trainingCourse)) {
                trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice;
            }
            else {
                trainingCourse.updateCurrentPrice();
            }

        }
    }

    private boolean fullPricePolicyApplies(TrainingCourse trainingCourse) {
        return trainingCourse.daysBeforeTrainingCourse <= 1 || (trainingCourse.remainingAvailableSeats < 3 && trainingCourse.daysBeforeTrainingCourse <= 5);
    }

}
