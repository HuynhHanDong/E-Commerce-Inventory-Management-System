package controller;

import DAO.InventoryDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import java.sql.Date;
import java.util.ArrayList;
import models.Inventory;
import models.Order;
import models.OrderItems;
import models.Product;

public class OrderMenuController extends BaseController {
    private final int userID;
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private Order order;
    private final ArrayList<OrderItems> cart;

    public OrderMenuController(int userID) {
        super();
        this.userID = userID;
        this.orderDAO = new OrderDAO();
        this.productDAO = new ProductDAO();
        this.order = null;
        this.cart = new ArrayList<>();
    }

    public void displayOrderMenu() {
        int choice;
        do {
            menu.orderMenu();
            choice = getValidChoice(0, 5);

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
                    viewCart();
                    break;
                case 5:
                    confirmOrder();
                    break;
                case 0:
                    checkConfirm();
                    break;
            }
        } while (choice != 0);
    }

    private void addToCart() {
        try {
            if (order == null) {
                order = new Order();
            }
            System.out.println("Enter product ID: ");
            int productID = scanner.nextInt();
            if (!UserValidation.isValidId(productID)) {
                return;
            }

            Product product = productDAO.getProductByID(productID);
            if (product != null) {
                System.out.println("Enter quantity: ");
                int quantity = scanner.nextInt();
                if (!UserValidation.isValidQuantity(quantity)) {
                    return;
                }

                InventoryDAO inventoryDAO = new InventoryDAO();
                Inventory inventory = inventoryDAO.getCurrentStockLevelById(productID);

                if (quantity <= inventory.getStockLevel()) {
                    // initialize orderID and orderItemID as zero
                    cart.add(new OrderItems(cart.size(), 0, product.getProductID(), product.getPrice(), quantity));
                    order.setItems(cart);
                    order.setTotalPrice();
                    System.out.println("Added to cart.");
                } else {
                    System.out.println("Not enough stock level.");
                }
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void changeQuantity() {
        if (cart.isEmpty()) {
            System.out.println("Nothing in cart");
        } else {
            try {
                // Show cart before changing quantity
                viewCart();
                boolean changed = false;
                System.out.println("Enter product id to change quantity: ");
                int productID = scanner.nextInt();
                if (!UserValidation.isValidId(productID)) {
                    return;
                }

                for (OrderItems item : cart) {
                    if (item.getProductID() == productID) {
                        System.out.println("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        if (!UserValidation.isValidQuantity(quantity)) {
                            return;
                        }

                        InventoryDAO inventoryDAO = new InventoryDAO();
                        Inventory inventory = inventoryDAO.getCurrentStockLevelById(productID);

                        if (quantity <= inventory.getStockLevel()) {
                            item.setQuanity(quantity);
                            order.setItems(cart);
                            order.setTotalPrice();
                            System.out.println("Updated successfully: " + item.toString());
                            changed = true;
                            break;
                        } else {
                            System.out.println("Not enough stock level.");
                        }
                    }
                }
                if (!changed) {
                    System.out.println("This product is not in the cart.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private void removeFromCart() {
        if (cart.isEmpty()) {
            System.out.println("Nothing in cart");
        } else {
            try {
                // Show cart before removing
                viewCart();
                boolean removed = false;
                System.out.print("Enter product id to delete: ");
                int productID = scanner.nextInt();
                if (!UserValidation.isValidId(productID)) {
                    return;
                }

                for (OrderItems item : cart) {
                    if (item.getProductID() == productID) {
                        cart.remove(item);
                        order.setItems(cart);
                        order.setTotalPrice();
                        System.out.println("Removed successfully");
                        removed = true;
                        break;
                    }
                }
                if (!removed) {
                    System.out.println("This product is not in the cart.");
                }
            } catch (Exception e) {
                System.out.println("ProductID must be a positive integer");
            }
        }
    }

    private void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Nothing in cart.");
        } else {
            for (OrderItems item : cart) {
                System.out.println(item.toString());
            }
        }
    }

    private void confirmOrder() {
        if (cart.isEmpty()) {
            System.out.println("Nothing in cart.");
        } else {
            System.out.println("+------------ Place Order -------------+");
            viewCart();
            System.out.println("Total price: " + order.getTotalPrice());
            System.out.println("+--------------------------------------+");
            System.out.println("| Confirm Order?                       |");
            System.out.println("| 1. YES                               |");
            System.out.println("| 0. NO                                |");
            System.out.println("+--------------------------------------+");
            int choice = getValidChoice(0, 1);
            if (choice == 1) {
                addOrder();
                order = null;
                cart.clear();
            } else {
                System.out.println("Continue shopping... ");
            }
        }
    }

    private void addOrder() {
        try {
            Date orderDate = new Date(System.currentTimeMillis());
            int result = orderDAO.addOrder(userID, orderDate, order.getTotalPrice(), "Pending");
            if (result > 0) {
                int orderID = orderDAO.getOrderID();
                for (OrderItems item : cart) {
                    item.setOrderID(orderID);
                }
                result = orderDAO.addItems(cart);
                if (result > 0) {
                    System.out.println("Placed order successfully. OrderID: " + orderID);
                } else {
                    System.out.println("Failed to add orderItems.");
                }
            } else {
                System.out.println("Failed to add order.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void checkConfirm() {
        if (cart.isEmpty()) {
            System.out.println("Going back to Customer Menu...");
        } else {
            System.out.println("Your order hasn't confirmed yet. Saved as draft.");
            System.out.println("Draft order will be erased when log out.");
        }
    }
}