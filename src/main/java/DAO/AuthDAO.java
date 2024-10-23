package DAO;

public interface AuthDAO {
    boolean authenticate(int id, String username, String email, String password);

    void logout(int id);
}