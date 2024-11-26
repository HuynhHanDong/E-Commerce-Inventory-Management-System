package controller;

import models.User;

public class StoreOwnerMenuController extends BaseController {
    private final User user;
    private final ProductMenuController productMenuController;
    private final CategoryMenuController categoryMenuController;
    private final InventoryMenuController inventoryMenuController;
    private final ReportMenuController reportMenuController;

    public StoreOwnerMenuController(User user) {
        super();
        this.user = user;
        this.productMenuController = new ProductMenuController();
        this.categoryMenuController = new CategoryMenuController();
        this.inventoryMenuController = new InventoryMenuController();
        this.reportMenuController = new ReportMenuController();
    }

    public void displayStoreOwnerMenu() {
        int choice;
        do {
            menu.ownerMenu();
            choice = getValidChoice(0, 4);
            switch (choice) {
                case 1:
                    productMenuController.manageProducts();
                    break;
                case 2:
                    categoryMenuController.manageCategories();
                    break;
                case 3:
                    inventoryMenuController.manageInventory();
                    break;
                case 4:
                    reportMenuController.manageReports();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
            }
        } while (choice != 0);
    }
}
