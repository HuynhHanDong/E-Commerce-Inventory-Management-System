package DAO;

import java.sql.Connection;
import models.Report;
import utils.JDBCConnection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReportDAO {
    private static final String GENERATE_SALES_REPORT = 
        "WITH sales as(SELECT Orders.OrderID, ProductID, Price, Quantity, OrderDate, Status " +
		    "FROM OrderItems INNER JOIN Orders ON OrderItems.OrderID = Orders.OrderID " +
		    "WHERE OrderDate between ? and ? and Status != 'Cancelled')" +
        "SELECT sales.ProductID, ProductName, Sum(sales.Price) as Revenue, Sum(Quantity) as Quantity " +
        "FROM Products INNER JOIN sales ON Products.ProductID = sales.ProductID " +
        "GROUP BY sales.ProductID, productName";
    private static final String GENERATE_INVENTORY_REPORT = "SELECT i.productID, productName, StockLevel, lowStockThreshold, lastUpdate " +
                                                            "FROM Inventory i INNER JOIN Products p ON i.ProductID = p.ProductID " +
                                                            "WHERE LastUpdate between ? and ? ";

    public ArrayList<Report> generateSalesReport(Timestamp startDate, Timestamp endDate) {
        ArrayList<Report> salesReport = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GENERATE_SALES_REPORT)) {
            statement.setTimestamp(1, startDate);
            statement.setTimestamp(2, endDate);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int productID = result.getInt("ProductID");
                String productName = result.getString("ProductName");
                double revenue = result.getDouble("Revenue");
                int quantity = result.getInt("Quantity");
                LocalDateTime dateTime = LocalDateTime.now();
                Timestamp generatedDate = Timestamp.valueOf(dateTime);
                Report report = new Report("SALES", generatedDate, startDate, endDate, productID, productName, quantity, revenue);
                salesReport.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesReport;
    }

    public ArrayList<Report> generateInventoryReport(Timestamp startDate, Timestamp endDate) {
        ArrayList<Report> inventoryReport = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GENERATE_INVENTORY_REPORT)) {
            statement.setTimestamp(1, startDate);
            statement.setTimestamp(2, endDate);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int productID = result.getInt("ProductID");
                String productName = result.getString("ProductName");
                int quantity = result.getInt("StockLevel");
                int lowStockThreshold = result.getInt("lowStockThreshold");
                Timestamp lastUpdate = result.getTimestamp("LastUpdate");
                LocalDateTime dateTime = LocalDateTime.now();
                Timestamp generatedDate = Timestamp.valueOf(dateTime);
                Report report = new Report("INVENTORY", generatedDate, startDate, endDate, productID, productName, quantity, lowStockThreshold, lastUpdate);
                inventoryReport.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryReport;
    }
}