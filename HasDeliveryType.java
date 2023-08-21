package model;

public class HasDeliveryType {
    private String deliveryType;
    private String trackingNumber;

    public String getDeliveryType() {
        return deliveryType;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public HasDeliveryType(String deliveryType, String trackingNumber) {
        this.deliveryType = deliveryType;
        this.trackingNumber = trackingNumber;
    }
}
