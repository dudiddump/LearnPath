package learnpath.ui;

import learnpath.model.Friend;
import learnpath.model.User;
import learnpath.service.AuthService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class FriendsFrame extends javax.swing.JFrame {
    private AuthService authService;
    private User currentUser;

    public FriendsFrame(AuthService authService, User user) {
        this.authService = authService;
        this.currentUser = user;
        
        initComponents();
        
        friendsListPanel.setLayout(new BoxLayout(friendsListPanel, BoxLayout.Y_AXIS));
        
        loadFriends();
        
        setupFilterButtons();
        
        setTitle("Friends");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private JPanel createFriendCard(User friend) {
        JPanel card = new JPanel(new BorderLayout(15, 5));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
        card.setBackground(Color.WHITE);

        JLabel friendNameLabel = new JLabel(friend.getUsername());
        friendNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton messageButton = new JButton("Message");
        messageButton.addActionListener(e -> {
            ChatFrame chatFrame = new ChatFrame(authService, currentUser);
            chatFrame.setVisible(true);
            chatFrame.loadPersonalChatHistory(friend);
            FriendsFrame.this.dispose();
        });
        
        card.add(friendNameLabel, BorderLayout.CENTER);
        card.add(messageButton, BorderLayout.EAST);
        return card;
    }

    private void loadFriends() {
        friendsListPanel.removeAll(); 

        List<User> friends = authService.getFriends(currentUser.getUserId());

        if (friends.isEmpty()) {
            friendsListPanel.add(new JLabel("  You have no friends yet."));
        } else {
            for (User friend : friends) {
                JPanel card = createFriendCard(friend);
                friendsListPanel.add(card);
            }
        }
        friendsListPanel.revalidate();
        friendsListPanel.repaint();
    }
    
    private void setupFilterButtons() {
        ButtonGroup filterButtonGroup = new ButtonGroup();
        filterButtonGroup.add(onlineTglBtn);
        filterButtonGroup.add(allFriendsTglBtn);
        filterButtonGroup.add(requestTglBtn);
        
        allFriendsTglBtn.setSelected(true);

        ActionListener filterListener = e -> {
            if (onlineTglBtn.isSelected()) {
                JOptionPane.showMessageDialog(this, "Fitur filter 'Online' belum diimplementasikan.");
            } else if (allFriendsTglBtn.isSelected()) {
                loadFriends();
            } else if (requestTglBtn.isSelected()) {
                JOptionPane.showMessageDialog(this, "Fitur filter 'Request' belum diimplementasikan.");
            }
        };
        
        onlineTglBtn.addActionListener(filterListener);
        allFriendsTglBtn.addActionListener(filterListener);
        requestTglBtn.addActionListener(filterListener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        btnRooms = new javax.swing.JButton();
        btnChats = new javax.swing.JButton();
        btnFriends = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        searchFriendPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        onlineTglBtn = new javax.swing.JToggleButton();
        allFriendsTglBtn = new javax.swing.JToggleButton();
        requestTglBtn = new javax.swing.JToggleButton();
        txtSearchFriends = new javax.swing.JTextField();
        btnAddFriends = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        friendsListPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuPanel.setBackground(new java.awt.Color(169, 164, 255));
        menuPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(14, 59, 118));
        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setText("LearnPath");

        btnLogout.setBackground(new java.awt.Color(14, 59, 118));
        btnLogout.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnDashboard.setBackground(new java.awt.Color(102, 102, 255));
        btnDashboard.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorderPainted(false);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });

        btnRooms.setBackground(new java.awt.Color(169, 164, 255));
        btnRooms.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnRooms.setText("Rooms");
        btnRooms.setBorderPainted(false);
        btnRooms.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomsActionPerformed(evt);
            }
        });

        btnChats.setBackground(new java.awt.Color(169, 164, 255));
        btnChats.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnChats.setText("Chats");
        btnChats.setBorderPainted(false);
        btnChats.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnChats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatsActionPerformed(evt);
            }
        });

        btnFriends.setBackground(new java.awt.Color(169, 164, 255));
        btnFriends.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnFriends.setText("Friends");
        btnFriends.setBorderPainted(false);
        btnFriends.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFriendsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFriends, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChats, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRooms, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDashboard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnDashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRooms)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChats)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFriends)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(18, 18, 18))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel17.setText("Friends");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap())
        );

        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Connect with friends and collaborate on projects");

        onlineTglBtn.setBackground(new java.awt.Color(14, 59, 118));
        onlineTglBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        onlineTglBtn.setForeground(new java.awt.Color(255, 255, 255));
        onlineTglBtn.setText("Online");
        onlineTglBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        allFriendsTglBtn.setText("All Friends");
        allFriendsTglBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        requestTglBtn.setText("Request");
        requestTglBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtSearchFriends.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtSearchFriends.setText("Search Friends...");
        txtSearchFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchFriendsActionPerformed(evt);
            }
        });

        btnAddFriends.setBackground(new java.awt.Color(14, 59, 118));
        btnAddFriends.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddFriends.setForeground(new java.awt.Color(255, 255, 255));
        btnAddFriends.setText("Add Friends");
        btnAddFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFriendsActionPerformed(evt);
            }
        });

        friendsListPanel.setLayout(new javax.swing.BoxLayout(friendsListPanel, javax.swing.BoxLayout.X_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendsListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendsListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout searchFriendPanelLayout = new javax.swing.GroupLayout(searchFriendPanel);
        searchFriendPanel.setLayout(searchFriendPanelLayout);
        searchFriendPanelLayout.setHorizontalGroup(
            searchFriendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchFriendPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchFriends)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddFriends)
                .addGap(11, 11, 11))
            .addGroup(searchFriendPanelLayout.createSequentialGroup()
                .addGroup(searchFriendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(searchFriendPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(searchFriendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchFriendPanelLayout.createSequentialGroup()
                                .addComponent(onlineTglBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(allFriendsTglBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(requestTglBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        searchFriendPanelLayout.setVerticalGroup(
            searchFriendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchFriendPanelLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchFriendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchFriends, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddFriends))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchFriendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(onlineTglBtn)
                    .addComponent(allFriendsTglBtn)
                    .addComponent(requestTglBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchFriendPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 303, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchFriendPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        new Login(authService).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        new Dashboard(authService, currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomsActionPerformed
        new RoomFrame(authService, currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRoomsActionPerformed

    private void btnChatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatsActionPerformed
        new ChatFrame(authService, currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnChatsActionPerformed

    private void btnFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFriendsActionPerformed
        new FriendsFrame(authService, currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnFriendsActionPerformed

    private void txtSearchFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchFriendsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchFriendsActionPerformed

    private void btnAddFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFriendsActionPerformed
        String usernameToAdd = txtSearchFriends.getText().trim();
        if (usernameToAdd.isEmpty() || usernameToAdd.equals("Search Friends...")) {
            JOptionPane.showMessageDialog(this, "Please enter a username to add.");
            return;
        }
        if (usernameToAdd.equals(currentUser.getUsername())) {
            JOptionPane.showMessageDialog(this, "You cannot add yourself as a friend.");
            return;
        }

        User friendToAdd = authService.getByUsername(usernameToAdd);
        if (friendToAdd == null) {
            JOptionPane.showMessageDialog(this, "User '" + usernameToAdd + "' not found.");
        } else {
            try {
                authService.addFriend(currentUser, friendToAdd);
                JOptionPane.showMessageDialog(this, "Friend added successfully!");
                loadFriends();
                txtSearchFriends.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to add friend. Maybe you are already friends?", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAddFriendsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton allFriendsTglBtn;
    private javax.swing.JButton btnAddFriends;
    private javax.swing.JButton btnChats;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnFriends;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRooms;
    private javax.swing.JPanel friendsListPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JToggleButton onlineTglBtn;
    private javax.swing.JToggleButton requestTglBtn;
    private javax.swing.JPanel searchFriendPanel;
    private javax.swing.JTextField txtSearchFriends;
    // End of variables declaration//GEN-END:variables
}
