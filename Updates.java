package model;

public class Updates {
    private String username;
    private String trackingNumber;
    private String status;

    public String getUsername() {
        return username;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getStatus() {
        return status;
    }

    public Updates(String username, String trackingNumber, String status) {
        this.username = username;
        this.trackingNumber = trackingNumber;
        this.status = status;
    }
}
