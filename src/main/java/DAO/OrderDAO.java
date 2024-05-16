package DAO;

import Model.Order;

/**
 * This is an OrderDAO class that handles the database operations for orders.
 * It extends the AbstractDAO class and inherits its methods.
 */
public class OrderDAO extends AbstractDAO<Order> {

    /**
     * This is a constructor that initializes an OrderDAO object.
     * It calls the constructor of the superclass with the Order class as the parameter.
     */
    public OrderDAO() {
        super(Order.class);
    }

}
