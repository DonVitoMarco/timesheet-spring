package pl.thewalkingcode.model;

import java.sql.Date;
import java.sql.Time;


public class EntryFormDTO {

    private Date date;
    private String timeStart;
    private String timeEnd;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "EntryFormDTO{" +
                "date=" + date +
                ", timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                '}';
    }

}
