package pl.thewalkingcode.model;

import java.sql.Date;
import java.sql.Time;

public class EntryQueryDTO {

    private int index;
    private String username;
    private Date date;
    private Time time;
    private Time startTime;
    private Time endTime;
    private boolean approve;
    private String department;

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "EntryQueryDTO{" +
                "index=" + index +
                ", username='" + username + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", approve=" + approve +
                ", department='" + department + '\'' +
                '}';
    }

}
