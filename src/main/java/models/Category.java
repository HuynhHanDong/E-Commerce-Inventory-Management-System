package models;

import java.util.ArrayList;
import java.util.List;

public class Category implements Displayable {
    private int categoryID;
    private String categoryName;

    private static List<Category> categories = new ArrayList<>();

    public Category() {
    }

    public Category(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static void createCategory(Category category) {
        categories.add(category);
    }

    public static List<Category> readCategories() {
        return categories;
    }

    public static boolean updateCategory(int categoryID, String newName) {
        for (Category category : categories) {
            if (category.getCategoryID() == categoryID) {
                category.setCategoryName(newName);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteCategory(int categoryID) {
        return categories.removeIf(category -> category.getCategoryID() == categoryID);
    }

    @Override
    public String toTableString() {
        return String.format("| %-10d | %-20s |", categoryID, categoryName);
    }
}