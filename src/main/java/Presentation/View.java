package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import Model.Bill;
import Model.Client;
import Model.Order;
import Model.Product;

/**
 * This is a View class that represents the user interface of the application.
 * It extends JFrame and contains various components such as buttons, text fields, and dialogs for managing clients, products, and orders.
 * It also provides methods for showing and hiding these components, resetting text fields, and displaying messages to the user.
 */
public class View extends JFrame {


    private static final Logger LOGGER = Logger.getLogger(View.class.getName());
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color BUTTON_COLOR = new Color(52, 152, 219);
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185);
    private static final Font MAIN_FONT = new Font("SansSerif", Font.PLAIN, 16);
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 24);

    private final JButton manageClientsButton = createStyledButton("Manage Clients");
    private final JButton manageProductsButton = createStyledButton("Manage Products");
    private final JButton manageOrdersButton = createStyledButton("Manage Orders");
    private final JButton viewAllBillsButton = createStyledButton("View Bills");

    private final JButton addClientButton = createStyledButton("Add Client", "icons/addClient.png");
    private final JButton editClientButton = createStyledButton("Edit Client", "icons/editClient.png");
    private final JButton deleteClientButton = createStyledButton("Delete Client", "icons/deleteClient.png");
    private final JButton viewAllClientsButton = createStyledButton("View All Clients", "icons/viewClients.png");

    private final JButton addProductButton = createStyledButton("Add Product", "icons/addProduct.png");
    private final JButton editProductButton = createStyledButton("Edit Product", "icons/editProduct.png");
    private final JButton deleteProductButton = createStyledButton("Delete Product", "icons/deleteProduct.png");
    private final JButton viewAllProductsButton = createStyledButton("View All Products", "icons/viewProducts.png");

    private final JButton backFromClientsButton = createStyledButton("Back", "icons/back.png");
    private final JButton backFromProductsButton = createStyledButton("Back", "icons/back.png");
    private final JButton backFromOrdersButton = createStyledButton("Back", "icons/back.png");

    private final JDialog addClientDialog = new JDialog(this, "Add Client", true);
    private final JTextField addClientNameField = createStyledTextField();
    private final JTextField addClientAddressField = createStyledTextField();
    private final JTextField addClientEmailField = createStyledTextField();
    private final JTextField addClientAgeField = createStyledTextField();
    private final JTextField addClientPhoneField = createStyledTextField();
    private final JButton addClientDialogButton = createStyledButton("Add");

    private final JDialog editClientDialog = new JDialog(this, "Edit Client", true);
    private final JTextField editClientNameField = createStyledTextField();
    private final JTextField editClientAddressField = createStyledTextField();
    private final JTextField editClientEmailField = createStyledTextField();
    private final JTextField editClientAgeField = createStyledTextField();
    private final JTextField editClientPhoneField = createStyledTextField();
    private final JComboBox<Client> editClientComboBox = new JComboBox<>();
    private final JButton editClientDialogButton = createStyledButton("Edit");

    private final JDialog addProductDialog = new JDialog(this, "Add Product", true);
    private final JTextField addProductNameField = createStyledTextField();
    private final JTextField addProductPriceField = createStyledTextField();
    private final JTextField addProductStockField = createStyledTextField();
    private final JButton addProductDialogButton = createStyledButton("Add");

    private final JDialog editProductDialog = new JDialog(this, "Edit Product", true);
    private final JTextField editProductNameField = createStyledTextField();
    private final JTextField editProductPriceField = createStyledTextField();
    private final JTextField editProductStockField = createStyledTextField();
    private final JComboBox<Product> editProductComboBox = new JComboBox<>();
    private final JButton editProductDialogButton = createStyledButton("Edit");

    private final JDialog deleteClientDialog = new JDialog(this, "Delete Client", true);
    private final JComboBox<Client> deleteClientComboBox = new JComboBox<>();
    private final JButton deleteClientDialogButton = createStyledButton("Delete");

    private final JDialog deleteProductDialog = new JDialog(this, "Delete Product", true);
    private final JComboBox<Product> deleteProductComboBox = new JComboBox<>();
    private final JButton deleteProductDialogButton = createStyledButton("Delete");

    private final JButton createOrderButton = createStyledButton("Create Order", "icons/addOrder.png");
    private final JButton viewAllOrdersButton = createStyledButton("View All Orders", "icons/viewOrders.png");
    private final JDialog createOrderDialog = new JDialog(this, "Add Order", true);
    private final JComboBox<Client> createOrderClientComboBox = new JComboBox<>();
    private final JComboBox<Product> createOrderProductComboBox = new JComboBox<>();
    private final JTextField createOrderQuantityField = createStyledTextField();
    private final JButton createOrderDialogButton = createStyledButton("Add Order");

    /**
     * This is the constructor of the View class.
     * It sets the title, size, location, and default close operation of the frame, and initializes the main panel and the panels for managing clients, products, and orders.
     * It also initializes the dialogs used for adding, editing, and deleting clients and products, and for creating orders.
     * Finally, it shows the main page of the application.
     */
    public View() {
        setTitle("Order Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        initMainPanel();
        initManageClientsPanel();
        initManageProductsPanel();
        initManageOrdersPanel();
        initDialogs();

        showMainPage();
    }

    /**
     * This method initializes the main panel of the application.
     */
    private void initMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(mainPanel, "Main Page");

        JLabel titleLabel = new JLabel("Order Management System", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.add(manageClientsButton);
        buttonPanel.add(manageProductsButton);
        buttonPanel.add(manageOrdersButton);
        buttonPanel.add(viewAllBillsButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * This method initializes the panel for managing clients.
     */
    private void initManageClientsPanel() {
        JPanel manageClientsPanel = new JPanel(new BorderLayout(10, 10));
        manageClientsPanel.setBackground(BACKGROUND_COLOR);
        manageClientsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(manageClientsPanel, "Manage Clients");

        JLabel titleLabel = new JLabel("Manage Clients", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        manageClientsPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.add(addClientButton);
        buttonPanel.add(editClientButton);
        buttonPanel.add(deleteClientButton);
        buttonPanel.add(viewAllClientsButton);
        buttonPanel.add(backFromClientsButton);
        manageClientsPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * This method initializes the panel for managing products.
     */
    private void initManageProductsPanel() {
        JPanel manageProductsPanel = new JPanel(new BorderLayout(10, 10));
        manageProductsPanel.setBackground(BACKGROUND_COLOR);
        manageProductsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(manageProductsPanel, "Manage Products");

        JLabel titleLabel = new JLabel("Manage Products", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        manageProductsPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.add(addProductButton);
        buttonPanel.add(editProductButton);
        buttonPanel.add(deleteProductButton);
        buttonPanel.add(viewAllProductsButton);
        buttonPanel.add(backFromProductsButton);
        manageProductsPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * This method initializes the panel for managing orders.
     */
    private void initManageOrdersPanel() {
        JPanel manageOrdersPanel = new JPanel(new BorderLayout(10, 10));
        manageOrdersPanel.setBackground(BACKGROUND_COLOR);
        manageOrdersPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(manageOrdersPanel, "Manage Orders");

        JLabel titleLabel = new JLabel("Manage Orders", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        manageOrdersPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.add(createOrderButton);
        buttonPanel.add(viewAllOrdersButton);
        buttonPanel.add(backFromOrdersButton);
        manageOrdersPanel.add(buttonPanel, BorderLayout.CENTER);
    }
    /**
     * This method initializes the dialogs used for adding, editing, and deleting clients and products, and for creating orders.
     */
    private void initDialogs() {
        setupDialog(addClientDialog, "Add Client", new String[]{"Name:", "Address:", "Email:", "Age:", "Phone:"},
                new JTextField[]{addClientNameField, addClientAddressField, addClientEmailField, addClientAgeField, addClientPhoneField},
                addClientDialogButton);

        setupDialog(editClientDialog, "Edit Client", new String[]{"Select Client:", "Name:", "Address:", "Email:", "Age:", "Phone:"},
                new Component[]{editClientComboBox, editClientNameField, editClientAddressField, editClientEmailField, editClientAgeField, editClientPhoneField},
                editClientDialogButton);

        setupDialog(addProductDialog, "Add Product", new String[]{"Name:", "Price:", "Stock:"},
                new JTextField[]{addProductNameField, addProductPriceField, addProductStockField},
                addProductDialogButton);

        setupDialog(editProductDialog, "Edit Product", new String[]{"Select Product:", "Name:", "Price:", "Stock:"},
                new Component[]{editProductComboBox, editProductNameField, editProductPriceField, editProductStockField},
                editProductDialogButton);

        setupDialog(deleteClientDialog, "Delete Client", new String[]{"Select Client:"},
                new JComboBox[]{deleteClientComboBox},
                deleteClientDialogButton);
        deleteClientDialog.setSize(400, 175);

        setupDialog(deleteProductDialog, "Delete Product", new String[]{"Select Product:"},
                new JComboBox[]{deleteProductComboBox},
                deleteProductDialogButton);
        deleteProductDialog.setSize(400, 175);

        setupDialog(createOrderDialog, "Add Order", new String[]{"Client Name:", "Product Name:", "Quantity:"},
                new Component[]{createOrderClientComboBox, createOrderProductComboBox, createOrderQuantityField},
                createOrderDialogButton);
    }

    /**
     * This method sets up a dialog with the given title, labels, fields, and action button.
     * @param dialog The dialog to be set up.
     * @param title The title of the dialog.
     * @param labels The labels to be used in the dialog.
     * @param fields The fields to be used in the dialog.
     * @param actionButton The action button to be used in the dialog.
     */
    private void setupDialog(JDialog dialog, String title, String[] labels, Component[] fields, JButton actionButton) {
        dialog.setTitle(title);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, fields.length * 60 + 80);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(BACKGROUND_COLOR);

        JPanel panel = new JPanel(new GridLayout(fields.length + 1, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(MAIN_FONT);
            panel.add(label);
            panel.add(fields[i]);
        }

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(actionButton, BorderLayout.SOUTH);
    }

    /**
     * This method creates a styled button with the given text.
     * @param text The text to be displayed on the button.
     * @return The created button.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        styleButton(button);
        return button;
    }

    /**
     * This method creates a styled button with the given text and icon.
     * @param text The text to be displayed on the button.
     * @param iconPath The path to the icon to be displayed on the button.
     * @return The created button.
     */
    private JButton createStyledButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        styleButton(button);
        return button;
    }

    /**
     * This method styles a button.
     * @param button The button to be styled.
     */
    private void styleButton(JButton button) {
        button.setFont(MAIN_FONT);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_COLOR);
            }
        });

    }
    /**
     * This method creates a styled text field.
     * @return The created text field.
     */
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(MAIN_FONT);
        textField.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR));
        return textField;
    }

    /**
     * This method shows the main page of the application.
     */
    public void showMainPage() {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Main Page");
    }

    /**
     * This method gets the manage clients button.
     * @return The manage clients button.
     */
    public JButton getManageClientsButton() {
        return manageClientsButton;
    }

    /**
     * This method gets the manage products button.
     * @return The manage products button.
     */
    public JButton getManageProductsButton() {
        return manageProductsButton;
    }

    /**
     * This method gets the create order button.
     * @return The create order button.
     */
    public JButton getCreateOrderButton() {
        return createOrderButton;
    }

    /**
     * This method gets the manage orders button.
     * @return The manage orders button.
     */
    public JButton getManageOrdersButton() {
        return manageOrdersButton;
    }

    /**
     * This method gets the view all clients button.
     * @return The view all clients button.
     */
    public JButton getViewAllClientsButton() {
        return viewAllClientsButton;
    }

    public JButton getViewAllProductsButton() {
        return viewAllProductsButton;
    }

    public JButton getViewAllBillsButton() {
        return viewAllBillsButton;
    }

    public JButton getViewAllOrdersButton() {
        return viewAllOrdersButton;
    }

    /**
     * This method gets the back from clients button.
     * @return The back from clients button.
     */
    public JButton getBackFromClientsButton() {
        return backFromClientsButton;
    }

    public JButton getBackFromProductsButton() {
        return backFromProductsButton;
    }

    public JButton getBackFromOrdersButton() {
        return backFromOrdersButton;
    }

    /**
     * This method gets the add client button.
     * @return The add client button.
     */
    public JButton getAddClientButton() {
        return addClientButton;
    }
    public JButton getEditClientButton() {
        return editClientButton;
    }
    public JButton getAddProductButton() {
        return addProductButton;
    }
    public JButton getEditProductButton() {
        return editProductButton;
    }
    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }
    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }
    /**
     * This method gets the add client dialog button.
     * @return The add client dialog button.
     */
    public JButton getAddClientDialogButton() {
        return addClientDialogButton;
    }
    public JButton getEditClientDialogButton() {
        return editClientDialogButton;
    }
    public JButton getAddProductDialogButton() {
        return addProductDialogButton;
    }
    public JButton getEditProductDialogButton() {
        return editProductDialogButton;
    }
    public JButton getDeleteClientDialogButton() {
        return deleteClientDialogButton;
    }
    public JButton getDeleteProductDialogButton() {
        return deleteProductDialogButton;
    }

    public JButton getCreateOrderDialogButton() {
        return createOrderDialogButton;
    }

    /**
     * This method gets the add client name field.
     * @return The add client name field.
     */
    public JTextField getAddClientNameField() {
        return addClientNameField;
    }

    public JTextField getAddClientAddressField() {
        return addClientAddressField;
    }

    public JTextField getAddClientEmailField() {
        return addClientEmailField;
    }

    public JTextField getAddClientAgeField() {
        return addClientAgeField;
    }

    public JTextField getAddClientPhoneField() {
        return addClientPhoneField;
    }

    public JTextField getAddProductNameField() {
        return addProductNameField;
    }

    public JTextField getAddProductPriceField() {
        return addProductPriceField;
    }

    public JTextField getAddProductStockField() {
        return addProductStockField;
    }

    public JTextField getEditClientNameField() {
        return editClientNameField;
    }

    public JTextField getEditClientAddressField() {
        return editClientAddressField;
    }

    public JTextField getEditClientEmailField() {
        return editClientEmailField;
    }
    public JTextField getEditClientAgeField() {
        return editClientAgeField;
    }

    public JTextField getEditClientPhoneField() {
        return editClientPhoneField;
    }

    public JTextField getEditProductNameField() {
        return editProductNameField;
    }

    public JTextField getEditProductPriceField() {
        return editProductPriceField;
    }

    public JTextField getEditProductStockField() {
        return editProductStockField;
    }

    /**
     * This method gets the create order client combo box.
     * @return The create order client combo box.
     */
    public JComboBox<Client> getCreateOrderClientComboBox() {
        return createOrderClientComboBox;
    }

    public JComboBox<Product> getCreateOrderProductComboBox() {
        return createOrderProductComboBox;
    }

    public JTextField getCreateOrderQuantityField() {
        return createOrderQuantityField;
    }

    public JComboBox<Client> getEditClientComboBox() {
        return editClientComboBox;
    }

    public JComboBox<Product> getEditProductComboBox() {
        return editProductComboBox;
    }


    public JComboBox<Client> getDeleteClientComboBox() {
        return deleteClientComboBox;
    }


    public JComboBox<Product> getDeleteProductComboBox() {
        return deleteProductComboBox;
    }

    /**
     * This method shows the page for managing clients.
     */
    public void showManageClientsPage() {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Manage Clients");
    }
    public void showManageProductsPage() {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Manage Products");
    }

    public void showManageOrdersPage() {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Manage Orders");
    }

    /**
     * This method shows a dialog for adding a client.
     */
    public void showAddClientDialog() {
        addClientDialog.setVisible(true);
    }
    /**
     * This method hides the dialog for adding a client.
     */
    public void hideAddClientDialog() {
        addClientDialog.setVisible(false);
    }

    public void showEditClientDialog() {
        editClientDialog.setVisible(true);
    }

    public void hideEditClientDialog() {
        editClientDialog.setVisible(false);
    }
    /**
     * This method shows a dialog for adding a product.
     */
    public void showAddProductDialog() {
        addProductDialog.setVisible(true);
    }
    /**
     * This method hides the dialog for adding a product.
     */
    public void hideAddProductDialog() {
        addProductDialog.setVisible(false);
    }

    public void showEditProductDialog() {
        editProductDialog.setVisible(true);
    }

    public void hideEditProductDialog() {
        editProductDialog.setVisible(false);
    }
    public void showDeleteClientDialog() {
        deleteClientDialog.setVisible(true);
    }

    public void hideDeleteClientDialog() {
        deleteClientDialog.setVisible(false);
    }
    public void showDeleteProductDialog() {
        deleteProductDialog.setVisible(true);
    }

    public void hideDeleteProductDialog() {
        deleteProductDialog.setVisible(false);
    }
    /**
     * This method shows a dialog for creating an order.
     */
    public void showCreateOrderDialog() {
        createOrderDialog.setVisible(true);
    }
    /**
     * This method hides the dialog for creating an order.
     */
    public void hideCreateOrderDialog() {
        createOrderDialog.setVisible(false);
    }
    /**
     * This method shows all clients in a new frame.
     * @param clients The list of clients to be shown.
     */
    public void showAllClients(List<Client> clients) {
        JTable table = createTableFromList(new LinkedList<>(clients));
        showTableInNewFrame(table, "All Clients");
    }
    /**
     * This method shows all products in a new frame.
     * @param products The list of products to be shown.
     */
    public void showAllProducts(List<Product> products) {
        JTable table = createTableFromList(new LinkedList<>(products));
        showTableInNewFrame(table, "All Products");
    }
    /**
     * This method shows all orders in a new frame.
     * @param orders The list of orders to be shown.
     */
    public void showAllOrders(List<Order> orders) {
        JTable table = createTableFromList(new LinkedList<>(orders));
        showTableInNewFrame(table, "All Orders");
    }
    /**
     * This method shows all bills in a new frame.
     * @param bills The list of bills to be shown.
     */
    public void showAllBills(List<Bill> bills) {
        JTable table = createTableFromList(new LinkedList<>(bills));
        showTableInNewFrame(table, "All Bills");
    }

    /**
     * This method shows a table in a new frame.
     * @param table The table to be shown.
     * @param title The title of the frame.
     */
    public void showTableInNewFrame(JTable table, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(BACKGROUND_COLOR);

        table.setFont(MAIN_FONT);
        table.setGridColor(PRIMARY_COLOR);
        table.getTableHeader().setFont(MAIN_FONT);
        table.getTableHeader().setBackground(PRIMARY_COLOR);
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR));
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * This method shows an error message to the user.
     * @param message The error message to be shown.
     */
    public void showError(String message) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(MAIN_FONT);
        label.setForeground(Color.RED);
        panel.add(label, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * This method shows a success message to the user.
     * @param message The success message to be shown.
     */
    public void showSuccess(String message) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(MAIN_FONT);
        label.setForeground(new Color(0, 128, 0));
        panel.add(label, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * This method shows a confirmation dialog to the user and returns the user's choice.
     * @param message The message to be shown in the confirmation dialog.
     * @return true if the user chooses "Yes", false otherwise.
     */
    public boolean showConfirmDialog(String message) {
        int result=JOptionPane.showConfirmDialog (null, message,"Confirmation", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
    /**
     * This method resets the text fields used for adding a client.
     */
    public void resetAddClientFields() {
        addClientNameField.setText("");
        addClientAddressField.setText("");
        addClientEmailField.setText("");
        addClientAgeField.setText("");
        addClientPhoneField.setText("");
    }
    /**
     * This method resets the text fields used for adding a product.
     */
    public void resetAddProductFields() {
        addProductNameField.setText("");
        addProductPriceField.setText("");
        addProductStockField.setText("");
    }

    public void resetEditClientFields() {
        editClientNameField.setText("");
        editClientAddressField.setText("");
        editClientEmailField.setText("");
        editClientAgeField.setText("");
        editClientPhoneField.setText("");
    }

    public void resetEditProductFields() {
        editProductNameField.setText("");
        editProductPriceField.setText("");
        editProductStockField.setText("");
    }

    public void resetCreateOrderFields() {
        createOrderQuantityField.setText("");
    }

    /**
     * This method creates a table from a list of objects and returns it.
     * @param list The list of objects to be converted into a table.
     * @return The created table.
     */
    public JTable createTableFromList(List<Object> list) {
        if (list.isEmpty()) {
            return new JTable();
        }
        Class<?> clazz = list.getFirst().getClass();

        Field[] fields = clazz.getDeclaredFields();
        DefaultTableModel tableModel = new DefaultTableModel();
        for (Field field : fields) {
            tableModel.addColumn(field.getName());
        }
        for (Object obj : list) {
            Object[] row = new Object[fields.length];

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                try {
                    row[i] = field.get(obj);
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "An error occurred", e);
                }
            }

            tableModel.addRow(row);
        }

        return new JTable(tableModel);
    }

}