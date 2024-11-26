package controller;

import java.util.List;

import DAO.CategoryDAO;
import models.Category;

public class CategoryMenuController extends BaseController {
    private final CategoryDAO categoryDAO;

    public CategoryMenuController() {
        this.categoryDAO = new CategoryDAO();
    }

    public void manageCategories() {
        int choice;
        do {
            menu.categoryMenu();
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
                    searchCategory();
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
            System.out.print("Enter Category Name: ");
            String categoryName = scanner.nextLine();

            Category category = new Category(0, categoryName);
            int result = categoryDAO.addCategory(category);
            if (result > 0) {
                int categoryID = categoryDAO.getCategoryID();
                System.out.println("Category added successfully. CategoryID: " + categoryID);
            } else {
                System.out.println("Failed to add category.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
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
            int result = categoryDAO.updateCategory(category);
            if (result > 0) {
                System.out.println("Category updated successfully.");
            } else {
                System.out.println("Failed to update category.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void deleteCategory() {
        try {
            System.out.println("Enter category ID to delete:");
            int categoryID = Integer.parseInt(scanner.nextLine());
            Category category = categoryDAO.getCategory("CategoryID = " + categoryID);
            if (category != null) {
                System.out.println("Current category details:");
                System.out.println(category.toString());
                System.out.println("\nAre you sure you want to delete this category?");
                System.out.println("[1]. Yes");
                System.out.println("[0]. No");
                int choice = getValidChoice(0, 1);
                if (choice == 1) {
                    int result = categoryDAO.deleteCategory(categoryID);
                    if (result > 0) {
                        System.out.println("Category deleted successfully.");
                    } else {
                        System.out.println("Category not found.");
                    }
                } else {
                    System.out.println("Deletion cancelled.");
                }
            } else {
                System.out.println("Category not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid category ID format.");
        }
    }

    public void searchCategory() {
        try {
            System.out.println("How would you like to search: \n" +
                    "[1]. Category ID \n" +
                    "[2]. Category Name \n" +
                    "[0]. Go back");

            int choice = getValidChoice(0, 2);

            switch (choice) {
                case 1:
                    searchCategoryByID();
                    break;
                case 2:
                    searchCategoryByName();
                    break;
                case 0:
                    break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void searchCategoryByID() {
        try {
            System.out.print("Enter Category ID to view: ");
            int categoryID = scanner.nextInt();
            Category category = categoryDAO.getCategory("CategoryID = " + categoryID);
            if (category != null) {
                System.out.println(category.toString());
            } else {
                System.out.println("Category not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void searchCategoryByName() {
        try {
            System.out.print("Enter Category name to view: ");
            String categoryName = scanner.nextLine();
            Category category = categoryDAO.getCategory("CategoryName = '" + categoryName + "'");
            if (category != null) {
                System.out.println(category.toString());
            } else {
                System.out.println("Category not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void viewAllCategories() {
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            if (!categories.isEmpty()) {
                for (Category category : categories) {
                    System.out.println(category.toString());
                }
            } else {
                System.out.println("There is no product at the moment. Please come back later");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
