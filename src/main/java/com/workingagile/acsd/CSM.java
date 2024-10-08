package com.workingagile.acsd;

public class CSM extends TrainingCourse {

    public CSM(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, int fullPrice) {
        super(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online, fullPrice);
    }

    public void updateCurrentPrice() {

        if (isProportionalEarlyBird()) {
            this.currentDiscountedPrice = this.fullPrice - (this.daysBeforeTrainingCourse * 20);
        }
        else if (isSuperEarlyBird()) {
            this.currentDiscountedPrice = this.fullPrice - 500;
        }

        if (this.currentDiscountedPrice < 1000) {
            this.currentDiscountedPrice = 1000;
        }

    }

    public String getDescription() {
        return "CSM";
    }
}
