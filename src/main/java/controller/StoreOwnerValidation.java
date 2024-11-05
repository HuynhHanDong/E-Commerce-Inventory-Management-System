package controller;

import java.text.SimpleDateFormat;

public class StoreOwnerValidation {
    public static boolean isValidUsername(String username) {
        if (username.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return false;
        }
        if (username.length() < 6 || username.length() > 20) {
            System.out.println("Username must be between 6 and 20 characters.");
            return false;
        }
        if (username.matches(".*[0-9!@#$%^&*(){}_+\\-=*/.<>?|\\s].*")) {
            System.out.println("Username cannot contain numbers, special characters, or spaces.");
            return false;
        }
        return true;
    }

    public static boolean isValidId(int id) {
        try {
            if (String.valueOf(id).isEmpty()) {
                System.out.println("ID cannot be empty.");
                return false;
            }
            if (id <= 0) {
                System.out.println("ID must be greater than 0.");
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            System.out.println("ID must be a positive integer.");
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        if (email.isEmpty()) {
            System.out.println("Email cannot be empty.");
            return false;
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Invalid email format.");
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        if (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            return false;
        }
        if (password.length() < 8 || password.length() > 20) {
            System.out.println("Password must be between 8 and 20 characters.");
            return false;
        }
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")) {
            System.out.println(
                    "Password must contain at least 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, and 1 special character.");
            return false;
        }
        return true;
    }

    public static boolean isValidProductID(int productID) {
        try {
            if (String.valueOf(productID).isEmpty()) {
                System.out.println("Product ID cannot be empty.");
                return false;
            }
            if (productID <= 0) {
                System.out.println("Product ID must be greater than 0.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Product ID must be a positive integer.");
            return false;
        }
    }

    public static boolean isValidProductName(String productName) {
        if (productName.isEmpty()) {
            System.out.println("Product name cannot be empty.");
            return false;
        }

        if (productName.length() < 3 || productName.length() > 100) {
            System.out.println("Product name must be between 3 and 100 characters.");
            return false;
        }

        if (!productName.matches("^[a-zA-Z0-9\\s-]+$")) {
            System.out.println("Product name must contain only letters, hyphens, and spaces.");
            return false;
        }
        return true;
    }

    public static boolean isValidPrice(double price) {
        try {
            if (price <= 0) {
                System.out.println("Price must be greater than 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Product ID must be a positive integer.");
            return false;
        }
        return true;
    }

    public static boolean isValidQuantity(int quantity) {
        try {
            if (String.valueOf(quantity).isEmpty()) {
                System.out.println("Quantity cannot be empty.");
                return false;
            }

            if (quantity < 0) {
                System.out.println("Quantity must be greater than 0");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Quantity must be a positive integer.");
            return false;
        }
    }

    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        return true;
    }

    public static boolean isValidCategoryName(String categoryName) {
        if (categoryName.isEmpty()) {
            System.out.println("Category name cannot be empty.");
            return false;
        }

        if (categoryName.length() < 3 || categoryName.length() > 50) {
            System.out.println("Category must be between 3 and 50 characters.");
            return false;
        }

        if (!categoryName.matches("^[a-zA-Z0-9\\s-]+$")) {
            System.out.println("Category name must contain only letters, hyphens, and spaces.");
            return false;
        }
        return true;
    }

    public static boolean isValidStockLevel(int stockLevel) {
        try {
            if (String.valueOf(stockLevel).isEmpty()) {
                System.out.println("Stock level cannot be empty.");
                return false;
            }

            if (stockLevel < 0 || stockLevel > 10000) {
                System.out.println("Stock level must be between 0 and 10000.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Stock level must be a positive integer.");
            return false;
        }
    }

    public static boolean isValidDescription(String description) {
        if (description.isEmpty()) {
            System.out.println("Description cannot be empty.");
            return false;
        }

        if (description.length() < 0 || description.length() > 200) {
            System.out.println("Description must be between 0 and 200 characters.");
            return false;
        }

        if (!description.matches("^[a-zA-Z0-9\\s-]+$")) {
            System.out.println("Description must contain only letters, hyphens, and spaces.");
            return false;
        }
        return true;
    }

    public static boolean isValidLowStockThreshold(int lowStockThreshold) {
        try {
            if (String.valueOf(lowStockThreshold).isEmpty()) {
                System.out.println("Low stock threshold cannot be empty.");
                return false;
            }

            if (lowStockThreshold < 10 || lowStockThreshold > 100) {
                System.out.println("Low stock threshold must be between 10 and 100");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Low stock threshold must be a positive integer.");
        }
        return true;
    }

    public static boolean isValidInventoryID(int inventoryID) {
        try {
            if (String.valueOf(inventoryID).isEmpty()) {
                System.out.println("Inventory ID cannot be empty.");
                return false;
            }

            if (inventoryID <= 0) {
                System.out.println("Inventory ID must be greater than 0.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Inventory ID must be the positive integer.");
            return false;
        }
    }
}
