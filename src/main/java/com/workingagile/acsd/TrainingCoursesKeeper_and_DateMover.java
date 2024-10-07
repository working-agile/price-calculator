package com.workingagile.acsd;

public class TrainingCoursesKeeper_and_DateMover {

	private final TrainingCourse[] scheduledTrainingCourses;

	private final PriceCalculator priceCalculator;

	private final SalesTargetCalculator salesTargetCalculator;


	public TrainingCoursesKeeper_and_DateMover(TrainingCourse[] trainingCourses) {
		scheduledTrainingCourses = trainingCourses;
		priceCalculator = new PriceCalculator();
		salesTargetCalculator = new SalesTargetCalculator();
	}

	public TrainingCourse[] getScheduledTrainingCourses() {
		return this.scheduledTrainingCourses.clone();
	}

	public int getRemainingSalesTarget() {
		return salesTargetCalculator.getRemainingSalesTarget();
	}

	public void moveToNextDayBeforeTrainingCourse_updateCurrentPricesOfTrainingCourses_updateSalesTarget(boolean moveToNextDay) {

		if (moveToNextDay) {
			moveToNextDayBeforeTrainingCourse();
		}

		priceCalculator.updateCurrentPrices(scheduledTrainingCourses);

		salesTargetCalculator.updateSalesTarget(scheduledTrainingCourses);
	}

	private void moveToNextDayBeforeTrainingCourse() {
		for (TrainingCourse trainingCourse: scheduledTrainingCourses) {

			if (isBeforeScheduledDate(trainingCourse)) {
				trainingCourse.daysBeforeTrainingCourse--;
			}
		}
	}

	private boolean isBeforeScheduledDate(TrainingCourse trainingCourse) {
		return trainingCourse.daysBeforeTrainingCourse > 0;
	}

}
