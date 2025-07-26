package learnpath.DAO.impl;

import learnpath.DAO.RoomMemberDAO;
import learnpath.model.RoomMember;
import java.sql.*;
import java.util.*;

public class RoomMemberImpl implements RoomMemberDAO {
    private Connection connection;

    public RoomMemberImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public RoomMember getById(String roomMemberId) {
        String sql = "SELECT * FROM room_members WHERE roomMemberId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, roomMemberId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RoomMember> getByRoomId(String roomId) {
        List<RoomMember> list = new ArrayList<>();
        String sql = "SELECT * FROM room_members WHERE roomId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, roomId);
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
    public RoomMember getByUserAndRoomId(String userId, String roomId) {
        String sql = "SELECT * FROM room_members WHERE userId = ? AND roomId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, roomId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RoomMember> getByUserId(String userId) {
        List<RoomMember> list = new ArrayList<>();
        String sql = "SELECT * FROM room_members WHERE userId=?";
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
    public void insert(RoomMember member) {
        String sql = "INSERT INTO room_members (roomMemberId, userId, roomId, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, member.getRoomMemberId());
            stmt.setString(2, member.getUserId());
            stmt.setString(3, member.getRoomId());
            stmt.setString(4, member.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(RoomMember member) {
        String sql = "UPDATE room_members SET role=? WHERE roomMemberId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, member.getRole());
            stmt.setString(2, member.getRoomMemberId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String roomMemberId) {
        String sql = "DELETE FROM room_members WHERE roomMemberId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, roomMemberId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RoomMember mapRow(ResultSet rs) throws SQLException {
        return new RoomMember(
            rs.getString("roomMemberId"),
            rs.getString("userId"),
            rs.getString("roomId"),
            rs.getString("role")
        );
    }
}
