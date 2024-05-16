package BLL.validators;

import DAO.ClientDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Order;

/**
 * This is an OrderValidator class that validates the details of an order.
 * It implements the Validator interface with Order as the type parameter.
 */
public class OrderValidator implements Validator<Order> {
    private static final double MIN_QUANTITY = 0.0;
    private final ClientDAO clientDAO;
    private final ProductDAO productDAO;
    private final OrderDAO orderDAO;

    /**
     * This is a constructor that initializes an OrderValidator object.
     * It initializes the clientDAO, productDAO, and orderDAO objects.
     */
    public OrderValidator() {
        clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();
    }

    /**
     * This method validates the details of an order.
     * @param order The order to be validated.
     * @param isInsert A boolean value that indicates whether the operation is an insert operation.
     * @throws IllegalArgumentException if the order's quantity is less than or equal to MIN_QUANTITY, or if the client's id or product's id does not exist, or if the order's id already exists.
     */
    public void validate(Order order, boolean isInsert) {
        if(order.getQuantity() <= MIN_QUANTITY) {
            throw new IllegalArgumentException("Order quantity must be greater than " + MIN_QUANTITY);
        }
        if(!clientDAO.doesIdExist(order.getClient_id())) {
            throw new IllegalArgumentException("Client id does not exist!");
        }
        if(!productDAO.doesIdExist(order.getProduct_id())) {
            throw new IllegalArgumentException("Product id does not exist!");
        }
        if (isInsert&&orderDAO.doesIdExist(order.getId())) {
            throw new IllegalArgumentException("Id already exists!");
        }
    }
}
