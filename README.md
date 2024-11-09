# Order Management System

A Java-based application for managing clients, products, orders, and billing, utilizing Object-Oriented Programming (OOP) principles for modularity and scalability. This system includes functionalities for creating and managing orders, generating bills, and performing CRUD operations, all within a user-friendly graphical interface.

## Features

- **Client Management**: Add, update, delete, and list clients.
- **Product Management**: Add, update, delete, and list products with validation checks.
- **Order Management**: Create orders by selecting clients and products, calculate total prices, and ensure availability.
- **Billing**: Generate detailed bills with client and order information, and save or print them.
- **Data Validation**: Ensures accuracy with input validations and meaningful error messages.

## Project Structure

The system is organized into layers to enhance modularity and maintainability:

1. **Data Access Layer (DAO)**: Handles database operations for clients, products, orders, and bills.
2. **Business Logic Layer (BLL)**: Implements core business rules and logic.
3. **Presentation Layer (GUI)**: Provides an intuitive graphical interface for interacting with the system.

### Key Classes

1. **Client**: Represents a client with attributes like name, address, email, and phone number.
2. **Product**: Represents a product with attributes like name, quantity, and price.
3. **Order**: Manages orders, including selected products and their quantities, and calculates total price.
4. **Bill**: Generates a bill for a specific order, including order details and client information.
5. **Controller**: Connects the GUI to the business logic layer, managing user actions.

### Validators

The system includes validation classes such as `ClientValidator`, `ProductValidator`, and `OrderValidator`, ensuring data integrity across all entities by checking field formats, quantities, and more.

### Strategy

The project employs a strategy pattern to define business rules flexibly, allowing for future expansion and adaptability.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven (for dependencies)
- MySQL (for database)

### Installation

1. **Clone the repository**:
```
git clone https://github.com/ButasRafael/Order_Management.git
cd Order_Management
```
2. **Set up the database** :
* Import the provided SQL file (if available) or set up a MySQL database with tables for clients, products, orders, and bills.
3. **Configure Database Connection**:
* Update database credentials in ConnectionFactory.java.
4. **Build the project**:
```
mvn clean install
```
5. **Run the application**:
```
java -jar target/Order_Management.jar
```
## Usage
* Launch the application: The main GUI window allows navigation to client, product, and order management screens.
* Add or Manage Entries: Access forms to add or update client, product, or order details, with validation for each entry.
* Generate Bills: After creating an order, generate a detailed bill viewable in the GUI.
### Testing
* JUnit tests are implemented to validate the core functionality, covering CRUD operations, business logic, and validations.
* To run tests:
```
mvn test
```

## Future Enhancements
### Potential improvements include:
* User Authentication: Role-based access for secure operations.
* Advanced Reporting: Generate reports and analytics on clients, products, orders, and billing.
* Improved GUI: Upgrade to JavaFX for a modern, responsive interface.
* Integration with External Systems: API integration for interoperability with external software.
* Mobile Version: Develop a mobile app to manage orders and clients on the go.
