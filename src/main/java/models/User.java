package models;

public interface User {
    void login(String username, String password);

    void logout();
}