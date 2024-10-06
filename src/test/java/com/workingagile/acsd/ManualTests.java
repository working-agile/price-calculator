package com.workingagile.acsd;

import org.junit.jupiter.api.Test;

public class ManualTests {

    @Test
    void manual() {

        System.out.println("Today is: 1. January 2025");
        System.out.println("Registering training courses:");

        TrainingCourse i1 = new TrainingCourse("10 January 2025",10, 30, 10, true, "CSD", 3000);
        TrainingCourse i2 = new TrainingCourse("9 January 2025",9, 30, 9, true, "CSPO", 4000);
        TrainingCourse i3 = new TrainingCourse("20 January 2025", 20, 30, 20, false, "CSM", 3000);
        TrainingCourse[] list = new TrainingCourse[] {i1, i2, i3};

        DataProcessor.scheduledTrainingCourses = list;

        DataProcessor.calculateData(false);

        for (int i = 0; i < list.length; i++) {
            System.out.println("------------------------------");
            System.out.println("Training course");
            System.out.println("Type: " + list[i].type);
            System.out.println("When: " + list[i].scheduledDate);
            System.out.println("Remaining days before training course: " + list[i].daysBeforeTrainingCourse);
            System.out.println("Online: " + list[i].online);
            System.out.println("Full Price: " + list[i].fullPrice);
            System.out.println("Current price: " + list[i].currentDiscountedPrice);
            System.out.println("Number of seats: " + list[i].totalNumberOfSeats);
            System.out.println("Remaining available seats: " + list[i].remainingAvailableSeats);
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + DataProcessor.remainingSalesTarget);
        System.out.println("-----------------------------------------------------");

        System.out.println("\n\nMove to next day: 2. January");

        DataProcessor.calculateData(true);

        System.out.println("Registered training courses");

        for (int i = 0; i < list.length; i++) {
            System.out.println("------------------------------");
            System.out.println("Training course");
            System.out.println("Type: " + list[i].type);
            System.out.println("When: " + list[i].scheduledDate);
            System.out.println("Remaining days before training course: " + list[i].daysBeforeTrainingCourse);
            System.out.println("Online: " + list[i].online);
            System.out.println("Full Price: " + list[i].fullPrice);
            System.out.println("Current price: " + list[i].currentDiscountedPrice);
            System.out.println("Number of seats: " + list[i].totalNumberOfSeats);
            System.out.println("Remaining available seats: " + list[i].remainingAvailableSeats);
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + DataProcessor.remainingSalesTarget);
        System.out.println("-----------------------------------------------------");
    }

}
