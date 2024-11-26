CREATE DATABASE ECommerceDB;
GO;

USE ECommerceDB;

CREATE TABLE Users
(
    UserID INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50) NOT NULL UNIQUE,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    Password NVARCHAR(60) NOT NULL,
    Role NVARCHAR(20) NOT NULL
    -- 'CUSTOMER' or 'STORE_OWNER'
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
    FOREIGN KEY (CustomerID) REFERENCES Users(UserID)
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
	LastUpdate date NOT NULL DEFAULT GETDATE()
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Insert sample data
INSERT INTO Users(Username, Email, Password, Role)
VALUES
    ('Storeowner', 'storeowner@example.com', '$2a$10$XQxlOFEZWo9Ej2qAOEXRxuCY3tN8yzqTvPHLwLbJZOGJ7.rnvy4Hy', 'STORE_OWNER'),
    ('Customer', 'customer@example.com', '$2a$10$XQxlOFEZWo9Ej2qAOEXRxuCY3tN8yzqTvPHLwLbJZOGJ7.rnvy4Hy', 'CUSTOMER');

INSERT INTO Category(CategoryName)
VALUES
    ('electronics'),
    ('vehicle'),
    ('clothes');

INSERT INTO Products(ProductName, CategoryID, Price, Description)
VALUES
    ('tivi', 1, 6590000, 'tivi LG'),
    ('iphone13', 1, 13670000, 'iphone 13') ,
    ('tivi', 1, 7890000, 'tivi SAMSUNG'),
	('bike', 2, 1999000, 'xe dap');

INSERT INTO Inventory(ProductID, StockLevel, LowStockThreshold, LastUpdate)
VALUES
	(1, 100, 30, '2024-10-15'),
	(2, 150, 50, '2024-10-15'),
	(3, 90, 30, '2024-10-15'),
	(4, 50, 10, '2024-10-30');

INSERT INTO Orders(CustomerID, OrderDate, TotalPrice, Status)
VALUES 
	(2, '2024-11-01', 6590000, 'Finished'),
	(2, '2024-11-02', 15669000, 'Finished'),
	(2, '2024-11-20', 9889000, 'In progress');

INSERT INTO OrderItems(OrderID, ProductID, Quantity, Price)
VALUES
	(1, 1, 1, 6590000),
	(2, 2, 1, 13670000),
	(2, 4, 1, 1999000),
	(3, 3, 1, 7890000),
	(3, 4, 1, 1999000);