package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;
import utils.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends Order implements User {

    private String id;
    private String username;
    private String password;
    private List<Order> orderHistory;

    public Customer(String orderId, Date orderDate, double totalPrice, String id, String username, String password) {
        super(orderId, orderDate, totalPrice);
        this.id = id;
        this.username = username;
        this.password = password;
        this.orderHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void searchProducts(String productName) {
        System.out.println("Searching for product: " + productName);
    }

    public void viewProductDetails(Product product) {
        System.out.println("Product's name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Description: " + product.getDescription());
        System.out.println("Stock: " + product.getStockLevel());
    }

    public void placeOrder(Order order) {
        orderHistory.add(order);
        System.out.println("Order Successfully, Total: " + order.getTotalPrice());
    }

    public void viewOrderHistory() {
        System.out.println("History of ordered:");
        for (Order order : orderHistory) {
            System.out.println("Order's ID: " + order.getOrderId() + " - Date: " + order.getOrderDate() + " - Total: "
                    + order.getTotalPrice());
        }
    }

    public void login(String username, String password) {
        try (Connection conn = JDBCConnection.getConnection()) {
            // Truy vấn để lấy mật khẩu đã mã hóa của người dùng từ db
            String query = "SELECT password FROM Users WHERE username = ? AND role = 'Customer'";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");

                if (BCrypt.checkpw(password, storedHashedPassword)) {
                    System.out.println("Customer login successful!");
                } else {
                    System.out.println("Invalid password. Please try again.");
                }
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logout() {
        System.out.println("Customer logged out.");
    }
}