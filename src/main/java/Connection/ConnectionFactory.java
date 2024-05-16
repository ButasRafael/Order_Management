package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a ConnectionFactory class that handles the database connection.
 * It uses the DriverManager class to establish a connection to the database.
 */
public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/orders_management";
    private static final String USER = "root";
    private static final String PASS = "Rafaelito123B!";

    private static final ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * This is a private constructor that initializes a ConnectionFactory object.
     * It loads the JDBC driver.
     * @throws RuntimeException if the JDBC driver is not found.
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Driver not found", e);
            throw new RuntimeException("Driver not found", e);
        }
    }

    /**
     * This method creates a connection to the database.
     * @return A connection to the database.
     * @throws RuntimeException if an error occurs while connecting to the database.
     */
    private Connection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error connecting to the database", e);
            throw new RuntimeException("Error connecting to the database", e);
        }
        return connection;
    }

    /**
     * This method gets a connection to the database.
     * @return A connection to the database.
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * This method closes a connection to the database.
     * @param connection The connection to be closed.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the connection", e);
            }
        }
    }

    /**
     * This method closes a statement.
     * @param statement The statement to be closed.
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the statement", e);
            }
        }
    }

    /**
     * This method closes a ResultSet.
     * @param resultSet The ResultSet to be closed.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the ResultSet", e);
            }
        }
    }
}