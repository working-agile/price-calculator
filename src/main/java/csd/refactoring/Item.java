package csd.refactoring;

public class Item {
	public int days;
	public int seats;
	public int available;
	public Boolean online;
	public String type;
	public int price;
	public int full;

	public Item(int days, int seats, int available, Boolean online, String type, int full) {
		this.days = days;
		this.seats = seats;
		this.available = available;
		this.online = online;
		this.type = type;
		this.price = full;
		this.full = full;
	}
}
