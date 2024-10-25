package controller;

import models.StoreOwner;
import models.Product;
import models.Category;
import models.Inventory;
import DAO.ProductDAO;
import DAO.CategoryDAO;
import DAO.InventoryDAO;
import java.util.InputMismatchException;

public class StoreOwnerMenuController extends BaseController {
    private StoreOwner storeOwner;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private InventoryDAO inventoryDAO;

    public StoreOwnerMenuController(StoreOwner storeOwner) {
        super();
        this.storeOwner = storeOwner;
        this.productDAO = new ProductDAO();
        this.categoryDAO = new CategoryDAO();
        this.inventoryDAO = new InventoryDAO();
    }

    public void displayStoreOwnerMenu() {
        int choice;
        do {
            menu.ownerMenu();
            choice = getValidChoice(0, 4);
            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    manageCategories();
                    break;
                case 3:
                    manageInventory();
                    break;
                case 4:
                    viewReports();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
            }
        } while (choice != 0);
    }

    private void manageProducts() {
        int choice;
        do {
            menu.productMenu();
            choice = getValidChoice(0, 6);
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
                    searchProductById();
                    break;
                case 5:
                    searchProductByCategory();
                    break;
                case 6:
                    productDAO.viewAllProducts();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
            }
        } while (choice != 0);
    }

    private void addProduct() {
        try {
            System.out.println("Enter product ID:");
            int productID = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter product name:");
            String productName = scanner.nextLine();
            System.out.println("Enter category:");
            String category = scanner.nextLine();
            System.out.println("Enter price:");
            double price = scanner.nextDouble();
            System.out.println("Enter stock level:");
            int stockLevel = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter description:");
            String description = scanner.nextLine();

            Product product = new Product(productID, productName, price, stockLevel, description, category);
            int result = productDAO.addProduct(product);
            if (result > 0) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine(); // Clear the scanner buffer
        }
    }

    private void updateProduct() {
        // Implement update product method
    }

    private void deleteProduct() {
        // Implement delete product method
    }

    private void searchProductById() {
        // Implement search product by ID method
    }

    private void searchProductByCategory() {
        // Implement search product by category method
    }

}
