package com.workingagile.acsd;

public class ODSF extends TrainingCourse {

    public ODSF(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, int fullPrice) {
        super(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online, fullPrice);
    }

    public void updateCurrentPrice() {
    }

    public String getDescription() {
        return "OD-SF";
    }

    public void decreaseDaysBeforeTrainingCourse() {
        throw new RuntimeException("ODSF don't have a scheduled date!");
    }

}
