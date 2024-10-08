package com.workingagile.acsd;

public class CSM extends TrainingCourse {

    public CSM(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, int fullPrice) {
        super(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online, fullPrice);
    }

    public void updateCurrentPrice() {

        if (isProportionalEarlyBird()) {
            setCurrentDiscountedPrice(getFullPrice() - (getDaysBeforeTrainingCourse() * 20));
        }
        else if (isSuperEarlyBird()) {
            setCurrentDiscountedPrice(getFullPrice() - 500);
        }

        if (getCurrentDiscountedPrice() < 1000) {
            setCurrentDiscountedPrice(1000);
        }

    }

    public String getDescription() {
        return "CSM";
    }
}
