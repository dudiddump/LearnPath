package learnpath.DAO.impl;

import learnpath.DAO.GroupChatDAO;
import learnpath.model.GroupChat;
import java.sql.*;
import java.util.*;

public class GroupChatImpl implements GroupChatDAO {
    private Connection connection;

    public GroupChatImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public GroupChat getById(String messageId) {
        String sql = "SELECT * FROM group_chats WHERE messageId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, messageId);
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
    public List<GroupChat> getByRoomId(String roomId) {
        List<GroupChat> list = new ArrayList<>();
        String sql = "SELECT * FROM group_chats WHERE roomId=?";
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
    public void insert(GroupChat chat) {
        String sql = "INSERT INTO group_chats (messageId, messageContent, timestamp, senderUserId, roomId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, chat.getMessageId());
            stmt.setString(2, chat.getMessageContent());
            stmt.setTimestamp(3, Timestamp.valueOf(chat.getTimestamp()));
            stmt.setString(4, chat.getSenderUserId());
            stmt.setString(5, chat.getClassId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String messageId) {
        String sql = "DELETE FROM group_chats WHERE messageId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, messageId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private GroupChat mapRow(ResultSet rs) throws SQLException {
        return new GroupChat(
            rs.getString("messageContent"),
            rs.getString("senderUserId"),
            rs.getString("roomId")
        );
    }
}
