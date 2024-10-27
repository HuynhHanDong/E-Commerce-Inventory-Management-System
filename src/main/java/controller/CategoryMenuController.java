package controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DAO.CategoryDAO;
import models.Category;

public class CategoryMenuController extends BaseController {
    private CategoryDAO categoryDAO;
    private Scanner scanner;

    public CategoryMenuController() {
        this.categoryDAO = new CategoryDAO();
        this.scanner = new Scanner(System.in);
    }

    public void manageCategories() {
        int choice;
        do {
            menu.categorytMenu(); 
            choice = getValidChoice(0, 5);
            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    updateCategory();
                    break;
                case 3:
                    deleteCategory();
                    break;
                case 4:
                    viewCategoryById();
                    break;
                case 5:
                    viewAllCategories();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
            }
        } while (choice != 0);
    }

    private void addCategory() {
        try {
            System.out.print("Enter Category ID: ");
            int categoryID = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer
            System.out.print("Enter Category Name: ");
            String categoryName = scanner.nextLine();

            Category category = new Category(categoryID, categoryName);
            boolean success = categoryDAO.addCategory(category) > 0;
            if (success) {
                System.out.println("Category added successfully.");
            } else {
                System.out.println("Failed to add category.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine();
        }
    }

    private void updateCategory() {
        try {
            System.out.print("Enter Category ID to update: ");
            int categoryID = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter new Category Name: ");
            String categoryName = scanner.nextLine();

            Category category = new Category(categoryID, categoryName);
            boolean success = categoryDAO.updateCategory(category) > 0;
            if (success) {
                System.out.println("Category updated successfully.");
            } else {
                System.out.println("Failed to update category.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine(); 
        }
    }

    private void deleteCategory() {
        try {
            System.out.print("Enter Category ID to delete: ");
            int categoryID = scanner.nextInt();
            boolean success = categoryDAO.deleteCategory(categoryID) > 0;
            if (success) {
                System.out.println("Category deleted successfully.");
            } else {
                System.out.println("Failed to delete category.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid Category ID.");
            scanner.nextLine(); 
        }
    }

    private void viewCategoryById() {
        try {
            System.out.print("Enter Category ID to view: ");
            int categoryID = scanner.nextInt();
            categoryDAO.getCategoryById(categoryID); // Adjust if you want to return a Category object
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid Category ID.");
            scanner.nextLine(); 
        }
    }

    private void viewAllCategories() {
    List<Category> categories = categoryDAO.getAllCategories();
    for (Category category : categories) {
        System.out.println(category);
    }
}

}
