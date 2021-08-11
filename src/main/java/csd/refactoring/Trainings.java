package csd.refactoring;

public class Trainings {

	public static void main(String[] args) {
		// int d, int s, int a, Boolean online, String type, int price, int fullPrice
		Item i1 = new Item(15, 50, 20, true, "CSD", 2000, 2000);
		Item i2 = new Item(31, 50, 15, true, "CSPO", 4000, 4000);
		Item i3 = new Item(4, 50, 2, true, "CSM", 2000, 3000);
		Item[] items = new Item[] { i1, i2, i3 };
		calculate(items);
	}

	public static Item[] calculate(Item[] items) {
		int vendavel = 0;

		for (int i = 0; i < items.length; i++) {
			items[i].d -= 1;
			

			if (items[i].d <= 10) {

				if (items[i].d == 10) {
					items[i].price = items[i].fullPrice;
				}

				if (items[i].d <= 1 || items[i].a <= 3 && items[i].d <= 5) {
					items[i].price = items[i].fullPrice;
				} else {
					if (items[i].type.equals("CSD")) {
						items[i].price = items[i].fullPrice - (items[i].d * 30);
					} else {
						items[i].price = items[i].fullPrice - (items[i].d * 20);
					}
				}

			} else if (items[i].d <= 20) {

				if (items[i].d == 20) {
					items[i].price = items[i].fullPrice;
				}

				if (items[i].d <= 1 || items[i].a <= 3 && items[i].d <= 5) {
					items[i].price = items[i].fullPrice;
				} else {
					if (items[i].type.equals("CSD")) {
						items[i].price = items[i].price - 500;
					} else {
						items[i].price = items[i].price - 400;
					}
				}
			} else if (items[i].d <= 30) {
				
				if (items[i].d == 30) {
					items[i].price = items[i].fullPrice;
				}

				if (items[i].d <= 1 || items[i].a <= 3 && items[i].d <= 5) {
					items[i].price = items[i].fullPrice;
				} else {
					if (items[i].type.equals("CSPO")) {
						items[i].price = items[i].price - 500;
					} else {
						items[i].price = items[i].price - 600;
					}
				}
			} else {
				if (items[i].d <= 1 || items[i].a <= 3 && items[i].d <= 5) {
					items[i].price = items[i].fullPrice;
				} else {
					if (items[i].type.equals("CSM")) {
						items[i].price = items[i].price - 700;
					} else {
						items[i].price = items[i].price - 800;
					}
				}
			}

			if (items[i].type.equals("CSD") && items[i].price < 900) {
				items[i].price = 900;
			} else if (items[i].type.equals("CSM") && items[i].price < 1000) {
				items[i].price = 1000;
			} else if (items[i].type.equals("CSPO") && items[i].price < 1200) {
				items[i].price = 1200;
			}

			vendavel += (items[i].a * items[i].price);

			System.out.println("Preço treinamento SEM desconto:" + items[i].fullPrice);
			System.out.println("Preço treinamento COM desconto:" + items[i].price);
		}
		System.out.println("Total Vendavel: " + vendavel);
		return items;
	}
}
