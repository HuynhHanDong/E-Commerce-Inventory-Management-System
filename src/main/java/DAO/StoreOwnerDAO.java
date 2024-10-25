package DAO;

import models.StoreOwner;
import utils.PasswordHasher;
import utils.JDBCConnection;
import java.sql.*;

public class StoreOwnerDAO implements AuthDAO {

    private Connection connection;

    public StoreOwnerDAO() {
        try {
            this.connection = JDBCConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StoreOwnerDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean authenticate(int id, String username, String email, String password) {
        String sql = "SELECT Password FROM StoreOwner WHERE StoreOwnerID = ? AND Email = ? AND Username = ?";
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
        System.out.println("Store Owner " + id + " logged out");
    }
}