package DAO;

import models.Customer;
import utils.PasswordHasher;
import java.sql.*;

public class CustomerDAO implements AuthDAO {

    private Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean authenticate(String id, String username, String email, String password) {
        String sql = "SELECT * FROM customers WHERE CustomerID = ? AND email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, email);
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
    public void logout(String id) {
        System.out.println("Customer " + id + " logged out");
    }
}
