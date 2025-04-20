package com.workingagile.acsd.backend.domain;

import java.util.ArrayList;
import java.util.List;

public class TrainingClassProcessor {

    private int salesValue;

    private List<Item> list;

    public int getSalesValue() {
        return salesValue;
    }

    public List<Item> getList() {
        return list;
    }

    public void processTrainingCourses(List<Item> trainingCourses, boolean moveToNextDay) {

        list = new ArrayList<>();
        salesValue = 0;

        for (Item item: trainingCourses) {
            processTrainingCourse(moveToNextDay, item);
            list.add(item);
        }
    }

    private void processTrainingCourse(boolean moveToNextDay, Item item) {
        if (!(item.days < 0 || (moveToNextDay && item.days == 0))) {

            if (moveToNextDay) {
                item.days--;
            }

            if (item.days <= 10) {

                if (item.days <= 1 || (item.avail < 3 && item.days <= 5)) {
                    item.curr = item.full;
                } else {
                    if (item.type.equals("CSD")) {
                        item.curr = item.full - (item.days * 30);
                    } else {
                        item.curr = item.full - (item.days * 20);
                    }
                }

            } else if (item.days > 10) {

                if (item.days <= 1 || (item.avail < 3 && item.days <= 5)) {
                    item.curr = item.full;
                } else {
                    if (item.type.equals("CSM")) {
                        item.curr = item.full - 500;
                    } else {
                        item.curr = item.full - 400;
                    }
                }
            }

            if (item.type.equals("CSD") && item.curr < 900) {
                item.curr = 900;
            } else if (item.type.equals("CSM") && item.curr < 1000) {
                item.curr = 1000;
            } else if (item.type.equals("CSPO") && item.curr < 1200) {
                item.curr = 1200;
            }

            salesValue += (item.avail * item.curr);

        }


    }

}
