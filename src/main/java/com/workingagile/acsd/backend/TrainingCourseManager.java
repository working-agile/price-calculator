package com.workingagile.acsd.backend;

import java.util.ArrayList;
import java.util.List;

public class TrainingCourseManager {


    private int remainingSalesTarget;

    private ArrayList<TrainingCourse> scheduledTrainingCourses;

    public int getRemainingSalesTarget() {
        return remainingSalesTarget;
    }

    public ArrayList<TrainingCourse> getScheduledTrainingCourses() {
        return scheduledTrainingCourses;
    }


    public ArrayList<TrainingCourse> processTrainingCourses(ArrayList<TrainingCourse> trainingCourses, boolean advanceDay) {

        remainingSalesTarget = 0;

        ArrayList<TrainingCourse> processedTrainingCourses = new ArrayList<>();

        for (TrainingCourse trainingCourse : trainingCourses) {
            processItem(advanceDay, trainingCourse, processedTrainingCourses);
        }

        scheduledTrainingCourses = processedTrainingCourses;

        return processedTrainingCourses;
    }


    private void processItem(boolean advanceDay, TrainingCourse trainingCourse, List<TrainingCourse> processedTrainingCourses) {
        if (!(trainingCourse.daysBeforeTrainingCourse < 0 || (advanceDay && trainingCourse.daysBeforeTrainingCourse == 0))) {

            processedTrainingCourses.add(trainingCourse);

            if (advanceDay) {
                trainingCourse.daysBeforeTrainingCourse--;
            }

            if (isFullPrice(trainingCourse)) {
                trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice;
            }
            else if (isProportionalEarlyBird(trainingCourse)) {

                if (trainingCourse.type.equals("CSD")) {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - (trainingCourse.daysBeforeTrainingCourse * 30);
                } else {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - (trainingCourse.daysBeforeTrainingCourse * 20);
                }
            }
            else if (isSuperEarlyBird(trainingCourse)) {

                if (trainingCourse.type.equals("CSM")) {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - 500;
                } else {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - 400;
                }
            }

            if (isUnderMinimumPriceCSD(trainingCourse)) {
                trainingCourse.currentDiscountedPrice = 900;
            } else if (isUnderMinimumPriceCSM(trainingCourse)) {
                trainingCourse.currentDiscountedPrice = 1000;
            } else if (isUnderMinimumPriceCSPO(trainingCourse)) {
                trainingCourse.currentDiscountedPrice = 1200;
            }

            remainingSalesTarget += (trainingCourse.remainingAvailableSeats * trainingCourse.currentDiscountedPrice);

        }
    }

    private static boolean isUnderMinimumPriceCSPO(TrainingCourse trainingCourse) {
        return trainingCourse.type.equals("CSPO") && trainingCourse.currentDiscountedPrice < 1200;
    }

    private static boolean isUnderMinimumPriceCSM(TrainingCourse trainingCourse) {
        return trainingCourse.type.equals("CSM") && trainingCourse.currentDiscountedPrice < 1000;
    }

    private static boolean isUnderMinimumPriceCSD(TrainingCourse trainingCourse) {
        return trainingCourse.type.equals("CSD") && trainingCourse.currentDiscountedPrice < 900;
    }

    private static boolean isSuperEarlyBird(TrainingCourse trainingCourse) {
        return trainingCourse.daysBeforeTrainingCourse > 10;
    }

    private static boolean isProportionalEarlyBird(TrainingCourse trainingCourse) {
        return trainingCourse.daysBeforeTrainingCourse <= 10;
    }

    private static boolean isFullPrice(TrainingCourse trainingCourse) {
        return trainingCourse.daysBeforeTrainingCourse <= 1 || (trainingCourse.remainingAvailableSeats < 3 && trainingCourse.daysBeforeTrainingCourse <= 5);
    }

}
