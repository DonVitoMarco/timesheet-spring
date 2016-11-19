package pl.thewalkingcode.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;


public class EntryCommandDTO {

    private String username;
    private Time startTime;
    private Time endTime;
    private Time time;
    private Date date;

    public String getUsername() {
        return username;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    private EntryCommandDTO() {}

    private EntryCommandDTO(EntryCommandDTOBuilder entryBuilder) {
        this.username = entryBuilder.username;
        this.startTime = entryBuilder.startTime;
        this.endTime = entryBuilder.endTime;
        this.time = entryBuilder.time;
        this.date = entryBuilder.date;
    }

    @Override
    public String toString() {
        return "EntryCommandDTO{" +
                "username='" + username + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", time=" + time +
                ", date=" + date +
                '}';
    }

    public static class EntryCommandDTOBuilder {

        private String username;
        private Time startTime;
        private Time endTime;
        private Time time;
        private Date date;

        public EntryCommandDTOBuilder addUsername(String username) {
            this.username = username;
            return this;
        }

        public EntryCommandDTOBuilder addStartTime(String startTime) {
            this.startTime = parseSqlTimeFromString(startTime);
            return this;
        }

        public EntryCommandDTOBuilder addEndTime(String endTime) {
            this.endTime = parseSqlTimeFromString(endTime);
            return this;
        }

        public EntryCommandDTOBuilder addDate(Date date) {
            this.date = date;
            return this;
        }

        public EntryCommandDTO build() {
            this.time = timeBetweenTwoTimesFromString(this.startTime, this.endTime);
            return new EntryCommandDTO(this);
        }

        private Time parseSqlTimeFromString(String time) {
            return Time.valueOf(LocalTime.parse(time));
        }

        private Time timeBetweenTwoTimesFromString(Time startTime, Time endTime) {
            LocalTime start = startTime.toLocalTime();
            LocalTime end = endTime.toLocalTime();
            return Time.valueOf(LocalTime.of((int) HOURS.between(start, end), (int) MINUTES.between(start, end) % 60));
        }

    }

}
