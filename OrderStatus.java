package model;

import java.sql.Date;

public class OrderStatus {
    private String status;
    private Date timeUpdated;
    private String trackingNumber;

    public String getStatus() {
        return status;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public OrderStatus(String status, Date timeUpdated, String trackingNumber) {
        this.status = status;
        this.timeUpdated = timeUpdated;
        this.trackingNumber = trackingNumber;
    }
}
