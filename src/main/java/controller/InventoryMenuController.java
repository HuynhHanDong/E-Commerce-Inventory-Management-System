package controller;

import java.util.List;
import DAO.InventoryDAO;
import DAO.ProductDAO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import models.Inventory;
import models.Product;

public class InventoryMenuController extends BaseController {
    private final InventoryDAO inventoryDAO;

    public InventoryMenuController() {
        super();
        this.inventoryDAO = new InventoryDAO();
    }

    public void manageInventory() {
        int choice;
        do {
            menu.inventoryMenu();
            choice = getValidChoice(0, 4);
            switch (choice) {
                case 1:
                    addInventoryItem();
                    break;
                case 2:
                    deleteInventoryItem();
                    break;
                case 3:
                    viewCurrentStockLevelById();
                    break;
                case 4:
                    viewCurrentStockLevel();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
            }
        } while (choice != 0);
    }

    private void addInventoryItem() {
        try {
            System.out.println("Enter Product ID:");
            int productID = Integer.parseInt(scanner.nextLine());
            if (!UserValidation.isValidId(productID)) {
                return;
            }
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductByID(productID);
            if (product == null){
                System.out.println("Product not found. Please add the product first.");
                return;
            }

            System.out.println("Enter current stock level:");
            int stockLevel = Integer.parseInt(scanner.nextLine());
            if (!UserValidation.isValidStockLevel(stockLevel)) {
                return;
            }

            System.out.println("Enter low stock threshold:");
            int lowStockThreshold = Integer.parseInt(scanner.nextLine());
            if (!UserValidation.isValidLowStockThreshold(lowStockThreshold)) {
                return;
            }

            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp lastUpdate = Timestamp.valueOf(dateTime);

            Inventory inventory = new Inventory(0, productID, stockLevel, lowStockThreshold, lastUpdate);
            int result = inventoryDAO.addInventoryItem(inventory);
            if (result > 0) {
                System.out.println("Inventory item added successfully.");
            } else {
                System.out.println("Failed to add inventory item.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void deleteInventoryItem() {
        try {
            System.out.println("Enter inventory ID to delete:");
            int inventoryID = Integer.parseInt(scanner.nextLine());

            if (!UserValidation.isValidId(inventoryID)) {
                return;
            }
            Inventory inventory = inventoryDAO.getCurrentStockLevelById(inventoryID);
            if (inventory != null) {
                System.out.println("Current inventory details:");
                System.out.println(inventory.toString());
                System.out.println("\nAre you sure you want to delete this inventory record?");
                System.out.println("[1]. Yes");
                System.out.println("[0]. No");
                int choice = getValidChoice(0, 1);
                if (choice == 1) {
                    int result = inventoryDAO.deleteInventoryItem(inventoryID);
                    if (result > 0) {
                        System.out.println("Inventory record deleted successfully.");
                    } else {
                        System.out.println("Failed to delete inventory record.");
                    }
                } else {
                    System.out.println("Deletion cancelled.");
                }
            } else {
                System.out.println("Inventory record not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void viewCurrentStockLevelById() {
        try {
            System.out.println("Enter product ID to view:");
            int productID = Integer.parseInt(scanner.nextLine());
           if (!UserValidation.isValidId(productID)) {
                return;
            }

            Inventory inventory = inventoryDAO.getCurrentStockLevelById(productID);
            if (inventory != null) {
                System.out.println(inventory.toString());
                if (inventory.getStockLevel() <= inventory.getLowStockThreshold()) {
                    System.out.println("LOW STOCK ALERT!");
                }
            } else {
                System.out.println("Inventory record not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void viewCurrentStockLevel() {
        try {
            System.out.println("All Inventory Items:");
            List<Inventory> inventoryList = inventoryDAO.getCurrentStockLevel();

            if (inventoryList.isEmpty()) {
                System.out.println("No inventory record found.");
            } else {
                for (Inventory inventory : inventoryList) {
                    System.out.println(inventory.toString());
                    if (inventory.getStockLevel() <= inventory.getLowStockThreshold()) {
                        System.out.println("+--------------------------------------+");
                       System.out.println("LOW STOCK ALERT! productID: " + inventory.getProductID() + ", productName: " + inventory.getProductName() + ", stockLevel: " + inventory.getStockLevel());
                       System.out.println("+--------------------------------------+");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
