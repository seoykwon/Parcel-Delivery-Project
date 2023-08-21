package model;

public class Transaction {
    private String tid;
    private Float amount;


    public Transaction(String tid, Float amount) {
        this.tid = tid;
        this.amount = amount;
    }

    public String getTid() {
        return tid;
    }

    public Float getAmount() {
        return amount;
    }
}
