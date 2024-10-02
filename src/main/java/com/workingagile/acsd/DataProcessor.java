package com.workingagile.acsd;

public class DataProcessor {

	public static int value;

	// countdownOneDayToTrainingCourse_and_updatePrices
	public static Item[] processData(Item[] itemList) {
		value = 0;

		for (int i = 0; i < itemList.length; i++) {

			itemList[i].days -= 1;

			if (itemList[i].days <= 10) {

				if (itemList[i].days <= 1 || (itemList[i].available < 3 && itemList[i].days <= 5)) {
					itemList[i].price = itemList[i].full;
				} else {
					if (itemList[i].type.equals("CSD")) {
						itemList[i].price = itemList[i].full - (itemList[i].days * 30);
					} else {
						itemList[i].price = itemList[i].full - (itemList[i].days * 20);
					}
				}

			} else if (itemList[i].days > 10) {

				if (itemList[i].days <= 1 || (itemList[i].available < 3 && itemList[i].days <= 5)) {
					itemList[i].price = itemList[i].full;
				} else {
					if (itemList[i].type.equals("CSM")) {
						itemList[i].price = itemList[i].full - 500;
					} else {
						itemList[i].price = itemList[i].full - 400;
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

			value += (itemList[i].available * itemList[i].price);

		}

		return itemList;
	}
}
