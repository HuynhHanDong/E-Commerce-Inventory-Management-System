package DAO;

import models.Category;
import utils.JDBCConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO {
    private static final String ADD_CATEGORY = "INSERT INTO Category (CategoryID, CategoryName) VALUES (?, ?);";
    private static final String UPDATE_CATEGORY = "UPDATE Category SET CategoryName = ? WHERE CategoryID = ?;";
    private static final String DELETE_CATEGORY = "DELETE FROM Category WHERE CategoryID = ?;";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM Category WHERE CategoryID = ?;";
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM Category;";

    public int addCategory(Category category) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_CATEGORY)) {
            statement.setInt(1, category.getCategoryID());
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
            statement.setInt(2, category.getCategoryID());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public int deleteCategory(int categoryID) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(DELETE_CATEGORY)) {
            statement.setInt(1, categoryID);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public void getCategoryById(int categoryID) {
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_CATEGORY_BY_ID)) {
            statement.setInt(1, categoryID);
            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    categoryID = result.getInt("categoryID");
                    String categoryName = result.getString("categoryName");
                    System.out.println("ID: " + categoryID + "; categoryName: " + categoryName);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ALL_CATEGORIES)) {
            ResultSet result = statement.executeQuery();
            while (result != null) {
                if (result.next()) {
                    int categoryID = result.getInt("categoryID");
                    String categoryName = result.getString("categoryName");
                    System.out.println("ID: " + categoryID + "; categoryName: " + categoryName);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return categories;
    }
}
