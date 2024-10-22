package models;

public class StoreOwner {
    private String StoreOwnerID;
    private String email;
    private String username;
    private String password;

    public StoreOwner() {
    }

    public StoreOwner(String StoreOwnerID, String email, String username, String password) {
        this.StoreOwnerID = StoreOwnerID;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getStoreOwnerID() {
        return StoreOwnerID;
    }

    public void setStoreOwnerID(String storeOwnerID) {
        this.StoreOwnerID = storeOwnerID;
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
        return "StoreOwner{" +
                "StoreOwnerID=" + StoreOwnerID +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password= " + password + '\'' +
                '}';
    }

}