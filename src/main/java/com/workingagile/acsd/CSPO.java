package com.workingagile.acsd;

public class CSPO extends TrainingCourse {

    public CSPO(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, int fullPrice) {
        super(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online, fullPrice);
    }

    public void updateCurrentPrice() {
        if (isProportionalEarlyBird()) {
            setCurrentDiscountedPrice(getFullPrice() - (getDaysBeforeTrainingCourse() * 20));
        }
        else if (isSuperEarlyBird()) {
            setCurrentDiscountedPrice(getFullPrice() - 400);
        }

        if (getCurrentDiscountedPrice() < 1200) {
            setCurrentDiscountedPrice(1200);
        }

    }

    public String getDescription() {
        return "CSPO";
    }

}
