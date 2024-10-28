package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Inventory;
import utils.JDBCConnection;

public class InventoryDAO {
    private static final String ADD_INVENTORY_ITEM = "INSERT INTO Inventory (ProductID, CurrentStock, LowStockThreshold) VALUES (?, ?, ?);";
    private static final String UPDATE_INVENTORY_ITEM = "UPDATE Inventory SET CurrentStock = ?, LowStockThreshold = ? WHERE ProductID = ?;";
    private static final String DELETE_INVENTORY_ITEM = "DELETE FROM Inventory WHERE ProductID = ?;";
    private static final String GET_INVENTORY_ITEM_BY_ID = "SELECT * FROM Inventory WHERE ProductID = ?;";
    private static final String GET_ALL_INVENTORY_ITEMS = "SELECT * FROM Inventory;";

    public boolean addInventoryItem(Inventory inventory) {
        boolean success = false;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_INVENTORY_ITEM)) {
            statement.setInt(1, inventory.getProductID());
            statement.setInt(2, inventory.getCurrentStock());
            statement.setInt(3, inventory.getLowStockThreshold());
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error adding inventory item", e);
        }
        return success;
    }

    public boolean updateInventoryItem(Inventory inventory) {
        boolean success = false;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_INVENTORY_ITEM)) {
            statement.setInt(1, inventory.getCurrentStock());
            statement.setInt(2, inventory.getLowStockThreshold());
            statement.setInt(3, inventory.getProductID());
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error updating inventory item", e);
        }
        return success;
    }

    public boolean deleteInventoryItem(int productID) {
        boolean success = false;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_INVENTORY_ITEM)) {
            statement.setInt(1, productID);
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error deleting inventory item", e);
        }
        return success;
    }

    public Inventory getInventoryItemById(int productID) {
        Inventory inventory = null;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_INVENTORY_ITEM_BY_ID)) {
            statement.setInt(1, productID);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                if (result.next()) {
                    inventory = new Inventory(result.getInt("productID"), result.getInt("currentStock"),
                            result.getInt("lowStockThreshold"));
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
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ALL_INVENTORY_ITEMS)) {
            ResultSet result = statement.executeQuery();
            while (result != null) {
                if (result.next()) {
                    int productID = result.getInt("productID");
                    int currentStock = result.getInt("currentStock");
                    int lowStockThreshold = result.getInt("lowStockThreshold");
                    System.out.println("ID: " + productID + "; currentStock: " + currentStock + "; lowStockThreshold: "
                            + lowStockThreshold);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error retrieving all inventory items", e);
        }
        return inventoryList;
    }
}