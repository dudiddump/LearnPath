package learnpath.ui;

import learnpath.DAO.*;
import learnpath.DAO.impl.*;
import learnpath.service.AuthService;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LandingPage extends javax.swing.JFrame {
    private AuthService authService;

    public LandingPage(AuthService authService) {
        this.authService = authService;
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public LandingPage() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSignUp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(14, 59, 118));
        jLabel1.setText("Welcome to");

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(14, 59, 118));
        jLabel2.setText("LearnPath");

        btnSignUp.setBackground(new java.awt.Color(14, 59, 118));
        btnSignUp.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setText("Get Started");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(14, 59, 118));
        jLabel3.setText("Your path to smarter learning and teamwork");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(btnSignUp))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(jLabel2)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(btnSignUp)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        new SignUp(authService).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSignUpActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LandingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LandingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LandingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LandingPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            learnpath.db.DatabaseConnector connector = new learnpath.db.DatabaseConnector();
            java.sql.Connection conn = connector.getConnection();
            learnpath.DAO.UserDAO userDAO = new learnpath.DAO.impl.UserImpl(conn);
            learnpath.DAO.ScheduleDAO scheduleDAO = new learnpath.DAO.impl.ScheduleImpl(conn);
            learnpath.DAO.TaskDAO taskDAO = new learnpath.DAO.impl.TaskImpl(conn);
            learnpath.DAO.MaterialDAO materialDAO = new learnpath.DAO.impl.MaterialImpl(conn);
            learnpath.DAO.RoomDAO roomDAO = new learnpath.DAO.impl.RoomImpl(conn);
            learnpath.DAO.RoomMemberDAO roomMemberDAO = new learnpath.DAO.impl.RoomMemberImpl(conn);
            learnpath.DAO.FriendDAO friendDAO = new learnpath.DAO.impl.FriendImpl(conn);
            learnpath.DAO.PersonalChatDAO personalChatDAO = new learnpath.DAO.impl.PersonalChatImpl(conn);
            learnpath.DAO.GroupChatDAO groupChatDAO = new learnpath.DAO.impl.GroupChatImpl(conn);
            
            learnpath.service.AuthService authService1 = new learnpath.service.AuthService(
                    conn,
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
            new LandingPage(authService1).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
