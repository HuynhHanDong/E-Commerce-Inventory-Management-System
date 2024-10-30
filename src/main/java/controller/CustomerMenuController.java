package controller;

import models.Customer;
import models.Product;
import models.Order;
import models.OrderItems;
import DAO.ProductDAO;
import DAO.OrderDAO;
import java.util.ArrayList;
import java.sql.Date;

public class CustomerMenuController extends BaseController {
    private Customer customer;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private OrderMenuController orderMenuController;
    private ProductMenuController productMenuController;

    public CustomerMenuController(Customer customer) {
        super();
        this.customer = customer;
        this.productDAO = new ProductDAO();
        this.orderDAO = new OrderDAO();
        this.productMenuController = new ProductMenuController();
    }

    public void displayCustomerMenu() {
        while (true) {
            menu.customerMenu();
            int choice = getValidChoice(0, 5);

            switch (choice) {
                case 1:
                    productMenuController.viewProductDetails();
                    break;
                case 2:
                    productMenuController.searchProduct();
                    break;
                case 3:
                    orderMenuController.displayOrderMenu();
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

    public void viewOrderHistory() {
        ArrayList<Order> history = orderDAO.viewOrderHistory();
        if (history != null) {
            for (Order order : history) {
                System.out.println(order.toString());
            }
        } else {
            System.out.println("No order history.");
        }
    }

    public void viewOrderDetails() {
        System.out.println("Enter order ID to view details: ");
        int orderID = scanner.nextInt();
        Order order = orderDAO.viewOrderDetails(orderID);
        if (order != null) {
            System.out.println(order.toString());

            ArrayList<OrderItems> items = orderDAO.viewItemsDetails(orderID);
            for (OrderItems item : items) {
                System.out.println(item.toString());
            }

        } else {
            System.out.println("Order not found.");
        }
    }
}
