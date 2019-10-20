package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

public class JunitTesting {

    public String computeBMI(int height_feet, double height_inches, double weight) {
        double height = (height_feet * 12) + height_inches;

        double metric1 = weight * 0.45;
        double metric2 = height * 0.025;
        metric2 = metric2 * metric2;
        double BMI = metric1 / metric2;
        double BMI_Rounded = Math.round(BMI * 10.0) / 10.0;
        String result = null;

        if (BMI_Rounded < 18.5) {
            result = "Your BMI is: " + BMI_Rounded + " You are underweight";
        }
        if ((BMI_Rounded >= 18.5) && (BMI_Rounded < 25)) {
            result = "Your BMI is: " + BMI_Rounded + " You are normal weight";
        }

        if ((BMI_Rounded >= 25) && (BMI_Rounded < 30)) {
            result = "Your BMI is: " + BMI_Rounded + " You are overweight";
        }
        if (BMI_Rounded >= 30) {
            result = "Your BMI is: " + BMI_Rounded + " You are obese";
        }

        return result;

    }

    public String calculateRetirement(double age, double salary, double percentage, double savings_goal) {
        String result = null;
        percentage = percentage / 100;
        double employer_percentage = 0.35;
        double death = 100;
        double retirement = 0;
        double years = 0;
        double AMT_SAVED = salary * percentage;

        while (retirement < savings_goal) {

            retirement = retirement + AMT_SAVED + (AMT_SAVED * (employer_percentage));
            years++;
        }
        double retirement_age = age + years;
        if (retirement_age <= death) {
            result = "You will retire at age: " + retirement_age;
        }
        if (retirement_age > death) {
            result = "You will not reach your retirement goal by the time you die";
        }

        if (age > death) {
            result = "Your age is over the death age.";
        }

        return result;

    }

    public String calculateDistanceTwoPoints(double x1, double y1, double x2, double y2) {
        String output = null;

        String text1 = String.valueOf(Math.abs(x1));
        int integerPlaces1 = text1.indexOf('.');
        int decimalPlaces1 = text1.length() - integerPlaces1 - 1;

        String text2 = String.valueOf(Math.abs(y1));
        int integerPlaces2 = text2.indexOf('.');
        int decimalPlaces2 = text2.length() - integerPlaces2 - 1;

        String text3 = String.valueOf(Math.abs(x2));
        int integerPlaces3 = text3.indexOf('.');
        int decimalPlaces3 = text3.length() - integerPlaces3 - 1;

        String text4 = String.valueOf(Math.abs(y2));
        int integerPlaces4 = text4.indexOf('.');
        int decimalPlaces4 = text4.length() - integerPlaces4 - 1;

        int decimal_final = decimalPlaces1;

        if (decimal_final < decimalPlaces2) {

            decimal_final = decimalPlaces2;
        } else if (decimal_final < decimalPlaces3) {

            decimal_final = decimalPlaces3;
        } else if (decimal_final < decimalPlaces4) {

            decimal_final = decimalPlaces4;
        }

        double x_factor = ((x2 - x1) * (x2 - x1));
        double y_factor = ((y2 - y1) * (y2 - y1));
        double added = x_factor + y_factor;
        double distance = Math.sqrt(added);
        double factor = 1.0;

        for (int i = 0; i < decimal_final; i++) {
            factor = factor * 10.0;

        }
        double distance_rounded = Math.round(distance * factor) / factor * 1.0;
        output = "The Distance is " + distance_rounded;

        return output;

    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z$!%+*=?^-_{|}~](([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    // DATABASE FUNCTIONS
    public String BMIDB(int height_feet, double height_inches, double weight, Connection con) throws SQLException { 
        try{
            Statement s = con.createStatement();

            double height = (height_feet*12) + height_inches;
		
            double metric1 = weight * 0.45;
            double metric2 = height *0.025;
            metric2 = metric2*metric2;
            double BMI = metric1/metric2;
            double BMI_Rounded = Math.round(BMI*10.0) / 10.0;
            String result = null;

            if (BMI_Rounded < 18.5){
            result = "Your BMI is: " + BMI_Rounded + " You are underweight";
            }
            if ((BMI_Rounded >= 18.5)&&(BMI_Rounded <25)){
            result = "Your BMI is: " + BMI_Rounded + " You are normal weight";
            }

            if ((BMI_Rounded >= 25)&&(BMI_Rounded <30)){
            result = "Your BMI is: " + BMI_Rounded + " You are overweight";
            }
            if (BMI_Rounded >= 30){
             result = "Your BMI is: " + BMI_Rounded + " You are obese";
             }

             s.executeUpdate("Insert into BMI (height_feet,height_inches,weight,BMI_Rounded,result) values(" + height_feet + "," + height_inches + "," + weight + "," + BMI_Rounded + "," + result + ")");

            return result; 
        }

        catch (SQLException e){
            System.out.println("Database connection lost!");
            throw e;
        }
    }

    public String RetireDB(double age, double salary, double percentage, double savings_goal, Connection con) throws SQLException{
        try{
            Statement s = con.createStatement();

            String result = null;
            percentage = percentage / 100;
            double employer_percentage = 0.35;
            double death = 100;
            double retirement = 0;
            double years = 0;
            double AMT_SAVED = salary * percentage;

            while (retirement < savings_goal) {
                retirement = retirement + AMT_SAVED + (AMT_SAVED * (employer_percentage));
                years++;
            }
            double retirement_age = age + years;
            if (retirement_age <= death) {
            result = "You will retire at age: " + retirement_age;
            }
            if (retirement_age > death) {
            result = "You will not reach your retirement goal by the time you die";
            }

            if (age > death) {
                result = "Your age is over the death age.";
            }

            s.executeUpdate("Insert into Retire (age,salary,percentage,savings_goal,retirement_age,result) values(" + age + "," + salary + "," + percentage + "," + savings_goal + "," + retirement_age + "," + result + ")"); 
            return result;
        }
        catch(SQLException e){
            System.out.println("Database connection lost!");
            throw e;
        }
        
        

    }





}
