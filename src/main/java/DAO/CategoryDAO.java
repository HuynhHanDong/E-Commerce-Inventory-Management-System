package DAO;

import models.Category;
import utils.JDBCConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO {
    private static final String ADD_CATEGORY = "INSERT INTO category (categoryId, categoryName) VALUES (?, ?);";
    private static final String UPDATE_CATEGORY = "UPDATE category SET categoryName = ? WHERE categoryId = ?;";
    private static final String DELETE_CATEGORY = "DELETE FROM category WHERE categoryId = ?;";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE categoryId = ?;";
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM category;";

    public int addCategory(Category category) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_CATEGORY)) {
            statement.setString(1, category.getCategoryId());
            statement.setString(2, category.getCategoryName());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int updateCategory(Category category) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(UPDATE_CATEGORY)) {
            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getCategoryId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int deleteCategory(String categoryId) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_CATEGORY)) {
            statement.setString(1, categoryId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public Category getCategoryById(String categoryId) {
        Category category = null;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_CATEGORY_BY_ID)) {
            statement.setString(1, categoryId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                category = new Category(rs.getString("categoryId"), rs.getString("categoryName"));
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return category;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ALL_CATEGORIES)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                categories.add(new Category(rs.getString("categoryId"), rs.getString("categoryName")));
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return categories;
    }
}
