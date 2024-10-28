package controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import java.util.ArrayList;
import java.util.Scanner;
import models.Customer;
import models.Order;
import models.OrderItems;
import models.Product;

public class OrderMenuController extends BaseController {
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private final Customer customer;
    private Order order;
    private ArrayList<OrderItems> cart;
    
    public OrderMenuController(Customer customer){
        super();
        this.customer = customer;
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
        this.order = new Order();
        this.cart = new ArrayList<>();
    }
    
    public void displayOrderMenu(){
        while (true){
            menu.ordertMenu();
            int choice = getValidChoice(0, 5);
            
            switch (choice) {
                case 1:
                    addToCart();
                    break;
                case 2:
                    changeQuantity();
                    break;
                case 3:
                    removeFromCart();
                    break;
                case 4:
                    viewOrderDetails();
                    break;
                case 5:
                    confirmOrder();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    return;
            }
        }
    }
    
    public void addToCart(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter product id: ");
        int id = scan.nextInt();
        Product product = productDAO.searchProductsById(id);
        System.out.println("Enter quantity: ");
        int quantity = scan.nextInt();
        
        OrderItems item = new OrderItems(orderItemID, orderID, product.getProductID(), product.getPrice(), quantity ); // Not finished
        cart.add(item);
        System.out.println("Added to cart");
    }
    
    public void changeQuantity(){
        if (cart.isEmpty()){
            System.out.println("Nothing in cart");
        } else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter product id to change quantity: ");
            int id = scan.nextInt();
            Product product = productDAO.searchProductsById(id);
            System.out.println("Enter quantity: ");
            int quantity = scan.nextInt();
        
            OrderItems item = new OrderItems(orderItemID, orderID, product.getProductID(), product.getPrice(), quantity ); // Not finished
            cart.set(orderItemID), item);
            System.out.println("Updated");
        }
    }
    
    public void removeFromCart(){
        if (cart.isEmpty()){
            System.out.println("Nothing in cart");
        } else {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter product id to delete: ");
            int id = scan.nextInt();
            cart.remove(id);
            System.out.println("Removed");
        }
    }  
    
    public void viewOrderDetails(){
        for (OrderItems item : cart){
            System.out.println(item.toString());
        }
    }
    
    public void confirmOrder(){
        System.out.println(order.getTotalPrice());
        System.out.println("Confirm order? ");
        System.out.println("1. YES");
        System.out.println("0. NO");
        int choice = getValidChoice(0,1);
        
        if (choice == 1){
            orderDAO.AddToCart(order);
            System.out.println("Place order successfully");
        } else {
            System.out.println("Continue shopping... ");
        }
    }
}
