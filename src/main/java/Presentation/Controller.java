package Presentation;

import BLL.BillBLL;
import BLL.ClientBLL;
import BLL.OrderBLL;
import BLL.ProductBLL;
import Model.Bill;
import Model.Client;
import Model.Order;
import Model.Product;

import java.util.List;

/**
 * This is a Controller class that handles the interaction between the View and the Business Logic Layer (BLL).
 * It contains instances of the BLL classes and the View, and it defines the actions performed when the buttons in the View are clicked.
 */
public class Controller {

    private final View view;
    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;
    private final OrderBLL orderBLL;
    private final BillBLL billBLL;

    /**
     * This is a constructor that initializes a Controller object with the given View.
     * It also initializes the BLL objects and defines the actions performed when the buttons in the View are clicked.
     * @param view The View to be controlled.
     */

    public Controller(View view) {
        this.clientBLL = new ClientBLL();
        this.productBLL = new ProductBLL();
        this.orderBLL = new OrderBLL();
        this.billBLL = new BillBLL();
        this.view = view;

        view.getManageClientsButton().addActionListener(_ -> view.showManageClientsPage());
        view.getManageProductsButton().addActionListener(_ -> view.showManageProductsPage());
        view.getManageOrdersButton().addActionListener(_ -> view.showManageOrdersPage());

        view.getBackFromClientsButton().addActionListener(_ -> view.showMainPage());
        view.getBackFromProductsButton().addActionListener(_ -> view.showMainPage());
        view.getBackFromOrdersButton().addActionListener(_ -> view.showMainPage());

        view.getAddClientButton().addActionListener(_ -> view.showAddClientDialog());

        view.getAddClientDialogButton().addActionListener(_ -> {
            String name = view.getAddClientNameField().getText();
            String address = view.getAddClientAddressField().getText();
            String phone = view.getAddClientPhoneField().getText();
            String email = view.getAddClientEmailField().getText();
            String ageStr = view.getAddClientAgeField().getText();

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty() || ageStr.isEmpty()) {
                view.showError("All fields must be filled!");
            } else {
                try {
                    int age = Integer.parseInt(ageStr);
                    try {
                        clientBLL.insertClient(new Client(name, address, email, age, phone));
                    } catch (IllegalArgumentException e) {
                        view.showError(e.getMessage());
                        return;
                    }
                    view.hideAddClientDialog();
                    view.showSuccess("Client added successfully!");
                    view.resetAddClientFields();
                } catch (NumberFormatException e) {
                    view.showError("Age must be an integer!");
                }
            }
        });

        view.getEditClientButton().addActionListener(_ -> {
            view.getEditClientComboBox().removeAllItems();
            for (Client client : clientBLL.getAllClients()) {
                view.getEditClientComboBox().addItem(client);
            }
            view.getEditClientComboBox().setSelectedItem(null);
            view.resetEditClientFields();
            view.showEditClientDialog();
        });

        view.getEditClientDialogButton().addActionListener(_ -> {
            Client selectedClient = (Client) view.getEditClientComboBox().getSelectedItem();
            if (selectedClient != null) {
                String name = view.getEditClientNameField().getText();
                String address = view.getEditClientAddressField().getText();
                String phone = view.getEditClientPhoneField().getText();
                String email = view.getEditClientEmailField().getText();
                String ageStr = view.getEditClientAgeField().getText();

                if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty() || ageStr.isEmpty()) {
                    view.showError("All fields must be filled!");
                } else {
                    try {
                        int age = Integer.parseInt(ageStr);
                        selectedClient.setName(name);
                        selectedClient.setAddress(address);
                        selectedClient.setPhone(phone);
                        selectedClient.setEmail(email);
                        selectedClient.setAge(age);
                        try {
                            clientBLL.updateClient(selectedClient);
                        } catch (IllegalArgumentException e) {
                            view.showError(e.getMessage());
                            return;
                        }
                        view.hideEditClientDialog();
                        view.showSuccess("Client updated successfully!");
                        view.resetEditClientFields();
                    } catch (NumberFormatException e) {
                        view.showError("Age must be an integer!");
                    }
                }
            } else {
                view.showError("All fields must be filled!");
            }
        });


        view.getAddProductButton().addActionListener(_ -> view.showAddProductDialog());

        view.getAddProductDialogButton().addActionListener(_ -> {
            String name = view.getAddProductNameField().getText();
            String priceStr = view.getAddProductPriceField().getText();
            String stockStr = view.getAddProductStockField().getText();
            if (name.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
                view.showError("All fields must be filled!");
            } else {
                try {
                    double price = Double.parseDouble(priceStr);
                    int stock = Integer.parseInt(stockStr);
                    try {
                        productBLL.insertProduct(new Product(name, price, stock));
                    } catch (IllegalArgumentException e) {
                        view.showError(e.getMessage());
                        return;
                    }
                    view.hideAddProductDialog();
                    view.showSuccess("Product added successfully!");
                    view.resetAddProductFields();
                } catch (NumberFormatException e) {
                    view.showError("Price must be a number and stock must be an integer!");
                }
            }
        });

        view.getEditProductButton().addActionListener(_ -> {
            view.getEditProductComboBox().removeAllItems();
            for (Product product : productBLL.getAllProducts()) {
                view.getEditProductComboBox().addItem(product);
            }
            view.getEditProductComboBox().setSelectedItem(null);
            view.resetEditProductFields();
            view.showEditProductDialog();
        });

        view.getEditProductDialogButton().addActionListener(_ -> {
            Product selectedProduct = (Product) view.getEditProductComboBox().getSelectedItem();
            if (selectedProduct != null) {
                String name = view.getEditProductNameField().getText();
                String priceStr = view.getEditProductPriceField().getText();
                String stockStr = view.getEditProductStockField().getText();
                if (name.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
                    view.showError("All fields must be filled!");
                } else {
                    try {
                        double price = Double.parseDouble(priceStr);
                        int stock = Integer.parseInt(stockStr);
                        selectedProduct.setName(name);
                        selectedProduct.setPrice(price);
                        selectedProduct.setStock(stock);
                        try {
                            productBLL.updateProduct(selectedProduct);
                        } catch (IllegalArgumentException e) {
                            view.showError(e.getMessage());
                            return;
                        }
                        view.hideEditProductDialog();
                        view.showSuccess("Product updated successfully!");
                        view.resetEditProductFields();
                    } catch (NumberFormatException e) {
                        view.showError("Price and stock must be valid numbers!");
                    }
                }
            } else {
                view.showError("All fields must be filled!");
            }
        });

        view.getEditClientComboBox().addActionListener(_ -> {
            Client selectedClient = (Client) view.getEditClientComboBox().getSelectedItem();
            if (selectedClient != null) {
                view.getEditClientNameField().setText(selectedClient.getName());
                view.getEditClientPhoneField().setText(selectedClient.getPhone());
                view.getEditClientAddressField().setText(selectedClient.getAddress());
                view.getEditClientEmailField().setText(selectedClient.getEmail());
                view.getEditClientAgeField().setText(String.valueOf(selectedClient.getAge()));

            }
        });

        view.getEditProductComboBox().addActionListener(_ -> {
            Product selectedProduct = (Product) view.getEditProductComboBox().getSelectedItem();
            if (selectedProduct != null) {
                view.getEditProductNameField().setText(selectedProduct.getName());
                view.getEditProductPriceField().setText(String.valueOf(selectedProduct.getPrice()));
                view.getEditProductStockField().setText(String.valueOf(selectedProduct.getStock()));
            }
        });

        view.getDeleteClientButton().addActionListener(_ -> {
            view.getDeleteClientComboBox().removeAllItems();
            for (Client client : clientBLL.getAllClients()) {
                view.getDeleteClientComboBox().addItem(client);
            }
            view.getDeleteClientComboBox().setSelectedItem(null);
            view.showDeleteClientDialog();
        });

        view.getDeleteProductButton().addActionListener(_ -> {
            view.getDeleteProductComboBox().removeAllItems();
            for (Product product : productBLL.getAllProducts()) {
                view.getDeleteProductComboBox().addItem(product);
            }
            view.getDeleteProductComboBox().setSelectedItem(null);
            view.showDeleteProductDialog();
        });

        view.getDeleteClientDialogButton().addActionListener(_ -> {
            Client selectedClient = (Client) view.getDeleteClientComboBox().getSelectedItem();
            if (selectedClient == null) {
                view.showError("All fields must be filled!");
            } else {
                if (view.showConfirmDialog("Are You Sure About Deleting This Client?")) {
                    clientBLL.deleteClient(selectedClient);
                    view.hideDeleteClientDialog();
                    view.showSuccess("Client deleted successfully!");
                }
            }
        });

        view.getDeleteProductDialogButton().addActionListener(_ -> {
            Product selectedProduct = (Product) view.getDeleteProductComboBox().getSelectedItem();
            if (selectedProduct == null) {
                view.showError("All fields must be filled!");
            } else {
                if (view.showConfirmDialog("Are You Sure About Deleting This Product?")) {
                    productBLL.deleteProduct(selectedProduct);
                    view.hideDeleteProductDialog();
                    view.showSuccess("Product deleted successfully!");
                }
            }
        });

        view.getCreateOrderButton().addActionListener(_ -> {
            view.getCreateOrderClientComboBox().removeAllItems();
            for (Client client : clientBLL.getAllClients()) {
                view.getCreateOrderClientComboBox().addItem(client);
            }
            view.getCreateOrderProductComboBox().removeAllItems();
            for (Product product : productBLL.getAllProducts()) {
                view.getCreateOrderProductComboBox().addItem(product);
            }
            view.getCreateOrderClientComboBox().setSelectedItem(null);
            view.getCreateOrderProductComboBox().setSelectedItem(null);
            view.showCreateOrderDialog();
        });

        view.getCreateOrderDialogButton().addActionListener(_ -> {
            Client selectedClient = (Client) view.getCreateOrderClientComboBox().getSelectedItem();
            Product selectedProduct = (Product) view.getCreateOrderProductComboBox().getSelectedItem();
            String quantityStr = view.getCreateOrderQuantityField().getText();
            if (quantityStr.isEmpty() || selectedClient == null || selectedProduct == null) {
                view.showError("All fields must be filled!");
            } else {
                try {
                    int quantity = Integer.parseInt(quantityStr);
                    try {
                        orderBLL.insertOrder(new Order(selectedClient.getId(), selectedProduct.getId(), quantity));
                    } catch (IllegalArgumentException e) {
                        view.showError(e.getMessage());
                        return;
                    }
                    view.hideCreateOrderDialog();
                    view.showSuccess("Order created successfully!");
                    view.resetCreateOrderFields();
                } catch (NumberFormatException e) {
                    view.showError("Quantity must be an integer!");
                }
            }
        });

        view.getViewAllClientsButton().addActionListener(_ -> viewAllClients());
        view.getViewAllProductsButton().addActionListener(_ -> viewAllProducts());
        view.getViewAllOrdersButton().addActionListener(_ -> viewAllOrders());
        view.getViewAllBillsButton().addActionListener(_ -> viewAllBills());

    }
    /**
     * This method retrieves all clients from the database and displays them in the View.
     */
    private void viewAllClients() {
        List<Client> clients = clientBLL.getAllClients();
        view.showAllClients(clients);
    }
    /**
     * This method retrieves all products from the database and displays them in the View.
     */
    private void viewAllProducts() {
        List<Product> products = productBLL.getAllProducts();
        view.showAllProducts(products);
    }
    /**
     * This method retrieves all orders from the database and displays them in the View.
     */
    public void viewAllOrders() {
        List<Order> orders = orderBLL.getAllOrders();
        view.showAllOrders(orders);
    }
    /**
     * This method retrieves all bills from the database and displays them in the View.
     */
    private void viewAllBills() {
        List<Bill> bills = billBLL.getAllBills();
        view.showAllBills(bills);
    }

}
