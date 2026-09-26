# StudentJavaCRUD

# Student Record Management System

A simple **Student Record Management System** built using Java Servlets, JDBC, MySQL, HTML, CSS, and JavaScript. This web application allows users to add, view, update, and delete student records through a clean and responsive interface.

## Features

* **Add Students:** Create new student records with name, email, and course.
* **View Students:** Display all student records in a table.
* **Update Students:** Edit existing student information.
* **Delete Students:** Remove student records with confirmation.
* **MySQL Integration:** Store and manage student data in a MySQL database.
* **Responsive UI:** Simple interface that adapts to smaller screens.

## Tech Stack

| Technology          | Purpose                                |
| ------------------- | -------------------------------------- |
| Java 17             | Backend programming                    |
| Jakarta Servlet 6.0 | HTTP request handling                  |
| JDBC                | Database connectivity                  |
| MySQL               | Relational database                    |
| HTML5               | Web page structure                     |
| CSS3                | Styling and responsive design          |
| JavaScript          | Frontend interactions and API requests |
| Maven               | Dependency management and build        |
| Apache Tomcat 10.1+ | Web application server                 |

## Project Structure

```text
StudentJavaCRUD/
├── src/
│   └── main/
│       ├── java/
│       │   └── StudentServlet.java
│       └── webapp/
│           └── index.html
├── database.sql
├── pom.xml
├── README.md
└── .gitignore
```

## Prerequisites

Make sure you have installed:

* JDK 17
* Apache Maven
* MySQL Server
* Apache Tomcat 10.1 or later
* Visual Studio Code (or another Java IDE)

## Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/puspalgoswami/StudentJavaCRUD.git
cd StudentJavaCRUD
```

### 2. Set Up the Database

Open MySQL and execute the SQL script:

```sql
CREATE DATABASE student_db;
USE student_db;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL,
    course VARCHAR(80) NOT NULL
);
```

Alternatively, run the provided `database.sql` file using the MySQL command line:

```sql
SOURCE /path/to/database.sql;
```

Replace the path with the actual location of the file.

### 3. Configure Database Credentials

Open `StudentServlet.java` and configure the MySQL connection details.

Update the connection URL, username, and password according to your local MySQL setup.

**Security Note:** Do not commit database passwords or other sensitive credentials to a public repository. Use environment variables or a secure configuration method.

### 4. Build the Project

Run the following command from the project directory:

```bash
mvn clean package
```

This generates the WAR file:

```text
target/Students.war
```

### 5. Deploy to Apache Tomcat

1. Copy `target/Students.war` into the Tomcat `webapps` directory.
2. Start the Tomcat server.
3. Wait for the application to deploy.

### 6. Open the Application

Visit:

http://localhost:8080/Students/

You can now add, view, edit, and delete student records.

## API Endpoints

The application uses a Java Servlet mapped to `/students`.

| HTTP Method | Endpoint            | Description                  |
| ----------- | ------------------- | ---------------------------- |
| GET         | `/students`         | Retrieve all student records |
| POST        | `/students`         | Add or update a student      |
| DELETE      | `/students?id={id}` | Delete a student by ID       |

### Example POST Parameters

**Add a student:**

```text
action=add
name=John Doe
email=john@example.com
course=Computer Science
```

**Update a student:**

```text
action=update
id=1
name=John Doe
email=john@example.com
course=Information Technology
```

## Application Workflow

```text
User Interface (HTML, CSS, JavaScript)
                  |
                  v
          Java Servlet
                  |
                  v
             JDBC
                  |
                  v
           MySQL Database
```

## Learning Objectives

This project demonstrates:

* Java Servlet development and HTTP request handling.
* JDBC database connectivity.
* MySQL database operations.
* CRUD operations using SQL.
* Frontend and backend integration using JavaScript `fetch()`.
* Building and deploying a Java web application using Maven and Tomcat.

## Future Improvements

* Add student search and filtering.
* Implement input validation and improved error handling.
* Introduce a Model-View-Controller (MVC) architecture.
* Add authentication and authorization.
* Improve the UI with pagination and sorting.

## Author

**Puspal Goswami**

GitHub: [@puspalgoswami](https://github.com/puspalgoswami)

## License

This project is available for educational and learning purposes.
