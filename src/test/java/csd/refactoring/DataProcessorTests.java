package csd.refactoring;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DataProcessorTests {

    @Test
    void test() {

        Item i1 = new Item(15, 30, 10, true, "CSD", 3000);
        Item i2 = new Item(9, 30, 9, true, "CSPO", 4000);
        Item i3 = new Item(8, 30, 4, true, "CSM", 3000);

        Item[] items = new Item[]{i1, i2, i3};

        items = DataProcessor.processData(items);

        for (int i = 0; i < items.length; i++) {
            System.out.println("Training course price " + items[i].full + " with discount: " + items[i].price + " days left: " + items[i].days);
        }

        System.out.println("Total amount still to sell: " + DataProcessor.value);
    }

}

