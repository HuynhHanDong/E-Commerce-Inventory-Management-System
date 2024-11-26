# E-Commerce Inventory Management System

A Java-based console application for managing e-commerce inventory, orders, and user roles.

## Features

- **User Authentication**
  - Role-based access control (Store Owner and Customer)
  - Secure password handling
  - Session management

- **Store Owner Features**
  - Product management (CRUD operations)
  - Category management
  - Inventory tracking
  - Sales and inventory reporting
  - Low stock alerts

- **Customer Features**
  - Browse products
  - Search products by various criteria
  - Shopping cart management
  - Order placement
  - Order history tracking

## Technology Stack

- Java
- JDBC for database connectivity
- SSMS database
- Maven for dependency management

## Project Structure

src/
├── main/
│   ├── java/
│   │   ├── controller/    # Business logic and user input handling
│   │   ├── models/        # Data models
│   │   ├── DAO/          # Database access objects
│   │   ├── utils/        # Utility classes
│   │   └── view/         # Menu and display classes
│   └── resources/        # Configuration files
```

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- SSMS Server
- Maven

### Installation

1. Clone the repository
```bash
git clone https://github.com/NT4105/E-Commerce-Inventory-Management-System.git
```

2. Configure database connection in `src/main/java/utils/JDBCConnection.java`

3. Run the application
```bash
mvn clean install
java -jar target/e-commerce-inventory-system.jar
```

## Team Members

- **Nguyen Minh Tu**
- **Huynh Han Dong**
- **Nguyen Hoang Minh**

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Thanks to all team members for their contributions
- Special thanks to our instructors and mentors
