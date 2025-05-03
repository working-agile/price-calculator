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


    private void processItem(boolean advanceDay, TrainingCourse trainingCourse, List<TrainingCourse> trainingCourses) {
        if (!(trainingCourse.daysBeforeTrainingCourse < 0 || (advanceDay && trainingCourse.daysBeforeTrainingCourse == 0))) {

            trainingCourses.add(trainingCourse);

            if (advanceDay) {
                trainingCourse.daysBeforeTrainingCourse--;
            }

            if (trainingCourse.daysBeforeTrainingCourse <= 10) {

                if (trainingCourse.daysBeforeTrainingCourse <= 1 || (trainingCourse.remainingAvailableSeats < 3 && trainingCourse.daysBeforeTrainingCourse <= 5)) {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice;
                } else {
                    if (trainingCourse.type.equals("CSD")) {
                        trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - (trainingCourse.daysBeforeTrainingCourse * 30);
                    } else {
                        trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - (trainingCourse.daysBeforeTrainingCourse * 20);
                    }
                }

            } else if (trainingCourse.daysBeforeTrainingCourse > 10) {

                if (trainingCourse.daysBeforeTrainingCourse <= 1 || (trainingCourse.remainingAvailableSeats < 3 && trainingCourse.daysBeforeTrainingCourse <= 5)) {
                    trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice;
                } else {
                    if (trainingCourse.type.equals("CSM")) {
                        trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - 500;
                    } else {
                        trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - 400;
                    }
                }
            }

            if (trainingCourse.type.equals("CSD") && trainingCourse.currentDiscountedPrice < 900) {
                trainingCourse.currentDiscountedPrice = 900;
            } else if (trainingCourse.type.equals("CSM") && trainingCourse.currentDiscountedPrice < 1000) {
                trainingCourse.currentDiscountedPrice = 1000;
            } else if (trainingCourse.type.equals("CSPO") && trainingCourse.currentDiscountedPrice < 1200) {
                trainingCourse.currentDiscountedPrice = 1200;
            }

            remainingSalesTarget += (trainingCourse.remainingAvailableSeats * trainingCourse.currentDiscountedPrice);

        }
    }

}
