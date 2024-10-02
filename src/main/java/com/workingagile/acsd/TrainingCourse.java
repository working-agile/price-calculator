package com.workingagile.acsd;

import java.time.LocalDate;

public class TrainingCourse {

    public String type;
    public LocalDate scheduledDate;
    public int fullPrice;
    public int currentPrice;
    public int daysLeft;

    public TrainingCourse(String type, LocalDate scheduledDate, int daysLeft, int fullPrice, int currentPrice) {
        this.type = type;
        this.scheduledDate = scheduledDate;
        this.daysLeft = daysLeft;
        this.fullPrice = fullPrice;
        this.currentPrice = currentPrice;
    }


}
