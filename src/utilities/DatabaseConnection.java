package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String DbName = "pip";
                String User = "root";
                String Password = "admin";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DbName +"?autoReconnect=true&useSSL=false", User, Password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
