package DAO;

import models.StoreOwner;
import utils.PasswordHasher;
import java.sql.*;

public class StoreOwnerDAO implements AuthDAO {

    private Connection connection;

    public StoreOwnerDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean authenticate(int id, String username, String email, String password) {
        String sql = "SELECT * FROM StoreOwner";
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
        System.out.println("Store Owner " + id + " logged out");
    }
}