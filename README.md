## Database Configuration:

1. Create Database and Schema 
- Open a PostgreSQL client (e.g., pgAdmin).
- Setup user and password for database if needed.
- Create a new database named "task"

2. Configure properties file in Springboot
- spring.datasource.driver-class-name=org.postgresql.Driver
- spring.datasource.url=jdbc:postgresql://localhost:5432/task
- spring.datasource.username=Your_Username
- spring.datasource.password=Your_Password

- spring.data.jpa.repositories.enabled=true
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=true
- spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect



## Build and Run


1. Clone the Repository

2. Open the Project in Spring Tool Suite 
- Open Spring Tool Suite (STS).
- Click on `File` > `Import`.
- Select `Git` > `Projects from Git` and click `Next`.
- Choose `Clone URI` and click `Next`.
- Enter the repository URL
- Follow the prompts to complete the import process.


3. Build and Run	
- Right-click on the project in the Project Explorer.
- Select Run As > Maven install.
- Once the build completes, right-click on the project again.
- Select Run As > Spring Boot App.


## Testing 
- To run unit tests:
- Right-click on the test file.
- Select Run As > JUnit Test.
- The integration tests will be executed, and the results will be displayed in the console.


## Running APIs
- The API will be accessible at http://localhost:8080.
- Use "/tasks" to map the request with endpoints.


## API Endpoints
- `POST /tasks`: Create a new task.
- `GET /tasks/{id}`: Retrieve a task by its ID.
- `GET /tasks`: Retrieve a list of all tasks.
- `PUT /tasks/{id}`: Update an existing task by its ID.
- `DELETE /tasks/{id}`: Delete a task by its ID.



## Access the API Endpoints 
- Use a tool like Postman to test your API endpoints based on the specified URLs and methods (`POST`, `GET`, `PUT`, `DELETE`) in your controller.
