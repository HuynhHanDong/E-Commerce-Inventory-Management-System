package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Order;
import models.OrderItems;
import utils.JDBCConnection;

/**
 *
 * @author Huynh Han Dong
 */
public class OrderDAO {
    private static final String ADD_ORDER = "INSERT INTO orders (orderId, customerId, orderDate, totalPrice, status) VALUES (?,?,?,?,?);";
    private static final String GET_ALL_ORDERS = "SELECT * FROM Orders;";
    private static final String GET_ORDER_DETAILS = "SELECT * FROM orders WHERE orderId = ?;";
    private static final String ADD_ITEM = "INSERT INTO orderItems (orderItemId, orderId, productId, price, quantity) VALUES (?,?,?,?);";
    private static final String GET_ITEMS_DETAILS = "SELECT orderItemId, productId, price, quantity FROM orderItems orderId = ?;";
    
    public int addOrder(Order order) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_ORDER)) {
            statement.setInt(1, order.getOrderId());
            statement.setString(2, order.getCustomerId());
            statement.setDate(3, order.getOrderDate());
            statement.setDouble(4, order.getTotalPrice());
            statement.setString(5, order.getStatus());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public void viewOrderHistory() {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ALL_ORDERS)) {
            ResultSet result = statement.executeQuery();
            
            if (result != null){
                while(result.next()) {
                int ordertId = result.getInt("orderId");
                String customerId = result.getString("customerId");
                Date orderDate = result.getDate("orderDate");
                double totalPrice = result.getDouble("totalPrice");
                String status = result.getString("status");
                System.out.println("Order: " + ordertId + " CustomerID: " + customerId + " Order date: " + orderDate + " Total price: " + totalPrice + " Status: " + status);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void viewOrderDetails(int orderId){
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ORDER_DETAILS)) {
            statement.setInt(1, orderId);
            
            ResultSet result = statement.executeQuery();
            
            if (result != null){
                while(result.next()) {
                int ordertId = result.getInt("orderId");
                String customerId = result.getString("customerId");
                Date orderDate = result.getDate("orderDate");
                double totalPrice = result.getDouble("totalPrice");
                String status = result.getString("status");
                System.out.println("Order: " + ordertId + " CustomerID: " + customerId + " Order date: " + orderDate + " Total price: " + totalPrice + " Status: " + status);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public int AddToCart(Order order){
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_ITEM)) {
            for (OrderItems item : order.getItems()){
                statement.setInt(1, item.getOrderItemId());
                statement.setInt(2, item.getOrderId());
                statement.setString(3, item.getProductId());
                statement.setDouble(4, item.getPrice());
                statement.setInt(5, item.getQuantity());
            
                result = statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }
    
    public void viewItemsDetails(int orderId){
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ITEMS_DETAILS)) {
            statement.setInt(1, orderId);
            
            ResultSet result = statement.executeQuery();
            
            if (result != null){
                while(result.next()) {
                int ordertItemId = result.getInt("ordertItemId");
                String productId = result.getString("productId");
                double price = result.getDouble("price");
                int quantity = result.getInt("quantity");
                System.out.println("OrderItemId: " + ordertItemId + " ProductID: " + productId + " Price: " + price + " Quantity: " + quantity);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}