package models;

public class StoreOwner {
    private int storeownerID;
    private String email;
    private String username;
    private String password;

    public StoreOwner() {
    }

    public StoreOwner(int storeownerID, String email, String username, String password) {
        this.storeownerID = storeownerID;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getStoreownerID() {
        return storeownerID;
    }

    public void setStoreownerID(int storeownerID) {
        this.storeownerID = storeownerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "storeownerID: " + storeownerID + ", email: " + email + ", username: " + username + ", password: " + password;
    }
}