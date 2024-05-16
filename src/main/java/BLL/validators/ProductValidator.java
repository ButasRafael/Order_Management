package BLL.validators;

import DAO.ProductDAO;
import Model.Product;

import java.util.regex.Pattern;

/**
 * This is a ProductValidator class that validates the details of a product.
 * It implements the Validator interface with Product as the type parameter.
 */
public class ProductValidator implements Validator<Product> {
    private static final double MIN_PRICE = 0.0;
    private static final int MIN_STOCK = 0;
    private static final String PRODUCT_NAME_PATTERN = "^[\\p{L} _-]+$";
    private final ProductDAO productDAO;

    /**
     * This is a constructor that initializes a ProductValidator object.
     * It initializes the productDAO object.
     */
    public ProductValidator() {
        productDAO = new ProductDAO();
    }

    /**
     * This method validates the details of a product.
     * @param product The product to be validated.
     * @param isInsert A boolean value that indicates whether the operation is an insert operation.
     * @throws IllegalArgumentException if the product's price is less than or equal to MIN_PRICE, or if the product's stock is less than MIN_STOCK, or if the product's name is not valid, or if the product's id or name already exists.
     */
    public void validate(Product product, boolean isInsert) {
        if (product.getPrice() <= MIN_PRICE) {
            throw new IllegalArgumentException("Price must be greater than " + MIN_PRICE);
        }
        if (product.getStock() < MIN_STOCK) {
            throw new IllegalArgumentException("Product stock cannot be negative!");
        }
        Pattern pattern = Pattern.compile(PRODUCT_NAME_PATTERN);
        if (!pattern.matcher(product.getName()).matches()) {
            throw new IllegalArgumentException("Product name is not valid!");
        }
        if (isInsert && productDAO.doesIdExist(product.getId())) {
            throw new IllegalArgumentException("Id already exists!");
        }

        if(productDAO.doesNameExist(product.getName())) {
            if (isInsert || !productDAO.isNameOwnedByProductId(product.getName(), product.getId())) {
                throw new IllegalArgumentException("Product name already exists");
            }
        }
    }
}