package utilities;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    String User = "root";
                    String Password = "admin";
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                    connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", User, Password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
