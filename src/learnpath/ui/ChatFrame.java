package learnpath.ui;

import learnpath.model.*;
import learnpath.service.AuthService;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ChatFrame extends javax.swing.JFrame {
    private AuthService authService;
    private User currentUser;
    private User activeRecipientUser;
    private Room activeRoom;
    
    private JPanel chatContainer;
    private JScrollPane scrollPane;
    
    public ChatFrame(AuthService authService, User user) {
        this.authService = authService;
        this.currentUser = user;
        
        initComponents();
        
        chatContainer = new JPanel();
        chatContainer.setLayout(new BoxLayout(chatContainer, BoxLayout.Y_AXIS));
        chatContainer.setBackground(Color.WHITE);
        chatContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        chatScrollPane.setViewportView(chatContainer);
        
        loadConversationLists();
        addSelectionListeners();
        
        lblChatName.setText("Select a conversation to start");
        
        setTitle("Chats");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void loadConversationLists() {
        DefaultListModel<User> friendListModel = new DefaultListModel<>();
        List<User> friends = authService.getFriends(currentUser.getUserId());
        for (User friend : friends) {
            friendListModel.addElement(friend);
        }
        friendChatList.setModel(friendListModel);
        friendChatList.setCellRenderer(new UserListCellRenderer());

        DefaultListModel<Room> roomListModel = new DefaultListModel<>();
        List<Room> rooms = authService.getUserRooms(currentUser.getUserId());
        for (Room room : rooms) {
            roomListModel.addElement(room);
        }
        groupChatList.setModel(roomListModel);
        groupChatList.setCellRenderer(new RoomListCellRenderer());
    }
    
    public ChatFrame(AuthService authService, User user, User selectedFriend) {
        this(authService, user); 
        friendChatList.setSelectedValue(selectedFriend, true);
        loadPersonalChatHistory(selectedFriend);
    }

    private void addSelectionListeners() {
        friendChatList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                User selectedFriend = friendChatList.getSelectedValue();
                if (selectedFriend != null) {
                    groupChatList.clearSelection();
                    loadPersonalChatHistory(selectedFriend);
                }
            }
        });

        groupChatList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Room selectedRoom = groupChatList.getSelectedValue();
                if (selectedRoom != null) {
                    friendChatList.clearSelection();
                    loadGroupChatHistory(selectedRoom);
                }
            }
        });
    }
    
    public void loadPersonalChatHistory(User friend) {
        this.activeRecipientUser = friend;
        this.activeRoom = null;
        lblChatName.setText(friend.getUsername());

        chatContainer.removeAll();
        List<PersonalChat> messages = authService.getPersonalChatHistory(currentUser.getUserId(), friend.getUserId());

        for (PersonalChat msg : messages) {
            ChatMessagePanel bubble = new ChatMessagePanel();
            if (msg.getSenderUserId().equals(currentUser.getUserId())) {
                bubble.setData("You", msg.getMessageContent(), new Color(220, 248, 198));
            } else {
                bubble.setData(friend.getUsername(), msg.getMessageContent(), Color.WHITE);
            }
            chatContainer.add(bubble);
            chatContainer.add(Box.createVerticalStrut(5));
        }
        chatContainer.revalidate();
        chatContainer.repaint();
        autoScrollToBottom();
    }

    private void loadGroupChatHistory(Room room) {
        this.activeRecipientUser = null;
        this.activeRoom = room;
        lblChatName.setText(room.getRoomName());

        chatContainer.removeAll();
        List<GroupChat> messages = authService.getGroupChatHistory(room.getRoomId());

        for (GroupChat msg : messages) {
            User sender = authService.getUserById(msg.getSenderUserId());
            String senderName = (sender != null) ? sender.getUsername() : "Unknown";

            ChatMessagePanel bubble = new ChatMessagePanel();
            if (msg.getSenderUserId().equals(currentUser.getUserId())) {
                bubble.setData("You", msg.getMessageContent(), new Color(220, 248, 198));
            } else {
                bubble.setData(senderName, msg.getMessageContent(), Color.WHITE);
            }
            chatContainer.add(bubble);
            chatContainer.add(Box.createVerticalStrut(5));
        }
        chatContainer.revalidate();
        chatContainer.repaint();
        autoScrollToBottom();
    }
    
    private void autoScrollToBottom() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = chatScrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        lblLearnPath = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        btnRooms = new javax.swing.JButton();
        btnChats = new javax.swing.JButton();
        btnFriends = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        friendListScrollPane = new javax.swing.JScrollPane();
        friendChatList = new javax.swing.JList<>();
        chatPanel = new javax.swing.JPanel();
        lblChatName = new javax.swing.JLabel();
        chatScrollPane = new javax.swing.JScrollPane();
        txtSendChat = new javax.swing.JTextField();
        btnSendChat = new javax.swing.JButton();
        GroupListScrollPane = new javax.swing.JScrollPane();
        groupChatList = new javax.swing.JList<>();
        txtSearchChat = new javax.swing.JTextField();
        lblFriendList = new javax.swing.JLabel();
        lblGroupList = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuPanel.setBackground(new java.awt.Color(169, 164, 255));
        menuPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblLearnPath.setBackground(new java.awt.Color(14, 59, 118));
        lblLearnPath.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        lblLearnPath.setText("LearnPath");

        btnLogout.setBackground(new java.awt.Color(14, 59, 118));
        btnLogout.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnDashboard.setBackground(new java.awt.Color(169, 164, 255));
        btnDashboard.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
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

        btnChats.setBackground(new java.awt.Color(102, 102, 255));
        btnChats.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnChats.setForeground(new java.awt.Color(255, 255, 255));
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
                    .addComponent(lblLearnPath)
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
                .addComponent(lblLearnPath)
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

        lblHeader.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        lblHeader.setText("Chat & Communication");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeader)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeader)
                .addContainerGap())
        );

        friendListScrollPane.setViewportView(friendChatList);

        chatPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblChatName.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        lblChatName.setText("Name");

        btnSendChat.setText("Send");
        btnSendChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendChatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chatPanelLayout = new javax.swing.GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chatScrollPane)
                    .addGroup(chatPanelLayout.createSequentialGroup()
                        .addComponent(lblChatName)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(chatPanelLayout.createSequentialGroup()
                        .addComponent(txtSendChat, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSendChat)))
                .addContainerGap())
        );
        chatPanelLayout.setVerticalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChatName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSendChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSendChat))
                .addContainerGap())
        );

        GroupListScrollPane.setViewportView(groupChatList);

        txtSearchChat.setText("Search Friend or Groups");

        lblFriendList.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        lblFriendList.setText("Friends");

        lblGroupList.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        lblGroupList.setText("Groups");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblFriendList)
                                .addComponent(GroupListScrollPane)
                                .addComponent(txtSearchChat)
                                .addComponent(friendListScrollPane, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(lblGroupList))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearchChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFriendList)
                        .addGap(0, 0, 0)
                        .addComponent(friendListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(lblGroupList)
                        .addGap(0, 0, 0)
                        .addComponent(GroupListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnSendChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendChatActionPerformed
        String message = txtSendChat.getText().trim();
        if (message.isEmpty()) {
            return;
        }
        if (activeRecipientUser != null) {
            PersonalChat newMsg = new PersonalChat(message, currentUser.getUserId(), activeRecipientUser.getUserId());
            authService.sendPersonalMessage(newMsg);
            loadPersonalChatHistory(activeRecipientUser);
        } else if (activeRoom != null) {
            GroupChat newMsg = new GroupChat(message, currentUser.getUserId(), activeRoom.getRoomId());
            authService.sendGroupMessage(newMsg);
            loadGroupChatHistory(activeRoom);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a conversation first.");
        }
        txtSendChat.setText("");
    }//GEN-LAST:event_btnSendChatActionPerformed

    class UserListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof User) {
                setText(((User) value).getUsername());
            }
            return this;
        }
    }

    class RoomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Room) {
                setText(((Room) value).getRoomName());
            }
            return this;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane GroupListScrollPane;
    private javax.swing.JButton btnChats;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnFriends;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRooms;
    private javax.swing.JButton btnSendChat;
    private javax.swing.JPanel chatPanel;
    private javax.swing.JScrollPane chatScrollPane;
    private javax.swing.JList<learnpath.model.User> friendChatList;
    private javax.swing.JScrollPane friendListScrollPane;
    private javax.swing.JList<learnpath.model.Room> groupChatList;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblChatName;
    private javax.swing.JLabel lblFriendList;
    private javax.swing.JLabel lblGroupList;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblLearnPath;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JTextField txtSearchChat;
    private javax.swing.JTextField txtSendChat;
    // End of variables declaration//GEN-END:variables
}