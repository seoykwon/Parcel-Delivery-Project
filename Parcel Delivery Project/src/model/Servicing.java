package model;

import java.sql.Date;

public class Servicing {
    private Date last_servicing;
    private Date next_servicing;

    public Date getLast_servicing() {
        return last_servicing;
    }

    public Date getNext_servicing() {
        return next_servicing;
    }

    public Servicing(Date last_servicing, Date next_servicing) {
        this.last_servicing = last_servicing;
        this.next_servicing = next_servicing;
    }
}
