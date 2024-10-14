package models;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int id;
    private String name;
    private String email;
    private String password; 
    private List<Order> orderHistory;

    public Customer(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.orderHistory = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        System.out.println("Đã đặt hàng thành công. Tổng giá: " + order.getTotalPrice());
    }

    // Phương thức để xem lịch sử đặt hàng
    public void viewOrderHistory() {
        System.out.println("History of ordered:");
        for (Order order : orderHistory) {
            System.out.println("Order's ID: " + order.getOrderId() + " - Date: " + order.getOrderDate() + " - Total: "
                    + order.getTotalPrice());
        }
    }

    // Phương thức login
    public boolean login(String email, String password) {
        // Giả định mã hóa mật khẩu và xác minh thông tin
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("Đăng nhập thành công");
            return true;
        } else {
            System.out.println("Đăng nhập thất bại");
            return false;
        }
    }

    // Phương thức logout
    public void logout() {
        System.out.println("Đã đăng xuất thành công");
    }
}