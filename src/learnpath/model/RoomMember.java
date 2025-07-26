package learnpath.model;

public class RoomMember {
    private String roomMemberId;
    private String userId;
    private String roomId;
    private String role; 
    
    public RoomMember(String roomMemberId, String userId, String roomId, String role) {
        this.roomMemberId = roomMemberId;
        this.userId = userId;
        this.roomId = roomId;
        this.role = role;
    }
    public String getRoomMemberId() {
        return roomMemberId;
    }
    public String getUserId() {
        return userId;
    }
    public String getRoomId() {
        return roomId;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}