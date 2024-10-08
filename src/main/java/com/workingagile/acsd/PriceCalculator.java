package com.workingagile.acsd;

public class PriceCalculator {

    public void updateCurrentPrices(TrainingCourse[] scheduledTrainingCourses) {

        for (TrainingCourse trainingCourse: scheduledTrainingCourses) {

            if (fullPricePolicyApplies(trainingCourse)) {
                trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice;
            }

            else if (trainingCourse.type.equals("CSD")) {

                if (isProportionalEarlyBird(trainingCourse)) {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - (trainingCourse.daysBeforeTrainingCourse * 30);
                } else if (isSuperEarlyBird(trainingCourse)) {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - 400;
                }

                if (trainingCourse.currentDiscountedPrice < 900) {
                    trainingCourse.currentDiscountedPrice = 900;
                }

            } else if (trainingCourse.type.equals("CSM")) {

                if (isProportionalEarlyBird(trainingCourse)) {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - (trainingCourse.daysBeforeTrainingCourse * 20);
                }
                else if (isSuperEarlyBird(trainingCourse)) {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - 500;
                }

                if (trainingCourse.currentDiscountedPrice < 1000) {
                    trainingCourse.currentDiscountedPrice = 1000;
                }

            } else if (trainingCourse.type.equals("CSPO")) {

                if (isProportionalEarlyBird(trainingCourse)) {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - (trainingCourse.daysBeforeTrainingCourse * 20);
                }
                else if (isSuperEarlyBird(trainingCourse)) {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - 400;
                }

                if (trainingCourse.currentDiscountedPrice < 1200) {
                    trainingCourse.currentDiscountedPrice = 1200;
                }

            }

        }
    }


    private boolean fullPricePolicyApplies(TrainingCourse trainingCourse) {
        return trainingCourse.daysBeforeTrainingCourse <= 1 || (trainingCourse.remainingAvailableSeats < 3 && trainingCourse.daysBeforeTrainingCourse <= 5);
    }

    private boolean isSuperEarlyBird(TrainingCourse trainingCourse) {
        return trainingCourse.daysBeforeTrainingCourse > 10;
    }

    private boolean isProportionalEarlyBird(TrainingCourse trainingCourse) {
        return trainingCourse.daysBeforeTrainingCourse <= 10;
    }
}
