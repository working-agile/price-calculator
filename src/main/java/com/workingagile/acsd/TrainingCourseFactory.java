package com.workingagile.acsd;

public class TrainingCourseFactory {

    public static TrainingCourse createTrainingCourse(String type, String scheduledDate, int daysBeforeTrainingCourse,
                                                      int totalNumberOfSeats, int remainingAvailableSeats,
                                                      Boolean online, int fullPrice) {
        if (type.equals("CSD")) {
            return new CSD(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online,
                    fullPrice);
        } else if (type.equals("CSM")) {
            return new CSM(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online,
                    fullPrice);
        } else if (type.equals("CSPO")) {
            return new CSPO(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online,
                    fullPrice);
        }  else if (type.equals("OD-SF")) {
            return new ODSF(scheduledDate, daysBeforeTrainingCourse, totalNumberOfSeats, remainingAvailableSeats, online,
                    fullPrice);
        } else
            throw new IllegalArgumentException("Unknown training course type");

    }

}
