# PPA2 - Connor McAdorey - Zach Tsarnas

***************************************************************************************
Commands to run postgres sql database in docker
***************************************************************************************

1. The command below starts up a new postgress datebase container named mystuff

docker run -d -p 5432:5432 -v postgres-data:/var/lib/postgresql/data --name ppa2db -e POSTGRES_PASSWORD=password -e POSTGRES_DB=mystuff postgres

2. Enter the docker container for the database

docker exec -it ppa2db sh

3. Enter the database ppa2db

psql -U postgres mystuff

4. Create the tables that will store the inputs and outputs for Retire and BMI
	create table Retire( 
			createdAt TIMESTAMPTZ NOT NULL PRIMARY KEY DEFAULT NOW(), 
			age NUMERIC(999,2), salary NUMERIC(999,2), percentage NUMERIC(999,2), 
			savings_goal NUMERIC(999,2), retirement_age NUMERIC(999,2) ); 
);
create table bodymass(
	create table BMI( 
			createdAt TIMESTAMPTZ NOT NULL PRIMARY KEY DEFAULT NOW(), 
			height_feet INTEGER, height_inches NUMERIC(999,2), 
			weight NUMERIC(999,2), BMI_Rounded NUMERIC(999,2) );

*************************************************************************************
Execute the code using maven
*************************************************************************************
1. open a terminal window in the folder that you cloned the repository into
2. Use the following command to build the project:

   mvn clean install

3. Use the follwoing command to run the code:

   mvn exec:java
*************************************************************************************
Connecting to the code using http requests
*************************************************************************************
You can use postman or a browser to make get and post requests to localhost:4789 when the program is running
The following explains the possible routes and their parameters

GET to localhost:4789/retire 
-returns the inputs and outputs of the function

POST to localhost:4789/retire
-accesses the retire function with the following variables passed in
Headers:
	Variable	Data Type
	age		double
	salary		double
	percentage	double
	savings_goal	double

GET to lcoalhost:4789/bmi
-returns the inputs and outputs of the function

POST to localhost:4789/bmi
-accesses the retire function with the following variables passed in
Headers:
	Variable	Data Type
	feet		int
	inches		double
	weight		double
