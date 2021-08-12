package csd.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class TrainingTest {
	
	@ParameterizedTest
	@ValueSource(ints = { 1000, 1200 })
	void CSDtrainings_shouldHaveMinimumPriceOf900(int priceWithDiscount) {

		// Arrange
		Item i1 = new Item(32, 50, 20, true, "CSD", priceWithDiscount);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(900, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 1000, 1300 })
	void CSMtrainings_shouldHaveMinimumPriceOf1000(int priceWithDiscount) {

		// Arrange
		Item i1 = new Item(25, 50, 20, true, "CSM", priceWithDiscount);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1000, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 1000, 1200 })
	void CSPOtrainings_shouldHaveMinimumPriceOf1200(int priceWithDiscount) {

		// Arrange
		Item i1 = new Item(25, 50, 20, true, "CSPO", priceWithDiscount);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1200, items[0].price);
	}

	void shouldHaveFullprice_theDayBeforeTraining() {

		// Arrange
		Item i1 = new Item(1, 50, 20, true, "CSPO", 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(4000, items[0].price);
	}
	
	@ParameterizedTest
	@ValueSource(ints = { 6, 3 })
	void when3orLessSeats5DaysBeforeCourse_shouldHaveFullprice(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 2, true, "CSD", 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(2000, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 35, 37 })
	void forCSDwhen30orMoreDaysBeforeCourse_shouldApplyDiscount800(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 5, true, "CSD", 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1200, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 34, 39 })
	void forCSMwhen30orMoreDaysBeforeCourse_shouldApplyDiscount700(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 5, true, "CSM", 3000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(2300, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 35, 37 })
	void forCSPOwhen30orMoreDaysBeforeCourse_shouldApplyDiscount800(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 5, true, "CSPO", 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(3200, items[0].price);
	}

	
	@ParameterizedTest
	@ValueSource(ints = { 31, 22 })
	void forCSDwhenBetween30and20DaysBeforeCourse_shouldApplyDiscount600(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 5, true, "CSD", 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1400, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 24, 30 })
	void forCSMwhenBetween30and20DaysBeforeCourse_shouldApplyDiscount600(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 5, true, "CSM", 3000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(2400, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 25, 31 })
	void forCSPOwhenBetween30and20DaysBeforeCourse_shouldApplyDiscount500(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 5, true, "CSPO", 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(3500, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 20, 19 })
	void forCSDwhenBetween20and10DaysBeforeCourse_shouldApplyDiscount500(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 5, true, "CSD", 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(1500, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 18, 14 })
	void forCSMwhenBetween20and10DaysBeforeCourse_shouldApplyDiscount400(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(18, 50, 5, true, "CSM", 3000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(2600, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 15, 12 })
	void forCSPOwhenBetween20and10DaysBeforeCourse_shouldApplyDiscount400int(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 5, true, "CSPO", 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertEquals(3600, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 8, 6 })
	void forCSDwhen10orLessDaysBeforeCourse_shouldApplyDiscount30perDay(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 5, true, "CSD", 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertTrue(1790 == items[0].price || 1900 == items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 6, 7 })
	void forCSMwhen10orLessDaysBeforeCourse_shouldApplyDiscount20perDay(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 5, true, "CSM", 3000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertTrue(2900 == items[0].price || 2880 == items[0].price);
	}

	@ParameterizedTest
	@ValueSource(ints = { 11, 6 })
	void forCSPOwhen10orLessDaysBeforeCourse_shouldApplyDiscount20perDay(int daysBeforeTraining) {

		// Arrange
		Item i1 = new Item(daysBeforeTraining, 50, 15, true, "CSPO", 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = Trainings.calculate(items);

		// Assert
		Assertions.assertTrue(3800 == items[0].price || 3900 == items[0].price);
	}

	
}
