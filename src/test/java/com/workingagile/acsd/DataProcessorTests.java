package com.workingagile.acsd;

import org.junit.jupiter.api.Test;

public class DataProcessorTests {

    @Test
    void manual() {

        Item i1 = new Item(15, 30, 10, true, "CSD", 3000);
        Item i2 = new Item(9, 30, 9, true, "CSPO", 4000);
        Item i3 = new Item(8, 30, 4, true, "CSM", 3000);
        Item[] items = new Item[]{i1, i2, i3};

        System.out.println("Moving the date one day forward");
        Item[] processedItems = DataProcessor.processData(items);

        for (int i = 0; i < processedItems.length; i++) {
            System.out.println("Training course price " + processedItems[i].full + " with discount: " + processedItems[i].price + " days left: " + processedItems[i].days);
        }

        System.out.println("Total sales target remaining: " + DataProcessor.value);
    }

}
