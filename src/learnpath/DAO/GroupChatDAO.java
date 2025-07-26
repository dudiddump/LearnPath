package learnpath.DAO;

import learnpath.model.GroupChat;
import java.util.List;

public interface GroupChatDAO{
    GroupChat getById(String messageId);
    List<GroupChat> getByRoomId(String roomId);
    void insert(GroupChat groupChat);
    void delete(String messageId);
}