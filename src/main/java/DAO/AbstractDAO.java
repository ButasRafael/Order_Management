package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import Connection.ConnectionFactory;

/**
 * This is an AbstractDAO class that provides the basic CRUD operations for a given type.
 * It uses reflection to dynamically create and manipulate objects of the given type.
 * @param <T> The type of the objects.
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;
    /**
     * This is a constructor that initializes an AbstractDAO object with the given type.
     * @param type The type of the objects.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> type) {
        this.type = type;
    }

    /**
     * This method creates a SELECT query for the given field.
     * @param field The field to be selected.
     * @return The SELECT query.
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM `");
        sb.append(type.getSimpleName());
        sb.append("` WHERE ").append(field).append(" = ?");
        return sb.toString();
    }
    /**
     * This method generates a new id for a record.
     * It retrieves all ids from the database and returns the smallest positive integer that is not in the database.
     * @return The new id.
     */
    public int generateNewId() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT id FROM `" + type.getSimpleName() + "` ORDER BY id";
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
            LOGGER.log(Level.WARNING, "Error generating new id for " + type.getSimpleName(), e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 1;
    }
    /**
     * This method retrieves all records from the database.
     * @return A list of all records.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<>();
        String query = "SELECT * FROM `" + type.getSimpleName() + "`";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(createObject(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error retrieving all records from " + type.getSimpleName(), e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return list;
    }
    /**
     * This method finds a record by its id.
     * @param id The id of the record.
     * @return The record with the given id, or null if no such record exists.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createObject(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error finding record by id in " + type.getSimpleName(), e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * This method creates an object from a ResultSet.
     * It uses reflection to dynamically create an object and set its fields.
     * @param resultSet The ResultSet.
     * @return The created object, or null if an error occurs.
     * @throws SQLException if an error occurs while reading from the ResultSet.
     */
    protected T createObject(ResultSet resultSet) throws SQLException {
        try {
            T instance = type.getDeclaredConstructor().newInstance();
            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();
                Object value;
                Class<?> fieldType = field.getType();
                if (fieldType == int.class) {
                    value = resultSet.getInt(fieldName);
                } else if (fieldType == double.class) {
                    value = resultSet.getDouble(fieldName);
                } else if (fieldType == String.class) {
                    value = resultSet.getString(fieldName);
                } else {
                    value = resultSet.getObject(fieldName);
                }
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                Method method = propertyDescriptor.getWriteMethod();
                method.invoke(instance, value);
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException | IntrospectionException e) {
            LOGGER.log(Level.WARNING, "Error creating object of type " + type.getSimpleName(), e);
        }
        return null;
    }
    /**
     * This method inserts a record into the database.
     * It uses reflection to dynamically get the values of the fields of the object.
     * @param t The object to be inserted.
     */
    public void insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "INSERT INTO `" + type.getSimpleName() + "`" + " VALUES (";
        StringBuilder sb = new StringBuilder(query);
        try {
            connection = ConnectionFactory.getConnection();
            for (Field field : type.getDeclaredFields()) {
                sb.append("?, ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(")");
            query = sb.toString();
            statement = connection.prepareStatement(query);
            int parameterIndex = 1;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true); // Make the field accessible
                Class<?> fieldType = field.getType();
                if (fieldType == int.class) {
                    statement.setInt(parameterIndex, field.getInt(t));
                } else if (fieldType == double.class) {
                    statement.setDouble(parameterIndex, field.getDouble(t));
                } else if (fieldType == String.class) {
                    statement.setString(parameterIndex, (String) field.get(t));
                } else {
                    statement.setObject(parameterIndex, field.get(t));
                }
                parameterIndex++;
            }
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Error inserting record into " + type.getSimpleName(), e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * This method updates a record in the database.
     * It uses reflection to dynamically get the values of the fields of the object.
     * @param t The object to be updated.
     * @param id The id of the record to be updated.
     */
    public void update(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "UPDATE `" + type.getSimpleName() + "`" + " SET ";
        StringBuilder sb = new StringBuilder(query);
        try {
            connection = ConnectionFactory.getConnection();
            for (Field field : type.getDeclaredFields()) {
                if (!field.getName().equals("id")) {
                    sb.append(field.getName()).append(" = ?, ");
                }
            }
            sb.setLength(sb.length() - 2);
            sb.append(" WHERE id = ?");
            query = sb.toString();
            statement = connection.prepareStatement(query);
            int parameterIndex = 1;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.getName().equals("id")) {
                    Class<?> fieldType = field.getType();
                    if (fieldType == int.class) {
                        statement.setInt(parameterIndex, field.getInt(t));
                    } else if (fieldType == double.class) {
                        statement.setDouble(parameterIndex, field.getDouble(t));
                    } else if (fieldType == String.class) {
                        statement.setString(parameterIndex, (String) field.get(t));
                    } else {
                        statement.setObject(parameterIndex, field.get(t));
                    }
                    parameterIndex++;
                }
            }
            statement.setInt(parameterIndex, id);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, "Error updating record in " + type.getSimpleName(), e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * This method deletes a record from the database.
     * @param id The id of the record to be deleted.
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM`" + type.getSimpleName() + "`" + " WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error deleting record from " + type.getSimpleName(), e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * This method checks if a record with the given id exists in the database.
     * @param id The id of the record.
     * @return true if the record exists, false otherwise.
     */
    public boolean doesIdExist(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT 1 FROM`" + type.getSimpleName() + "`" + " WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error checking id in " + type.getSimpleName(), e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }

}
