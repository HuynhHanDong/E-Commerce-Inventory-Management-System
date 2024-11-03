package controller;

import models.Customer;

public class CustomerMenuController extends BaseController {
    private final Customer customer;
    private final OrderMenuController orderMenuController;
    private final ProductMenuController productMenuController;
    private final OrderHistoryController orderHistoryController;

    public CustomerMenuController(Customer customer) {
        super();
        this.customer = customer;
        this.productMenuController = new ProductMenuController();
        this.orderMenuController = new OrderMenuController(customer.getCustomerID());
        this.orderHistoryController = new OrderHistoryController(customer.getCustomerID());
    }

    public void displayCustomerMenu() {
        int choice;
        do {
            menu.customerMenu();
            choice = getValidChoice(0, 5);

            switch (choice) {
                case 1:
                    productMenuController.viewAllProducts();
                    break;
                case 2:
                    productMenuController.searchProduct();
                    break;
                case 3:
                    orderMenuController.displayOrderMenu();
                    break;
                case 4:
                    orderHistoryController.viewOrderHistory();
                    break;
                case 5:
                    orderHistoryController.viewOrderDetails();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
            }
        } while (choice != 0);
    }
}
