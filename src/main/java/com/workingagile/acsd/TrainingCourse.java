package com.workingagile.acsd;

public abstract class TrainingCourse {

	public String scheduledDate;
	public int daysBeforeTrainingCourse;
	public int totalNumberOfSeats;
	public int remainingAvailableSeats;
	public Boolean online;
	public int currentDiscountedPrice;
	public int fullPrice;

	public TrainingCourse(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, int fullPrice) {
		this.scheduledDate = scheduledDate;
		this.daysBeforeTrainingCourse = daysBeforeTrainingCourse;
		this.totalNumberOfSeats = totalNumberOfSeats;
		this.remainingAvailableSeats = remainingAvailableSeats;
		this.online = online;
		this.currentDiscountedPrice = fullPrice;
		this.fullPrice = fullPrice;
	}

	public abstract String getDescription();

	public abstract void updateCurrentPrice();

	boolean isSuperEarlyBird() {
		return this.daysBeforeTrainingCourse > 10;
	}

	boolean isProportionalEarlyBird() {
		return this.daysBeforeTrainingCourse <= 10;
	}


}
