package Model;

/**
 * This is an Order class that represents an order in the system.
 * It contains the id of the order, the id of the client who made the order, the id of the product ordered, and the quantity of the product ordered.
 */
public class Order {
    private int id;
    private int client_id;
    private int product_id;
    private int quantity;

    /**
     * This is a constructor that initializes an Order object with the given parameters.
     * @param id The id of the order.
     * @param client_id The id of the client who made the order.
     * @param product_id The id of the product ordered.
     * @param quantity The quantity of the product ordered.
     */
    public Order(int id, int client_id, int product_id, int quantity) {
        this.id = id;
        this.client_id = client_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    /**
     * This is a default constructor that initializes an Order object with no parameters.
     */
    public Order() {
    }

    /**
     * This is a constructor that initializes an Order object with the given parameters, excluding id.
     * @param client_id The id of the client who made the order.
     * @param product_id The id of the product ordered.
     * @param quantity The quantity of the product ordered.
     */
    public Order(int client_id, int product_id, int quantity) {
        this.client_id = client_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    /**
     * This method returns the id of the order.
     * @return The id of the order.
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets the id of the order.
     * @param id The id to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns the id of the client who made the order.
     * @return The id of the client.
     */
    public int getClient_id() {
        return client_id;
    }

    /**
     * This method returns the id of the product ordered.
     * @return The id of the product.
     */
    public int getProduct_id() {
        return product_id;
    }

    /**
     * This method sets the id of the client who made the order.
     * @param client_id The client id to be set.
     */
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    /**
     * This method sets the id of the product ordered.
     * @param product_id The product id to be set.
     */
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    /**
     * This method sets the quantity of the product ordered.
     * @param quantity The quantity to be set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * This method returns the quantity of the product ordered.
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This method returns a string representation of the order.
     * @return A string representation of the order.
     */
    @Override
    public String toString() {
        return "Order [id=" + id + ", client_id=" + client_id + ", product_id=" + product_id + ", quantity=" + quantity + "]";
    }
}