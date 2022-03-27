import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnectionLittleConcurrent {
    private Connection connection;
    private static SingletonConnectionLittleConcurrent singletonConnectionLittleConcurrent;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgres";
    private String password = "admin";

    private SingletonConnectionLittleConcurrent() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static SingletonConnectionLittleConcurrent getInstance() {
        try {
            if (singletonConnectionLittleConcurrent == null || singletonConnectionLittleConcurrent.getConnection().isClosed()) {
                return singletonConnectionLittleConcurrent = new SingletonConnectionLittleConcurrent();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return singletonConnectionLittleConcurrent;
    }

    public Connection getConnection() {
        return connection;
    }

}
