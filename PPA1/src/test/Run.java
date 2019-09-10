package test;

import java.util.*;
import java.lang.Math;
import java.lang.String;

public class Run {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		JunitTesting test = new JunitTesting();

        //Menu
        int active = 1;

        while (active == 1){

            System.out.println("Welcome to this Program!");
            System.out.println("Please select a number to choose from the menu below");
            System.out.println ("1) Body Mass Index");
            System.out.println ("2) Retirement");
            System.out.println ("3) Shortest Distance");
            System.out.println ("4) Email Address");
            System.out.println ("5) Exit");

            String selection = input.next();

                if(selection.equals ("1")){
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
                    
                    String BMI = test.computeBMI(feet, inches, weight);
                    
                    System.out.println (BMI);
                    }
                    
                if(selection.equals ("2")){

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
                    
                    
                   String retirement = test.calculateRetirement(age, salary, percentage, goal);
                   System.out.println(retirement);
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
                	System.exit(0);
                }
        }
	}

}
