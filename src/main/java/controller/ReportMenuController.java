package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;

import DAO.ReportDAO;
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
            if (!UserValidation.isValidDate(startDateStr)) {
                return;
            }
            System.out.println("Enter end date (YYYY-MM-DD):");
            String endDateStr = scanner.nextLine();
            if (!UserValidation.isValidDate(endDateStr)) {
                return;
            }
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);

            ArrayList<Report> salesReport = reportDAO.generateSalesReport(startDate, endDate);
            if (!salesReport.isEmpty()) {
                for (Report report : salesReport) {
                    System.out.println(report.printSalesReport());
                }
                saveToFile(salesReport, "Sales");
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
            if (!UserValidation.isValidDate(startDateStr)) {
                return;
            }
            System.out.println("Enter end date (YYYY-MM-DD):");
            String endDateStr = scanner.nextLine();
            if (!UserValidation.isValidDate(endDateStr)) {
                return;
            }
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);

            ArrayList<Report> inventoryReport = reportDAO.generateInventoryReport(startDate, endDate);
            if (!inventoryReport.isEmpty()) {
                for (Report report : inventoryReport) {
                    System.out.println(report.printInventoryReport());
                }
                saveToFile(inventoryReport, "Inventory");
            } else {
                System.out.println("Nothing to report.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void saveToFile(ArrayList<Report> reports, String reportType) {
        String fileName = reportType + "_report_" + System.currentTimeMillis() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report-text-file/" + fileName))) {
            writer.write(reportType + " Report\n");
            writer.write("Generated on: " + new Date(System.currentTimeMillis()) + "\n\n");

            for (Report report : reports) {
                if ("Sales".equals(reportType)) {
                    writer.write(report.printSalesReport() + "\n");
                } else if ("Inventory".equals(reportType)) {
                    writer.write(report.printInventoryReport() + "\n");
                }
            }

            System.out.println("Report saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurr while saving the report: " + e.getMessage());
        }
    }

}
