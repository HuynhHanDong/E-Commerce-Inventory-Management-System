package models;

import java.io.Serializable;


/**
 *
 * @author Huynh Han Dong
 */
public class OrderItems implements Serializable {
    private int orderItemId;
    private int orderId;
    private String productId;
    private double price;
    private int quantity;
    
    public OrderItems(int orderItemId, int orderId, String productId, double price, int quantity){
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }
    
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuanity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getTotalPrice() {
        return this.quantity * this.price;
    }
}