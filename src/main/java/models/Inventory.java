package models;

public class Inventory {
    private String productId;
    private int currentStock; // Mức tồn kho hiện tại
    private int lowStockThreshold; // Cảnh báo mức độ tồn kho thấp

    public Inventory() {
    }

    public Inventory(String productId, int currentStock, int lowStockThreshold) {
        this.productId = productId;
        this.currentStock = currentStock;
        this.lowStockThreshold = lowStockThreshold;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
        return "Inventory{" +
                "productId='" + productId + '\'' +
                ", currentStock=" + currentStock + '\'' +
                ", lowStockThreshold=" + lowStockThreshold + '\'' +
                '}';
    }
}
