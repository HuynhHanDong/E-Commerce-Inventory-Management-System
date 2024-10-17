package models;

public class Customer {
    private String id;
    private String name;
    private String email;
    private String password;

    public Customer(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("Login Success!!!");
            return true;
        } else {
            System.out.println("Login Fail!!!");
            return false;
        }
    }
    
    public void logout() {
        System.out.println("Logout Success!!!");
    }
}