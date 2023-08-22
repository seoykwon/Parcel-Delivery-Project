package model;

public class DeliveryType {
    private String delivery_type;
    private Float rate;

    public String getDelivery_type() {
        return delivery_type;
    }

    public Float getRate() {
        return rate;
    }

    public DeliveryType(String delivery_type, Float rate) {
        this.delivery_type = delivery_type;
        this.rate = rate;
    }
}
