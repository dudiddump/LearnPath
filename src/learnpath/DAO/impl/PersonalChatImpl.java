package learnpath.DAO.impl;

import learnpath.DAO.PersonalChatDAO;
import learnpath.model.PersonalChat;
import java.sql.*;
import java.util.*;

public class PersonalChatImpl implements PersonalChatDAO {
    private Connection connection;

    public PersonalChatImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PersonalChat getById(String messageId) {
        String sql = "SELECT * FROM personal_chats WHERE messageId=?";
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
    public List<PersonalChat> getByUsers(String senderUserId, String receiverUserId) {
        List<PersonalChat> list = new ArrayList<>();
        String sql = "SELECT * FROM personal_chats WHERE (senderId = ? AND receiverId = ?) OR (senderId = ? AND receiverId = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, senderUserId);
            stmt.setString(2, receiverUserId);
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
    public List<PersonalChat> getConversation(String userId1, String userId2) {
        List<PersonalChat> list = new ArrayList<>();
        String sql = "SELECT * FROM personal_chats " +
                     "WHERE (senderUserId = ? AND receiverUserId = ?) " +
                     "OR (senderUserId = ? AND receiverUserId = ?) " +
                     "ORDER BY timestamp DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId1);
            stmt.setString(2, userId2);
            stmt.setString(3, userId2);
            stmt.setString(4, userId1);
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
    public void insert(PersonalChat chat) {
        String sql = "INSERT INTO personal_chats (messageId, messageContent, timestamp, senderUserId, receiverUserId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, chat.getMessageId());
            stmt.setString(2, chat.getMessageContent());
            stmt.setTimestamp(3, Timestamp.valueOf(chat.getTimestamp()));
            stmt.setString(4, chat.getSenderUserId());
            stmt.setString(5, chat.getReceiverUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String messageId) {
        String sql = "DELETE FROM personal_chats WHERE messageId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, messageId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PersonalChat mapRow(ResultSet rs) throws SQLException {
        return new PersonalChat(
            rs.getString("messageContent"),
            rs.getString("senderUserId"),
            rs.getString("receiverUserId")
        );
    }
}
