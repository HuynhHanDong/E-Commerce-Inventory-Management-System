package controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DAO.InventoryDAO;
import models.Inventory;

public class InventoryMenuController extends BaseController {
    private InventoryDAO inventoryDAO;
    private Scanner scanner;

    public InventoryMenuController() {
        this.inventoryDAO = new InventoryDAO();
        this.scanner = new Scanner(System.in);
    }

    public void manageInventory() {
        int choice;
        do {
            menu.inventorytMenu();
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
            System.out.println("Enter product ID:");
            int productID = scanner.nextInt();
            System.out.println("Enter current stock:");
            int currentStock = scanner.nextInt();
            System.out.println("Enter low stock threshold:");
            int lowStockThreshold = scanner.nextInt();

            Inventory inventory = new Inventory(productID, currentStock, lowStockThreshold);
            boolean success = inventoryDAO.addInventoryItem(inventory);
            if (success) {
                System.out.println("Inventory item added successfully.");
            } else {
                System.out.println("Failed to add inventory item.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine(); 
        }
    }

    private void updateInventoryItem() {
        try {
            System.out.println("Enter product ID to update:");
            int productID = scanner.nextInt();
            System.out.println("Enter new current stock:");
            int currentStock = scanner.nextInt();
            System.out.println("Enter new low stock threshold:");
            int lowStockThreshold = scanner.nextInt();

            Inventory inventory = new Inventory(productID, currentStock, lowStockThreshold);
            boolean success = inventoryDAO.updateInventoryItem(inventory);
            if (success) {
                System.out.println("Inventory item updated successfully.");
            } else {
                System.out.println("Failed to update inventory item.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine(); 
        }
    }

    private void deleteInventoryItem() {
        try {
            System.out.println("Enter product ID to delete:");
            int productID = scanner.nextInt();
            boolean success = inventoryDAO.deleteInventoryItem(String.valueOf(productID));
            if (success) {
                System.out.println("Inventory item deleted successfully.");
            } else {
                System.out.println("Failed to delete inventory item.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid product ID.");
            scanner.nextLine();
        }
    }

    private void viewInventoryItemById() {
        try {
            System.out.println("Enter product ID to view:");
            int productID = scanner.nextInt();
            Inventory inventory = inventoryDAO.getInventoryItemById(String.valueOf(productID));
            if (inventory != null) {
                System.out.println(inventory);
            } else {
                System.out.println("Inventory item not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid product ID.");
            scanner.nextLine(); 
        }
    }

    private void viewAllInventoryItems() {
    List<Inventory> inventoryItems = inventoryDAO.getAllInventoryItems();
    for (Inventory inventory : inventoryItems) {
        System.out.println(inventory);
    }
}

}
