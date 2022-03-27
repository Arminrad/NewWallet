import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class SingletonConnectionConcurrent {

    private Connection connection;

    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgres";
    private String password = "admin";

    public SingletonConnectionConcurrent() {
        try {
            Class.forName("org.postgresql.Driver");
            setConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class Singleton {
        static SingletonConnectionConcurrent singletonConnection = new SingletonConnectionConcurrent();
    }

    public static SingletonConnectionConcurrent getInstance() {
        return Singleton.singletonConnection;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                setConnection();
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
