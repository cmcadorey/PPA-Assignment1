package test;

import java.util.*;
import java.lang.Math;
import java.lang.String;
import java.sql.*;

public class Run {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
        JunitTesting test = new JunitTesting();
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        boolean connected = false;
		String selection = null;

        System.out.println("Welcome to this Program!");

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not find SQL connection driver.");
        }

        try{
            //change localhost to 192.168.99.100 if using Docker Toolbox
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/mystuff", "postgres", "password");
			s = conn.createStatement();
			connected = true;
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Could not connect to the database. Terminating program!");
        }

        PPA2_Server server = new PPA2_Server();
        boolean servedStarted = server.start(conn);


        //Menu
        int active = 1;

        while (active == 1){

            
            System.out.println("Please select a number to choose from the menu below");
            System.out.println ("1) Body Mass Index - Database");
            System.out.println ("2) Retirement - Database");
            System.out.println ("3) Shortest Distance");
            System.out.println ("4) Email Address");
            System.out.println ("5) Exit");

            try {
		selection = input.next();
		}
	    catch(Exception e) {
		//exit upon input error
		selection = "5";
		}

                if(selection.equals ("1")){
                    System.out.println("These are the previous entries for this program:");
				    try{
					rs = s.executeQuery("SELECT * FROM BMI");
					while (rs.next()) {
						System.out.println("TimeStamp: " + rs.getString("createdAt") + ", Feet: " + rs.getInt("height_feet")
						+ ", Inches: " + rs.getDouble("height_inches") + ", Weight: " + rs.getDouble("weight")
						+ ", Bmi: " + rs.getDouble("BMI_Rounded"));
					}
				}
				catch(SQLException e){
					e.printStackTrace();
					System.out.println("Could not connect to BMI table in the database.");
				}


                    System.out.println ("Enter your height in feet");
                    int feet = input.nextInt();
                    while (feet<0){
                        System.out.println("#ERROR Invalid input. Please input again");
				        feet = input.nextInt();
                    }
                    System.out.println ("Enter your height in inches");
                    double inches = input.nextDouble();
                    while ((inches<0) || (inches>12)){
                        System.out.println("#ERROR Invalid input. Please input again");
				        inches = input.nextDouble();
                    }
                    
                    System.out.println ("Enter your weight in pounds");
                    double weight = input.nextDouble();
                    while (weight<0){
                        System.out.println("#ERROR Invalid input. Please input again");
				        weight = input.nextDouble();
                    }
                    try{
                    String BMI = test.BMIDB(feet, inches, weight, conn);
                    System.out.println (BMI);
                    }
                    catch(SQLException e){
                        e.printStackTrace();
                        System.out.println("There was an SQLException\n");
                    }
                }
                    
                if(selection.equals ("2")){
                    System.out.println("These are the previous entries for this program:");
				    try{
					    rs = s.executeQuery("SELECT * FROM Retire");
					while (rs.next()) {
						System.out.println("TimeStamp: " + rs.getString("createdAt") + ", Age: " + rs.getDouble("age")
						+ ", salary: " + rs.getDouble("salary") + ", Percentage: " + rs.getDouble("percentage")
						+ ", Savings Goal: " + rs.getDouble("savings_goal") + ", Retirement Age: " + rs.getDouble("retirement_age") );
					}
				}
				catch(SQLException e){
					e.printStackTrace();
					System.out.println("Could not connect to Retire table in the database.");
				}
                    System.out.println("Enter your current age");
                    double age = input.nextDouble();
                    while (age<0){
                        System.out.println("#ERROR Invalid input. Please input again");
				        age = input.nextDouble();
                    }
                    System.out.println("Enter your current annual salary");
                    double salary = input.nextDouble();
                    while (salary<0){
                        System.out.println("#ERROR Invalid input. Please input again");
				        salary = input.nextDouble();
                    }
                    
                    System.out.println("Enter your savings percentage (as whole number)");
                    double percentage = input.nextDouble();
                    while (percentage<0){
                        System.out.println("#ERROR Invalid input. Please input again");
				        percentage = input.nextDouble();
                    }
                    
                    System.out.println("Enter your retirement savings goal");
                    double goal = input.nextDouble();
                    while (goal<0){
                        System.out.println("#ERROR Invalid input. Please input again");
				        goal = input.nextDouble();
                    }
                    
                try{
                   String retirement = test.RetireDB(age, salary, percentage, goal, conn);
                   System.out.println(retirement);
                }
                catch(SQLException e){
                    e.printStackTrace();
                    System.out.println("There was an SQLException\n");
                }
            }

                if(selection.equals ("3")){
                    System.out.println("Enter the value of X1");
                    double x1 = input.nextDouble();

                    System.out.println("Enter the value of Y1");
                    double y1 = input.nextDouble();

                    System.out.println("Enter the value of X2");
                    double x2 = input.nextDouble();

                    System.out.println("Enter the value of Y2");
                    double y2 = input.nextDouble();
                    
                    String distance = test.calculateDistanceTwoPoints(x1, y1, x2, y2);
                    	System.out.println(distance);
                }

                if(selection.equals ("4")){
                    
                    System.out.println("Enter the email address");
                    String email = input.next();
                    
                    if(test.isValidEmail(email)){
                    		System.out.println("This is a valid email address");
                    }
                    else
                    		System.out.println("This is not a valid email");
                }
                if(selection.equals ("5")) {
                    //server.close();
                    input.close();
                	System.exit(0);
                }
        }
	}

}
