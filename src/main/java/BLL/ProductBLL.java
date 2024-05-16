package BLL;

import BLL.validators.ProductValidator;
import DAO.ProductDAO;
import Model.Product;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This is a ProductBLL class that handles the business logic for products.
 * It uses the ProductDAO class to interact with the database and the ProductValidator class to validate products.
 */
public class ProductBLL {

    private final ProductDAO productDAO;
    private final ProductValidator productValidator;

    /**
     * This is a constructor that initializes a ProductBLL object.
     * It initializes the productDAO and productValidator objects.
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
        productValidator = new ProductValidator();
    }

    /**
     * This method finds a product by its id.
     * It uses the ProductDAO class to find the product.
     * @param id The id of the product to be found.
     * @return The product with the given id.
     * @throws NoSuchElementException if the product with the given id is not found.
     */
    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id = " + id + " was not found!");
        }
        return product;
    }

    /**
     * This method inserts a product into the database.
     * It first generates a new id for the product, then validates the product using the ProductValidator class, and finally uses the ProductDAO class to insert the product into the database.
     * @param product The product to be inserted.
     */
    public void insertProduct(Product product) {
        int newId = productDAO.generateNewId();
        product.setId(newId);
        productValidator.validate(product, true);
        productDAO.insert(product);
    }

    /**
     * This method updates a product in the database.
     * It first validates the product using the ProductValidator class, then uses the ProductDAO class to update the product in the database.
     * @param product The product to be updated.
     */
    public void updateProduct(Product product) {
        productValidator.validate(product, false);
        productDAO.update(product, product.getId());
    }

    /**
     * This method deletes a product from the database.
     * It uses the ProductDAO class to delete the product from the database.
     * @param product The product to be deleted.
     * @throws IllegalArgumentException if the product does not exist.
     */
    public void deleteProduct(Product product) {
        if (product != null) {
            productDAO.delete(product.getId());
        } else {
            throw new IllegalArgumentException("Product does not exist!");
        }
    }

    /**
     * This method retrieves all products from the database.
     * It uses the ProductDAO class to retrieve all products from the database.
     * @return A list of all products in the database.
     */
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
}