package pl.thewalkingcode.model;


import java.sql.Date;
import java.sql.Time;

public class Entry {

    private Date date;
    private Time timeStart;
    private Time timeEnd;
    private Time time;
    private String user;
    private String department;

    public Entry() {
    }

    public Entry(Date date, Time timeStart, Time timeEnd, Time time, String user, String department) {
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.time = time;
        this.user = user;
        this.department = department;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
