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
    private static final String ADD_ORDER = "INSERT INTO orders (orderID, customerID, orderDate, totalPrice, status) VALUES (?,?,?,?,?);";
    private static final String GET_ALL_ORDERS = "SELECT * FROM orders;";
    private static final String GET_ORDER_DETAILS = "SELECT * FROM orders WHERE orderID = ?;";
    private static final String ADD_ITEM = "INSERT INTO orderItems (orderItemID, orderID, productID, price, quantity) VALUES (?,?,?,?);";
    private static final String GET_ITEMS_DETAILS = "SELECT orderItemID, productID, price, quantity FROM orderItems orderID = ?;";

    public int addOrder(Order order) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_ORDER)) {
            statement.setInt(1, order.getOrderID());
            statement.setInt(2, order.getCustomerID());
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

            if (result != null) {
                while (result.next()) {
                    int orderID = result.getInt("orderID");
                    int customerID = result.getInt("customerID");
                    Date orderDate = result.getDate("orderDate");
                    double totalPrice = result.getDouble("totalPrice");
                    String status = result.getString("status");
                    System.out.println("Order: " + orderID + " CustomerID: " + customerID + " Order date: " + orderDate
                            + " Total price: " + totalPrice + " Status: " + status);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void viewOrderDetails(int orderID) {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ORDER_DETAILS)) {
            statement.setInt(1, orderID);

            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    orderID = result.getInt("orderID");
                    int customerID = result.getInt("customerID");
                    Date orderDate = result.getDate("orderDate");
                    double totalPrice = result.getDouble("totalPrice");
                    String status = result.getString("status");
                    System.out.println("Order: " + orderID + " CustomerID: " + customerID + " Order date: " + orderDate
                            + " Total price: " + totalPrice + " Status: " + status);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public int AddToCart(Order order) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_ITEM)) {
            for (OrderItems item : order.getItems()) {
                statement.setInt(1, item.getOrderItemID());
                statement.setInt(2, item.getOrderID());
                statement.setInt(3, item.getProductID());
                statement.setDouble(4, item.getPrice());
                statement.setInt(5, item.getQuantity());

                result = statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public void viewItemsDetails(int orderID) {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ITEMS_DETAILS)) {
            statement.setInt(1, orderID);

            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    int orderItemID = result.getInt("orderItemID");
                    int productID = result.getInt("productID");
                    double price = result.getDouble("price");
                    int quantity = result.getInt("quantity");
                    System.out.println("OrderItemID: " + orderItemID + " ProductID: " + productID + " Price: " + price
                            + " Quantity: " + quantity);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}