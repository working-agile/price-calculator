package com.workingagile.acsd;

public class DataProcessor {

	public static int value;

	public static Item[] list;

	public static void calculateData(boolean next) {

		value = 0;

		for (int i = 0; i < list.length; i++) {

			if (next && list[i].days > 0) {
				list[i].days--;
			}

			if (list[i].days <= 10) {

				if (list[i].days <= 1 || (list[i].avail < 3 && list[i].days <= 5)) {
					list[i].current = list[i].full;
				} else {
					if (list[i].type.equals("CSD")) {
						list[i].current = list[i].full - (list[i].days * 30);
					} else {
						list[i].current = list[i].full - (list[i].days * 20);
					}
				}

			} else if (list[i].days > 10) {

				if (list[i].days <= 1 || (list[i].avail < 3 && list[i].days <= 5)) {
					list[i].current = list[i].full;
				} else {
					if (list[i].type.equals("CSM")) {
						list[i].current = list[i].full - 500;
					} else {
						list[i].current = list[i].full - 400;
					}
				}
			}

			if (list[i].type.equals("CSD") && list[i].current < 900) {
				list[i].current = 900;
			} else if (list[i].type.equals("CSM") && list[i].current < 1000) {
				list[i].current = 1000;
			} else if (list[i].type.equals("CSPO") && list[i].current < 1200) {
				list[i].current = 1200;
			}

			value += (list[i].avail * list[i].current);

		}
	}
}
