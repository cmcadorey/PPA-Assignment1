package test;


//junit imports
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

//mockito imports
import org.mockito.junit.jupiter.MockitoExtension;

import test.JunitTesting;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import java.sql.*;

@ExtendWith(MockitoExtension.class)
public class DB_tests  {

  @Mock Statement s;
  @Mock Connection c;

  @InjectMocks JunitTesting f;

  @BeforeEach
  public void setUp() throws SQLException{
    lenient().when(c.createStatement()).thenReturn(s);
    lenient().when(s.executeUpdate(any(String.class))).thenReturn(1);
  }

  

  //BMI Tests
  @Test
  public void bmiConnectionShouldCreateStatementTest() throws SQLException {
    f.BMIDB(5,3,125,c);
    verify(c).createStatement();
  }

  @Test
  public void bmiNormalExecutionNormalWeight() throws SQLException {
    int height_feet = 5;
    double height_inches = 3;
    double weight = 125.0;
    double BMI_Rounded =0.0;
    String answer = f.BMIDB(height_feet,height_inches,weight,c);
    assertEquals( answer,
        "Your BMI is: 22.7 You are normal weight");
    String shouldHaveHappened = "Insert into BMI (height_feet,height_inches,weight,BMI_Rounded) values(" + height_feet + "," + height_inches + "," + weight + "," + BMI_Rounded + ")";
    verify(s).executeUpdate(shouldHaveHappened);
  }

  @Test
  public void bmiNormalExecutionOverweight() throws SQLException {
    int height_feet = 5;
    double height_inches = 5;
    double weight = 150.0;
    double BMI_Rounded =0.0;
    String answer = f.BMIDB(height_feet,height_inches,weight,c);
    assertEquals( answer,
        "Your BMI is: 25.6 You are overweight");
    String shouldHaveHappened = "Insert into BMI (height_feet,height_inches,weight,BMI_Rounded,result) values(" + height_feet + "," + height_inches + "," + weight + "," + BMI_Rounded + ")";
    verify(s).executeUpdate(shouldHaveHappened);
  }
  
  @Test
  public void bmiErrorShouldThrowSQLException() throws SQLException {
    lenient().when(c.createStatement()).thenThrow(new SQLException());
    assertThrows(SQLException.class,
           () -> f.BMIDB(5,5,150,c),
           "There was an SQLException");
    verify(c).createStatement();
    verify(s, never()).executeUpdate(any(String.class));
  }
  
  // Retire Tests

  @Test
  public void RetireConnectionShouldCreateStatementTest() throws SQLException {
    f.RetireDB(78,10.0,10.0,1.35,c);
    verify(c).createStatement();
  }
  @Test
  public void RetireNormalExecution() throws SQLException {
    double age = 78;
    double salary = 10.0;
    double percentage = 10.0;
    double savings_goal = 1.35;
    double retirement_age =0.0;
    String answer = f.RetireDB(age, salary, percentage, savings_goal,c);
    assertEquals( answer,
    "You will retire at age: 79.0");
    String shouldHaveHappened = "Insert into Retire (age, salary, percentage, savings_goal,retirement_age) values(" + age + "," + salary + "," + percentage + "," + savings_goal + ",'" + retirement_age + "')";
    verify(s).executeUpdate(shouldHaveHappened);
  }
  @Test
  public void RetireAgeLimitNormalExecution() throws SQLException {
    double age = 105;
    double salary = 10.0;
    double percentage = 10.0;
    double savings_goal = 1.35;
    double retirement_age =0.0;
    String answer = f.RetireDB(age, salary, percentage, savings_goal,c);
    assertEquals( answer,
    "Your age is over the death age.");
    String shouldHaveHappened = "Insert into Retire (age, salary, percentage, savings_goal,retirement_age) values(" + age + "," + salary + "," + percentage + "," + savings_goal + ",'" + retirement_age + "')";
    verify(s).executeUpdate(shouldHaveHappened);
  }

  @Test
  public void RetireErrorShouldThrowSQLException() throws SQLException {
    lenient().when(c.createStatement()).thenThrow(new SQLException());
    assertThrows(SQLException.class,
           () -> f.RetireDB(-5,5,5,5,c),
           "There was an SQLException");
    verify(c).createStatement();
    verify(s, never()).executeUpdate(any(String.class));
  }
}

