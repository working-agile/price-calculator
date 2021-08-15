package csd.refactoring;

public class Item {
	public int d;
	public int s;
	public int a;
	public Boolean online;
	public String type;
	public int price;
	public int full;

	public Item(int d, int s, int a, Boolean online, String type, int full) {
		this.d = d;
		this.s = s;
		this.a = a;
		this.online = online;
		this.type = type;
		this.price = full;
		this.full = full;
	}
}
