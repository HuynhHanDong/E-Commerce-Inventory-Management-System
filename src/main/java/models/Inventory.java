package models;

import java.sql.Date;

public class Inventory {
    private int inventoryID;
    private int productID;
    private int stockLevel; // Mức tồn kho hiện tại
    private int lowStockThreshold; // Cảnh báo mức độ tồn kho thấp
    private Date lastUpdate;

    public Inventory() {
    }

    public Inventory(int inventoryID, int productID, int stockLevel, int lowStockThreshold, Date lastUpdate) {
        this.inventoryID = inventoryID;
        this.productID = productID;
        this.stockLevel = stockLevel;
        this.lowStockThreshold = lowStockThreshold;
        this.lastUpdate = lastUpdate;
    }

    public int getInventoryID() {
        return this.inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(int lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }
    
    public Date getLastUpdate() {
        return lastUpdate;
    }
    
    public void setLastUpdated(Date lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "invetoryID: " + inventoryID + ", productID: " + productID + ", stockLevel: " + stockLevel
                + ", lowStockThreshold: " + lowStockThreshold + ", last update: " + lastUpdate;
    }
}
