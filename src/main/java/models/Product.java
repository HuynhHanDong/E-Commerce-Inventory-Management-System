package models;

public class Product {
    private String productId;
    private String name;
    private double price;
    private int stockLevel;
    private String description;
    private String categoryName;

    public Product() {
    }

    public Product(String productId, String name, double price, int stockLevel, String description,
            String categoryName) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockLevel = stockLevel;
        this.description = description;
        this.categoryName = categoryName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return categoryName;
    }

    public void setCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId +
                ", name='" + name + '\'' +
                ", price=" + price + '\'' +
                ", stockLevel=" + stockLevel +
                ", description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
