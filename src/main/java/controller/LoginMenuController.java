package controller;

import models.Customer;
import models.StoreOwner;
import DAO.CustomerDAO;
import DAO.StoreOwnerDAO;
import java.util.InputMismatchException;

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
        StoreOwner storeOwner = getStoreOwnerCredentials();
        if (storeOwner != null && storeOwnerDAO.authenticate(storeOwner.getStoreownerID(), storeOwner.getUsername(),
                storeOwner.getEmail(), storeOwner.getPassword())) {
            System.out.println("Store owner login successful!");
            new StoreOwnerMenuController(storeOwner).displayStoreOwnerMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private void loginCustomer() {
        Customer customer = getCustomerCredentials();
        if (customer != null && customerDAO.authenticate(customer.getCustomerID(), customer.getUsername(),
                customer.getEmail(), customer.getPassword())) {
            System.out.println("Customer login successful!");
            new CustomerMenuController(customer).displayCustomerMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private StoreOwner getStoreOwnerCredentials() {
        try {
            System.out.println("Enter store owner ID:");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            return new StoreOwner(id, username, email, password);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for ID.");
            scanner.nextLine();
            return null;
        }
    }

    private Customer getCustomerCredentials() {
        try {
            System.out.println("Enter customer ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            return new Customer(id, username, email, password);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for ID.");
            scanner.nextLine();
            return null;
        }
    }
}
