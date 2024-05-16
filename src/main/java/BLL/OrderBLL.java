package BLL;

import BLL.validators.OrderValidator;
import DAO.BillDAO;
import DAO.OrderDAO;
import Model.Bill;
import Model.Order;
import Model.Product;

import java.time.LocalDateTime;
import java.util.List;

import Connection.ConnectionFactory;

/**
 * This is an OrderBLL class that handles the business logic for orders.
 * It uses the OrderDAO class to interact with the database, the BillDAO class to interact with the bills in the database, and the OrderValidator class to validate orders.
 */
public class OrderBLL {

    private final OrderDAO orderDAO;
    private final BillDAO billDAO;
    private final OrderValidator orderValidator;

    /**
     * This is a constructor that initializes an OrderBLL object.
     * It initializes the orderDAO, billDAO, and orderValidator objects.
     */
    public OrderBLL() {
        orderDAO = new OrderDAO();
        billDAO = new BillDAO(ConnectionFactory.getConnection());
        orderValidator = new OrderValidator();
    }

    /**
     * This method inserts an order into the database.
     * It first finds the product associated with the order, checks if there is enough stock for the order, generates a new id for the order, validates the order, and finally inserts the order into the database.
     * It also updates the stock of the product and inserts a new bill into the database.
     * @param order The order to be inserted.
     * @throws IllegalArgumentException if there is not enough stock for the order.
     */
    public void insertOrder(Order order) {
        ProductBLL productBLL = new ProductBLL();
        Product product = productBLL.findProductById(order.getProduct_id());

        if (product.getStock() < order.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock!");
        }

        int newId = orderDAO.generateNewId();
        order.setId(newId);
        orderValidator.validate(order, true);
        orderDAO.insert(order);

        product.setStock(product.getStock() - order.getQuantity());
        productBLL.updateProduct(product);
        int newBillId = billDAO.generateNewId();
        Bill bill = new Bill(newBillId, order.getId(), order.getQuantity() * product.getPrice(), LocalDateTime.now());
        BillBLL billBLL = new BillBLL();
        billBLL.insertBill(bill);
    }

    /**
     * This method retrieves all orders from the database.
     * It uses the OrderDAO class to retrieve all orders from the database.
     * @return A list of all orders in the database.
     */
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }
}