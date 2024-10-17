package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {

    private static final String hostname = "localhost";
    private static final String port = "1433";
    private static final String databaseName = "ECommerceDB";
    private static final String username = "sa";
    private static final String password = "123123123";

    private static final String url = "jdbc:sqlserver://" + hostname + ":" + port + ";databaseName=" + databaseName;

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}