package com.workingagile.acsd;

public class DataProcessor {

	public static int remainingSalesTarget;

	public static TrainingCourse[] scheduledTrainingCourses;

	public static void decreaseDaysBeforeTrainingCourse_updateCurrentPricesOfTrainingCourses_updateSalesTarget(boolean moveToNextDay) {

		remainingSalesTarget = 0;

		for (int i = 0; i < scheduledTrainingCourses.length; i++) {

			if (isBeforeScheduledDate(i) && moveToNextDay) {
				scheduledTrainingCourses[i].daysBeforeTrainingCourse--;
			}

			if (isProportionalEarlyBird(i)) {
				if (fullPricePolicyApplies(i)) {
					scheduledTrainingCourses[i].currentDiscountedPrice = scheduledTrainingCourses[i].fullPrice;
				} else {
					if (scheduledTrainingCourses[i].type.equals("CSD")) {
						scheduledTrainingCourses[i].currentDiscountedPrice = scheduledTrainingCourses[i].fullPrice - (scheduledTrainingCourses[i].daysBeforeTrainingCourse * 30);
					} else {
						scheduledTrainingCourses[i].currentDiscountedPrice = scheduledTrainingCourses[i].fullPrice - (scheduledTrainingCourses[i].daysBeforeTrainingCourse * 20);
					}
				}

			} else if (isSuperEarlyBird(i)) {

				if (fullPricePolicyApplies(i)) {
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

	private static boolean fullPricePolicyApplies(int i) {
		return scheduledTrainingCourses[i].daysBeforeTrainingCourse <= 1 || (scheduledTrainingCourses[i].remainingAvailableSeats < 3 && scheduledTrainingCourses[i].daysBeforeTrainingCourse <= 5);
	}

	private static boolean isBeforeScheduledDate(int i) {
		return scheduledTrainingCourses[i].daysBeforeTrainingCourse > 0;
	}

	private static boolean isSuperEarlyBird(int i) {
		return scheduledTrainingCourses[i].daysBeforeTrainingCourse > 10;
	}

	private static boolean isProportionalEarlyBird(int i) {
		return scheduledTrainingCourses[i].daysBeforeTrainingCourse <= 10;
	}
}
