package DAO;

import Connection.ConnectionFactory;
import Model.Product;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * This is a ProductDAO class that handles the database operations for products.
 * It extends the AbstractDAO class and inherits its methods.
 * It uses the Connection class to interact with the database.
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * This is a constructor that initializes a ProductDAO object.
     * It calls the constructor of the superclass with the Product class as the parameter.
     */
    public ProductDAO() {
        super(Product.class);
    }

    /**
     * This is a constructor that initializes a ProductDAO object.
     * It calls the constructor of the superclass with the Product class as the parameter.
     */
    public boolean doesNameExist(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT 1 FROM `product` WHERE name = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error checking name in product", e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }
    /**
     * This method checks if the given name is owned by a product with the given id.
     * @param name The name of the product.
     * @param productId The id of the product.
     * @return true if the name is owned by the product, false otherwise.
     */
    public boolean isNameOwnedByProductId(String name, int productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT id FROM `product` WHERE name = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id") == productId;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error checking name ownership", e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }
}