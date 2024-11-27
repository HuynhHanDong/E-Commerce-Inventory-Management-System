package models;

import java.io.Serializable;

/**
 *
 * @author Huynh Han Dong
 */
public class OrderItems implements Serializable {
    private int orderItemID;
    private int orderID;
    private int productID;
    private double unitPrice;
    private int quantity;

    public OrderItems() {
    }

    public OrderItems(int orderItemID, int orderID, int productID, double unitPrice, int quantity) {
        this.orderItemID = orderItemID;
        this.orderID = orderID;
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(int orderItemID) {
        this.orderItemID = orderItemID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuanity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return this.quantity * this.unitPrice;
    }

    @Override
    public String toString() {
        return "orderItemID: " + orderItemID + ", productID: " + productID + ", unit price: " + unitPrice
                + ", quantity: " + quantity;
    }
}