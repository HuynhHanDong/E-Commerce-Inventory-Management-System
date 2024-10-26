package controller;

import models.Customer;
import models.StoreOwner;
import DAO.CustomerDAO;
import DAO.StoreOwnerDAO;

public class LoginMenuController extends BaseController {
    private CustomerDAO customerDAO;
    private StoreOwnerDAO storeOwnerDAO;

    public LoginMenuController() {
        super();
        this.customerDAO = new CustomerDAO();
        this.storeOwnerDAO = new StoreOwnerDAO();
    }

    public void displayLoginMenu() {
        while (true) {
            menu.loginMenu();
            int choice = getValidChoice(0, 2);

            switch (choice) {
                case 1:
                    loginStoreOwner();
                    break;
                case 2:
                    loginCustomer();
                    break;
                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
            }
        }
    }

    private void loginStoreOwner() {
        StoreOwner storeOwner = getStoreOwnerInfo();
        if (storeOwner != null && storeOwnerDAO.authenticate(storeOwner.getStoreownerID(), storeOwner.getUsername(),
                storeOwner.getEmail(), storeOwner.getPassword())) {
            System.out.println("Store owner login successful!");
            new StoreOwnerMenuController(storeOwner).displayStoreOwnerMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private void loginCustomer() {
        Customer customer = getCustomerInfo();
        if (customer != null && customerDAO.authenticate(customer.getCustomerID(), customer.getUsername(),
                customer.getEmail(), customer.getPassword())) {
            System.out.println("Customer login successful!");
            new CustomerMenuController(customer).displayCustomerMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private StoreOwner getStoreOwnerInfo() {
        int id = 0;
        String username = "";
        String email = "";
        String password = "";

        while (!StoreOwnerValidation.isValidId(id)) {
            System.out.println("Enter store owner ID:");
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for ID.");
            }
        }

        while (!StoreOwnerValidation.isValidUsername(username)) {
            System.out.println("Enter username:");
            username = scanner.nextLine();
        }

        while (!StoreOwnerValidation.isValidEmail(email)) {
            System.out.println("Enter email:");
            email = scanner.nextLine();
        }

        while (!StoreOwnerValidation.isValidPassword(password)) {
            System.out.println("Enter password:");
            password = scanner.nextLine();
        }

        return new StoreOwner(id, username, email, password);
    }

    private Customer getCustomerInfo() {
        int id = 0;
        String username = "";
        String email = "";
        String password = "";

        while (!StoreOwnerValidation.isValidId(id)) {
            System.out.println("Enter customer ID:");
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for ID.");
            }
        }

        while (!StoreOwnerValidation.isValidUsername(username)) {
            System.out.println("Enter username:");
            username = scanner.nextLine();
        }

        while (!StoreOwnerValidation.isValidEmail(email)) {
            System.out.println("Enter email:");
            email = scanner.nextLine();
        }

        while (!StoreOwnerValidation.isValidPassword(password)) {
            System.out.println("Enter password:");
            password = scanner.nextLine();
        }

        return new Customer(id, username, email, password);
    }
}
