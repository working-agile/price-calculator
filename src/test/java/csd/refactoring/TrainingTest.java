package csd.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class TrainingTest {

	// minimum prices

	@Test
	void discountsOverruledByMinimumPriceForCSD() {

		// Arrange
		Item i1 = new Item(32, 50, 20, true, "CSD", 1200);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertEquals(900, items[0].price);
	}

	@Test
	void discountsAllowedWhenOverMinimumPriceForCSD() {

		// Arrange
		Item i1 = new Item(32, 50, 20, true, "CSD", 3000);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertTrue(items[0].price > 900);
	}

	@ParameterizedTest
	@ValueSource(ints = { 1000, 1500 })
	void discountsOverruledByMinimumPriceForCSM(int initialFullPrice) {

		// Arrange
		Item i1 = new Item(32, 50, 20, true, "CSM", initialFullPrice);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertEquals(1000, items[0].price);
	}

	@Test
	void discountsAllowedWhenOverMinimumPriceForCSM() {

		// Arrange
		Item i1 = new Item(32, 50, 20, true, "CSM", 3000);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertTrue(items[0].price > 1000);
	}

	@ParameterizedTest
	@ValueSource(ints = { 1000, 1700 })
	void discountsOverruledByMinimumPriceForCSPO(int initialFullPrice) {

		// Arrange
		Item i1 = new Item(32, 50, 20, true, "CSPO", initialFullPrice);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertEquals(1200, items[0].price);
	}

	@Test
	void discountsAllowedWhenOverMinimumPriceForCSPO() {

		// Arrange
		Item i1 = new Item(32, 50, 20, true, "CSPO", 2001);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertTrue(items[0].price > 1200);
	}

	// full prices

	@ParameterizedTest
	@ValueSource(strings = { "CSD", "CSPO", "CSM" })
	void shouldHaveFullPrice_theDayBeforeTraining(String trainingCourse) {

		// Arrange
		Item i1 = new Item(2, 50, 20, true, trainingCourse, 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertEquals(4000, items[0].price);
	}

	// full prices when few seats left close to training course

	@ParameterizedTest
	@ValueSource(strings = { "CSD", "CSPO", "CSM" })
	void whenFewSeatsLeftCloseToTrainingDate_shouldHaveFullPrice(String trainingCourse) {

		// Arrange
		Item i1 = new Item(6 /* days left */, 50, 3 /* seats left */, true, trainingCourse, 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertEquals(2000, items[0].price);
	}

	// Proportional discount 10 days before the training course

	@Test
	void shouldApplyProportionalDiscountForCSD() {

		// Arrange
		int daysBeforeProcessing = 11;
		Item i1 = new Item(daysBeforeProcessing /* days left */, 50, 5, true, "CSD", 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		int expectedPrice = 2000 - 10 * 30;
		Assertions.assertEquals(expectedPrice, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(strings = { "CSPO", "CSM" })
	void shouldApplyProportionalDiscountForCSMandCSPO(String trainingCourse) {

		// Arrange
		int daysBeforeProcessing = 11;
		Item i1 = new Item(daysBeforeProcessing /* days left */, 50, 5, true, trainingCourse, 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		int expectedPrice = 2000 - 10 * 20;
		Assertions.assertEquals(expectedPrice, items[0].price);
	}

	// discount when 20 or less days before the training course

	@Test
	void when20orLessDaysBeforeTraining_shouldApplyDiscountForCSD() {

		// Arrange
		Item i1 = new Item(21, 50, 5, true, "CSD", 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertEquals(2000 - 500, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(strings = { "CSPO", "CSM" })
	void when20orLessDaysBeforeTraining_shouldApplyDiscountForCSMandCSPO(String trainingCourse) {

		// Arrange
		Item i1 = new Item(21, 50, 5, true, trainingCourse, 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertEquals(2000 - 400, items[0].price);
	}

	// discount when more than 20 days before the training course

	@Test
	void whenMoreThan20DaysBeforeTraining_shouldApplyDiscountForCSPO() {

		// Arrange
		Item i1 = new Item(22, 50, 5, true, "CSPO", 4000);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertEquals(4000 - 700, items[0].price);
	}

	@ParameterizedTest
	@ValueSource(strings = { "CSD", "CSM" })
	void whenMoreThan20DaysBeforeTraining_shouldApplyDiscountForCSDandCSM(String trainingCourse) {

		// Arrange
		Item i1 = new Item(22, 50, 5, true, trainingCourse, 2000);
		Item[] items = new Item[] { i1 };

		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertEquals(2000 - 600, items[0].price);
	}

 
 
	// total current value of training courses 
	
	@Test
	void should_calculate_total_value_of_remaining_training_courses() {
		
		Item i1 = new Item(30, 50, 5, true, "CSD", 4000);
		Item i2 = new Item(15, 50, 1, true, "CSD", 2000);
		
		Item[] items = new Item[] { i1, i2 };
		
		// Act
		items = DataProcessor.processData(items);

		// Assert
		Assertions.assertEquals(3400*5 + 1500*1 , DataProcessor.value);
		
	}
	
	 
}
