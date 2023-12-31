package model;

public abstract class ShippingUser {
    private String username;
    private String userPassword;

    private Integer phone;
    private String email;
    private String firstName;
    private String lastName;
    private String address;

    public String getUsername() {
        return username;
    }

    public Integer getPhone() {
        return phone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(Integer phone) { this.phone = phone; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ShippingUser(String username, String userPassword, Integer phone, String email, String firstName, String lastName, String address) {
        this.username = username;
        this.userPassword = userPassword;
        this.phone = phone;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
