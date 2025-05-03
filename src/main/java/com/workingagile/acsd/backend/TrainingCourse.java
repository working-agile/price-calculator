package com.workingagile.acsd.backend;

public class TrainingCourse {

	public long id;
	public String scheduledDate;
	public int daysBeforeTrainingCourse;
	public int totalNumberOfSeats;
	public int remainingAvailableSeats;
	public String type;
	public int currentDiscountedPrice;
	public int fullPrice;

	public TrainingCourse(long id, String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats,
						  int remainingAvailableSeats, String type, int currentDiscountedPrice, int fullPrice) {
		this.id = id;
		this.scheduledDate = scheduledDate;
		this.daysBeforeTrainingCourse = daysBeforeTrainingCourse;
		this.totalNumberOfSeats = totalNumberOfSeats;
		this.remainingAvailableSeats = remainingAvailableSeats;
		this.type = type;
		this.currentDiscountedPrice = currentDiscountedPrice;
		this.fullPrice = fullPrice;
	}
}
