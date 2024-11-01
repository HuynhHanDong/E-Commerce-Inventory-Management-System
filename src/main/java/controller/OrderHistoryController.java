/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.OrderDAO;
import java.util.ArrayList;
import models.Order;

/**
 *
 * @author Huynh Han Dong
 */
public class OrderHistoryController extends BaseController{
    private final int customerID;
    private final OrderDAO orderDAO;
   
    public OrderHistoryController(int customerID){
        super();
        this.customerID = customerID;
        this.orderDAO = new OrderDAO();
    }
    
    public void DisplayOrderHistoryMenu() {
        int choice;
        do {
            menu.orderHistoryMenu();
            choice = getValidChoice(0, 5);
            
            switch (choice) {
                case 1:
                    viewAllOrders();
                    break;
                case 2:
                    menu.orderHistorySubMenu();
                    choice = getValidChoice(0, 5);
                    switch (choice) {
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
                            viewOrdersByStatus("Canceled");
                            break;
                        case 0:
                            break;
                    }
                case 0:
                    System.out.println("Going back to Customer Menu...");
                    break;
                }
        } while (choice != 0);
    }        
    
    private void viewAllOrders() {
        ArrayList<Order> orderList = orderDAO.getAllOrders(customerID);
        if (orderList != null) {
            for (Order order : orderList) {
                System.out.println(order.toString());
            }
        } else {
            System.out.println("No order history.");
        }
    }
    
    private void viewOrdersByStatus(String status) {
        ArrayList<Order> orderList = orderDAO.getOrderByStatus(customerID, status);
        if (orderList != null) {
            for (Order order : orderList) {
                System.out.println(order.toString());
            }
        } else {
            System.out.println("No order history.");
        }
    }
    
    public void viewOrderDetails() {
        System.out.println("Enter orderID to view details: ");
        int orderID = scanner.nextInt();
        Order order = orderDAO.getOrderByID(orderID, customerID);
        if (order != null) {
            System.out.println(order.getDetails()); // Print out order details and list of orderItems
        } else {
            System.out.println("Order not found.");
        }
    }
    
    public void cancelOrder() {
        System.out.println("Enter orderID to cancel:");
        int id = scanner.nextInt();
        int result = orderDAO.updateOrder("Cancelled", id);
        if (result > 0) {
            System.out.println("Cancelled order.");
        } else {
            System.out.println("Order not found.");
        }
    }
}