package csd.refactoring;

public class Item {
	public int d;
	public int s;
	public int a;
	public Boolean online;
	public String type;
	public int price;
	public int fullPrice;

	public Item(int d, int s, int a, Boolean online, String type, int price, int fullPrice) {
		this.d = d;
		this.s = s;
		this.a = a;
		this.online = online;
		this.type = type;
		this.price = price;
		this.fullPrice = fullPrice;
	}
}
