package controller;

import java.sql.Date;
import java.util.InputMismatchException;

import DAO.ReportDAO;
import java.util.ArrayList;
import models.Report;

public class ReportMenuController extends BaseController {
    private final ReportDAO reportDAO;

    public ReportMenuController() {
        super();
        this.reportDAO = new ReportDAO();
    }

    public void manageReports() {
        int choice;
        do {
            menu.reportMenu();
            choice = getValidChoice(0, 2);
            switch (choice) {
                case 1:
                    generateSalesReport();
                    break;
                case 2:
                    generateInventoryReport();
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

            ArrayList<Report> salesReport = reportDAO.generateSalesReport(startDate, endDate);
            if (salesReport != null) {
                for (Report report : salesReport) {
                    System.out.println(report.printSalesReport());
                }
            } else {
                System.out.println("Nothing to report.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void generateInventoryReport() {
        try {
            System.out.println("Generate Inventory Report");
            System.out.println("Enter start date (YYYY-MM-DD):");
            String startDateStr = scanner.nextLine();
            System.out.println("Enter end date (YYYY-MM-DD):");
            String endDateStr = scanner.nextLine();
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);

            ArrayList<Report> inventoryReport = reportDAO.generateSalesReport(startDate, endDate);
            if (inventoryReport != null) {
                for (Report report : inventoryReport) {
                    System.out.println(report.printInventoryReport());
                }
            } else {
                System.out.println("Nothing to report.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }
    
    private void saveToFile() {
        
    }
}