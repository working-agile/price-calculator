package com.workingagile.acsd.backend;

import java.util.ArrayList;
import java.util.List;

public class ItemProcessor {


    private int salesValue;

    private ArrayList<TrainingCourse> list;

    public int getSalesValue() {
        return salesValue;
    }

    public ArrayList<TrainingCourse> getList() {
        return list;
    }


    public ArrayList<TrainingCourse> processItems(ArrayList<TrainingCourse> trainingCourses, boolean advanceDay) {

        salesValue = 0;

        ArrayList<TrainingCourse> processedTrainingCourses = new ArrayList<>();

        for (TrainingCourse trainingCourse : trainingCourses) {
            processItem(advanceDay, trainingCourse, processedTrainingCourses);
        }

        list = processedTrainingCourses;

        return processedTrainingCourses;
    }


    private void processItem(boolean advanceDay, TrainingCourse trainingCourse, List<TrainingCourse> newList) {
        if (!(trainingCourse.days < 0 || (advanceDay && trainingCourse.days == 0))) {

            newList.add(trainingCourse);

            if (advanceDay) {
                trainingCourse.days--;
            }

            if (trainingCourse.days <= 10) {

                if (trainingCourse.days <= 1 || (trainingCourse.avail < 3 && trainingCourse.days <= 5)) {
                    trainingCourse.curr = trainingCourse.full;
                } else {
                    if (trainingCourse.type.equals("CSD")) {
                        trainingCourse.curr = trainingCourse.full - (trainingCourse.days * 30);
                    } else {
                        trainingCourse.curr = trainingCourse.full - (trainingCourse.days * 20);
                    }
                }

            } else if (trainingCourse.days > 10) {

                if (trainingCourse.days <= 1 || (trainingCourse.avail < 3 && trainingCourse.days <= 5)) {
                    trainingCourse.curr = trainingCourse.full;
                } else {
                    if (trainingCourse.type.equals("CSM")) {
                        trainingCourse.curr = trainingCourse.full - 500;
                    } else {
                        trainingCourse.curr = trainingCourse.full - 400;
                    }
                }
            }

            if (trainingCourse.type.equals("CSD") && trainingCourse.curr < 900) {
                trainingCourse.curr = 900;
            } else if (trainingCourse.type.equals("CSM") && trainingCourse.curr < 1000) {
                trainingCourse.curr = 1000;
            } else if (trainingCourse.type.equals("CSPO") && trainingCourse.curr < 1200) {
                trainingCourse.curr = 1200;
            }

            salesValue += (trainingCourse.avail * trainingCourse.curr);

        }
    }

}
