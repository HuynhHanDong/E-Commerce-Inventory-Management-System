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

    private static final String ADD_ORDER = "INSERT INTO Orders (CustomerID, OrderDate, TotalPrice, Status) VALUES (?,?,?,?);";
    private static final String UPDATE_ORDER_STATUS = "UPDATE Orders SET Status = ? WHERE OrderID = ?;";
    private static final String GET_ALL_ORDERS = "SELECT * FROM Orders WHERE CustomerID = ?;";
    private static final String GET_ORDER_BY_ID = "SELECT * FROM Orders WHERE OrderID = ? AND CustomerID = ?;";
    private static final String GET_ORDER_BY_STATUS = "SELECT * FROM Orders WHERE CustomerID = ? AND Status = ?;";
    private static final String GET_ORDERID = "SELECT MAX(OrderID) AS OrderID FROM Orders;";
    private static final String ADD_ITEMS = "INSERT INTO OrderItems (OrderID, ProductID, Price, Quantity) VALUES (?,?,?,?);";
    private static final String GET_ITEMS = "SELECT * FROM OrderItems WHERE OrderID = ?;";

    public int addOrder(int customerID, Date orderDate, Double totalPrice, String status) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_ORDER)) {
            statement.setInt(1, customerID);
            statement.setDate(2, orderDate);
            statement.setDouble(3, totalPrice);
            statement.setString(4, status);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int updateOrder(String status, int orderID) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_ORDER_STATUS)) {
            statement.setString(1, status);
            statement.setInt(2, orderID);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public ArrayList<Order> getAllOrders(int customerID) {
        ArrayList<Order> orderList = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ALL_ORDERS)) {
            statement.setInt(1, customerID);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int orderID = result.getInt("orderID");
                customerID = result.getInt("customerID");
                Date orderDate = result.getDate("orderDate");
                double totalPrice = result.getDouble("totalPrice");
                String status = result.getString("status");

                ArrayList<OrderItems> items = getOrderItems(orderID);
                orderList.add(new Order(orderID, customerID, orderDate, items, totalPrice, status));
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return orderList;
    }

    public Order getOrderByID(int orderID, int customerID) {
        Order order = null;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ORDER_BY_ID)) {
            statement.setInt(1, orderID);
            statement.setInt(2, customerID);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                orderID = result.getInt("orderID");
                customerID = result.getInt("customerID");
                Date orderDate = result.getDate("orderDate");
                double totalPrice = result.getDouble("totalPrice");
                String status = result.getString("status");

                ArrayList<OrderItems> items = getOrderItems(orderID);
                order = new Order(orderID, customerID, orderDate, items, totalPrice, status);
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return order;
    }

    public ArrayList<Order> getOrderByStatus(int customerID, String status) {
        ArrayList<Order> orderList = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ORDER_BY_STATUS)) {
            statement.setInt(1, customerID);
            statement.setString(2, status);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int orderID = result.getInt("orderID");
                customerID = result.getInt("customerID");
                Date orderDate = result.getDate("orderDate");
                double totalPrice = result.getDouble("totalPrice");
                status = result.getString("status");

                ArrayList<OrderItems> items = getOrderItems(orderID);
                orderList.add(new Order(orderID, customerID, orderDate, items, totalPrice, status));
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return orderList;
    }

    public int getOrderID() {
        int orderID = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ORDERID)) {
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                orderID = result.getInt("OrderID");
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return orderID;
    }

    public int addItems(ArrayList<OrderItems> items) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_ITEMS)) {
            for (OrderItems item : items) {
                statement.setInt(1, item.getOrderID());
                statement.setInt(2, item.getProductID());
                statement.setDouble(3, item.getUnitPrice());
                statement.setInt(4, item.getQuantity());

                result = statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    private ArrayList<OrderItems> getOrderItems(int orderID) {
        ArrayList<OrderItems> itemList = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ITEMS)) {
            statement.setInt(1, orderID);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int orderItemID = result.getInt("orderItemID");
                orderID = result.getInt("orderID");
                int productID = result.getInt("productID");
                double price = result.getDouble("price");
                int quantity = result.getInt("quantity");
                itemList.add(new OrderItems(orderItemID, orderID, productID, price, quantity));
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return itemList;
    }
}
