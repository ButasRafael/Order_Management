package BLL;

import BLL.validators.BillValidator;
import DAO.BillDAO;
import Model.Bill;
import java.sql.Connection;
import Connection.ConnectionFactory;
import java.util.List;

/**
 * This is a BillBLL class that handles the business logic for bills.
 * It uses the BillDAO class to interact with the database and the BillValidator class to validate bills.
 */
public class BillBLL {

    private final BillDAO billDAO;

    /**
     * This is a constructor that initializes a BillBLL object.
     * It establishes a connection to the database and initializes the billDAO object.
     */
    public BillBLL() {
        Connection connection = ConnectionFactory.getConnection();
        billDAO = new BillDAO(connection);
    }

    /**
     * This method inserts a bill into the database.
     * It first validates the bill using the BillValidator class, then uses the BillDAO class to insert the bill into the database.
     * @param bill The bill to be inserted.
     */
    public void insertBill(Bill bill) {
        BillValidator.validate(bill.totalAmount(), bill.createdAt());
        billDAO.insert(bill);
    }

    /**
     * This method retrieves all bills from the database.
     * It uses the BillDAO class to retrieve all bills from the database.
     * @return A list of all bills in the database.
     */
    public List<Bill> getAllBills() {
        return billDAO.findAll();
    }
}