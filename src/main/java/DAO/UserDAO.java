package DAO;

import utils.JDBCConnection;
import utils.PasswordHasher;
import java.sql.*;

import models.User;

public class UserDAO implements AuthDAO {
    private Connection connection;

    public UserDAO() {
        try {
            this.connection = JDBCConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User authenticate(String username, String password) {
        String sql = "SELECT * FROM Users WHERE Username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && PasswordHasher.verifyPassword(password, rs.getString("Password"))) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void logout(int id) {
        System.out.println("User " + id + " logged out");
    }
}