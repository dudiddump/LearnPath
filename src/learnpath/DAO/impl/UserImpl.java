package learnpath.DAO.impl;

import learnpath.DAO.UserDAO;
import learnpath.model.User;
import java.sql.*;
import java.util.*;

public class UserImpl implements UserDAO {
    private Connection connection;

    public UserImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getById(String userId) {
        String sql = "SELECT * FROM users WHERE userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
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
    public User getByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
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
    public User getByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
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
    public User getByUsernameAndPassword(String username, String hashedPassword) throws Exception {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return null;
    }
    
    @Override
        public boolean insertUser(User user) throws Exception {
        String sql = "INSERT INTO users (userId, username, email, password) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getUserId());
        stmt.setString(2, user.getUsername());
        stmt.setString(3, user.getEmail());
        stmt.setString(4, user.getPassword());
        int rows = stmt.executeUpdate();
        return rows > 0;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
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
    public void insert(User user) {
        String sql = "INSERT INTO users (userId, username, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET username=?, email=?, password=? WHERE userId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String userId) {
        String sql = "DELETE FROM users WHERE userId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        return new User(
            rs.getString("userId"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password")
        );
    }
}
