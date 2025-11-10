All files required are contained within the Java project created out of Eclipse.
The project is created using the JDBC driver.

Project Name: COMP3005_Assignment3
Repo: https://github.com/Arsenaultm90/COMP3005_Assignment3
Video Link: https://vimeo.com/1135359263

SETUP:
1. Create a new database in a Postgres SQL server running on a local host for ease.
2. Open the DDL+DML script and run the snippets of code in order to ensure proper naming convention.
3. In the JAVA source code of Crud.java, ensure the following:
	URL - Prefix should stay the same but check the port number and database name to ensure they match. Example: jdbc:postgresql://localhost:5432/Assignment3
	USER - Check that it matches the username for the database server.
	PASSWORD - Check that it matches the password for the database server.
4. In Eclipse, go into Project -> Properties -> Java Build Path -> Libraries and ensure that the Postgres-42.7.8.jar is already added. (It should be)
	If not, click on 'Add External JARs' and add the .jar file in the root project folder. Then in 'Order and Export' ensure the box is ticked.

The project should now be good to run. Print statements have been added to help with any error detection but if the setup is correct there shouldn't be any issues.

EXECUTION:
1. Run the project using the green play button.
2. A menu will be displayed in the console giving the user the options for a CRUD operation.
3. Make a selection and complete the required prompts. 
4. Check back with your database for the added data. You may have to refresh it for the data to appear. 
5. When you have completed your operations, press 5 to exit.