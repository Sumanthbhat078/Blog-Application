
# Task Manager
Designed to provide a solution for managing tasks and to-do lists. It caters to individuals, teams, and organizations seeking an efficient and organized way to track, create, update, and delete tasks.



## Technologies Used

- **Spring Boot**: Empowering rapid and efficient Java application development.
- **Spring Data JPA**: Simplifying database interaction through Java Persistence API.
- **MySQL**: A robust and widely-used relational database management system.
- **RestFul APIs**: Enabling seamless communication between client and server through REST architecture.
- **HTML/CSS**: Building interactive and visually appealing web interfaces using standard web technologies.

## Installation


Initially provide the Database name, userName and Pasword for MySQL 
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/Your_db_name
spring.datasource.username=your_user_name
spring.datasource.password=your_password
```
Run the Project
## Swagger API Documentation
To access the Swagger API documentation for this project, please follow these steps:

1. Ensure that the project is running locally.

2. Open a web browser and navigate to the following URL:
http://localhost:8080/swagger-ui/index.html
## API Design and Usage

### API Design and Usage

The APIs for this project are designed to support **only HTTP GET and POST requests**. This design choice is driven by the use of an input form that is integrated into the application. **As the forms only render GET and POST requests**.

### Viewing API Responses

When testing APIs using Swagger, you may notice that the **response appears as a raw HTML data**. This is because the controller classes in this project are configured to render output as HTML pages. **To view the actual web output**, please follow these steps:

1. Execute the desired API endpoint in Swagger.

2. To see the actual frontend response , **copy the URL of the Swagger request (endpoints) and paste it into a web browser**.

This approach ensures that the API responses are rendered as HTML pages when accessed through browser.


## API Reference
You can access the Endpoints through http://localhost:8080/ 
#### Home Page

```http
  GET /home
```

#### Create Task

```http
  GET /createTaskForm
```
#### Get All Tasks
```http
  GET /getAllTasks
```
#### Update a task by Id
```http
  POST /showUpdateForm/{Id}
```
#### Delete a task by Id
```http
  POST /deleteTask/{id}
```




## Running JUnit Test Cases

These tests cover various aspects of our application, including the functionality of the `TaskService` implementation.

You can view the test cases and verify their results by running the `TaskManagerApplicationTests.java` class. This test class contains a set of unit tests that exercise the methods and logic implemented in the `TaskService` component.






