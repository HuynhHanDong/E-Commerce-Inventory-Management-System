package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {
    private final String hostname = "DESKTOP-AJ4PPFA\\SQLEXPRESS01";
    private final String databaseName = "ECommerceDB";
    private final String port = "1433";
    private final String username = "sa";
    private final String password = "123123123";
    
    private Connection con;

    public void connect() {
        String url = "jdbc:sqlserver://" + hostname + ":" + port + ";databaseName=" + databaseName + ";trustServerCertificate=true";
        try {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to sql");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getConnect(){
        return con;
    }
}