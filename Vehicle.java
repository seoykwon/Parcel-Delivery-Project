package model;

import java.sql.Date;

public class Vehicle {
    private String vin;
    private String insuranceNumber;
    private Date lastServicing;

    public String getVin() {
        return vin;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public Date getLastServicing() {
        return lastServicing;
    }

    public Vehicle(String vin, String insuranceNumber, Date lastServicing) {
        this.vin = vin;
        this.insuranceNumber = insuranceNumber;
        this.lastServicing = lastServicing;
    }
}
