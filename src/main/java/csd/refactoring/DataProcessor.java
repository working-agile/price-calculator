package csd.refactoring;

public class DataProcessor {

	public static void main(String[] args) {

		Item i1 = new Item(15, 50, 20, true, "CSD", 2000);
		Item i2 = new Item(31, 50, 15, true, "CSPO", 4000);
		Item i3 = new Item(4, 50, 2, true, "CSM", 3000);
		Item[] items = new Item[] { i1, i2, i3 };

		items = processData(items);

		for (int i = 0; i < items.length; i++) {
			System.out.println("Training course price" + i + " with discount: " + items[i].price);
		}

		System.out.println("Total amount left to sell: " + value);
	}

	public static int value;

	public static Item[] processData(Item[] itemList) {
		value = 0;

		for (int i = 0; i < itemList.length; i++) {
			itemList[i].d -= 1;

			if (itemList[i].d <= 10) {

				if (itemList[i].d <= 1 || itemList[i].a <= 3 && itemList[i].d <= 5) {
					itemList[i].price = itemList[i].full;
				} else {
					if (itemList[i].type.equals("CSD")) {
						itemList[i].price = itemList[i].full - (itemList[i].d * 30);
					} else {
						itemList[i].price = itemList[i].full - (itemList[i].d * 20);
					}
				}

			} else if (itemList[i].d <= 20) {

				if (itemList[i].d <= 1 || itemList[i].a <= 3 && itemList[i].d <= 5) {
					itemList[i].price = itemList[i].full;
				} else {
					if (itemList[i].type.equals("CSD")) {
						itemList[i].price = itemList[i].full - 500;
					} else {
						itemList[i].price = itemList[i].full - 400;
					}
				}
			} else {
				if (itemList[i].d <= 1 || itemList[i].a <= 3 && itemList[i].d <= 5) {
					itemList[i].price = itemList[i].full;
				} else {
					if (itemList[i].type.equals("CSPO")) {
						itemList[i].price = itemList[i].full - 700;
					} else {
						itemList[i].price = itemList[i].full - 600;
					}
				}
			} 

			if (itemList[i].type.equals("CSD") && itemList[i].price < 900) {
				itemList[i].price = 900;
			} else if (itemList[i].type.equals("CSM") && itemList[i].price < 1000) {
				itemList[i].price = 1000;
			} else if (itemList[i].type.equals("CSPO") && itemList[i].price < 1200) {
				itemList[i].price = 1200;
			}

			value += (itemList[i].a * itemList[i].price);

		}

		return itemList;
	}
}
