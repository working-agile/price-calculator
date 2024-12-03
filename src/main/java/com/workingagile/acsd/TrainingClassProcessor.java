package com.workingagile.acsd;

import java.util.ArrayList;
import java.util.List;

public class TrainingClassProcessor {

    int salesValue;

    public ArrayList<Item> processTrainingCourses(boolean next, List<Item> currentTrainingCourses) {
        salesValue = 0;
        ArrayList<Item> newList = new ArrayList<>();
        for (Item currentItem: currentTrainingCourses) {
            processTrainingCourse(next, currentItem, newList);
        }

        return newList;
    }

    private void processTrainingCourse(boolean next, Item item, ArrayList<Item> newList) {
        if (!(item.days < 0 || (next && item.days == 0))) {

            newList.add(item);

            if (next) {
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

            salesValue = salesValue + (item.avail * item.curr);

        }
    }


}
