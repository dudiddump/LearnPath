package learnpath.DAO.impl;

import learnpath.DAO.ScheduleDAO;
import learnpath.model.Schedule;
import java.sql.*;
import java.util.*;
import java.time.LocalTime;

public class ScheduleImpl implements ScheduleDAO {
    private Connection connection;

    public ScheduleImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Schedule getById(String scheduleId) {
        String sql = "SELECT * FROM schedules WHERE scheduleId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, scheduleId);
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
    public List<Schedule> getByUserId(String userId) {
        List<Schedule> list = new ArrayList<>();
        String sql = "SELECT * FROM schedules WHERE userId=?";
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
    public List<Schedule> getByRoomId(String roomId) {
        List<Schedule> list = new ArrayList<>();
        String sql = "SELECT * FROM schedules WHERE roomId=?";
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
    public List<Schedule> getBySubjectId(String subject) {
        List<Schedule> list = new ArrayList<>();
        String sql = "SELECT * FROM schedules WHERE subject=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject);
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
    public List<Schedule> getAll() {
        List<Schedule> list = new ArrayList<>();
        String sql = "SELECT * FROM schedule";
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
    public void insert(Schedule schedule) {
        String sql = "INSERT INTO schedules (userId, scheduleId, subject, day, time, roomId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, schedule.getUserId());
            stmt.setString(2, schedule.getScheduleId());
            stmt.setString(3, schedule.getSubject());
            stmt.setString(4, schedule.getDay());
            stmt.setTime(5, schedule.getTime());
            stmt.setString(6, schedule.getRoomId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Schedule schedule) {
        String sql = "UPDATE schedules SET subject=?, day=?, time=? WHERE scheduleId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, schedule.getSubject());
            stmt.setString(2, schedule.getDay());
            stmt.setTime(3, schedule.getTime());
            stmt.setString(4, schedule.getScheduleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String scheduleId) {
        String sql = "DELETE FROM schedules WHERE scheduleId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, scheduleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Schedule mapRow(ResultSet rs) throws SQLException {
        Schedule s = new Schedule(
            rs.getString("userId"),
            rs.getString("subject"),
            rs.getString("day"),
            rs.getTime("time"),
            rs.getString("roomId")
        );
        s.setScheduleId(rs.getString("scheduleId"));
        return s;
    }
}
