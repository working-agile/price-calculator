package com.workingagile.acsd;

public class CSD extends TrainingCourse {

    public CSD(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, int fullPrice) {
        super(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online, fullPrice);
    }

    public void updateCurrentPrice() {
        if (isProportionalEarlyBird()) {
            this.currentDiscountedPrice = this.fullPrice - (this.daysBeforeTrainingCourse * 30);
        }
        else if (isSuperEarlyBird()) {
            this.currentDiscountedPrice = this.fullPrice - 400;
        }

        if (this.currentDiscountedPrice < 900) {
            this.currentDiscountedPrice = 900;
        }
    }


}
