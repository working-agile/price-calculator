package com.workingagile.acsd;

public class DataProcessor {

	public static int remainingSalesTarget;

	public static TrainingCourse[] scheduledTrainingCourses;

	public static void decreaseDaysBeforeTrainingCourse_updateCurrentPricesOfTrainingCourses_updateSalesTarget(boolean moveToNextDay) {

		remainingSalesTarget = 0;

		for (TrainingCourse trainingCourse: scheduledTrainingCourses) {

			if (isBeforeScheduledDate(trainingCourse) && moveToNextDay) {
				trainingCourse.daysBeforeTrainingCourse--;
			}

			if (isProportionalEarlyBird(trainingCourse)) {
				if (fullPricePolicyApplies(trainingCourse)) {
					trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice;
				} else {
					if (trainingCourse.type.equals("CSD")) {
						trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - (trainingCourse.daysBeforeTrainingCourse * 30);
					} else {
						trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - (trainingCourse.daysBeforeTrainingCourse * 20);
					}
				}

			} else if (isSuperEarlyBird(trainingCourse)) {

				if (fullPricePolicyApplies(trainingCourse)) {
					trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice;
				} else {
					if (trainingCourse.type.equals("CSM")) {
						trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - 500;
					} else {
						trainingCourse.currentDiscountedPrice = trainingCourse.fullPrice - 400;
					}
				}
			}

			if (trainingCourse.type.equals("CSD") && trainingCourse.currentDiscountedPrice < 900) {
				trainingCourse.currentDiscountedPrice = 900;
			} else if (trainingCourse.type.equals("CSM") && trainingCourse.currentDiscountedPrice < 1000) {
				trainingCourse.currentDiscountedPrice = 1000;
			} else if (trainingCourse.type.equals("CSPO") && trainingCourse.currentDiscountedPrice < 1200) {
				trainingCourse.currentDiscountedPrice = 1200;
			}

			remainingSalesTarget += (trainingCourse.remainingAvailableSeats * trainingCourse.currentDiscountedPrice);

		}
	}

	private static boolean fullPricePolicyApplies(TrainingCourse trainingCourse) {
		return trainingCourse.daysBeforeTrainingCourse <= 1 || (trainingCourse.remainingAvailableSeats < 3 && trainingCourse.daysBeforeTrainingCourse <= 5);
	}

	private static boolean isBeforeScheduledDate(TrainingCourse trainingCourse) {
		return trainingCourse.daysBeforeTrainingCourse > 0;
	}

	private static boolean isSuperEarlyBird(TrainingCourse trainingCourse) {
		return trainingCourse.daysBeforeTrainingCourse > 10;
	}

	private static boolean isProportionalEarlyBird(TrainingCourse trainingCourse) {
		return trainingCourse.daysBeforeTrainingCourse <= 10;
	}
}
