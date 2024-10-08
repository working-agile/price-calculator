package com.workingagile.acsd;

public class CSD extends TrainingCourse {
    public CSD(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, int fullPrice) {
        super(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online, "CSD", fullPrice);
    }
}
