package learnpath.DAO.impl;

import learnpath.DAO.MaterialDAO;
import learnpath.model.Material;
import java.sql.*;
import java.util.*;

public class MaterialImpl implements MaterialDAO {
    private Connection connection;

    public MaterialImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Material getById(String fileId) {
        String sql = "SELECT * FROM materials WHERE fileId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fileId);
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
    public List<Material> getByUserId(String userId) {
        List<Material> list = new ArrayList<>();
        String sql = "SELECT * FROM materials WHERE uploadedByUserId = ?";
                
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
    public List<Material> getBySubjectId(String subjectId) {
        List<Material> list = new ArrayList<>();
        String sql = "SELECT * FROM materials WHERE subjectId=?";
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
    public List<Material> getByRoomId(String roomId) {
        List<Material> list = new ArrayList<>();
        String sql = "SELECT * FROM materials WHERE roomId=?";
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
    public List<Material> getAll() {
        List<Material> list = new ArrayList<>();
        String sql = "SELECT * FROM material";
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
    public void insert(Material material) {
        String sql = "INSERT INTO materials (fileId, subjectId, fileLink) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, material.getMaterialId());
            stmt.setString(2, material.getSubjectId());
            stmt.setString(3, material.getFileLink());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Material material) {
        String sql = "UPDATE materials SET fileLink=? WHERE fileId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, material.getFileLink());
            stmt.setString(2, material.getMaterialId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String fileId) {
        String sql = "DELETE FROM materials WHERE fileId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fileId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Material mapRow(ResultSet rs) throws SQLException {
        return new Material(
            rs.getString("fileName"),
            rs.getString("fileLink"),
            rs.getString("materialDetail"),
            rs.getString("uploadedBy"),
            rs.getString("roomId")
        );
    }
}
