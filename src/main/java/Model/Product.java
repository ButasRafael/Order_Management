package Model;

/**
 * This is a Product class that represents a product in the system.
 * It contains the id, name, price, and stock of the product.
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    /**
     * This is a constructor that initializes a Product object with the given parameters.
     * @param id The id of the product.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param stock The stock of the product.
     */
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * This is a default constructor that initializes a Product object with no parameters.
     */
    public Product() {
    }

    /**
     * This is a constructor that initializes a Product object with the given parameters, excluding id.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param stock The stock of the product.
     */
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * This method returns the id of the product.
     * @return The id of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets the id of the product.
     * @param id The id to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns the name of the product.
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the product.
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the price of the product.
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method sets the price of the product.
     * @param price The price to be set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method returns the stock of the product.
     * @return The stock of the product.
     */
    public int getStock() {
        return stock;
    }

    /**
     * This method sets the stock of the product.
     * @param stock The stock to be set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * This method returns a string representation of the product.
     * @return A string representation of the product.
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + stock + "]";
    }
}