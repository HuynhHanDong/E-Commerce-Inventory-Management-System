/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Huynh Han Dong
 */
public class Menu {
    public void loginMenu() {
        System.out.println("+--------------- Login ----------------+");
        System.out.println("| Welcome! you are?                    |");
        System.out.println("| 1. Store owner                       |");
        System.out.println("| 2. Customer                          |");
        System.out.println("| 0. Exit                              |");
        System.out.println("+--------------------------------------+");
    }

    public void customerMenu() {
        System.out.println("+----------- Customer Menu ------------+");
        System.out.println("| 1. View all Products                 |");
        System.out.println("| 2. Search product                    |");
        System.out.println("| 3. Place order                       |");
        System.out.println("| 4. View order history                |");
        System.out.println("| 5. View order details                |");
        System.out.println("| 0. Logout                            |");
        System.out.println("+--------------------------------------+");
    }

    public void ownerMenu() {
        System.out.println("+------------- Owner Menu -------------+");
        System.out.println("| 1. Manage products                   |");
        System.out.println("| 2. Manage categories                 |");
        System.out.println("| 3. Manage inventory                  |");
        System.out.println("| 4. View reports                      |");
        System.out.println("| 0. Logout                            |");
        System.out.println("+--------------------------------------+");
    }

    public void productMenu() {
        System.out.println("+------------ Product Menu ------------+");
        System.out.println("| 1. Add product                       |");
        System.out.println("| 2. Update product details            |");
        System.out.println("| 3. Delete product                    |");
        System.out.println("| 4. Search product                    |");
        System.out.println("| 5. View all products                 |");
        System.out.println("| 0. Go back                           |");
        System.out.println("+--------------------------------------+");
    }

    public void categoryMenu() {
        System.out.println("+ ----------- Category Menu -----------+");
        System.out.println("| 1. Add category                      |");
        System.out.println("| 2. Update category                   |");
        System.out.println("| 3. Delete category                   |");
        System.out.println("| 4. Search category                   |");
        System.out.println("| 5. View all categories               |");
        System.out.println("| 0. Go back                           |");
        System.out.println("+--------------------------------------+");
    }

    public void inventoryMenu() {
        System.out.println("+----------- Inventory Menu -----------+");
        System.out.println("| 1. Add inventory item                |");
        System.out.println("| 2. Delete inventory item             |");
        System.out.println("| 3. View stock levels by product ID   |");
        System.out.println("| 4. View stock levels of all products |");
        System.out.println("| 0. Go back                           |");
        System.out.println("+--------------------------------------+");
    }

    public void reportMenu() {
        System.out.println("+------------ Report Menu -------------+");
        System.out.println("| 1. Generate sales report             |");
        System.out.println("| 2. Generate inventory report         |");
        System.out.println("| 0. Go back                           |");
        System.out.println("+--------------------------------------+");
    }

    public void orderMenu() {
        System.out.println("+------------- Order Menu -------------+");
        System.out.println("| 1. Add to cart                       |");
        System.out.println("| 2. Change item amount                |");
        System.out.println("| 3. Remove item from cart             |");
        System.out.println("| 4. View order details                |");
        System.out.println("| 5. Confirm order                     |");
        System.out.println("| 0. Go back                           |");
        System.out.println("+--------------------------------------+");
    }

    public void confirmOrderMenu() {
        System.out.println("+----------- Confirm Order ------------+");
        System.out.println("| 1. YES                               |");
        System.out.println("| 0. NO                                |");
        System.out.println("+--------------------------------------+");
    }

    public void orderHistoryMenu() {
        System.out.println("+----------- Order History ------------+");
        System.out.println("| 1. View all                          |");
        System.out.println("| 2. View status                       |");
        System.out.println("| 3. Cancel order                      |");
        System.out.println("| 0. Go back                           |");
        System.out.println("+--------------------------------------+");
    }

    public void orderHistorySubMenu() {
        System.out.println("+----------- Order History ------------+");
        System.out.println("| 1. Pending                           |");
        System.out.println("| 2. In progress                       |");
        System.out.println("| 3. In transit                        |");
        System.out.println("| 4. Finished                          |");
        System.out.println("| 5. Canceled                          |");
        System.out.println("| 0. Go back                           |");
        System.out.println("+--------------------------------------+");
    }
}
