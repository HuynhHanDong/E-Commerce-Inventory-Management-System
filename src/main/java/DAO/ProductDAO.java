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
    private static final String ADD_PRODUCT = "INSERT INTO products (productID, productName, categoryID, price, stock, description) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE_PRODUCT = "UPDATE products SET productName = ?, categoryID = ?, price = ?, stock = ?, description = ? WHERE productID = ?;";
    private static final String UPDATE_STOCK_LEVEL = "UPDATE products SET stock = ? WHERE productID = ?;";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE productID = ?;";
    private static final String GET_PRODUCT_BY_ID = "SELECT productID, productName, categoryName, price, stock, description FROM products, category WHERE  productID = ?;";
    private static final String GET_PRODUCT_BY_CATEGORY = "SELECT productID, productName, categoryName, price, stock, description FROM products, category WHERE categoryName = ?;";
    private static final String GET_ALL_PRODUCTS = "SELECT productID, productName, categoryName, price, stock, description FROM products p, category c WHERE p.categoryID = c.categoryID;";

    public int addProduct(Product product) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_PRODUCT)) {
            statement.setInt(1, product.getProductID());
            statement.setString(2, product.getProductName());
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
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStockLevel());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getProductID());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int updateStockLevel(int productID, int newStockLevel) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_STOCK_LEVEL)) {
            statement.setInt(1, newStockLevel);
            statement.setInt(2, productID);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int deleteProduct(int productID) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_PRODUCT)) {
            statement.setInt(1, productID);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public void searchProductsById(int productID) {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_PRODUCT_BY_ID)) {
            statement.setInt(1, productID);

            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    productID = result.getInt("productID");
                    String productName = result.getString("productName");
                    String category = result.getString("categoryName");
                    double price = result.getDouble("price");
                    int stock = result.getInt("stock");
                    String description = result.getString("description");
                    System.out.println("ID: " + productID + "; Name: " + productName + "; Category: " + category
                            + "; Price: " + price + "; Stock: " + stock + "; Description: " + description);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void searchProductsByCategory(String categoryName) {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_PRODUCT_BY_CATEGORY)) {
            statement.setString(1, categoryName);

            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    int productID = result.getInt("productID");
                    String productName = result.getString("productName");
                    String category = result.getString("categoryName");
                    double price = result.getDouble("price");
                    int stock = result.getInt("stock");
                    String description = result.getString("description");
                    System.out.println("ID: " + productID + "; Name: " + productName + "; Category: " + category
                            + "; Price: " + price + "; Stock: " + stock + "; Description: " + description);
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
                    int productID = result.getInt("productID");
                    String productName = result.getString("productName");
                    String category = result.getString("categoryName");
                    double price = result.getDouble("price");
                    int stock = result.getInt("stock");
                    String description = result.getString("description");
                    System.out.println("ID: " + productID + "; Name: " + productName + "; Category: " + category
                            + "; Price: " + price + "; Stock: " + stock + "; Description: " + description);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}