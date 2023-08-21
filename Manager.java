package model;

public class Manager extends Customer{
    private Float salary;

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Manager(String username, String userPassword, Integer phone, String email, String firstName, String lastName, String address, int loyaltyMember, Float salary) {
        super(username, userPassword, phone, email, firstName, lastName, address, loyaltyMember);
        this.salary = salary;
    }

    public Float getSalary() {
        return salary;
    }
}
