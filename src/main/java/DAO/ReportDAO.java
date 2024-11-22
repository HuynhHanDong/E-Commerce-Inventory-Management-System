package DAO;

import models.Report;
import utils.JDBCConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ReportDAO {
    private static final String GENERATE_SALES_REPORT = "SELECT COUNT(o.OrderID) as TotalOrders, SUM(od.Quantity * p.Price) as TotalRevenue " +
                                                        "FROM Orders o JOIN OrderDetails od ON o.OrderID = od.OrderID JOIN Products p " + 
                                                        "ON od.ProductID = p.ProductID WHERE o.OrderDate BETWEEN ? AND ?";
    private static final String VIEW_SALES_REPORT = "SELECT * WHERE ReportType = 'SALES' ORDER BY GeneratedDate DESC";
    private static final String GENERATE_INVENTORY_REPORT = "SELECT COUNT(ProductID) as TotalProducts, SUM(StockLevel * Price) as TotalValue, " +
                                                        "SUM(CASE WHEN StockLevel <= ReorderLevel THEN 1 ELSE 0 END) as LowStockItems FROM Products";
    private static final String VIEW_INVENTORY_REPORT = "SELECT * WHERE ReportType = 'INVENTORY' ORDER BY GeneratedDate DESC";
    private static final String SAVE_REPORT = "INSERT INTO Reports (ReportType, GeneratedDate, StartDate, EndDate, TotalAmount, Status) VALUES (?, ?, ?, ?, ?, ?)";

    // Generate Sales Report
    public Report generateSalesReport(Date startDate, Date endDate) {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GENERATE_SALES_REPORT)) {
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Report report = new Report();
                report.setReportType("SALES");
                report.setGeneratedDate(new Date());
                report.setStartDate(startDate);
                report.setEndDate(endDate);
                report.setTotalAmount(rs.getDouble("TotalRevenue"));
                report.setStatus("Generated");

                saveReport(report);
                return report;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // View Sales Reports
    public List<Report> viewSalesReports() {
        List<Report> reports = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(VIEW_SALES_REPORT)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getString("ReportType"),
                        rs.getTimestamp("GeneratedDate"),
                        rs.getTimestamp("StartDate"),
                        rs.getTimestamp("EndDate"),
                        rs.getDouble("TotalAmount"),
                        rs.getString("Status"));
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    // Generate Inventory Report
    public Report generateInventoryReport() {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GENERATE_INVENTORY_REPORT)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Report report = new Report();
                report.setReportType("INVENTORY");
                report.setGeneratedDate(new Date());
                report.setTotalAmount(rs.getDouble("TotalValue"));
                report.setStatus("Generated");

                saveReport(report);
                return report;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // View Inventory Reports
    public List<Report> viewInventoryReports() {
        List<Report> reports = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(VIEW_INVENTORY_REPORT)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getInt("ReportID"),
                        rs.getString("ReportType"),
                        rs.getTimestamp("GeneratedDate"),
                        rs.getTimestamp("StartDate"),
                        rs.getTimestamp("EndDate"),
                        rs.getDouble("TotalAmount"),
                        rs.getString("Status"));
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    // Save Report
    private void saveReport(Report report) {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(SAVE_REPORT)) {
            statement.setString(1, report.getReportType());
            statement.setTimestamp(2, new java.sql.Timestamp(report.getGeneratedDate().getTime()));
            statement.setTimestamp(3, report.getStartDate() != null ? new java.sql.Timestamp(report.getStartDate().getTime()) : null);
            statement.setTimestamp(4, report.getEndDate() != null ? new java.sql.Timestamp(report.getEndDate().getTime()) : null);
            statement.setDouble(5, report.getTotalAmount());
            statement.setString(6, report.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}