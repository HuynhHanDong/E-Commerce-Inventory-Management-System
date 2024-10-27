package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private static final String ADD_ORDER = "INSERT INTO Orders (OrderID, CustomerID, OrderDate, TotalPrice, Status) VALUES (?,?,?,?,?);";
    private static final String GET_ALL_ORDERS = "SELECT * FROM Orders;";
    private static final String GET_ORDER_DETAILS = "SELECT * FROM Orders WHERE OrderID = ?;";
    private static final String ADD_ITEM = "INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Price, Quantity) VALUES (?,?,?,?);";
    private static final String GET_ITEMS_DETAILS = "SELECT OrderItemID, ProductID, Price, Quantity FROM OrderItems WHERE OrderID = ?;";

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

    public ArrayList<Order> viewOrderHistory() {
        ArrayList<Order> orderList = new ArrayList<>();
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
                    
                    ArrayList<OrderItems> items = this.viewItemsDetails(orderID);
                    orderList.add(new Order(orderID, customerID, orderDate, items, totalPrice, status));
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return orderList;
    }

    public Order viewOrderDetails(int orderID) {
        Order order = null;
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
                    
                    ArrayList<OrderItems> items = this.viewItemsDetails(orderID);
                    order = new Order(orderID, customerID, orderDate, items, totalPrice, status);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return order;
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

    public ArrayList<OrderItems> viewItemsDetails(int orderID) {
        ArrayList<OrderItems> itemList = new ArrayList<>();
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
                    itemList.add(new OrderItems(orderItemID, orderID, productID, price, quantity));
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return itemList;
    }
}