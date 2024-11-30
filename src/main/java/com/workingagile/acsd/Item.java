package com.workingagile.acsd;

public class Item {

	public long id;
	public String trDate;
	public int days;
	public int seats;
	public int avail;
	public String type;
	public int curr;
	public int full;

	public Item(long id, String trDate, int days, int seats, int avail, String type, int curr, int full) {
		this.id = id;
		this.trDate = trDate;
		this.days = days;
		this.seats = seats;
		this.avail = avail;
		this.type = type;
		this.curr = curr;
		this.full = full;
	}
}
