package learnpath.DAO.impl;

import learnpath.DAO.RoomDAO;
import learnpath.model.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomImpl implements RoomDAO {
    private Connection connection;

    public RoomImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public Connection getConnection() {
        return this.connection;
    }
    
    @Override
    public List<Room> getByUserId(String userId) {
        List<Room> list = new ArrayList<>();
        System.out.println("Rooms retrieved: " + list.size());
        for (Room r : list) {
    System.out.println("Room: " + r.getRoomId() + " - " + r.getRoomName());
        }
        String sql = """
            SELECT r.*,
                (SELECT COUNT(*) FROM room_members rm WHERE rm.roomId = r.roomId) as memberCount,
                (SELECT COUNT(*) FROM materials m WHERE m.roomId = r.roomId) as materialCount,
                (SELECT COUNT(*) FROM tasks t WHERE t.roomId = r.roomId) as taskCount
            FROM rooms r
            JOIN room_members rm ON r.roomId = rm.roomId
            WHERE rm.userId = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public Room getById(String roomId) {
        String sql = """
            SELECT r.*,
                (SELECT COUNT(*) FROM room_members rm WHERE rm.roomId = r.roomId) as memberCount,
                (SELECT COUNT(*) FROM materials m WHERE m.roomId = r.roomId) as materialCount,
                (SELECT COUNT(*) FROM tasks t WHERE t.roomId = r.roomId) as taskCount
            FROM rooms r
            WHERE r.roomId = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, roomId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   @Override
    public List<Room> getAll() {
        List<Room> list = new ArrayList<>();
        String sql = """
            SELECT
                r.*,
                (SELECT COUNT(*) FROM room_members rm WHERE rm.roomId = r.roomId) as memberCount,
                (SELECT COUNT(*) FROM materials m WHERE m.roomId = r.roomId) as materialCount,
                (SELECT COUNT(*) FROM tasks t WHERE t.roomId = r.roomId) as taskCount
            FROM rooms r
        """;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public void joinRoom(String roomId, String userId) {
        String sql = "INSERT INTO room_members (roomMemberId, userId, roomId, role) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, userId);
            stmt.setString(3, roomId);
            stmt.setString(4, "Member");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Room room) {
        String sql = "INSERT INTO rooms (roomId, roomName, description, creatorUserId, creationDate, memberCount, materialCount, taskCount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomId());
            stmt.setString(2, room.getRoomName());
            stmt.setString(3, room.getDescription());
            stmt.setString(4, room.getCreatorUserId());
            stmt.setTimestamp(5, room.getCreationDate());
            stmt.setInt(6, room.getMemberCount());
            stmt.setInt(7, room.getMaterialCount());
            stmt.setInt(8, room.getTaskCount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Room room) {
        String sql = "UPDATE rooms SET roomName=?, description=? WHERE roomId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomName());
            stmt.setString(2, room.getDescription());
            stmt.setString(3, room.getRoomId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String roomId) {
        String sql = "DELETE FROM rooms WHERE roomId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, roomId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Room mapRow(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setRoomId(rs.getString("roomId"));
        room.setRoomName(rs.getString("roomName"));
        room.setDescription(rs.getString("description"));
        room.setCreatorUserId(rs.getString("creatorUserId"));
        room.setCreationDate(rs.getTimestamp("creationDate"));
        room.setMemberCount(rs.getInt("memberCount"));
        room.setMaterialCount(rs.getInt("materialCount"));
        room.setTaskCount(rs.getInt("taskCount"));
        return room;
    }
}
