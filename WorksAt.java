package model;

import java.sql.Date;

public class WorksAt {
    private String worksAt;
    private Date startDate;
    private String bid;

    public String getWorksAt() {
        return worksAt;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getBid() {
        return bid;
    }

    public WorksAt(String worksAt, Date startDate, String bid) {
        this.worksAt = worksAt;
        this.startDate = startDate;
        this.bid = bid;
    }
}
