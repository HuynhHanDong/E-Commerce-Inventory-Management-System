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
    public boolean authenticate(int id, String username, String email, String password) {
        String sql = "SELECT * FROM Customer";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, email);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
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