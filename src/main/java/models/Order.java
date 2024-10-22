package models;

import java.util.Date;

public class Order {
    private String orderId;
    private Date orderDate;
    private double totalPrice;

    private String customerID;

    public Order() {
    }

    public Order(String orderId, String customerID, Date orderDate, double totalPrice) {
        this.orderId = orderId;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerID='" + customerID + '\'' +
                ", orderDate=" + orderDate + '\'' +
                ", totalPrice=" + totalPrice + '\'' +
                '}';
    }
}
