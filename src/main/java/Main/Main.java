package Main;

import controller.LoginMenuController;

public class Main {
    public static void main(String[] args) {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|        Welcome to E-Commerce-Management-System          |");
        System.out.println("+---------------------------------------------------------+");

        // Create login menu controller and start the application
        LoginMenuController loginMenu = new LoginMenuController();
        loginMenu.displayLoginMenu();
    }
}
