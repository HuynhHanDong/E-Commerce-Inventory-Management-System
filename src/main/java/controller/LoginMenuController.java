package controller;

import models.User;
import DAO.UserDAO;

public class LoginMenuController extends BaseController {
    private UserDAO userDAO;

    public LoginMenuController() {
        super();
        this.userDAO = new UserDAO();
    }

    public void displayLoginMenu() {
        while (true) {
            menu.loginMenu();
            int choice = getValidChoice(0, 2);

            switch (choice) {
                case 1:
                    loginUser("STORE_OWNER");
                    break;
                case 2:
                    loginUser("CUSTOMER");
                    break;
                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
            }
        }
    }

    private void loginUser(String role) {
        String username;
        String password;

        // Validate username
        do {
            System.out.println("Enter username:");
            username = scanner.nextLine().trim();
        } while (!UserValidation.isValidUsername(username));

        // Validate password
        do {
            System.out.println("Enter password:");
            password = scanner.nextLine().trim();
        } while (!UserValidation.isValidPassword(password));

        User user = userDAO.authenticate(username, password);

        if (user != null && user.getRole().equals(role)) {
            System.out.println("Login successful!");
            switch (role) {
                case "STORE_OWNER":
                    new StoreOwnerMenuController(user).displayStoreOwnerMenu();
                    break;
                case "CUSTOMER":
                    new CustomerMenuController(user).displayCustomerMenu();
                    break;
            }
        } else {
            if (user == null) {
                System.out.println("Invalid username or password.");
            } else {
                System.out.println("You don't have permission to access this role.");
            }
        }
    }
}
