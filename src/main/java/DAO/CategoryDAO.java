package DAO;

import models.Category;
import utils.JDBCConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO {
    private static final String ADD_CATEGORY = "INSERT INTO Category (CategoryName) VALUES (?);";
    private static final String UPDATE_CATEGORY = "UPDATE Category SET CategoryName = ? WHERE CategoryID = ?;";
    private static final String DELETE_CATEGORY = "DELETE FROM Category WHERE CategoryID = ?;";
    private static final String GET_CATEGORY_ID = "SELECT MAX(CategoryID) AS CategoryID FROM Category;";
    private static final String GET_CATEGORY_WITH_CONDITION = "SELECT * FROM Category WHERE ";
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM Category;";

    public int addCategory(Category category) {
        int result = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(ADD_CATEGORY)) {
            statement.setString(1, category.getCategoryName());
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
    
    public int getCategoryID() {
        int categoryID = 0;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_CATEGORY_ID)) {
                ResultSet result = statement.executeQuery();
                if (result != null && result.next()) {
                    categoryID = result.getInt("CategoryID");
                }
            
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return categoryID;
    }

    public Category getCategory(String condition) {
        Category category = null;
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_CATEGORY_WITH_CONDITION + condition)) {
            ResultSet result = statement.executeQuery();

            if (result != null) {
                while (result.next()) {
                    int categoryID = result.getInt("categoryID");
                    String categoryName = result.getString("categoryName");
                    category = new Category(categoryID, categoryName);
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return category;
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(GET_ALL_CATEGORIES)) {
            ResultSet result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    int categoryID = result.getInt("categoryID");
                    String categoryName = result.getString("categoryName");
                    categories.add(new Category(categoryID, categoryName));
                }
            }
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return categories;
    }
}
