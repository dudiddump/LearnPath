package learnpath.DAO.impl;

import learnpath.DAO.FriendDAO;
import learnpath.model.Friend;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendImpl implements FriendDAO {
    private Connection connection;

    public FriendImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Friend friend) {
        String sql = "INSERT INTO friends (id, userId, friendUserId) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, friend.getId());
            stmt.setString(2, friend.getUserId());
            stmt.setString(3, friend.getFriendUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM friends WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Friend getById(String id) {
        String sql = "SELECT * FROM friends WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
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
    public List<Friend> getAll() {
        List<Friend> list = new ArrayList<>();
        String sql = "SELECT * FROM friends";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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
    public List<Friend> getFriendsByUserId(String userId) {
        List<Friend> list = new ArrayList<>();
        String sql = "SELECT * FROM friends WHERE userId = ?";
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

    private Friend mapRow(ResultSet rs) throws SQLException {
        return new Friend(
            rs.getString("id"),
            rs.getString("userId"),
            rs.getString("friendUserId")
        );
    }
}
