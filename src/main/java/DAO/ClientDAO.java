package DAO;

import Connection.ConnectionFactory;
import Model.Client;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * This is a ClientDAO class that handles the database operations for clients.
 * It extends the AbstractDAO class and inherits its methods.
 * It uses the Connection class to interact with the database.
 */
public class ClientDAO extends AbstractDAO<Client> {
    /**
     * This is a constructor that initializes a ClientDAO object.
     * It calls the constructor of the superclass with the Client class as the parameter.
     */
    public ClientDAO() {
        super(Client.class);
    }
    /**
     * This method checks if a client with the given email exists in the database.
     * @param email The email of the client.
     * @return true if the client exists, false otherwise.
     */
    public boolean doesEmailExist(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT 1 FROM `client` WHERE email = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error checking email in client", e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }
    /**
     * This method checks if a client with the given phone number exists in the database.
     * @param phone The phone number of the client.
     * @return true if the client exists, false otherwise.
     */
    public boolean doesPhoneExist(String phone) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT 1 FROM `client` WHERE phone = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, phone);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error checking phone in client", e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }
    /**
     * This method checks if the given email is owned by a client with the given id.
     * @param email The email of the client.
     * @param clientId The id of the client.
     * @return true if the email is owned by the client, false otherwise.
     */
    public boolean isEmailOwnedByClientId(String email, int clientId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT id FROM `client` WHERE email = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id") == clientId;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error checking email ownership", e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }
    /**
     * This method checks if the given phone number is owned by a client with the given id.
     * @param phone The phone number of the client.
     * @param clientId The id of the client.
     * @return true if the phone number is owned by the client, false otherwise.
     */
    public boolean isPhoneOwnedByClientId(String phone, int clientId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT id FROM `client` WHERE phone = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, phone);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id") == clientId;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error checking phone ownership", e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }
}
