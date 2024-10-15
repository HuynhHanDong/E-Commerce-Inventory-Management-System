package models;

import java.util.Date;

public class Order extends Product {
    private int orderId;
    private Date orderDate;
    private double totalPrice;

    public Order(int orderId, Date orderDate, double totalPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addToCart(Product product, int quantity) {

    }

    public void confirmOrder() {
        System.out.println("Order confirmed with ID: " + orderId + " Total Price: " + totalPrice);
    }

    @Override
    public String toTableString() {
        return String.format("| %10d | %15s | %10.2f |", orderId, orderDate.toString(), totalPrice);
    }
}
