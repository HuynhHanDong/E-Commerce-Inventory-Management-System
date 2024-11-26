package controller;

import java.sql.Date;
import java.util.InputMismatchException;

import DAO.InventoryDAO;
import DAO.OrderDAO;

public class ReportMenuController extends BaseController {
    private OrderDAO orderDAO;
    private InventoryDAO inventoryDAO;

    public ReportMenuController() {
        super();
        this.orderDAO = new OrderDAO();
        this.inventoryDAO = new InventoryDAO();
    }

    public void manageReports() {
        int choice;
        do {
            menu.reportMenu();
            choice = getValidChoice(0, 4);
            switch (choice) {
                case 1:
                    generateSalesReport();
                    break;
                case 2:
                    viewSalesReports();
                    break;
                case 3:
                    generateInventoryReport();
                    break;
                case 4:
                    viewInventoryReports();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
            }
        } while (choice != 0);
    }

    private void generateSalesReport() {
        try {
            System.out.println("Generate Sales Report");
            System.out.println("Enter start date (YYYY-MM-DD):");
            String startDateStr = scanner.nextLine();
            System.out.println("Enter end date (YYYY-MM-DD):");
            String endDateStr = scanner.nextLine();

            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);

            boolean success = orderDAO.generateSalesReport(startDate, endDate);
            if (success) {
                System.out.println("Sales report generated successfully.");
            } else {
                System.out.println("Failed to generate sales report.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void viewSalesReports() {
        try {
            System.out.println("View Sales Reports");
            orderDAO.viewSalesReports();
        } catch (Exception e) {
            System.out.println("Error viewing sales reports: " + e.getMessage());
        }
    }

    private void generateInventoryReport() {
        try {
            System.out.println("Generate Inventory Report");
            System.out.println("Select report type:");
            System.out.println("1. Low Stock Report");
            System.out.println("2. Total Inventory Value Report");
            int reportType = getValidChoice(1, 2);

            boolean success;
            switch (reportType) {
                case 1:
                    success = inventoryDAO.generateLowStockReport();
                    break;
                case 2:
                    success = inventoryDAO.generateInventoryValueReport();
                    break;
                default:
                    success = false;
            }

            if (success) {
                System.out.println("Inventory report generated successfully.");
            } else {
                System.out.println("Failed to generate inventory report.");
            }
        } catch (Exception e) {
            System.out.println("Error generating inventory report: " + e.getMessage());
        }
    }

    private void viewInventoryReports() {
        try {
            System.out.println("View Inventory Reports");
            inventoryDAO.viewInventoryReports();
        } catch (Exception e) {
            System.out.println("Error viewing inventory reports: " + e.getMessage());
        }
    }
}