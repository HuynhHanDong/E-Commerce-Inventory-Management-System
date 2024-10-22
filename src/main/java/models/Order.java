package models;

import java.util.ArrayList;
import java.sql.Date;

public class Order {
    private int orderId;
    private String customerId;
    private Date orderDate;
    private ArrayList<OrderItems> items;
    private double totalPrice;
    private String status;
    
    public Order(){
    }

    public Order(int orderId, String customerId, Date orderDate, ArrayList<OrderItems> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.items = items;
        this.status = "Pending";
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public String getCustomerId() {
        return customerId;
    }

    public void setcustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }
 
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<OrderItems> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItems> items) {
        this.items = items;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void calculateTotalPrice() {
        double total = 0;
        for (OrderItems item : items) {
            total += item.getTotalPrice();
        }
        this.totalPrice = total;
    }

    public String toTableString() {
        return String.format("| %10d | %15s | %10.2f |", orderId, orderDate.toString(), totalPrice);
    }
}