package model;

import java.sql.Date;

public class PaysFor {
    private String tid;
    private Date datePaid;
    private String trackingNumber;

    public String getTid() {
        return tid;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public PaysFor(String tid, Date datePaid, String trackingNumber) {
        this.tid = tid;
        this.datePaid = datePaid;
        this.trackingNumber = trackingNumber;
    }
}
