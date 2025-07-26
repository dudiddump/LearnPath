package learnpath.DAO;

import learnpath.model.PersonalChat;
import java.util.List;

public interface PersonalChatDAO {
    PersonalChat getById(String messageId);
    List<PersonalChat> getByUsers(String senderUserId, String receiverUserId);
    List<PersonalChat> getConversation(String userId1, String userId2);
    void insert(PersonalChat chat);
    void delete(String messageId);
}
