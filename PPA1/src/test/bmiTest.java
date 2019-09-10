package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import test.JunitTesting;

class bmiTest {
	@Test
	void test_BMINormalWeight() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		String output = test.computeBMI(5, 3, 125);
		// Assert
		assertEquals("Your BMI is: 22.7 You are normal weight", output);

	}

	@Test
	void test_BMIUnderWeight() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		String output = test.computeBMI(5, 0, 50);
		// Assert
		assertEquals("Your BMI is: 10.0 You are underweight", output);

	}

	@Test
	void test_BMIOverWeight() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		String output = test.computeBMI(5, 5, 150);
		// Assert
		assertEquals("Your BMI is: 25.6 You are overweight", output);
	}

	@Test
	void test_BMTObese() {
		//Arrange
		JunitTesting test = new JunitTesting();
		//ACT
		String output = test.computeBMI(5, 5, 250);
		//Assert
		assertEquals("Your BMI is: 42.6 You are obese", output);
			
	}

}
