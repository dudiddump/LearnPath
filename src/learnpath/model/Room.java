package learnpath.model;

import java.sql.Timestamp;
import learnpath.util.RoomIdGenerator;

public class Room {
    private String roomId;
    private String roomName;
    private String description;
    private String creatorUserId;
    private Timestamp creationDate;
    private int memberCount;
    private int materialCount;
    private int taskCount;
    
    public Room(){
    }
    public Room(String roomId, String roomName, String description, String creatorUserId, Timestamp creationDate,
            int memberCount, int materialCount, int taskCount) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.description = description;
        this.creatorUserId = creatorUserId;
        this.creationDate = creationDate;
        this.memberCount = memberCount;
        this.materialCount = materialCount;
        this.taskCount = taskCount;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreatorUserId() {
        return creatorUserId;
    }
    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }
    public Timestamp getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
    public int getMemberCount() {
        return memberCount;
    }
    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }
    public int getMaterialCount() {
        return materialCount;
    }
    public void setMaterialCount(int materialCount) {
        this.materialCount = materialCount;
    }
    public int getTaskCount() {
        return taskCount;
    }
    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }   
}
