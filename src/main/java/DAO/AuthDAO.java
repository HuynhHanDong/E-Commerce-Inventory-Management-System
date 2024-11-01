package DAO;

public interface AuthDAO {
    boolean authenticate(String username, String password);

    void logout(int id);
}