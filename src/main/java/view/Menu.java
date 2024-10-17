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
    public void loginMenu(){
        System.out.println("------------------------------");
        System.out.println("Welcome");
        System.out.println("1. Login");
        System.out.println("2. Exit");
    }
    
    public void customerMenu(){
        System.out.println("\n----------- Customer Menu -----------");
        System.out.println("1. View product details");
        System.out.println("2. Search product");
        System.out.println("3. Place order");
        System.out.println("4. View order history");
        System.out.println("0. Logout");
    }
    
    public void ownerMenu(){
        System.out.println("\n----------- Owner Menu -----------");
        System.out.println("1. Manage products");
        System.out.println("2. Manage categories");
        System.out.println("3. Manage inventory");
        System.out.println("4. View reports");
        System.out.println("0. Logout");
    }
    
    public void productMenu(){
        System.out.println("\n----------- Product Menu -----------");
        System.out.println("1. Add product");
        System.out.println("2. Update product details");
        System.out.println("3. Delete product");
        System.out.println("4. Search product");
        System.out.println("5. View all products");
        System.out.println("0. Go back");
    }
    
    public void categorytMenu(){
        System.out.println("\n----------- Category Menu -----------");
        System.out.println("1. Add category");
        System.out.println("2. Update category");
        System.out.println("3. Delete category");
        System.out.println("4. Search category");
        System.out.println("5. View all categories");
        System.out.println("0. Go back");
    }
    
    public void inventorytMenu(){
        System.out.println("\n----------- Inventory Menu -----------");
        System.out.println("1. View stock levels by product");
        System.out.println("2. View stock levels of all products");
        System.out.println("0. Go back");
    }
    
    public void reporttMenu(){
        System.out.println("\n----------- Report Menu -----------");
        System.out.println("1. Generate sales report");
        System.out.println("2. View sales reports");
        System.out.println("3. Generate invetory report");
        System.out.println("4. View invetory reports");
        System.out.println("0. Go back");
    }
    
    public void ordertMenu(){
        System.out.println("\n----------- Order Menu -----------");
        System.out.println("1. Add to cart");
        System.out.println("2. Change product amount");
        System.out.println("3. Remove product");
        System.out.println("4. Confirm order");
        System.out.println("0. Go back");
    }
}
