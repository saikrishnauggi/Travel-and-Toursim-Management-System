package travel.management.system;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;

    public Conn() {
        try {
            // Load the MySQL JDBC driver (use the newer driver class)
            Class.forName("com.mysql.cj.jdbc.Driver");  
            
            // Establish the connection using the correct database details
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourismmanagementsystem", "root", "Shiv@5502");
            
            // Create the statement to execute queries
            s = c.createStatement();
            
            System.out.println("Connection established successfully!");
            
        } catch (Exception e) {
            // Print the exception stack trace for better debugging
            e.printStackTrace();
        }
    }

    // Getter for the connection object (if needed elsewhere in your code)
    public Connection getConnection() {
        return c;
    }

    Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
