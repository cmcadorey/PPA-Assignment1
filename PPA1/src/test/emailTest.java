package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class emailTest {

	@Test
	void test_normalEmail() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		boolean output = test.isValidEmail("johnsmith@gmail.com");
		// Assert
		assertEquals(true, output);
	}
	
	@Test
	void test_emailWithStartingPeriod() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		boolean output = test.isValidEmail(".johnsmith@gmail.com");
		// Assert
		assertEquals(false, output);
	}
	
	@Test
	void test_emailWithInvalidCharacter() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		boolean output = test.isValidEmail("john@/smith@gmail.com");
		// Assert
		assertEquals(false, output);
	}
	
	@Test
	void test_emailWithTwoPeriods() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		boolean output = test.isValidEmail("john..smith@gmail.com");
		// Assert
		assertEquals(false, output);
	}
	
	@Test
	void test_emailWithOnePeriod() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		boolean output = test.isValidEmail("john.smith@gmail.com");
		// Assert
		assertEquals(true, output);
	}
	@Test
	void test_emailStartWithNum() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		boolean output = test.isValidEmail("1john.smith@gmail.com");
		// Assert
		assertEquals(false, output);
	}

}
