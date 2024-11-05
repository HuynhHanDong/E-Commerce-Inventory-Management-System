package models;

public class Inventory {
    private int inventoryID;
    private int productID;
    private int stockLevel; // Mức tồn kho hiện tại
    private int lowStockThreshold; // Cảnh báo mức độ tồn kho thấp

    public Inventory() {
    }

    public Inventory(int inventoryID, int productID, int stockLevel, int lowStockThreshold) {
        this.inventoryID = inventoryID;
        this.productID = productID;
        this.stockLevel = stockLevel;
        this.lowStockThreshold = lowStockThreshold;
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

    @Override
    public String toString() {
        return "invetoryID: " + inventoryID + ", productID: " + productID + ", stockLevel: " + stockLevel
                + ", lowStockThreshold: " + lowStockThreshold;
    }
}
