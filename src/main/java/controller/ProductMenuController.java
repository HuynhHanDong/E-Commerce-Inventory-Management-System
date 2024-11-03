package controller;

import models.Product;
import DAO.ProductDAO;
import java.util.List;

public class ProductMenuController extends BaseController {
    private ProductDAO productDAO;

    public ProductMenuController() {
        super();
        this.productDAO = new ProductDAO();
    }

    public void manageProducts() {
        int choice;
        do {
            menu.productMenu();
            choice = getValidChoice(0, 5);
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    searchProduct();
                    break;
                case 5:
                    viewAllProducts();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
            }
        } while (choice != 0);
    }

    private void addProduct() {
        try {
            System.out.println("Enter product name:");
            String productName = scanner.nextLine();
            if (!StoreOwnerValidation.isValidProductName(productName)) {
                return;
            }

            System.out.println("Enter category:");
            String categoryName = scanner.nextLine();
            if (!StoreOwnerValidation.isValidCategoryName(categoryName)) {
                return;
            }

            System.out.println("Enter price:");
            double price = Double.parseDouble(scanner.nextLine());
            if (!StoreOwnerValidation.isValidPrice(price)) {
                return;
            }

            System.out.println("Enter description:");
            String description = scanner.nextLine();
            if (!StoreOwnerValidation.isValidDescription(description)) {
                return;
            }

            Product product = new Product(0, productName, price, description, categoryName);
            int result = productDAO.addProduct(product);
            if (result > 0) {
                int productID = productDAO.getproductID();
                System.out.println("Product added successfully. ProductID: " + productID);
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void updateProduct() {
        try {
            System.out.println("Enter product ID to update:");
            int productID = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidProductID(productID)) {
                return;
            }

            Product existingProduct = productDAO.searchProduct("ProductID = " + productID);
            if (existingProduct == null) {
                System.out.println("Product not found.");
                return;
            }

            System.out.println("Enter new product name (or press enter to skip):");
            String productName = scanner.nextLine();
            if (!productName.isEmpty() && !StoreOwnerValidation.isValidProductName(productName)) {
                return;
            }

            System.out.println("Enter new category (or press enter to skip):");
            String categoryName = scanner.nextLine();
            if (!categoryName.isEmpty() && !StoreOwnerValidation.isValidCategoryName(categoryName)) {
                return;
            }

            System.out.println("Enter new price (or -1 to skip):");
            double price = Double.parseDouble(scanner.nextLine());
            if (price != -1 && !StoreOwnerValidation.isValidPrice(price)) {
                return;
            }

            System.out.println("Enter new description (or press enter to skip):");
            String description = scanner.nextLine();
            if (!description.isEmpty() && !StoreOwnerValidation.isValidDescription(description)) {
                return;
            }

            if (!productName.isEmpty())
                existingProduct.setProductName(productName);

            if (!categoryName.isEmpty())
                existingProduct.setCategory(categoryName);

            if (price != -1)
                existingProduct.setPrice(price);

            if (!description.isEmpty())
                existingProduct.setDescription(description);

            int result = productDAO.updateProduct(existingProduct);
            if (result > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Failed to update product.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        try {
            System.out.println("Enter product ID to delete:");
            int productID = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidProductID(productID)) {
                return;
            }

            Product existingProduct = productDAO.searchProduct("ProductID = " + productID);
            if (existingProduct == null) {
                System.out.println("Product not found.");
                return;
            }

            System.out.println("Are you sure you want to delete this product? (y/n)");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                int result = productDAO.deleteProduct(productID);
                if (result > 0) {
                    System.out.println("Product deleted successfully.");
                } else {
                    System.out.println("Failed to delete product.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void searchProductById() {
        try {
            System.out.println("Enter product ID to search:");
            int productID = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidProductID(productID)) {
                return;
            }

            Product product = productDAO.searchProduct("ProductID = " + productID);
            if (product != null) {
                System.out.println(product.toString());
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    
    private void searchProductByName() {
        try {
            System.out.println("Enter product name to view details:");
            String productName = scanner.nextLine();
            if (!StoreOwnerValidation.isValidProductName(productName)) {
                return;
            }

            Product product = productDAO.searchProduct("productName = '" + productName + "'");
            if (product != null) {
                System.out.println(product.toString());
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void searchProductByCategory() {
        try {
            System.out.println("Enter category name to search:");
            String categoryName = scanner.nextLine();
            if (!StoreOwnerValidation.isValidCategoryName(categoryName)) {
                return;
            }

            Product product = productDAO.searchProduct("CategoryName = '" + categoryName + "'");
            if (product != null) {
                System.out.println(product.toString());
            } else {
                System.out.println("No products found in this category.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void searchProduct() {
        try {
            System.out.println("How would you like to search: \n" +
                    "[1]. ProductID \n" +
                    "[2]. Product Name \n" +
                    "[3]. Category Name \n" +
                    "[0]. Go back");
            
            int choice = getValidChoice(1, 3);

            switch (choice) {
                case 1:
                    searchProductById();
                    break;
                case 2:
                    searchProductByName();
                    break;
                case 3:
                    searchProductByCategory();
                    break;
                case 0:
                    break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    
    public void viewAllProducts() {
        try {
            List<Product> productList = productDAO.viewAllProducts();
            if (!productList.isEmpty()) {
                for (Product product : productList) {
                    System.out.println(product.toString());
                }
            } else {
                System.out.println("There is no product at the moment. Please come back later");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}