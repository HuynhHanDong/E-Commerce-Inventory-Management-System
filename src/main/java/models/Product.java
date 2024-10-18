package models;

import java.util.*

public class Product implements Displayable {
    private String productId;
    private String name;
    private double price;
    private int stockLevel;
    private String description;
    private String category;

    private static List<Product> products = new ArrayList<>();
    
    public Product() {
    }

    public Product(String productId, String name, double price, int stockLevel, String description, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockLevel = stockLevel;
        this.description = description;
        this.category = category;
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
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static boolean updateProduct(int productId, String newName, double newPrice, int newStockLevel, String newDescription, Category newCategory) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                product.setName(newName);
                product.setPrice(newPrice);
                product.setStockLevel(newStockLevel);
                product.setDescription(newDescription);
                product.setCategory(newCategory);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteProduct(int productId) {
        return products.removeIf(product -> product.getProductId() == productId);
    }
    
    @Override
    public String toTableString() {
        return String.format("| %-10s | %-20s | %-10.2f | %-10d | %-25s | %-20s |", 
                             productId, name, price, stockLevel, description, category);
    }
}
