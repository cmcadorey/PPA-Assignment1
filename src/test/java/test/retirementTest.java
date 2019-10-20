package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class retirementTest {

	@Test
	void test_workingAge() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		String output = test.calculateRetirement(78.0, 10.0, 10.0, 1.35);
		// Assert
		assertEquals("You will retire at age: 79.0", output);
	}
	
	
	@Test
	void test_ageOverLimit() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		String output = test.calculateRetirement(105, 10.0, 10.0, 1.35);
		// Assert
		assertEquals("Your age is over the death age.", output);
	}
	
	@Test
	void test_notReachGoalByDeath() {
		// Arrange
		JunitTesting test = new JunitTesting();
		// ACT
		String output = test.calculateRetirement(21, 780000, 5, 10000000);
		// Assert
		assertEquals("You will not reach your retirement goal by the time you die", output);
	}	
}
