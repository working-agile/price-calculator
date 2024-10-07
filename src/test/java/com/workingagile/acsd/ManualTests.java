package com.workingagile.acsd;

import org.junit.jupiter.api.Test;

public class ManualTests {

    @Test
    void manual() {

        System.out.println("Today is: 1. January 2025");
        System.out.println("Registering training courses:");

        TrainingCourse i1 = new TrainingCourse("10 January 2025",10, 30, 8, true, "CSD", 3000);
        TrainingCourse i2 = new TrainingCourse("9 January 2025",9, 30, 7, true, "CSPO", 4000);
        TrainingCourse i3 = new TrainingCourse("20 January 2025", 20, 30, 27, false, "CSM", 3000);
        TrainingCourse[] trainingCourses = new TrainingCourse[] {i1, i2, i3};

        PriceCalculator_and_SalesTargetCalculator_and_DateMover processor = new PriceCalculator_and_SalesTargetCalculator_and_DateMover(trainingCourses);

        processor.moveToNextDayBeforeTrainingCourse_updateCurrentPricesOfTrainingCourses_updateSalesTarget(false);


        for (TrainingCourse trainingCourse: processor.getScheduledTrainingCourses()) {
            System.out.println("------------------------------");
            System.out.println("Training course");
            System.out.println("Type: " + trainingCourse.type);
            System.out.println("When: " + trainingCourse.scheduledDate);
            System.out.println("Remaining days before training course: " + trainingCourse.daysBeforeTrainingCourse);
            System.out.println("Online: " + trainingCourse.online);
            System.out.println("Full Price: " + trainingCourse.fullPrice);
            System.out.println("Current price: " + trainingCourse.currentDiscountedPrice);
            System.out.println("Number of seats: " + trainingCourse.totalNumberOfSeats);
            System.out.println("Remaining available seats: " + trainingCourse.remainingAvailableSeats);
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + processor.getRemainingSalesTarget());
        System.out.println("-----------------------------------------------------");

        System.out.println("\n\nMove to next day: 2. January");

        processor.moveToNextDayBeforeTrainingCourse_updateCurrentPricesOfTrainingCourses_updateSalesTarget(true);

        System.out.println("Registered training courses");

        for (TrainingCourse trainingCourse: processor.getScheduledTrainingCourses()) {
            System.out.println("------------------------------");
            System.out.println("Training course");
            System.out.println("Type: " + trainingCourse.type);
            System.out.println("When: " + trainingCourse.scheduledDate);
            System.out.println("Remaining days before training course: " + trainingCourse.daysBeforeTrainingCourse);
            System.out.println("Online: " + trainingCourse.online);
            System.out.println("Full Price: " + trainingCourse.fullPrice);
            System.out.println("Current price: " + trainingCourse.currentDiscountedPrice);
            System.out.println("Number of seats: " + trainingCourse.totalNumberOfSeats);
            System.out.println("Remaining available seats: " + trainingCourse.remainingAvailableSeats);
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + processor.getRemainingSalesTarget());
        System.out.println("-----------------------------------------------------");
    }

}
