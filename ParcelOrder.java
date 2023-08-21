package model;

public class ParcelOrder {
    private String trackingNumber;
    private String destination;


    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getDestination() {
        return destination;
    }

    public ParcelOrder(String trackingNumber, String destination) {
        this.trackingNumber = trackingNumber;
        this.destination = destination;
    }
}
