package com.workingagile.acsd;

public class TrainingCourseService {

	private final TrainingCourseRepository repository;

	private final PriceCalculator priceCalculator;

	private final SalesTargetCalculator salesTargetCalculator;


	public TrainingCourseService(TrainingCourse[] trainingCourses) {
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


	public void updateCurrentPrices() {

		priceCalculator.updateCurrentPrices(repository.getScheduledTrainingCourses());

	}

	public void updateSalesTarget() {

		salesTargetCalculator.updateSalesTarget(repository.getScheduledTrainingCourses());

	}

	public void moveToNextDayBeforeTrainingCourse() {
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
