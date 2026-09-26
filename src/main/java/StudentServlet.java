import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    // Database connection
    private Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/student_db",
            "root",
            "181222"
        );
    }

    // Escape special characters for JSON
    private String safe(String s) {
        if (s == null) return "";

        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", " ")
                .replace("\r", " ");
    }

    // READ: Get all students
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json;charset=UTF-8");

        StringBuilder json = new StringBuilder("[");

        try (Connection c = connect();
             PreparedStatement p = c.prepareStatement(
                 "SELECT * FROM students ORDER BY id");
             ResultSet r = p.executeQuery()) {

            boolean first = true;

            while (r.next()) {
                if (!first) {
                    json.append(",");
                }

                first = false;

                json.append("{\"id\":")
                    .append(r.getInt("id"))
                    .append(",\"name\":\"")
                    .append(safe(r.getString("name")))
                    .append("\",\"email\":\"")
                    .append(safe(r.getString("email")))
                    .append("\",\"course\":\"")
                    .append(safe(r.getString("course")))
                    .append("\"}");
            }

            json.append("]");
            resp.getWriter().print(json);

        } catch (SQLException e) {
            e.printStackTrace();

            resp.setStatus(500);
            resp.getWriter().print(
                "{\"error\":\"Database error. Check DB password and database setup.\"}"
            );
        }
    }

    // CREATE and UPDATE: Add or edit a student
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String course = req.getParameter("course");
        String id = req.getParameter("id");

        String sql = "add".equals(action)
            ? "INSERT INTO students(name,email,course) VALUES(?,?,?)"
            : "UPDATE students SET name=?,email=?,course=? WHERE id=?";

        try (Connection c = connect();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, name);
            p.setString(2, email);
            p.setString(3, course);

            if (!"add".equals(action)) {
                p.setInt(4, Integer.parseInt(id));
            }

            p.executeUpdate();

            resp.setStatus(200);
            resp.getWriter().print("OK");

        } catch (Exception e) {
            e.printStackTrace();

            resp.setStatus(500);
            resp.getWriter().print(
                "Could not save student. Check database setup."
            );
        }
    }

    // DELETE: Remove a student
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try (Connection c = connect();
             PreparedStatement p = c.prepareStatement(
                 "DELETE FROM students WHERE id=?")) {

            p.setInt(1, Integer.parseInt(req.getParameter("id")));
            p.executeUpdate();

            resp.getWriter().print("OK");

        } catch (Exception e) {
            e.printStackTrace();

            resp.setStatus(500);
            resp.getWriter().print("Could not delete student.");
        }
    }
}