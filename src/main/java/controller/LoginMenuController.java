package controller;

import view.Menu;
import models.Customer;
import models.StoreOwner;
import controller.CustomerMenuController;

import DAO.CustomerDAO;
import DAO.StoreOwnerDAO;
import java.sql.Connection;

public class LoginMenuController extends BaseController {
    private CustomerDAO customerDAO;
    private StoreOwnerDAO storeOwnerDAO;

    public LoginMenuController(Connection connection) {
        super();
        this.customerDAO = new CustomerDAO(connection);
        this.storeOwnerDAO = new StoreOwnerDAO(connection);
    }

    public void displayLoginMenu() {
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

    private void loginStoreOwner() {
        System.out.println("Enter store owner ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (storeOwnerDAO.authenticate(id, username, email, password)) {
            System.out.println("Store owner login successful!");
            StoreOwner storeOwner = new StoreOwner(id, email, username, password);
            new StoreOwnerMenuController(storeOwner).displayStoreOwnerMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
            displayLoginMenu();
        }
    }

    private void loginCustomer() {
        System.out.println("Enter customer ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (customerDAO.authenticate(id, username, email, password)) {
            System.out.println("Customer login successful!");
            Customer customer = new Customer(id, email, username, password);
            new CustomerMenuController(customer).displayCustomerMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
            displayLoginMenu();
        }
    }
}