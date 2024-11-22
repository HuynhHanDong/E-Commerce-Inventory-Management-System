package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Inventory;
import utils.JDBCConnection;

public class InventoryDAO {

    private static final String ADD_INVENTORY_ITEM = "INSERT INTO Inventory (ProductID, StockLevel, LowStockThreshold) VALUES (?, ?, ?);";
    private static final String UPDATE_INVENTORY_ITEM = "UPDATE Inventory SET StockLevel = ?, LowStockThreshold = ? WHERE ProductID = ?;";
    private static final String DELETE_INVENTORY_ITEM = "DELETE FROM Inventory WHERE InventoryID = ?;";
    private static final String GET_INVENTORY_ITEM_BY_ID = "SELECT * FROM Inventory WHERE InventoryID = ?;";
    private static final String GET_ALL_INVENTORY_ITEMS = "SELECT * FROM Inventory;";

    public int addInventoryItem(Inventory inventory) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(ADD_INVENTORY_ITEM)) {
            statement.setInt(1, inventory.getProductID());
            statement.setInt(2, inventory.getStockLevel());
            statement.setInt(3, inventory.getLowStockThreshold());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error adding inventory item", e);
        }
        return result;
    }

    public int updateInventoryItem(Inventory inventory) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(UPDATE_INVENTORY_ITEM)) {

            statement.setInt(1, inventory.getStockLevel());
            statement.setInt(2, inventory.getLowStockThreshold());
            statement.setInt(3, inventory.getProductID());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error updating inventory item", e);
        }
        return result;
    }

    public int deleteInventoryItem(int productID) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(DELETE_INVENTORY_ITEM)) {
            statement.setInt(1, productID);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error deleting inventory item", e);
        }
        return result;
    }

    public Inventory getInventoryItemById(int productID) {
        Inventory inventory = null;
        try (Connection conn = JDBCConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(GET_INVENTORY_ITEM_BY_ID)) {
            statement.setInt(1, productID);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                if (result.next()) {
                    int inventoryID = result.getInt("inventoryID");
                    productID = result.getInt("productID");
                    int stockLevel = result.getInt("stockLevel");
                    int lowStockThreshold = result.getInt("lowStockThreshold");
                    inventory = new Inventory(inventoryID, productID, stockLevel, lowStockThreshold);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error retrieving inventory item", e);
        }
        return inventory;
    }

    public ArrayList<Inventory> getAllInventoryItems() {
        ArrayList<Inventory> inventoryList = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(GET_ALL_INVENTORY_ITEMS)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int inventoryID = result.getInt("InventoryID");
                int productID = result.getInt("ProductID");
                int stockLevel = result.getInt("StockLevel");
                int lowStockThreshold = result.getInt("LowStockThreshold");

                Inventory inventory = new Inventory(inventoryID, productID, stockLevel, lowStockThreshold);
                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error retrieving all inventory items", e);
        }
        return inventoryList;
    }

    public boolean generateInventoryValueReport() {
        String query = "SELECT product_id, product_name, current_stock, unit_price, "
                + "(current_stock * unit_price) AS total_value FROM products;";
        try (Connection conn = JDBCConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery(); PrintWriter writer = new PrintWriter(new FileWriter("inventory_value_report.txt"))) {

            writer.println("Inventory Value Report");
            writer.println("Product ID\tProduct Name\tCurrent Stock\tUnit Price\tTotal Value");

            double totalInventoryValue = 0;
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                int currentStock = rs.getInt("current_stock");
                double unitPrice = rs.getDouble("unit_price");
                double totalValue = rs.getDouble("total_value");
                writer.printf("%d\t\t%s\t\t%d\t\t%.2f\t\t%.2f%n", productId, productName, currentStock, unitPrice, totalValue);
                totalInventoryValue += totalValue;
            }
            writer.printf("%nTotal Inventory Value: %.2f%n", totalInventoryValue);
            return true;
        } catch (SQLException | IOException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public void viewInventoryReports() {
        String[] reportFiles = {"low_stock_report.txt", "inventory_value_report.txt"};
        for (String fileName : reportFiles) {
            File reportFile = new File(fileName);
            if (!reportFile.exists()) {
                System.out.println("No " + fileName + " available.");
                continue;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
                System.out.println("\n" + fileName + " Contents:");
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public boolean generateLowStockReport() {
        List<String> lowStockItems = new ArrayList<>();
        String query = "SELECT productID, stockLevel FROM Inventory WHERE stockLevel < ?";

        try (Connection connection = JDBCConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, 10);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                int stockLevel = resultSet.getInt("stockLevel");
                lowStockItems.add("Product ID: " + productID + " (Stock: " + stockLevel + ")");
            }

            if (!lowStockItems.isEmpty()) {
                System.out.println("Low Stock Report:");
                for (String item : lowStockItems) {
                    System.out.println(item);
                }
                return true;
            } else {
                System.out.println("No products with low stock.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error generating low stock report: " + e.getMessage());
            return false;
        }
    }

}
