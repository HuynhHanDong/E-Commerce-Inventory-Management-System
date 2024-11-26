package controller;

import models.User;

public class CustomerMenuController extends BaseController {

    private final User user;
    private final OrderMenuController orderMenuController;
    private final ProductMenuController productMenuController;
    private final OrderHistoryController orderHistoryController;

    public CustomerMenuController(User user) {
        super();
        this.user = user;
        this.productMenuController = new ProductMenuController();
        this.orderMenuController = new OrderMenuController(user.getUserID());
        this.orderHistoryController = new OrderHistoryController(user.getUserID());
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
