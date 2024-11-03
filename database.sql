CREATE DATABASE ECommerceDB;
GO;

USE ECommerceDB;

CREATE TABLE Customer
(
    CustomerID INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50) NOT NULL UNIQUE,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    Password NVARCHAR(60) NOT NULL
);

CREATE TABLE StoreOwner
(
    StoreOwnerID INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50) NOT NULL UNIQUE,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    Password NVARCHAR(60) NOT NULL
);

CREATE TABLE Category
(
    CategoryID INT PRIMARY KEY IDENTITY(1,1),
    CategoryName NVARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Products
(
    ProductID INT PRIMARY KEY IDENTITY(1,1),
    ProductName NVARCHAR(100) NOT NULL,
    CategoryID INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Description NVARCHAR(200),
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

CREATE TABLE Orders
(
    OrderID INT PRIMARY KEY IDENTITY(1,1),
    CustomerID INT NOT NULL,
    OrderDate DATETIME NOT NULL DEFAULT GETDATE(),
    TotalPrice DECIMAL(10, 2) NOT NULL,
    Status NVARCHAR(20) NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

CREATE TABLE OrderItems
(
    OrderItemID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

CREATE TABLE Inventory
(
    InventoryID INT PRIMARY KEY IDENTITY(1,1),
    ProductID INT NOT NULL,
    StockLevel INT NOT NULL,
    LowStockThreshold INT NOT NULL,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

CREATE TABLE Reports
(
    ReportID INT PRIMARY KEY IDENTITY(1,1),
    ReportType VARCHAR(20) NOT NULL,
    GeneratedDate DATETIME NOT NULL,
    StartDate DATETIME,
    EndDate DATETIME,
    TotalAmount DECIMAL(18,2) NOT NULL,
    Status VARCHAR(20) NOT NULL
)

-- Insert sample customer with BCrypt hashed password
INSERT INTO Customer (Username, Email, Password) VALUES ('customer1', 'customer1@example.com', '$2a$10$XQxlOFEZWo9Ej2qAOEXRxuCY3tN8yzqTvPHLwLbJZOGJ7.rnvy4Hy');

-- Insert sample store owner with BCrypt hashed password
INSERT INTO StoreOwner (Username, Email, Password) VALUES ('storeowner1', 'storeowner1@example.com', '$2a$10$XQxlOFEZWo9Ej2qAOEXRxuCY3tN8yzqTvPHLwLbJZOGJ7.rnvy4Hy');

-- The password for both users is 'password123'

INSERT INTO Category (CategoryName) VALUES ('electronics'),('vehicle'),('clothes');
INSERT INTO Products (ProductName, CategoryID, Price, Description) VALUES ('tivi','1',6590000,'tivi LG'), ('iphone13','1',13670000,'iphone 13') ,('tivi','1',7890000,'tivi SAMSUNG');

