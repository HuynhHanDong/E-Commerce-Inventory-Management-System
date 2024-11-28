package models;

public class Product {
    private int productID;
    private String productName;
    private double price;;
    private String description;
    private String categoryName;

    public Product() {
    }

    public Product(int productID, String productName, double price, String description, String categoryName) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.categoryName = categoryName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        return String.format("productID: %d, productName: %s, price: %.2f, description: %s, categoryName: %s",
                productID, productName, price, description, categoryName);
    }
}
