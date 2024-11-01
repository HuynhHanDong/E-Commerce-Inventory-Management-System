package DAO;

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
    public boolean authenticate(String username,String password) {
        String sql = "SELECT Password FROM Customer WHERE Username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
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
