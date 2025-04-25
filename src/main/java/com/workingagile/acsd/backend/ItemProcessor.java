package com.workingagile.acsd.backend;

import java.util.ArrayList;
import java.util.List;

public class ItemProcessor {


    private int salesValue;

    private ArrayList<Item> list;

    public int getSalesValue() {
        return salesValue;
    }

    public ArrayList<Item> getList() {
        return list;
    }


    public ArrayList<Item> processItems(ArrayList<Item> items, boolean advanceDay) {

        salesValue = 0;

        ArrayList<Item> processedItems = new ArrayList<>();

        for (Item item: items) {
            processItem(advanceDay, item, processedItems);
        }

        list = processedItems;

        return processedItems;
    }


    private void processItem(boolean advanceDay, Item item, List<Item> newList) {
        if (!(item.days < 0 || (advanceDay && item.days == 0))) {

            newList.add(item);

            if (advanceDay) {
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
