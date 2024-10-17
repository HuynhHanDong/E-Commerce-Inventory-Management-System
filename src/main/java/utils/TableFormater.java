package utils;

public class TableFormater {
    public static String getProductTableHeader() {
        return String.format("| %-5s | %-20s | %-10s | %-10s | %-30s | %-10s |", "ID", "Name", "CategoryID", "Price", "StockLevel",
                "Description");
    }

    public static String getProductTableDivider() {
        return "+-------+----------------------+------------+------------+--------------------------------+-------------+";
    }
}