package learnpath.DAO.impl;

import learnpath.DAO.SubjectDAO;
import learnpath.model.Subject;
import java.sql.*;
import java.util.*;

public class SubjectImpl implements SubjectDAO {
    private Connection connection;

    public SubjectImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Subject getById(String subjectId) {
        String sql = "SELECT * FROM subjects WHERE subjectId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subjectId);
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
    public List<Subject> getByRoomId(String roomId) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subjects WHERE roomId=?";
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
    public void insert(Subject subject) {
        String sql = "INSERT INTO subjects (subjectId, roomId, subjectName) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getSubjectId());
            stmt.setString(2, subject.getRoomId());
            stmt.setString(3, subject.getSubjectName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Subject subject) {
        String sql = "UPDATE subjects SET subjectName=? WHERE subjectId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getSubjectName());
            stmt.setString(2, subject.getSubjectId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String subjectId) {
        String sql = "DELETE FROM subjects WHERE subjectId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subjectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Subject mapRow(ResultSet rs) throws SQLException {
        return new Subject(
            rs.getString("subjectId"),
            rs.getString("roomId"),
            rs.getString("subjectName")
        );
    }
}
