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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JDBCConnection;
import models.Product;

/**
 *
 * @author Huynh Han Dong
 */
public class ProductDAO {
    private static final String ADD_PRODUCT = "INSERT INTO Products (ProductName, CategoryID, Price, Description) VALUES (?,?,?,?);";
    private static final String UPDATE_PRODUCT = "UPDATE Products SET ProductName = ?, CategoryID = ?, Price = ?, Description = ? WHERE ProductID = ?;";
    private static final String DELETE_PRODUCT = "DELETE FROM Products WHERE ProductID = ?;";
    private static final String GET_PRODUCT_ID = "SELECT MAX(ProductID) AS ProductID FROM Products;";
    private static final String GET_PRODUCT_BY_ID = "SELECT ProductID, ProductName, CategoryName, Price, Description FROM Products, Category WHERE ProductID = ?";
    private static final String GET_PRODUCT_WITH_CONDITION = "SELECT ProductID, ProductName, CategoryName, Price, Description FROM Products, Category WHERE ";
    private static final String GET_ALL_PRODUCTS = "SELECT ProductID, ProductName, CategoryName, Price, Description FROM Products, Category WHERE Products.CategoryID = Category.CategoryID;";

    public int addProduct(Product product, int categoryID) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_PRODUCT)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, categoryID);
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getDescription());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int updateProduct(Product product, int categoryID) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, categoryID);
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getProductID());

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
    
    public int getProductID() {
        int productID = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_PRODUCT_ID)) {
                ResultSet result = statement.executeQuery();
                if (result != null && result.next()) {
                    productID = result.getInt("ProductID");
                }
            
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return productID;
    }
    
    public Product getProductByID(int productID) {
        Product product = null;
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
                    String description = result.getString("description");
                    product = new Product(productID, productName, price, description, category);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return product;
    }

    public ArrayList<Product> searchProduct(String condition) {
        ArrayList<Product> productList = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_PRODUCT_WITH_CONDITION + condition)) {

            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    int productID = result.getInt("productID");
                    String productName = result.getString("productName");
                    String category = result.getString("categoryName");
                    double price = result.getDouble("price");
                    String description = result.getString("description");
                    productList.add(new Product(productID, productName, price, description, category));
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return productList;
    }
    
    public ArrayList<Product> viewAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ALL_PRODUCTS)) {
            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    int productID = result.getInt("productID");
                    String productName = result.getString("productName");
                    String category = result.getString("categoryName");
                    double price = result.getDouble("price");
                    String description = result.getString("description");
                    productList.add(new Product(productID, productName, price, description, category));
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return productList;
    }
}