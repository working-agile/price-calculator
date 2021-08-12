package csd.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrainingTest {
	@Test
	void should_have_minimum_price_for_CSD_of_900() {

		// Arrange
		Item i1 = new Item(32, 50, 20, true, "CSD", 1000, 1000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(900, items[0].price);
	}

	@Test
	void should_have_minimum_price_for_CSM_of_1000() {

		// Arrange
		Item i1 = new Item(25, 50, 20, true, "CSM", 1100, 1100);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1000, items[0].price);
	}

	@Test
	void should_have_minimum_price_for_CSPO_of_1200() {

		// Arrange
		Item i1 = new Item(25, 50, 20, true, "CSPO", 1300, 1300);
		Item[] items = new Item[] { i1 };
		
		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1200, items[0].price);
	}

	@Test
	void should_have_fullprice_the_day_before_training() {

		// Arrange
		Item i1 = new Item(1, 50, 20, true, "CSPO", 1300, 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(4000, items[0].price);
	}

	@Test
	void should_have_fullprice_5_days_before_training_and_less_than_3_seats() {

		// Arrange
		Item i1 = new Item(6, 50, 2, true, "CSD", 1300, 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(2000, items[0].price);
	}

	@Test
	void should_apply_30_plus_days_range_discount_CSD() {

		// Arrange
		Item i1 = new Item(35, 50, 5, true, "CSD", 2000, 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1200, items[0].price);
	}

	@Test
	void should_apply_30_plus_days_range_discount_CSM() {

		// Arrange
		Item i1 = new Item(35, 50, 5, true, "CSM", 3000, 3000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(2300, items[0].price);
	}

	@Test
	void should_apply_30_plus_days_range_discount_CSPO() {

		// Arrange
		Item i1 = new Item(35, 50, 5, true, "CSPO", 4000, 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(3200, items[0].price);
	}
	
	@Test
	void should_apply_30_to_20_days_range_discount_CSD() {

		// Arrange
		Item i1 = new Item(31, 50, 5, true, "CSD", 2000, 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1400, items[0].price);
	}

	@Test
	void should_apply_30_to_20_days_range_discount_CSM() {

		// Arrange
		Item i1 = new Item(28, 50, 5, true, "CSM", 3000, 3000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(2400, items[0].price);
	}

	@Test
	void should_apply_30_to_20_days_range_discount_CSPO() {

		// Arrange
		Item i1 = new Item(25, 50, 5, true, "CSPO", 4000, 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(3500, items[0].price);
	}
	
	@Test
	void should_apply_20_to_10_days_range_discount_CSD() {

		// Arrange
		Item i1 = new Item(20, 50, 5, true, "CSD", 2000, 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1500, items[0].price);
	}

	@Test
	void should_apply_20_to_10_days_range_discount_CSM() {

		// Arrange
		Item i1 = new Item(18, 50, 5, true, "CSM", 3000, 3000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(2600, items[0].price);
	}

	@Test
	void should_apply_20_to_10_days_range_discount_CSPO() {

		// Arrange
		Item i1 = new Item(15, 50, 5, true, "CSPO", 4000, 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(3600, items[0].price);
	}
	
	@Test
	void should_apply_less_than_10_days_range_discount_CSD() {

		// Arrange
		Item i1 = new Item(8, 50, 5, true, "CSD", 2000, 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1790, items[0].price);
	}

	@Test
	void should_apply_less_than_10_days_range_discount_CSM() {

		// Arrange
		Item i1 = new Item(5, 50, 5, true, "CSM", 3000, 3000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(2920, items[0].price);
	}

	@Test
	void should_apply_less_than_10_days_range_discount_CSPO() {

		// Arrange
		Item i1 = new Item(11, 50, 5, true, "CSPO", 4000, 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(3800, items[0].price);
	}
}
