package learnpath.DAO;

import learnpath.model.RoomMember;
import java.util.List;

public interface RoomMemberDAO {
    RoomMember getById(String roomMemberId);
    List<RoomMember> getByRoomId(String roomId);
    List<RoomMember> getByUserId(String userId);
    RoomMember getByUserAndRoomId(String userId, String roomId);
    void insert(RoomMember member);
    void update(RoomMember member);
    void delete(String roomMemberId);
}
