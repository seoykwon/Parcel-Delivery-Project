package model;

public class Dispatches {
    private String username;
    private String trackingNumber;
    private String bid;

    public String getUsername() {
        return username;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getBid() {
        return bid;
    }

    public Dispatches(String username, String trackingNumber, String bid) {
        this.username = username;
        this.trackingNumber = trackingNumber;
        this.bid = bid;
    }
}
