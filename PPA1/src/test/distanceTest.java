package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class distanceTest {

	@Test
	void test_DistanceWithDecimals() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		String output = test.calculateDistanceTwoPoints(-8, -2.8569, 7.5555, 2.55);
		// Assert
		assertEquals("The Distance is 16.4684", output);

	}

	@Test
	void test_DistanceWithAllNegatives() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		String output = test.calculateDistanceTwoPoints(-8, -2.8569, -2.5, -2);
		// Assert
		assertEquals("The Distance is 5.5664", output);
	}
	
	@Test
	void test_DistanceWithWholeNumbers() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		String output = test.calculateDistanceTwoPoints(5, 1, 8, 9);
		// Assert
		assertEquals("The Distance is 8.5", output);
	}

}
