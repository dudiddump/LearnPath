package learnpath.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                
                String url = "jdbc:mysql://localhost:3306/db_learnpath";
                String user = "root";
                String password = "";

                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
