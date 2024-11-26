package DAO;

import utils.JDBCConnection;
import utils.PasswordHasher;
import java.sql.*;

import models.User;

public class UserDAO implements AuthDAO {
    private Connection connection;
    private String GET_USER = "SELECT * FROM Users WHERE Username = ?";

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
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(GET_USER)) {
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if (result.next() && PasswordHasher.verifyPassword(password, result.getString("Password"))) {
                int userID = result.getInt("UserID");
                String email = result.getString("Email");
                String role = result.getString("Role");
                user = new User(userID, username, email, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void logout(int id) {
        System.out.println("User " + id + " logged out");
    }
}