package learnpath.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PersonalChat {
    private String messageId;
    private String messageContent;
    private LocalDateTime timestamp;
    private String senderUserId;
    private String receiverUserId;
    
    public PersonalChat(String messageContent, String senderUserId, String receiverUserId) {
        this.messageId = UUID.randomUUID().toString();
        this.messageContent = messageContent;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
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
    public String getReceiverUserId() {
        return receiverUserId;
    }
}