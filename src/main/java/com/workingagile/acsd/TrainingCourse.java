package com.workingagile.acsd;

public abstract class TrainingCourse {

	private String scheduledDate;
	private int daysBeforeTrainingCourse;
	private int totalNumberOfSeats;
	private int remainingAvailableSeats;
	private Boolean online;
	private int currentDiscountedPrice;
	private int fullPrice;

	public TrainingCourse(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, int fullPrice) {
		this.scheduledDate = scheduledDate;
		this.daysBeforeTrainingCourse = daysBeforeTrainingCourse;
		this.totalNumberOfSeats = totalNumberOfSeats;
		this.remainingAvailableSeats = remainingAvailableSeats;
		this.online = online;
		this.currentDiscountedPrice = fullPrice;
		this.fullPrice = fullPrice;
	}

	public String getScheduledDate() {
		return scheduledDate;
	}

	public int getDaysBeforeTrainingCourse() {
		return daysBeforeTrainingCourse;
	}

	public int getTotalNumberOfSeats() {
		return totalNumberOfSeats;
	}

	public int getRemainingAvailableSeats() {
		return remainingAvailableSeats;
	}

	public Boolean getOnline() {
		return online;
	}

	public int getCurrentDiscountedPrice() {
		return currentDiscountedPrice;
	}

	public int getFullPrice() {
		return fullPrice;
	}

	// ---------------------------------------------------------------------------

	public abstract String getDescription();

	public abstract void updateCurrentPrice();

	boolean isSuperEarlyBird() {
		return this.daysBeforeTrainingCourse > 10;
	}

	boolean isProportionalEarlyBird() {
		return this.daysBeforeTrainingCourse <= 10;
	}

	public void decreaseDaysBeforeTrainingCourse() { daysBeforeTrainingCourse--; }


	public void setCurrentDiscountedPrice(int currentDiscountedPrice) {
		this.currentDiscountedPrice = currentDiscountedPrice;
	}
}
