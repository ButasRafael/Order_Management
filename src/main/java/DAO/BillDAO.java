package DAO;

import Model.Bill;

import java.sql.Connection;
import Connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This is a BillDAO class that handles the database operations for bills.
 * It uses the Connection class to interact with the database.
 */
public class BillDAO {

    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(BillDAO.class.getName());

    /**
     * This is a constructor that initializes a BillDAO object with the given connection.
     * @param connection The connection to the database.
     */
    public BillDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * This method inserts a bill into the database.
     * It uses a PreparedStatement to execute the INSERT query.
     * @param bill The bill to be inserted.
     */
    public void insert(Bill bill) {
        String query = "INSERT INTO `bill` (id, order_id, totalAmount, createdAt) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bill.id());
            statement.setInt(2, bill.orderId());
            statement.setDouble(3, bill.totalAmount());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(bill.createdAt()));

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error adding new bill", e);
        }
    }

    /**
     * This method generates a new id for a bill.
     * It retrieves all ids from the database and returns the smallest positive integer that is not in the database.
     * @return The new id.
     */
    public int generateNewId() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT id FROM `bill` ORDER BY id";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            int expectedId = 1;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                if (id != expectedId) {
                    return expectedId;
                }
                expectedId++;
            }
            return expectedId;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error generating new id for bill", e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 1;
    }

    /**
     * This method retrieves all bills from the database.
     * It uses a PreparedStatement to execute the SELECT query and creates a Bill object for each record in the ResultSet.
     * @return A list of all bills.
     */
    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM `bill`";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getInt("id"),
                        resultSet.getInt("order_id"),
                        resultSet.getDouble("totalAmount"),
                        resultSet.getTimestamp("createdAt").toLocalDateTime()
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error retrieving all records from bill", e);
        }

        return bills;
    }
}