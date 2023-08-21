package model;

public class Parcel {
    private String pid;
    private Integer parcelSize;
    private String trackingNumber;
    private Float weight;
    private String deliveryType;

    public String getPid() {
        return pid;
    }

    public Integer getParcelSize() {
        return parcelSize;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public Float getWeight() {
        return weight;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public Parcel(String pid, Integer parcelSize, String trackingNumber, Float weight, String deliveryType) {
        this.pid = pid;
        this.parcelSize = parcelSize;
        this.trackingNumber = trackingNumber;
        this.weight = weight;
        this.deliveryType = deliveryType;
    }
}
