package pl.thewalkingcode.model;

import java.sql.Date;

public class EntryDeleteFormDTO {

    private int index;
    private Date date;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EntryDeleteFormDTO{" +
                "index=" + index +
                ", date=" + date +
                '}';
    }

}
