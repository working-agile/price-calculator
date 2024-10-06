package com.workingagile.acsd;

public class Item {
	public String date;
	public int days;
	public int seats;
	public int avail;
	public Boolean online;
	public String type;
	public int current;
	public int full;

	public Item(String date, int days, int seats, int avail, Boolean online, String type, int full) {
		this.date = date;
		this.days = days;
		this.seats = seats;
		this.avail = avail;
		this.online = online;
		this.type = type;
		this.current = full;
		this.full = full;
	}
}
