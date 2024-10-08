package com.workingagile.acsd;

public class PriceCalculator {

    public void updateCurrentPrices(TrainingCourse[] scheduledTrainingCourses) {

        for (TrainingCourse trainingCourse: scheduledTrainingCourses) {

            if (fullPricePolicyApplies(trainingCourse)) {
                trainingCourse.setCurrentDiscountedPrice(trainingCourse.getFullPrice());
            }
            else {
                trainingCourse.updateCurrentPrice();
            }

        }
    }

    private boolean fullPricePolicyApplies(TrainingCourse trainingCourse) {
        return trainingCourse.getDaysBeforeTrainingCourse() <= 1 ||
                (trainingCourse.getRemainingAvailableSeats() < 3 && trainingCourse.getDaysBeforeTrainingCourse() <= 5);
    }

}
