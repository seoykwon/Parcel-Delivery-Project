package model;

public class Customer extends ShippingUser{
    private int loyaltyMember;

    public void setLoyaltyMember(int loyaltyMember) {
        this.loyaltyMember = 1;
    }

    public Customer(String username, String userPassword, Integer phone, String email, String firstName,
                    String lastName, String address, int loyaltyMember) {
        super(username, userPassword, phone, email, firstName, lastName, address);
        this.loyaltyMember = loyaltyMember;
    }

    public int isLoyaltyMember() {
        return loyaltyMember;
    }

}
