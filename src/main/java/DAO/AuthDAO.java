package DAO;

import models.User;

public interface AuthDAO {
    User authenticate(String username, String password);

    void logout(int id);
}