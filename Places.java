package model;

public class Places {
    private String username;
    private String trackingNumber;

    public String getUsername() {
        return username;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public Places(String username, String trackingNumber) {
        this.username = username;
        this.trackingNumber = trackingNumber;
    }
}
