package model;


import java.sql.Date;

public class Insurance {
    private String insuranceNumber;
    private Date insuranceExpiry;

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public Date getInsuranceExpiry() {
        return insuranceExpiry;
    }

    public Insurance(String insuranceNumber, Date insuranceExpiry) {
        this.insuranceNumber = insuranceNumber;
        this.insuranceExpiry = insuranceExpiry;
    }
}
