package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.DatabaseConfig;

public class DatabaseConnection {

    /**
     * Establishes and returns a connection to the database using the configuration
     * provided by {@link DatabaseConfig}.
     *
     * <p>This method uses {@link DriverManager#getConnection(String, String, String)}
     * to create the connection.</p>
     *
     * @return a {@link Connection} object representing the database connection
     * @throws RuntimeException if a {@link SQLException} occurs while trying to connect
     */
    public static Connection getConnection() {
        Connection dbConnection = null;
        try {
            /*Class.forName("com.mysql.cj.jdbc.Driver");
             Since JDBC 4+, the driver registers automatically; Class.forName is not required*/

            dbConnection = DriverManager.getConnection(
                    DatabaseConfig.getDbUrl(),
                    DatabaseConfig.getDbUser(),
                    DatabaseConfig.getDbPassword());

        } catch (SQLException e) {
            System.out.println("Failed to establish database connection: " + e.getMessage());
            throw new RuntimeException("Unable to connect to the database", e);
        }
        return dbConnection;
    }

}
