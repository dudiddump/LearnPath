package learnpath.model;

import java.sql.Time;
import java.util.UUID;

public class Schedule {
    private String scheduleId;
    private String subject;
    private String day;
    private Time time;
    private String roomId;
    private String userId;

    public Schedule() {
    }
    
    public Schedule(String userId, String subject, String day, Time time, String roomId) {
        this.scheduleId = UUID.randomUUID().toString();
        this.userId = userId;
        this.subject = subject;
        this.day = day;
        this.time = time;
        this.roomId = roomId;
    }

    public String getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}