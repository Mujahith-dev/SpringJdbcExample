From start.spring.io, add the dependencies JDBC API and MySql Driver for connecting to the data.
In application.properties file, the necessary properties for connecting to the database like url, username, password and driver-class-name are mentioned.
The project structure has 3 important packages namely Model, Repository and Services
In the model package, we have the Student class and its properties, necessary getters and setters, constructors and toString method.
In the service class, all the methoda thats to be performed are displayed .
The service layer,calls the repo to execute all these methods. 
The repo layer has the StudentRepository class which uses JdbcTemplate to perform operation in database.
To make it more user interactive, CommandLineRunner is implemented to make it a complete application. 
The call goes from the CommandLineRunner to the service layer. The service layer then calls the repo layer to perform all the operation in database.
