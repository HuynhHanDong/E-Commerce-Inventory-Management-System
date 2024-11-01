package models;

public class Inventory {
    private int productID;
    private int currentStock; // Mức tồn kho hiện tại
    private int lowStockThreshold; // Cảnh báo mức độ tồn kho thấp

    public Inventory() {
    }

    public Inventory(int productID, int currentStock, int lowStockThreshold) {
        this.productID = productID;
        this.currentStock = currentStock;
        this.lowStockThreshold = lowStockThreshold;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(int lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    @Override
    public String toString() {
        return "productID: " + productID + ", currentStock: " + currentStock + ", lowStockThreshold: " + lowStockThreshold;
    }
}
