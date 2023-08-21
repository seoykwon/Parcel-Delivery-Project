package model;

public class StoredAt {
    private String bid;
    private String vin;

    public String getBid() {
        return bid;
    }

    public String getVin() {
        return vin;
    }

    public StoredAt(String bid, String vin) {
        this.bid = bid;
        this.vin = vin;
    }
}
