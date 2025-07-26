package learnpath.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class GroupChat{
    private String messageId;
    private String messageContent;
    private LocalDateTime timestamp;
    private String senderUserId;
    private String roomId;

    public GroupChat(String messageContent, String senderUserId, String roomId) {
        this.messageId = UUID.randomUUID().toString();
        this.messageContent = messageContent;
        this.senderUserId = senderUserId;
        this.roomId = roomId;
        this.timestamp = LocalDateTime.now();
    }
    public String getMessageId() {
        return messageId;
    }
    public String getMessageContent() {
        return messageContent;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getSenderUserId() {
        return senderUserId;
    }
    public String getClassId() {
        return roomId;
    }
}
