package com.workingagile.acsd;

public class DataProcessor {

	private int remainingSalesTarget;

	private TrainingCourse[] scheduledTrainingCourses;


	public DataProcessor(TrainingCourse[] trainingCourses) {
		this.scheduledTrainingCourses = trainingCourses;
	}

	public TrainingCourse[] getScheduledTrainingCourses() {
		return this.scheduledTrainingCourses.clone();
	}

	public int getRemainingSalesTarget() {
		return remainingSalesTarget;
	}

	public void moveToNextDayBeforeTrainingCourse_updateCurrentPricesOfTrainingCourses_updateSalesTarget(boolean moveToNextDay) {

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

	private boolean fullPricePolicyApplies(TrainingCourse trainingCourse) {
		return trainingCourse.daysBeforeTrainingCourse <= 1 || (trainingCourse.remainingAvailableSeats < 3 && trainingCourse.daysBeforeTrainingCourse <= 5);
	}

	private boolean isBeforeScheduledDate(TrainingCourse trainingCourse) {
		return trainingCourse.daysBeforeTrainingCourse > 0;
	}

	private boolean isSuperEarlyBird(TrainingCourse trainingCourse) {
		return trainingCourse.daysBeforeTrainingCourse > 10;
	}

	private boolean isProportionalEarlyBird(TrainingCourse trainingCourse) {
		return trainingCourse.daysBeforeTrainingCourse <= 10;
	}

}
