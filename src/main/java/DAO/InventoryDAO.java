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

    private static final String ADD_INVENTORY_ITEM = "INSERT INTO Inventory (ProductID, StockLevel, LowStockThreshold, LastUpdate) VALUES (?, ?, ?);";
    private static final String DELETE_INVENTORY_ITEM = "DELETE FROM Inventory WHERE InventoryID = ?;";
    private static final String GET_INVENTORY_ITEM_BY_ID = "SELECT * FROM Inventory WHERE InventoryID = ?;";
    private static final String GET_ALL_INVENTORY_ITEMS = "SELECT * FROM Inventory;";

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
                    Date lastUpdate = result.getDate("lastUpdate");
                    inventory = new Inventory(inventoryID, productID, stockLevel, lowStockThreshold, lastUpdate);
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
                Date lastUpdate = result.getDate("lastUpdate");
                Inventory inventory = new Inventory(inventoryID, productID, stockLevel, lowStockThreshold, lastUpdate);
                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error retrieving all inventory items", e);
        }
        return inventoryList;
    }
}
