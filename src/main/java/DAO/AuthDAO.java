package DAO;

public interface AuthDAO {
    boolean authenticate(String id, String username, String email, String password);

    void logout(String id);
}