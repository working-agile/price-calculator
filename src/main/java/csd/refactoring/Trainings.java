package csd.refactoring;

public class Trainings {

	public static void main(String[] args) {
		//int d, int s, int a, Boolean online, String type, int price, int fullPrice
		Item i1 = new Item(15, 50, 20,true, "CSD", 2000, 2000);
		Item i2 = new Item(31, 50, 15,true, "CSPO", 4000, 4000);
		Item i3 = new Item(4, 50, 2,true, "CSM", 2000, 3000);
		Item[] items = new Item[] { i1, i2, i3 };
		calculate(items);
	}

	public static void calculate(Item[] items) {

		for (Item training : items) {
			training.d -= 1;

			if (training.d <= 1 || training.a <= 3 && training.d <= 5) {
				training.price = training.fullPrice;
			}

			if (training.d <= 10) {
				if (training.type.equals("CSD")) {
					training.price = training.fullPrice - (training.d * 30);
				} else {
					training.price = training.fullPrice - (training.d * 20);
				}
			} else if (training.d <= 20) {
				if (training.type.equals("CSD")) {
					training.price = training.fullPrice - 500;
				} else if (training.type.equals("CSPO")) {
					training.price = training.fullPrice - 400;
				} else {
					training.price = training.fullPrice - 350;
				}
			} else if (training.d <= 30) {
				if (training.type.equals("CSPO")) {
					training.price = training.fullPrice - 500;
				} else {
					training.price = training.fullPrice - 600;
				}
			} else {
				if (training.type.equals("CSM")) {
					training.price = training.fullPrice - 700;
				} else {
					training.price = training.fullPrice - 800;
				}
			}
			
			System.out.println("Preço treinamento SEM desconto:" + training.fullPrice);
			System.out.println("Preço treinamento COM desconto:" + training.price);
			System.out.println("Total Vendavel" + training.type + training.a * training.price);
		}
	}
}
