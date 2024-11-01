package DAO;

import models.Report;
import utils.JDBCConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ReportDAO {
    private Connection connection;

    public ReportDAO() {
        this.connection = JDBCConnection.getConnection();
    }

    // Generate Sales Report
    public Report generateSalesReport(Date startDate, Date endDate) {
        String sql = "SELECT " +
                "COUNT(o.OrderID) as TotalOrders, " +
                "SUM(od.Quantity * p.Price) as TotalRevenue " +
                "FROM Orders o " +
                "JOIN OrderDetails od ON o.OrderID = od.OrderID " +
                "JOIN Products p ON od.ProductID = p.ProductID " +
                "WHERE o.OrderDate BETWEEN ? AND ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
            pstmt.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = pstmt.executeQuery();
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
        String sql = "SELECT ReportID, ReportType, GeneratedDate, StartDate, " +
                "EndDate, TotalAmount, Status " +
                "FROM Reports " +
                "WHERE ReportType = 'SALES' " +
                "ORDER BY GeneratedDate DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
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
        String sql = "SELECT " +
                "COUNT(ProductID) as TotalProducts, " +
                "SUM(StockLevel * Price) as TotalValue, " +
                "SUM(CASE WHEN StockLevel <= ReorderLevel THEN 1 ELSE 0 END) as LowStockItems " +
                "FROM Products";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
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
        String sql = "SELECT ReportID, ReportType, GeneratedDate, StartDate, " +
                "EndDate, TotalAmount, Status " +
                "FROM Reports " +
                "WHERE ReportType = 'INVENTORY' " +
                "ORDER BY GeneratedDate DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
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
        String sql = "INSERT INTO Reports (ReportType, GeneratedDate, StartDate, " +
                "EndDate, TotalAmount, Status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, report.getReportType());
            pstmt.setTimestamp(2, new java.sql.Timestamp(report.getGeneratedDate().getTime()));
            pstmt.setTimestamp(3,
                    report.getStartDate() != null ? new java.sql.Timestamp(report.getStartDate().getTime()) : null);
            pstmt.setTimestamp(4,
                    report.getEndDate() != null ? new java.sql.Timestamp(report.getEndDate().getTime()) : null);
            pstmt.setDouble(5, report.getTotalAmount());
            pstmt.setString(6, report.getStatus());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}