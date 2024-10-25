package controller;

import models.Customer;
import models.Product;
import models.Order;
import models.OrderItems;
import DAO.ProductDAO;
import DAO.OrderDAO;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.sql.Date;

public class CustomerMenuController extends BaseController {
    private Customer customer;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private ArrayList<OrderItems> cart;

    public CustomerMenuController(Customer customer) {
        super();
        this.customer = customer;
        this.productDAO = new ProductDAO();
        this.orderDAO = new OrderDAO();
        this.cart = new ArrayList<>();
    }

    public void displayCustomerMenu() {
        while (true) {
            menu.customerMenu();
            int choice = getValidChoice(0, 5);

            switch (choice) {
                case 1:
                    viewProductDetails();
                    break;
                case 2:
                    searchProduct();
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                    viewOrderHistory();
                    break;
                case 5:
                    viewOrderDetails();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    return;
            }
        }
    }

}
