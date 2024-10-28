package controller;

import models.StoreOwner;
import models.Product;
import models.Category;
import models.Inventory;
import DAO.ProductDAO;
import DAO.CategoryDAO;
import DAO.InventoryDAO;

public class StoreOwnerMenuController extends BaseController {
    private StoreOwner storeOwner;
    private ProductMenuController productMenuController;
    private CategoryMenuController categoryMenuController;
    private InventoryMenuController inventoryMenuController;
    private CategoryDAO categoryDAO;
    private InventoryDAO inventoryDAO;

    public StoreOwnerMenuController(StoreOwner storeOwner) {
        super();
        this.storeOwner = storeOwner;
        this.productMenuController = new ProductMenuController();
        this.categoryDAO = new CategoryDAO();
        this.inventoryDAO = new InventoryDAO();
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
                    viewReports();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
            }
        } while (choice != 0);
    }
}
