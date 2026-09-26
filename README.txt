SIMPLE STUDENT CRUD WEB PROJECT (VS CODE)

Files:
- src/main/webapp/index.html: HTML page + small JavaScript for CRUD
- src/main/java/StudentServlet.java: Java Servlet backend
- database.sql: MySQL database and table
- pom.xml: Maven dependencies

Needs: JDK 17, Maven, MySQL Server, Tomcat 10.1+

Setup:
1. Run database.sql in MySQL (mysql -u root -p, then type SOURCE full-path-to-database.sql; or paste SQL into MySQL).
2. In StudentServlet.java, replace YOUR_MYSQL_PASSWORD with your MySQL root password.
3. Open VS Code terminal in this folder and run: mvn clean package
4. Deploy target/Students.war to Tomcat 10.1+ webapps folder and start Tomcat.
5. Open http://localhost:8080/Students/

CRUD: Add, list, edit, and delete student records.
