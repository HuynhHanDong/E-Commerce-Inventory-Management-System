/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JDBCConnection;
import models.Product;

/**
 *
 * @author Huynh Han Dong
 */
public class ProductDAO {
    private static final String INSERT_PRODUCT = "INSERT INTO [dbo].[products] (productId, productName, categoryId, price, stock, description) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE_PRODUCT = "UPDATE products SET productName = ?, categoryId = ?, price = ?, stock = ?, description = ? WHERE productId = ?;";
    private static final String UPDATE_STOCK_LEVEL = "UPDATE products SET stock = ? WHERE productId = ?;";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE productId = ?;";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE productId = ?;";
    private static final String GET_PRODUCT_BY_CATEGORY = "SELECT * FROM products WHERE categoryId = ?;";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM products;";

    public int addProduct(Product product) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(INSERT_PRODUCT)) {
            statement.setString(1, product.getProductId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getCategory());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getStockLevel());
            statement.setString(6, product.getDescription());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int updateProduct(Product product) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStockLevel());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getProductId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int updateStockLevel(String id, int newStockLevel) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_STOCK_LEVEL)) {
            statement.setInt(1, newStockLevel);
            statement.setString(2, id);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int deleteProduct(String id) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_PRODUCT)) {
            statement.setString(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public void searchProductsById(String id) {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_PRODUCT_BY_ID)) {
            statement.setString(1, id);

            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    String productId = result.getString("productId");
                    String productName = result.getString("productname");
                    String categoryId = result.getString("categoryId");
                    float price = result.getFloat("price");
                    int stock = result.getInt("stock");
                    String description = result.getString("description");
                    System.out.println("ID: " + productId + "; Name: " + productName + "; Category: " + categoryId
                            + "; Price: " + price + "; Stock: " + stock + "; Description" + description);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void searchProductsByCategory(String category) {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_PRODUCT_BY_CATEGORY)) {
            statement.setString(1, category);

            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    String productId = result.getString("productId");
                    String productName = result.getString("productname");
                    String categoryId = result.getString("categoryId");
                    float price = result.getFloat("price");
                    int stock = result.getInt("stock");
                    String description = result.getString("description");
                    System.out.println("ID: " + productId + "; Name: " + productName + "; Category: " + categoryId
                            + "; Price: " + price + "; Stock: " + stock + "; Description" + description);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void viewAllProducts() {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ALL_PRODUCTS)) {
            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    String productId = result.getString("productId");
                    String productName = result.getString("productname");
                    String categoryId = result.getString("categoryId");
                    float price = result.getFloat("price");
                    int stock = result.getInt("stock");
                    String description = result.getString("description");
                    System.out.println("ID: " + productId + "; Name: " + productName + "; Category: " + categoryId
                            + "; Price: " + price + "; Stock: " + stock + "; Description" + description);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}