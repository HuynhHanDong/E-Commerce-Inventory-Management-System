/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.OrderDAO;
import java.util.List;
import models.Order;
import models.OrderItems;

/**
 *
 * @author Huynh Han Dong
 */
public class OrderHistoryController extends BaseController {
    private final int userID;
    private final OrderDAO orderDAO;

    public OrderHistoryController(int userID) {
        super();
        this.userID = userID;
        this.orderDAO = new OrderDAO();
    }

    public void viewOrderHistory() {
        int choice;
        do {
            menu.orderHistoryMenu();
            choice = getValidChoice(0, 3);

            switch (choice) {
                case 1:
                    viewAllOrders();
                    break;
                case 2:
                    menu.orderHistorySubMenu();
                    int status = getValidChoice(0, 5);
                    switch (status) {
                        case 1:
                            viewOrdersByStatus("Pending");
                            break;
                        case 2:
                            viewOrdersByStatus("In progress");
                            break;
                        case 3:
                            viewOrdersByStatus("In transit");
                            break;
                        case 4:
                            viewOrdersByStatus("Finished");
                            break;
                        case 5:
                            viewOrdersByStatus("Cancelled");
                            break;
                        case 0:
                            break;
                    }
                    break;
                case 3:
                    cancelOrder();
                    break;
                case 0:
                    System.out.println("Going back to Customer Menu...");
                    break;
            }
        } while (choice != 0);
    }

    private void viewAllOrders() {
        List<Order> orderList = orderDAO.getAllOrders(userID);
        if (!orderList.isEmpty()) {
            for (Order order : orderList) {
                System.out.println(order.toString());
            }
        } else {
            System.out.println("No order history.");
        }
    }

    private void viewOrdersByStatus(String status) {
        List<Order> orderList = orderDAO.getOrderByStatus(userID, status);
        if (!orderList.isEmpty()) {
            for (Order order : orderList) {
                System.out.println(order.toString());
            }
        } else {
            System.out.println("There is no order with this status.");
        }
    }

    public void viewOrderDetails() {
        System.out.println("Enter orderID to view details: ");
        int orderID = scanner.nextInt();
        Order order = orderDAO.getOrderByID(orderID, userID);
        if (order != null) {
            System.out.println("--------------------------------------");
            System.out.println(order.toString()); // Print out order details
            System.out.println("--------------------------------------");
            for (OrderItems item : order.getItems()) {
                System.out.println(item.toString()); // Print out list of orderItems
            }
            System.out.println("--------------------------------------");
        } else {
            System.out.println("Order not found.");
        }
    }

    private void cancelOrder() {
        System.out.println("Enter orderID to cancel:");
        int orderID = scanner.nextInt();
        Order order = orderDAO.getOrderByID(orderID, userID);
        if (order != null) {
            System.out.println(order.toString());
            if (!"In transit".equals(order.getStatus()) || !"Finished".equals(order.getStatus())) {
                System.out.println("+--------------------------------------+");
                System.out.println("| Cancel this order?                   |");
                System.out.println("| 1. YES                               |");
                System.out.println("| 0. NO                                |");
                System.out.println("+--------------------------------------+");
                int choice = getValidChoice(0, 1);
                if (choice == 1) {
                    int result = orderDAO.updateOrder("Cancelled", orderID);
                    if (result > 0) {
                        System.out.println("Cancelled order.");
                    } else {
                        System.out.println("Failed to cancel order.");
                    }
                }
            } else {
                System.out.println("Cannot cancel. This order is in transit or already finished.");
            }
        } else {
            System.out.println("Order not found.");
        }
    }
}