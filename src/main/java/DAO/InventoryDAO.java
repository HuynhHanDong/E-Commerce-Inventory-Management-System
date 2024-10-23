package DAO;

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
    private static final String INSERT_INVENTORY_ITEM = "INSERT INTO inventory (productID, currentStock, lowStockThreshold) VALUES (?, ?, ?);";
    private static final String UPDATE_INVENTORY_ITEM = "UPDATE inventory SET currentStock = ?, lowStockThreshold = ? WHERE productID = ?;";
    private static final String DELETE_INVENTORY_ITEM = "DELETE FROM inventory WHERE productID = ?;";
    private static final String GET_INVENTORY_ITEM_BY_ID = "SELECT * FROM inventory WHERE productID = ?;";
    private static final String GET_ALL_INVENTORY_ITEMS = "SELECT * FROM inventory;";

    public boolean addInventoryItem(Inventory inventory) {
        boolean success = false;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(INSERT_INVENTORY_ITEM)) {
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

    public boolean deleteInventoryItem(String productId) {
        boolean success = false;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_INVENTORY_ITEM)) {
            statement.setString(1, productId);
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(InventoryDAO.class.getName()).log(Level.SEVERE, "Error deleting inventory item", e);
        }
        return success;
    }

    public Inventory getInventoryItemById(String productId) {
        Inventory inventory = null;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_INVENTORY_ITEM_BY_ID)) {
            statement.setString(1, productId);
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

    public List<Inventory> getAllInventoryItems() {
        List<Inventory> inventoryList = new ArrayList<>();
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