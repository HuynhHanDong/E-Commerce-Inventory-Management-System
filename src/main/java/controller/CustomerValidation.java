package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomerValidation {
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
        if (String.valueOf(id).isEmpty()) {
            System.out.println("ID cannot be empty.");
            return false;
        }
        if (String.valueOf(id).length() != 4) {
            System.out.println("ID must be 4 digits.");
            return false;
        }
        if (!String.valueOf(id).matches("^\\d+$")) {
            System.out.println("ID must be a number.");
            return false;
        }
        return true;
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
}
