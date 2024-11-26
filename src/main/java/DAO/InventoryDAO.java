package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Inventory;
import utils.JDBCConnection;

public class InventoryDAO {

    private static final String ADD_INVENTORY_ITEM = "INSERT INTO Inventory (ProductID, StockLevel, LowStockThreshold, LastUpdate) VALUES (?, ?, ?, ?);";
    private static final String DELETE_INVENTORY_ITEM = "DELETE FROM Inventory WHERE InventoryID = ?;";
    private static final String GET_LATEST_INVENTORY_ITEM_BY_ID = "SELECT top 1 i.InventoryID, i.ProductID, p.ProductName, i.StockLevel, i.LowStockThreshold, i.LastUpdate " +
                                                                  "FROM Inventory i INNER JOIN Products p ON i.ProductID = p.ProductID " +
                                                                  "WHERE i.ProductID = ? ORDER BY LastUpdate DESC;";
    private static final String GET_LATEST_INVENTORY_ITEMS = "WITH LatestInventory AS (SELECT ProductID, MAX(LastUpdate) AS LastUpdate FROM Inventory GROUP BY ProductID) " +
                                                             "SELECT i.InventoryID, i.ProductID, p.ProductName, i.StockLevel, i.LowStockThreshold, i.LastUpdate " +
                                                             "FROM Inventory i INNER JOIN LatestInventory li ON i.ProductID = li.ProductID AND i.LastUpdate = li.LastUpdate " +
                                                             "INNER JOIN Products p ON i.ProductID = p.ProductID ORDER BY ProductID;";

    public int addInventoryItem(Inventory inventory) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(ADD_INVENTORY_ITEM)) {
            statement.setInt(1, inventory.getProductID());
            statement.setInt(2, inventory.getStockLevel());
            statement.setInt(3, inventory.getLowStockThreshold());
            statement.setDate(4, inventory.getLastUpdate());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error adding inventory item", e);
        }
        return result;
    }

    public int deleteInventoryItem(int inventoryID) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(DELETE_INVENTORY_ITEM)) {
            statement.setInt(1, inventoryID);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error deleting inventory item", e);
        }
        return result;
    }

    public Inventory  getCurrentStockLevelById(int productID) {
        Inventory inventory = null;
        try (Connection conn = JDBCConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(GET_LATEST_INVENTORY_ITEM_BY_ID)) {
            statement.setInt(1, productID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                    int inventoryID = result.getInt("inventoryID");
                    productID = result.getInt("productID");
                    String productName = result.getString("productName");
                    int stockLevel = result.getInt("stockLevel");
                    int lowStockThreshold = result.getInt("lowStockThreshold");
                    Date lastUpdate = result.getDate("lastUpdate");
                    inventory = new Inventory(inventoryID, productID, productName, stockLevel, lowStockThreshold, lastUpdate);
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error retrieving inventory item", e);
        }
        return inventory;
    }

    public ArrayList<Inventory> getCurrentStockLevel() {
        ArrayList<Inventory> inventoryList = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection(); PreparedStatement statement = conn.prepareStatement(GET_LATEST_INVENTORY_ITEMS)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int inventoryID = result.getInt("InventoryID");
                int productID = result.getInt("ProductID");
                String productName = result.getString("productName");
                int stockLevel = result.getInt("StockLevel");
                int lowStockThreshold = result.getInt("LowStockThreshold");
                Date lastUpdate = result.getDate("lastUpdate");
                Inventory inventory = new Inventory(inventoryID, productID, productName, stockLevel, lowStockThreshold, lastUpdate);
                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error retrieving all inventory items", e);
        }
        return inventoryList;
    }
}
