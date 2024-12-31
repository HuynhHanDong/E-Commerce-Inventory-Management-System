package models;

import java.util.ArrayList;
import java.sql.Timestamp;

public class Order {
    private int orderID;
    private int customerID;
    private Timestamp orderDate;
    private ArrayList<OrderItems> items;
    private double totalPrice;
    private String status;

    public Order() {
    }

    public Order(int orderID, int customerID, Timestamp orderDate, ArrayList<OrderItems> items, double totalPrice,
            String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order(int orderID, int customerID) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = null;
        this.items = null;
        this.totalPrice = 0;
        this.status = "Not confirmed";
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
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

    public void setTotalPrice() {
        calculateTotalPrice();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private void calculateTotalPrice() {
        double total = 0;
        for (OrderItems item : items) {
            total += item.getTotalPrice();
        }
        this.totalPrice = total;
    }

    @Override
    public String toString() {
        return String.format("orderID: %d, customerID: %d, orderDate: %s, totalPrice: %.2f, status: %s",
                orderID, customerID, orderDate, totalPrice, status);
    }
}