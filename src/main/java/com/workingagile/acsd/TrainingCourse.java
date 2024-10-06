package com.workingagile.acsd;

public class TrainingCourse {
	public String scheduledDate;
	public int daysBeforeTrainingCourse;
	public int totalNumberOfSeats;
	public int remainingAvailableSeats;
	public Boolean online;
	public String type;
	public int currentDiscountedPrice;
	public int fullPrice;

	public TrainingCourse(String scheduledDate, int daysBeforeTrainingCourse, int totalNumberOfSeats, int remainingAvailableSeats, Boolean online, String type, int fullPrice) {
		this.scheduledDate = scheduledDate;
		this.daysBeforeTrainingCourse = daysBeforeTrainingCourse;
		this.totalNumberOfSeats = totalNumberOfSeats;
		this.remainingAvailableSeats = remainingAvailableSeats;
		this.online = online;
		this.type = type;
		this.currentDiscountedPrice = fullPrice;
		this.fullPrice = fullPrice;
	}
}
