package com.workingagile.acsd;

import com.workingagile.acsd.backend.DataProcessor;
import com.workingagile.acsd.backend.TrainingCourse;

import static com.workingagile.acsd.backend.persistence.ECommercePersistence.bootingECommerceSystem;

public class ECommerceClientApplication {

    public static void main(String[] args){

        System.out.println("Booting the system...");
        bootingECommerceSystem();


        System.out.println("Simulating operations...");

        System.out.println("------------------------------------------------------------------");
        System.out.println("   Calculate the current prices...");
        System.out.println("------------------------------------------------------------------");

        DataProcessor processor = new DataProcessor();
        processor.calculateData(false /* don't change the date */);
        printReport(processor);

        System.out.println("------------------------------------------------------------------");
        System.out.println("   Job signals a new day has started, prices need to be updated...");
        System.out.println("------------------------------------------------------------------");

        processor.calculateData(true /* move to next day */);
        printReport(processor);

    }

    private static void printReport(DataProcessor processor) {
        System.out.println("Current prices of scheduled training courses:");
        System.out.println("---------------------------------------------");
        for (TrainingCourse trainingCourse : processor.getList()) {
            System.out.println("Type: " + trainingCourse.type);
            System.out.println("When: " + trainingCourse.scheduledDate);
            System.out.println("Remaining days before training course: " + trainingCourse.daysBeforeTrainingCourse);
            System.out.println("Full Price: " + trainingCourse.fullPrice);
            System.out.println("Current price: " + trainingCourse.currentDiscountedPrice);
            System.out.println("Number of seats: " + trainingCourse.totalNumberOfSeats);
            System.out.println("Remaining available seats: " + trainingCourse.remainingAvailableSeats);
            System.out.println();
        }
        System.out.println("Remaining total sales target: " + processor.getSalesValue());
    }


}
