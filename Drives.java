package model;

public class Drives {
    private String vin;
    private String username;

    public String getVin() {
        return vin;
    }

    public String getUsername() {
        return username;
    }

    public Drives(String vin, String username) {
        this.vin = vin;
        this.username = username;
    }
}
