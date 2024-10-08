package com.workingagile.acsd;

public class CSD extends TrainingCourse {

    public CSD(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, int fullPrice) {
        super(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online, fullPrice);
    }

    public void updateCurrentPrice() {
        if (isProportionalEarlyBird()) {
            setCurrentDiscountedPrice(
                    getFullPrice() - (getDaysBeforeTrainingCourse() * 30));
        }
        else if (isSuperEarlyBird()) {
            setCurrentDiscountedPrice(getFullPrice() - 400);
        }

        if (getCurrentDiscountedPrice() < 900) {
            setCurrentDiscountedPrice(900);
        }
    }

    public String getDescription() {
        return "CSD";
    }

}
