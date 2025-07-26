package learnpath;

import learnpath.DAO.*;
import learnpath.DAO.impl.*;
import learnpath.db.DatabaseConnector;
import learnpath.ui.LandingPage;
import learnpath.service.AuthService;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                Connection connection = DatabaseConnector.getConnection();

                UserDAO userDAO = new UserImpl(connection);
                ScheduleDAO scheduleDAO = new ScheduleImpl(connection);
                TaskDAO taskDAO = new TaskImpl(connection);
                MaterialDAO materialDAO = new MaterialImpl(connection);
                RoomDAO roomDAO = new RoomImpl(connection);
                RoomMemberDAO roomMemberDAO = new RoomMemberImpl(connection);
                FriendDAO friendDAO = new FriendImpl(connection);
                PersonalChatDAO personalChatDAO = new PersonalChatImpl(connection);
                GroupChatDAO groupChatDAO = new GroupChatImpl(connection);

                AuthService authService = new AuthService(
                    connection,
                    userDAO, 
                    scheduleDAO, 
                    taskDAO, 
                    materialDAO, 
                    roomDAO, 
                    roomMemberDAO,
                    friendDAO,
                    personalChatDAO,
                    groupChatDAO
                );

                new LandingPage(authService).setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not connect to the database. Please check your connection and try again.", "Database Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
