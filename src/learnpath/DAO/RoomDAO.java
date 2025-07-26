package learnpath.DAO;

import java.sql.Connection;
import learnpath.model.Room;
import java.util.List;

public interface RoomDAO {
    Connection getConnection();
    List<Room> getAll();
    List<Room> getByUserId(String userId);
    Room getById(String roomId);
    void joinRoom(String roomId, String userId);
    void insert(Room room);
    void update(Room room);
    void delete(String roomId);
}
