package models;

import java.sql.Date;

public class Report {
    private String reportType; // "SALES" or "INVENTORY"
    private Date generatedDate;
    private Date startDate;
    private Date endDate;
    private int productID;
    private String productName;
    private int quantity;
    private int lowStockThreshold;
    private double revenue;
    private Date lastUpdate;

    public Report() {
    }

    public Report(String reportType, Date generatedDate, Date startDate, Date endDate, int productID, String productName, int quantity, double revenue) {
        this.reportType = reportType;
        this.generatedDate = generatedDate; // tự động tạo ngày báo cáo
        this.startDate = startDate; // ngày báo cáo đầu tiên
        this.endDate = endDate; // ngày báo cáo cuối cùng
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.revenue = revenue; // tổng số tiền
    }
    
    public Report(String reportType, Date generatedDate, Date startDate, Date endDate, int productID, String productName, int quantity, int lowStockThreshold, Date lastUpdate) {
        this.reportType = reportType;
        this.generatedDate = generatedDate; // tự động tạo ngày báo cáo
        this.startDate = startDate; // ngày báo cáo đầu tiên
        this.endDate = endDate; // ngày báo cáo cuối cùng
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.lowStockThreshold = lowStockThreshold;
        this.lastUpdate = lastUpdate; 
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
    
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public int getQuanity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public int setLowStockThreshold() {
        return lowStockThreshold;
    }
    
    public void getLowStockThreshold(int lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    
    public Date getLastUpdate() {
        return lastUpdate;
    }
    
    public void setLastUpdate(Date lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "reportType: " + reportType + ", generatedDate:" + generatedDate + ", startDate: " + startDate + 
               ", endDate: " + endDate;
    }
    
    public String printSalesReport() {
        return "product ID: " +  productID + ", product name:" + productName + ", quantity: " + quantity + ", revenue: " + revenue;
    }
    
    public String printInventoryReport() {
        return "product ID: " +  productID + ", product name:" + productName + ", stock level: " + quantity + 
                ", low stock threshold: " + lowStockThreshold + ", last update: " + lastUpdate;
    }
}