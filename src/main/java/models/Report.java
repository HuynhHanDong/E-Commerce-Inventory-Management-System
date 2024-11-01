package models;

import java.util.Date;

public class Report {
    private int reportID;
    private String reportType; // "SALES" or "INVENTORY"
    private Date generatedDate;
    private Date startDate;
    private Date endDate;
    private double totalAmount;
    private String status; // "Generated", "Viewed", etc.

    public Report() {
    }

    public Report(int reportID, String reportType, Date generatedDate,
            Date startDate, Date endDate, double totalAmount, String status) {
        this.reportID = reportID;
        this.reportType = reportType;
        this.generatedDate = generatedDate; // tự động tạo ngày báo cáo
        this.startDate = startDate; // ngày báo cáo đầu tiên
        this.endDate = endDate; // ngày báo cáo cuối cùng
        this.totalAmount = totalAmount; // tổng số tiền
        this.status = status; // trạng thái báo cáo
    }

    // Getters and Setters
    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportID=" + reportID +
                ", reportType='" + reportType + '\'' +
                ", generatedDate=" + generatedDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}