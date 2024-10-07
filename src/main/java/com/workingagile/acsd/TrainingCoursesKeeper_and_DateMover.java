package com.workingagile.acsd;

public class TrainingCoursesKeeper_and_DateMover {

	private final TrainingCourseRepository repository;

	private final PriceCalculator priceCalculator;

	private final SalesTargetCalculator salesTargetCalculator;


	public TrainingCoursesKeeper_and_DateMover(TrainingCourse[] trainingCourses) {
		repository = new TrainingCourseRepository(trainingCourses);
		priceCalculator = new PriceCalculator();
		salesTargetCalculator = new SalesTargetCalculator();
	}

	public TrainingCourse[] getScheduledTrainingCourses() {
		return repository.getScheduledTrainingCourses().clone();
	}

	public int getRemainingSalesTarget() {
		return salesTargetCalculator.getRemainingSalesTarget();
	}

	public void moveToNextDayBeforeTrainingCourse_updateCurrentPricesOfTrainingCourses_updateSalesTarget(boolean moveToNextDay) {

		if (moveToNextDay) {
			moveToNextDayBeforeTrainingCourse();
		}

		priceCalculator.updateCurrentPrices(repository.getScheduledTrainingCourses());

		salesTargetCalculator.updateSalesTarget(repository.getScheduledTrainingCourses());
	}

	private void moveToNextDayBeforeTrainingCourse() {
		for (TrainingCourse trainingCourse: repository.getScheduledTrainingCourses()) {

			if (isBeforeScheduledDate(trainingCourse)) {
				trainingCourse.daysBeforeTrainingCourse--;
			}
		}
	}

	private boolean isBeforeScheduledDate(TrainingCourse trainingCourse) {
		return trainingCourse.daysBeforeTrainingCourse > 0;
	}

}
