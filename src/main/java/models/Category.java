package models;

import java.util.ArrayList;
import java.util.List;

public class Category implements Displayable {
    private String categoryID;
    private String categoryName;

    private static List<Category> categories = new ArrayList<>();

    public Category() {
    }

    public Category(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
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

    public static boolean updateCategory(String categoryID, String newName) {
        for (Category category : categories) {
            if (category.getCategoryID() == categoryID) {
                category.setCategoryName(newName);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteCategory(String categoryID) {
        return categories.removeIf(category -> category.getCategoryID() == categoryID);
    }

    @Override
    public String toTableString() {
        return String.format("| %-10d | %-20s |", categoryID, categoryName);
    }
}