package learnpath.DAO.impl;

import learnpath.DAO.TaskDAO;
import learnpath.model.Task;

import java.sql.*;
import java.util.*;

public class TaskImpl implements TaskDAO {
    private Connection connection;

    public TaskImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Task getById(String taskId) {
        String sql = """
            SELECT t.*, r.roomName
            FROM tasks t
            JOIN rooms r ON t.roomId = r.roomId
            WHERE t.taskId = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, taskId);
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
    public List<Task> getByUserId(String userId) {
        List<Task> list = new ArrayList<>();
        String sql = """
            SELECT t.*, r.roomName
            FROM tasks t
            JOIN rooms r ON t.roomId = r.roomId
            WHERE t.creatorUserId = ?
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
    public List<Task> getByRoomId(String roomId) {
        List<Task> list = new ArrayList<>();
        String sql = """
            SELECT t.*, r.roomName
            FROM tasks t
            JOIN rooms r ON t.roomId = r.roomId
            WHERE t.roomId = ?
        """;
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
    public List<Task> getBySubjectId(String subjectId) {
        List<Task> list = new ArrayList<>();
        String sql = """
            SELECT t.*, r.roomName
            FROM tasks t
            JOIN rooms r ON t.roomId = r.roomId
            JOIN room_members rm ON rm.roomId = t.roomId
            WHERE rm.userId = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subjectId);
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
    public void insert(Task task) {
        String sql = "INSERT INTO tasks (taskId, title, deadline, roomId, subjectId, creatorUserId, isCompleted) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, task.getTaskId());
            stmt.setString(2, task.getTitle());
            stmt.setTimestamp(3, task.getDeadline());
            stmt.setString(4, task.getRoomId());
            stmt.setString(5, task.getSubjectId());
            stmt.setString(6, task.getCreatorUserId());
            stmt.setBoolean(7, task.isIsCompleted());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Task task) {
        String sql = "UPDATE tasks SET title=?, isCompleted=?, deadline=? WHERE taskId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setBoolean(2, task.isIsCompleted());
            stmt.setTimestamp(3, task.getDeadline());
            stmt.setString(4, task.getTaskId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String taskId) {
        String sql = "DELETE FROM tasks WHERE taskId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, taskId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Task mapRow(ResultSet rs) throws SQLException {
        return new Task(
            rs.getString("taskId"),
            rs.getString("title"),
            rs.getTimestamp("deadline"),
            rs.getString("roomId"),
            rs.getString("roomName"),
            rs.getString("subjectId"),
            rs.getString("creatorUserId"),
            rs.getBoolean("isCompleted")
        );
    }
}