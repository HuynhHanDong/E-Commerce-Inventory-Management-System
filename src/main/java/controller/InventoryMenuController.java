package controller;

import java.util.List;
import DAO.InventoryDAO;
import models.Inventory;

public class InventoryMenuController extends BaseController {
    private InventoryDAO inventoryDAO;

    public InventoryMenuController() {
        super();
        this.inventoryDAO = new InventoryDAO();
    }

    public void manageInventory() {
        int choice;
        do {
            menu.inventoryMenu();
            choice = getValidChoice(0, 5);
            switch (choice) {
                case 1:
                    addInventoryItem();
                    break;
                case 2:
                    updateInventoryItem();
                    break;
                case 3:
                    deleteInventoryItem();
                    break;
                case 4:
                    viewInventoryItemById();
                    break;
                case 5:
                    viewAllInventoryItems();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
            }
        } while (choice != 0);
    }

    private void addInventoryItem() {
        try {
            System.out.println("Enter current stock level:");
            int stockLevel = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidStockLevel(stockLevel)) {
                return;
            }

            System.out.println("Enter low stock threshold:");
            int lowStockThreshold = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidLowStockThreshold(lowStockThreshold)) {
                return;
            }

            // Assume Product ID is required here
            System.out.println("Enter Product ID:");
            int productID = Integer.parseInt(scanner.nextLine());

            Inventory inventory = new Inventory(0, productID, stockLevel, lowStockThreshold);
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

    private void updateInventoryItem() {
        try {
            System.out.println("Enter inventory ID to update:");
            int inventoryID = Integer.parseInt(scanner.nextLine());
            if (!StoreOwnerValidation.isValidInventoryID(inventoryID)) {
                return;
            }

            Inventory existingInventory = inventoryDAO.getInventoryItemById(inventoryID);
            if (existingInventory == null) {
                System.out.println("Inventory item not found.");
                return;
            }

            System.out.println("Enter new stock level (or press -1 to skip):");
            int stockLevel = Integer.parseInt(scanner.nextLine());
            if (stockLevel != -1 && !StoreOwnerValidation.isValidStockLevel(stockLevel)) {
                return;
            }

            System.out.println("Enter new low stock threshold (or press -1 to skip):");
            int lowStockThreshold = Integer.parseInt(scanner.nextLine());
            if (lowStockThreshold != -1 && !StoreOwnerValidation.isValidLowStockThreshold(lowStockThreshold)) {
                return;
            }

            // Update values only if valid
            if (stockLevel != -1) existingInventory.setStockLevel(stockLevel);
            if (lowStockThreshold != -1) existingInventory.setLowStockThreshold(lowStockThreshold);

            int result = inventoryDAO.updateInventoryItem(existingInventory);
            if (result > 0) {
                System.out.println("Inventory item updated successfully.");
            } else {
                System.out.println("Failed to update inventory item.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void deleteInventoryItem() {
        System.out.println("Enter product ID to delete:");
        String productIdInput = scanner.nextLine();
        if (!StoreOwnerValidation.isValidProductId(productIdInput)) {
            return;
        }
        int productId = Integer.parseInt(productIdInput);

        System.out.println("Are you sure you want to delete this inventory item? (y/n)");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("y")) {
            boolean success = inventoryDAO.deleteInventoryItem(productId);
            if (success) {
                System.out.println("Inventory item deleted successfully.");
            } else {
                System.out.println("Failed to delete inventory item.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private void viewInventoryItemById() {
        System.out.println("Enter product ID to view:");
        String productIdInput = scanner.nextLine();
        if (!StoreOwnerValidation.isValidProductId(productIdInput)) {
            return;
        }
        int productId = Integer.parseInt(productIdInput);

        Inventory inventory = inventoryDAO.getInventoryItemById(productId);
        if (inventory != null) {
            System.out.println(inventory.toString());
        } else {
            System.out.println("Inventory item not found.");
        }
    }

    private void viewAllInventoryItems() {
        System.out.println("All Inventory Items:");
        System.out.println("----------------------------------------");
        List<Inventory> inventoryList = inventoryDAO.getAllInventoryItems();

        if (inventoryList.isEmpty()) {
            System.out.println("No inventory items found.");
        } else {
            for (Inventory inventory : inventoryList) {
                System.out.println(inventory.toString());
            }
        }
        System.out.println("----------------------------------------");
    }
}
