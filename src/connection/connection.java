package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static final String URL = "jdbc:mysql://localhost:3306/datasiswa"; // Ganti dengan URL database Anda
    private static final String USER = "root"; // Ganti dengan username MySQL Anda
    private static final String PASSWORD = ""; // Ganti dengan password MySQL Anda

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
public static void main(String[] args) {
        getConnection();
    }
}
