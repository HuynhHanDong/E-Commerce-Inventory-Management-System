CREATE DATABASE ECommerceDB;
GO

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
    LastUpdate DATETIME NOT NULL DEFAULT GETDATE()
        FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
GO

-- Trigger
CREATE TRIGGER trg_UpdateInventoryAfterOrder ON OrderItems AFTER INSERT
AS
BEGIN
    INSERT INTO Inventory
        (ProductID, StockLevel, LowStockThreshold, LastUpdate)
    SELECT i.ProductID, (inv.StockLevel - i.Quantity) AS NewStockLevel, inv.LowStockThreshold, GETDATE()
    FROM inserted i
        INNER JOIN (SELECT ProductID, MAX(LastUpdate) AS LastUpdate
        FROM Inventory
        GROUP BY ProductID) latest
        ON i.ProductId = latest.ProductId
        INNER JOIN Inventory inv
        ON latest.ProductId = inv.ProductId AND latest.LastUpdate = inv.LastUpdate;
END;
GO

CREATE TRIGGER trg_UpdateInventoryAfterCancel ON Orders AFTER UPDATE
AS
BEGIN
    IF EXISTS (SELECT 1
    FROM inserted
    WHERE Status = 'cancelled')
	BEGIN
        INSERT INTO Inventory
            (ProductID, StockLevel, LowStockThreshold, LastUpdate)
        SELECT oi.ProductID, (inv.StockLevel + oi.Quantity) AS NewStockLevel, inv.LowStockThreshold, GETDATE()
        FROM OrderItems oi
            INNER JOIN inserted i
            ON oi.OrderID = i.OrderID
            INNER JOIN (SELECT ProductID, MAX(LastUpdate) AS LastUpdate
            FROM Inventory
            GROUP BY ProductID) latest
            ON oi.ProductID = latest.ProductID
            INNER JOIN Inventory inv
            ON latest.ProductId = inv.ProductId AND latest.LastUpdate = inv.LastUpdate
        WHERE i.Status = 'cancelled';
    END
END;
GO

-- Thêm tài khoản mới
-- Password cho cả 2 tài khoản là: Password@123
INSERT INTO Users
    (Username, Email, Password, Role)
VALUES
    ('StoreManager', 'store.manager@example.com', '$2a$10$PH0P6j2.2qgR9K9PkNJmBu7mMmqSxXhvqp9q2mWrE4d8V1.fJNxJi', 'STORE_OWNER'),
    ('CustomerUser', 'customer.user@example.com', '$2a$10$PH0P6j2.2qgR9K9PkNJmBu7mMmqSxXhvqp9q2mWrE4d8V1.fJNxJi', 'CUSTOMER');

INSERT INTO Category
    (CategoryName)
VALUES
    ('electronics'),
    ('vehicle'),
    ('clothes');

INSERT INTO Products
    (ProductName, CategoryID, Price, Description)
VALUES
    ('tivi', 1, 65900, 'tivi LG'),
    ('iphone13', 1, 13670, 'iphone 13') ,
    ('tivi', 1, 78900, 'tivi SAMSUNG'),
    ('bike', 2, 1999, 'xe dap');

INSERT INTO Inventory
    (ProductID, StockLevel, LowStockThreshold, LastUpdate)
VALUES
    (1, 100, 30, '2024-10-15'),
    (2, 150, 50, '2024-10-15'),
    (3, 90, 30, '2024-10-15'),
    (4, 50, 10, '2024-10-30');

INSERT INTO Orders
    (CustomerID, OrderDate, TotalPrice, Status)
VALUES
    (2, '2024-11-01', 65900, 'Finished'),
    (2, '2024-11-02', 15669, 'Finished'),
    (2, '2024-11-20', 80899, 'In progress');

INSERT INTO OrderItems
    (OrderID, ProductID, Quantity, Price)
VALUES
    (1, 1, 1, 65900),
    (2, 2, 1, 13670),
    (2, 4, 1, 1999),
    (3, 3, 1, 78900),
    (3, 4, 1, 1999);