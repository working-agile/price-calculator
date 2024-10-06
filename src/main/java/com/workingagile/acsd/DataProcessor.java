package com.workingagile.acsd;

public class DataProcessor {

	public static int remainingSalesTarget;

	public static TrainingCourse[] scheduledTrainingCourses;

	public static void calculateData(boolean moveToNextDay) {

		remainingSalesTarget = 0;

		for (int i = 0; i < scheduledTrainingCourses.length; i++) {

			if (moveToNextDay && scheduledTrainingCourses[i].daysBeforeTrainingCourse > 0) {
				scheduledTrainingCourses[i].daysBeforeTrainingCourse--;
			}

			if (scheduledTrainingCourses[i].daysBeforeTrainingCourse <= 10) {

				if (scheduledTrainingCourses[i].daysBeforeTrainingCourse <= 1 || (scheduledTrainingCourses[i].remainingAvailableSeats < 3 && scheduledTrainingCourses[i].daysBeforeTrainingCourse <= 5)) {
					scheduledTrainingCourses[i].currentDiscountedPrice = scheduledTrainingCourses[i].fullPrice;
				} else {
					if (scheduledTrainingCourses[i].type.equals("CSD")) {
						scheduledTrainingCourses[i].currentDiscountedPrice = scheduledTrainingCourses[i].fullPrice - (scheduledTrainingCourses[i].daysBeforeTrainingCourse * 30);
					} else {
						scheduledTrainingCourses[i].currentDiscountedPrice = scheduledTrainingCourses[i].fullPrice - (scheduledTrainingCourses[i].daysBeforeTrainingCourse * 20);
					}
				}

			} else if (scheduledTrainingCourses[i].daysBeforeTrainingCourse > 10) {

				if (scheduledTrainingCourses[i].daysBeforeTrainingCourse <= 1 || (scheduledTrainingCourses[i].remainingAvailableSeats < 3 && scheduledTrainingCourses[i].daysBeforeTrainingCourse <= 5)) {
					scheduledTrainingCourses[i].currentDiscountedPrice = scheduledTrainingCourses[i].fullPrice;
				} else {
					if (scheduledTrainingCourses[i].type.equals("CSM")) {
						scheduledTrainingCourses[i].currentDiscountedPrice = scheduledTrainingCourses[i].fullPrice - 500;
					} else {
						scheduledTrainingCourses[i].currentDiscountedPrice = scheduledTrainingCourses[i].fullPrice - 400;
					}
				}
			}

			if (scheduledTrainingCourses[i].type.equals("CSD") && scheduledTrainingCourses[i].currentDiscountedPrice < 900) {
				scheduledTrainingCourses[i].currentDiscountedPrice = 900;
			} else if (scheduledTrainingCourses[i].type.equals("CSM") && scheduledTrainingCourses[i].currentDiscountedPrice < 1000) {
				scheduledTrainingCourses[i].currentDiscountedPrice = 1000;
			} else if (scheduledTrainingCourses[i].type.equals("CSPO") && scheduledTrainingCourses[i].currentDiscountedPrice < 1200) {
				scheduledTrainingCourses[i].currentDiscountedPrice = 1200;
			}

			remainingSalesTarget += (scheduledTrainingCourses[i].remainingAvailableSeats * scheduledTrainingCourses[i].currentDiscountedPrice);

		}
	}
}
