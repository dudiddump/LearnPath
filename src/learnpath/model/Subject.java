package learnpath.model;

import java.util.UUID;

public class Subject {
    private String subjectId;
    private String subjectName;
    private String roomId;

    public Subject(String subjectName, String roomId) {
        this.subjectId = UUID.randomUUID().toString();
        this.subjectName = subjectName;
        this.roomId = roomId;
    }

    public Subject(String subjectId, String subjectName, String roomId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.roomId = roomId;
    }

    public String getSubjectId() {
        return subjectId;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public String getRoomId() {
        return roomId;
    }
}
