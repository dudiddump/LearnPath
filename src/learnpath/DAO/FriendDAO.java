package learnpath.DAO;

import learnpath.model.Friend;
import java.util.List;

public interface FriendDAO {
    void insert(Friend friend);
    void delete(String id);
    Friend getById(String id);
    List<Friend> getAll();
    List<Friend> getFriendsByUserId(String userId);
}
