package learnpath.service;

import learnpath.DAO.*;
import learnpath.model.*;
import learnpath.util.UserIdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class AuthService {
    private final Connection connection;
    private final UserDAO userDAO;
    private final ScheduleDAO scheduleDAO;
    private final TaskDAO taskDAO;
    private final MaterialDAO materialDAO;
    private final RoomDAO roomDAO;
    private final RoomMemberDAO roomMemberDAO;
    private final FriendDAO friendDAO;
    private final PersonalChatDAO personalChatDAO;
    private final GroupChatDAO groupChatDAO;

    public AuthService(
        Connection connection,
        UserDAO userDAO, ScheduleDAO scheduleDAO, TaskDAO taskDAO, MaterialDAO materialDAO,
        RoomDAO roomDAO, RoomMemberDAO roomMemberDAO, FriendDAO friendDAO,
        PersonalChatDAO personalChatDAO, GroupChatDAO groupChatDAO) {
        
        this.connection = connection;
        this.userDAO = userDAO;
        this.scheduleDAO = scheduleDAO;
        this.taskDAO = taskDAO;
        this.materialDAO = materialDAO;
        this.roomDAO = roomDAO;
        this.roomMemberDAO = roomMemberDAO;
        this.friendDAO = friendDAO;
        this.personalChatDAO = personalChatDAO;
        this.groupChatDAO = groupChatDAO;
    }

    public AuthService(Connection connection, UserDAO userDAO) {
        this.connection = connection;
        this.userDAO = userDAO;
        this.scheduleDAO = null;
        this.taskDAO = null;
        this.materialDAO = null;
        this.roomDAO = null;
        this.roomMemberDAO = null;
        this.friendDAO = null;
        this.personalChatDAO = null;
        this.groupChatDAO = null;
    }

    public boolean signUp(String username, String email, String password) throws Exception {
        User existingUser = userDAO.getByEmail(email);
        if (existingUser != null) {
            return false;
        }
        String userId = UserIdGenerator.generateUserId();
        String hashedPassword = hashPassword(password);
        User user = new User(userId, username, email, hashedPassword);
        return userDAO.insertUser(user);
    }

    public boolean login(String username, String password) throws Exception {
        String hashedPassword = hashPassword(password);
        User user = userDAO.getByUsernameAndPassword(username, hashedPassword);
        return user != null;
    }

    public String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public User getByUsername(String username) {
        try {
            return userDAO.getByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(String userId) {
        try {
            return userDAO.getById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RoomMember getRoomMemberByUserAndRoom(String userId, String roomId) {
        return roomMemberDAO.getByUserAndRoomId(userId, roomId);
    }
    public List<Schedule> getUserSchedules(String userId) {
        return scheduleDAO.getByUserId(userId);
    }
    public List<Task> getUserTasks(String userId) {
        return taskDAO.getByUserId(userId);
    }
    public List<Material> getUserMaterials(String userId) {
        return materialDAO.getByUserId(userId);
    }
    public List<Room> getUserRooms(String userId) {
        return roomDAO.getByUserId(userId);
    }
    public List<Task> getTasksForRoom(String roomId) {
        return taskDAO.getByRoomId(roomId);
    }
    public List<Schedule> getSchedulesForRoom(String roomId) {
        return scheduleDAO.getByRoomId(roomId);
    }
    public List<Material> getMaterialsForRoom(String roomId) {
        return materialDAO.getByRoomId(roomId);
    }
    public List<RoomMember> getMembersForRoom(String roomId) {
        return roomMemberDAO.getByRoomId(roomId);
    }

    public List<User> getFriends(String userId) {
        List<User> friendUsers = new ArrayList<>();
        List<Friend> friendRelations = friendDAO.getFriendsByUserId(userId);
        for (Friend relation : friendRelations) {
            String friendId = relation.getFriendUserId();
            User friend = userDAO.getById(friendId);
            if (friend != null) {
                friendUsers.add(friend);
            }
        }
        return friendUsers;
    }

    public void addFriend(User user1, User user2) {
        String friendshipId1 = UUID.randomUUID().toString();
        String friendshipId2 = UUID.randomUUID().toString();

        Friend friendship1 = new Friend(friendshipId1, user1.getUserId(), user2.getUserId());
        Friend friendship2 = new Friend(friendshipId2, user2.getUserId(), user1.getUserId());

        friendDAO.insert(friendship1);
        friendDAO.insert(friendship2);
    }
    public List<PersonalChat> getPersonalChatHistory(String userId1, String userId2) {
        return personalChatDAO.getConversation(userId1, userId2);
    }
    public List<GroupChat> getGroupChatHistory(String roomId) {
        return groupChatDAO.getByRoomId(roomId);
    }
    public void sendPersonalMessage(PersonalChat message) {
        personalChatDAO.insert(message);
    }
    public void sendGroupMessage(GroupChat message) {
        groupChatDAO.insert(message);
    }

    public void saveRoom(Room room, RoomMember initialMember) {
        try {
            connection.setAutoCommit(false);
            roomDAO.insert(room);
            roomMemberDAO.insert(initialMember);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void joinRoom(String userId, String roomId) {
        if (isAlreadyJoined(roomId, userId)) return;
        roomDAO.joinRoom(roomId, userId);
    }

    private boolean isAlreadyJoined(String roomId, String userId) {
        String sql = "SELECT COUNT(*) FROM room_members WHERE roomId=? AND userId=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, roomId);
            stmt.setString(2, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertSchedule(Schedule schedule) {
        scheduleDAO.insert(schedule);
    }
    public void updateSchedule(Schedule schedule) {
        scheduleDAO.update(schedule);
    }
    public void deleteSchedule(String scheduleId) {
        scheduleDAO.delete(scheduleId);
    }
    public Schedule getScheduleById(String scheduleId) {
        return scheduleDAO.getById(scheduleId);
    }
    public void insertTask(Task task) {
        taskDAO.insert(task);
    }
    public void updateTask(Task task) {
        taskDAO.update(task);
    }
    public void deleteTask(String taskId) {
        taskDAO.delete(taskId);
    }
    public Task getTaskById(String taskId) {
        return taskDAO.getById(taskId);
    }
    public void insertMaterial(Material material) {
        materialDAO.insert(material);
    }
    public void updateMaterial(Material material) {
        materialDAO.update(material);
    }
    public void deleteMaterial(String materialId) {
        materialDAO.delete(materialId);
    }
    public Material getMaterialById(String materialId) {
        return materialDAO.getById(materialId);
    }
    public void deleteRoomMember(String roomMemberId) {
        roomMemberDAO.delete(roomMemberId);
    }
    public void updateRoomMember(RoomMember member) {
        roomMemberDAO.update(member);
    }
    public void createRoom(Room room) {
        roomDAO.insert(room);
    }
    public void addRoomMember(RoomMember member) {
        roomMemberDAO.insert(member);
    }
    public Room getRoomById(String roomId) {
        return roomDAO.getById(roomId);
    }
}