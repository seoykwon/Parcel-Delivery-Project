package model;

public class Driver extends Customer {
    private String licenseNumber;
    private Float salary;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Float getSalary() {
        return salary;
    }

    public Integer getOrdersDelivered() {
        return ordersDelivered;
    }

    private Integer ordersDelivered;

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public void setOrdersDelivered(Integer ordersDelivered) {
        this.ordersDelivered = ordersDelivered;
    }

    public Driver(String username, String userPassword, Integer phone, String email, String firstName, String lastName, String address, int loyaltyMember, String licenseNumber, Float salary, Integer ordersDelivered) {
        super(username, userPassword, phone, email, firstName, lastName, address, loyaltyMember);
        this.licenseNumber = licenseNumber;
        this.salary = salary;
        this.ordersDelivered = ordersDelivered;
    }
}
