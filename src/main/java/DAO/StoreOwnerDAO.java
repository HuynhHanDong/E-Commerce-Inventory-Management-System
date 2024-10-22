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
    public boolean authenticate(String id, String username, String email, String password) {
        String sql = "SELECT * FROM store_owners WHERE StoreOwnerID = ? AND email = ?";
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
        System.out.println("Store Owner " + id + " logged out");
    }
}
