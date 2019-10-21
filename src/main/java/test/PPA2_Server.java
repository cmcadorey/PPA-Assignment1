package main.java.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import test.JunitTesting;

public class PPA2_Server {

    HttpServer server;
    Connection connection;

    //create a socket and start listening for requests
    public boolean start(Connection con) {
        try {
            connection=con;
            server = HttpServer.create(new InetSocketAddress(5000), 0);
            server.createContext("/", new MainHandler());
            server.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not open at port 5000");
            return false;
        }
    }

    public void shut_down(){
        server.stop(1);
    }

    public void setUp(Connection con){
        connection = con;
    }


    public class MainHandler implements HttpHandler{

        @Override

        public void handle (HttpExchange httpExchange) throws IOException{
                String uri = httpExchange.getRequestURI().toString();
                String method = httpExchange.getRequestMethod().toString();
                String tmp = "Method: " + httpExchange.getRequestMethod() + "\n";
                tmp = tmp + "Path: " + httpExchange.getRequestURI();

                if(method.equals("GET") && (uri.equals("/bmi"))){
                    getBmi(httpExchange);
                }
                else if(method.equals("GET") && (uri.equals("/retire"))){
                  getRetire(httpExchange);
                }
                else if(method.equals("POST") && (uri.contains("/bmi"))){
                  postBmi(httpExchange);
                }
                else if(method.equals("POST") && (uri.contains("/retire"))){
                  postRetire(httpExchange);
                }
                else{
                  home(httpExchange);
                }
            }

            private void getBmi(HttpExchange h) throws IOException{
                Statement statement= null;
                ResultSet rSet=null;
                try{
                  statement = connection.createStatement();
                  rSet=statement.executeQuery("SELECT * FROM BMI");
                }
                catch(SQLException e){
                  e.printStackTrace();
                  System.out.println("Could not connect to the database.");
                }
      
                String response= formatIntoJSON(rSet);
                h.getResponseHeaders().set("Content-type","application/json");
                h.sendResponseHeaders(200,response.length());
                OutputStream os= h.getResponseBody();
                os.write(response.getBytes());
                os.close();
              }  

              private void getRetire(HttpExchange h) throws IOException{
                Statement statement= null;
                ResultSet rSet=null;
                try{
                  statement = connection.createStatement();
                  rSet=statement.executeQuery("SELECT * FROM retire");
                }
                catch(SQLException e){
                  e.printStackTrace();
                  System.out.println("Could not connect to the database. Terminating program.");
                }
                String response= formatIntoJSON(rSet);
                h.getResponseHeaders().set("Content-type","application/json");
                h.sendResponseHeaders(200,response.length());
                OutputStream os= h.getResponseBody();
                os.write(response.getBytes());
                os.close();
              }

              private void postRetire(HttpExchange h) throws IOException{
                try{
                    Map <String, List<String>> map= h.getRequestHeaders();
                    double age=-1.0;
                    double salary =-1.0;
                    double percentage =-1.0;
                    double savings_goal = -1.0;
                      for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                      String tmp = entry.getValue().toString();
                      tmp = tmp.substring(tmp.indexOf("[")+1,tmp.indexOf("]"));
                      if(entry.getKey().equalsIgnoreCase("age")){
                          age= Double.parseDouble(tmp);
                      }
                      if(entry.getKey().equalsIgnoreCase("salary")){
                        salary= Double.parseDouble(tmp);
                    }
                      if(entry.getKey().equalsIgnoreCase("percentage")){
                        percentage=Double.parseDouble(tmp);
                      }
                      if(entry.getKey().equalsIgnoreCase("savings_goal")){
                        savings_goal= Double.parseDouble(tmp);
                    }
                    }
        
                    //check for missing arguments
                    if(age < 0 || salary < 0 || percentage < 0 || savings_goal < 0){
                      String response="One or more request headers were not set correctly";
                      h.sendResponseHeaders(200,response.getBytes().length);
                      OutputStream os= h.getResponseBody();
                      os.write(response.getBytes());
                      os.close();
                    }
                    else{

                      JunitTesting PPA1= new JunitTesting();
                      String ret = PPA1.RetireDB(age, salary, percentage, savings_goal, connection);
  
                      String response=ret;
                      h.sendResponseHeaders(200,response.getBytes().length);
                      OutputStream os= h.getResponseBody();
                      os.write(response.getBytes());
                      os.close();
                    }      
                  }
                  catch(Exception e){
                    String response="Could not parse request header variables";
                    h.sendResponseHeaders(404,response.getBytes().length);
                    OutputStream os= h.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                  }
                }
      
              private void postBmi(HttpExchange h) throws IOException{
                try{
                  Map <String, List<String>> map= h.getRequestHeaders();
                  int feet=-1;
                  double inches=-1.0;
                  double weight=-1.0;
                    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    String tmp = entry.getValue().toString();
                    tmp = tmp.substring(tmp.indexOf("[")+1,tmp.indexOf("]"));
                    if(entry.getKey().equalsIgnoreCase("feet")){
                        feet= Integer.parseInt(tmp);
                    }
                    if(entry.getKey().equalsIgnoreCase("inches")){
                      inches=Double.parseDouble(tmp);
                    }
                    if(entry.getKey().equalsIgnoreCase("weight")){
                      weight=Double.parseDouble(tmp);
                    }
                  }
      
                  //check for missing arguments
                  if(inches < 0 || feet < 0 || weight < 0 ){
                    String response="One or more request headers were not set correctly";
                    h.sendResponseHeaders(200,response.getBytes().length);
                    OutputStream os= h.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                  }
                  else{
                    JunitTesting PPA1= new JunitTesting();
                    String ret = PPA1.BMIDB(feet, inches, weight,connection);

                    String response=ret;
                    h.sendResponseHeaders(200,response.getBytes().length);
                    OutputStream os= h.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                  }       
                }
                catch(Exception e){
                  String response="Could not parse request header variables";
                  h.sendResponseHeaders(404,response.getBytes().length);
                  OutputStream os= h.getResponseBody();
                  os.write(response.getBytes());
                  os.close();
                }
              }
      



              private void home(HttpExchange h) throws IOException{
                String response = "Welcome to the http interface.\n";
                response += "To see a list of all Retirement database entries go to /retire\n";
                response += "To see a list of all BMI database entries go to /bmi\n";
                response += "To call the function Retirement Checker send a post request to /retire with these arguments\n";
                response += "\tVariable \tData Type\n";
                response += "\tage \tdouble\n";
                response += "\tsalary \tdouble\n";
                response += "\tpercentage \tdouble\n";
                response += "\tsavings_goal \tdouble\n";
                response += "To call the function BMI send a post request to /bmi with these arguments\n";
                response += "\tVariable \tData Type\n";
                response += "\theight_feet \t\tint\n";
                response += "\theight_inches \t\tdouble\n";
                response += "\tweight \t\tdouble\n";
                h.sendResponseHeaders(200,response.getBytes().length);
                OutputStream os= h.getResponseBody();
                os.write(response.getBytes());
                os.close();
              }
      
              //formats the result set for the bmi or retire into JSON
              private String formatIntoJSON(ResultSet rs){
                try{
                  ResultSetMetaData meta = rs.getMetaData();
                  int cols = meta.getColumnCount();
                  boolean first = true;
      
                  //return empty string if empty columns
                  if(cols == 0){
                    return "[]";
                  }
      
                  //start building return string
                  String json = "[";
                  while(rs.next()){
                    if(!first){
                      json += ",";
                    }
                    else{
                      first = false;
                    }
      
                    //build the json object for this row based on what table its from
                    json += "{";
                    if(meta.getTableName(1).equals("BMI")){
                      json += "\"createdAt\":\"" + rs.getString("createdAt") + "\",";
                      json += "\"height_feet\":" + rs.getInt("feet") + ",";
                      json += "\"height_inches\":" + rs.getDouble("inches") + ",";
                      json += "\"weight\":" + rs.getDouble("weight") + ",";
                      json += "\"BMI_Rounded\":" + rs.getDouble("BMI_Rounded") + ",";
                      json += "\"result\":\"" + rs.getString("result") + "\"";
                    }
                    else{
                      json += "\"createdAt\":\"" + rs.getString("createdAt") + "\",";
                      json += "\"age\":" + rs.getDouble("age") + ",";
                      json += "\"salary\":" + rs.getDouble("salary") + ",";
                      json += "\"percentage\":" + rs.getDouble("percentage") + ",";
                      json += "\"savings_goal\":" + rs.getDouble("savings_goal") + ",";
                      json += "\"retirement_age\":" + rs.getDouble("retirement_age") + ",";
                      json += "\"result\":" + rs.getString("result");
                      
                    }
                    json += "}";
      
                  }
                  json += "]";
                  return json;
                }
                catch(SQLException e){
                  return "Error reading result set.";
                }
              }
          }
      
        }
    
