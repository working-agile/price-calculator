package com.workingagile.acsd;

public class CSPO extends TrainingCourse {

    public CSPO(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, int fullPrice) {
        super(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online, fullPrice);
    }

    public void updateCurrentPrice() {
        if (isProportionalEarlyBird()) {
            this.currentDiscountedPrice = this.fullPrice - (this.daysBeforeTrainingCourse * 20);
        }
        else if (isSuperEarlyBird()) {
            this.currentDiscountedPrice = this.fullPrice - 400;
        }

        if (this.currentDiscountedPrice < 1200) {
            this.currentDiscountedPrice = 1200;
        }

    }

    public String getDescription() {
        return "CSPO";
    }

}
