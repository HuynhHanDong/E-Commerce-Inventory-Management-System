package DAO;

import models.Customer;
import utils.PasswordHasher;
import utils.JDBCConnection;
import java.sql.*;

public class CustomerDAO implements AuthDAO {

    private Connection connection;

    public CustomerDAO() {
        try {
            this.connection = JDBCConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean authenticate(int id, String username, String email, String password) {
        String sql = "SELECT Password FROM Customer WHERE CustomerID = ? AND Email = ? AND Username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, email);
            pstmt.setString(3, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedUsername = rs.getString("username");
                String storedPassword = rs.getString("password");
                return storedUsername.equals(username) && PasswordHasher.verifyPassword(password, storedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void logout(int id) {
        System.out.println("Customer " + id + " logged out");
    }
}
