package learnpath.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Task {
    private String taskId;
    private String title;
    private Timestamp deadline;
    private String roomId;
    private String roomName;
    private String subjectId;
    private String creatorUserId;
    private boolean isCompleted;

    public Task(String taskId, String title, Timestamp deadline, String roomId, String roomName, String subjectId, String creatorUserId, boolean isCompleted) {
        this.taskId = taskId;
        this.title = title;
        this.deadline = deadline;
        this.roomId = roomId;
        this.roomName = roomName;
        this.subjectId = subjectId;
        this.creatorUserId = creatorUserId;
        this.isCompleted = isCompleted;
    }
    public Task(){
    }
    
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Timestamp getDeadline() {
        return deadline;
    }
    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public String getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
    public String getCreatorUserId() {
        return creatorUserId;
    }
    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public boolean isIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}