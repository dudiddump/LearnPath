package learnpath.model;

import java.util.UUID;

public class Friend{
    private String id;
    private String userId;
    private String friendUserId;

    public Friend(String userId, String friendUserId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.friendUserId = friendUserId;
    }

    public Friend(String id, String userId, String friendUserId) {
        this.id = id;
        this.userId = userId;
        this.friendUserId = friendUserId;
    }

    public String getId() {
        return id;
    }
    public String getUserId() {
        return userId;
    }
    public String getFriendUserId() {
        return friendUserId;
    }
    
}