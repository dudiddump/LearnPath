package learnpath.ui;

import learnpath.model.*;
import learnpath.service.AuthService;
import learnpath.util.RoomIdGenerator;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.Timestamp;

public class RoomFrame extends javax.swing.JFrame {
    private AuthService authService;
    private User currentUser;
    private String currentUserId;
    
    private JScrollPane roomScrollPane;
    private javax.swing.JPanel roomScrollWrapperPanel;

    public RoomFrame(AuthService authService, User user) {
        this.authService = authService;
        this.currentUser = user;
        this.currentUserId = user.getUserId();
        initComponents();
        roomCardPanel.setLayout(new java.awt.FlowLayout(
            java.awt.FlowLayout.LEFT, 20,20));
        loadRooms();
        setLocationRelativeTo(null);
    }
    
    public void addRoomCard(Room newRoom) {
        JPanel newCard = createRoomCard(newRoom);
        roomCardPanel.add(newCard);
        roomCardPanel.revalidate();
        roomCardPanel.repaint();
    }
    
    private void loadRooms() {
        roomCardPanel.removeAll();
        
        List<Room> rooms = authService.getUserRooms(currentUserId);
        if (rooms.isEmpty()) {
            JLabel label = new JLabel("Create or join a room!");
            roomCardPanel.add(label);
        } else {
            for (Room room : rooms) {
                JPanel card = createRoomCard(room);
                roomCardPanel.add(card);
            }
        }
        System.out.println("Jumlah rooms user = " + rooms.size());
        for (Room r : rooms) {
            System.out.println("Room: " + r.getRoomId() + " - " + r.getRoomName());
        }
        roomCardPanel.revalidate();
        roomCardPanel.repaint();
    }
    
    private void refreshRoomList() {
        roomCardPanel.removeAll();
        loadRooms();
        roomCardPanel.revalidate();
        roomCardPanel.repaint();
    }
    
    private JPanel createRoomCard(Room room) {        
        //Card
        JPanel card = new JPanel();        
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(225, 250));
        card.setBackground(new Color(244, 242, 255));
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        //Header
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        JLabel lblRoomName = new JLabel(room.getRoomName(), SwingConstants.CENTER);
        lblRoomName.setFont(new Font("Sans UI", Font.BOLD, 20));
        headerPanel.add(lblRoomName);
        //Content
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        //label
        JLabel lblRoomId = new JLabel("Room ID: " + room.getRoomId(), SwingConstants.CENTER);
        lblRoomId.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRoomId.setFont(new Font("Sans UI", Font.PLAIN, 12));
        lblRoomId.setForeground(new Color(80, 80, 80));
        
        //Statistik
        JPanel statsPanel = new JPanel(new GridLayout(1, 3));
        statsPanel.setOpaque(false);
        // Statistik Member
        JPanel memberStatPanel = new JPanel();
        memberStatPanel.setLayout(new BoxLayout(memberStatPanel, BoxLayout.Y_AXIS));
        memberStatPanel.setOpaque(false);
        JLabel lblMemberCount = new JLabel(String.valueOf(room.getMemberCount()), SwingConstants.CENTER);
        lblMemberCount.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblMemberCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel lblMember = new JLabel("Members", SwingConstants.CENTER);
        lblMember.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        lblMember.setAlignmentX(Component.CENTER_ALIGNMENT);
        memberStatPanel.add(lblMemberCount);
        memberStatPanel.add(lblMember);
        // Statistik Material
        JPanel materialStatPanel = new JPanel();
        materialStatPanel.setLayout(new BoxLayout(materialStatPanel, BoxLayout.Y_AXIS));
        materialStatPanel.setOpaque(false);
        JLabel lblMaterialCount = new JLabel(String.valueOf(room.getMaterialCount()), SwingConstants.CENTER);
        lblMaterialCount.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblMaterialCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel lblMaterial = new JLabel("Materials", SwingConstants.CENTER);
        lblMaterial.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        lblMaterial.setAlignmentX(Component.CENTER_ALIGNMENT);
        materialStatPanel.add(lblMaterialCount);
        materialStatPanel.add(lblMaterial);
        // Statistik Task
        JPanel taskStatPanel = new JPanel();
        taskStatPanel.setLayout(new BoxLayout(taskStatPanel, BoxLayout.Y_AXIS));
        taskStatPanel.setOpaque(false);
        JLabel lblTaskCount = new JLabel(String.valueOf(room.getTaskCount()), SwingConstants.CENTER);
        lblTaskCount.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblTaskCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel lblTask = new JLabel("Tasks", SwingConstants.CENTER);
        lblTask.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        lblTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        taskStatPanel.add(lblTaskCount);
        taskStatPanel.add(lblTask);
        
        statsPanel.add(memberStatPanel);
        statsPanel.add(materialStatPanel);
        statsPanel.add(taskStatPanel);

        contentPanel.add(lblRoomId);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(statsPanel);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setOpaque(false);
        footerPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        JButton btnOpenRoom = new JButton("Open Room");
        btnOpenRoom.setFont(new Font("Sans UI", Font.BOLD, 12));
        btnOpenRoom.setFocusPainted(false);
        btnOpenRoom.setBackground(new Color(14, 59, 118));
        btnOpenRoom.setForeground(Color.WHITE);
        btnOpenRoom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOpenRoom.setBorder(new EmptyBorder(8, 15, 8, 15));

        btnOpenRoom.addActionListener(e -> {
            new RoomDetailFrame(authService, room, currentUser).setVisible(true);
            this.dispose();
        });

        footerPanel.add(btnOpenRoom);
        
        card.add(headerPanel, BorderLayout.NORTH);
        card.add(contentPanel, BorderLayout.CENTER);
        card.add(footerPanel, BorderLayout.SOUTH);

        return card;
    }
    
    private boolean isRoomAlreadyDisplayed(String roomId) {
        for (Component comp : roomCardPanel.getComponents()) {
            if (comp instanceof JPanel panel) {
                for (Component innerComp : panel.getComponents()) {
                    if (innerComp instanceof JPanel contentPanel) {
                        for (Component labelComp : contentPanel.getComponents()) {
                            if (labelComp instanceof JLabel label) {
                                String text = label.getText();
                                if (text != null && text.contains(roomId)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        btnRooms = new javax.swing.JButton();
        btnChats = new javax.swing.JButton();
        btnFriends = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        headerPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roomCardPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        btnJoin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        menuPanel.setBackground(new java.awt.Color(169, 164, 255));
        menuPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        btnRooms.setBackground(new java.awt.Color(102, 102, 255));
        btnRooms.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnRooms.setForeground(new java.awt.Color(255, 255, 255));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(18, 18, 18))
        );

        headerPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel17.setText("Rooms");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setText("My Rooms");

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Manage your enrolled classes and create new ones");

        roomCardPanel.setBackground(new java.awt.Color(244, 242, 255));
        roomCardPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        roomCardPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roomCardPanel.setPreferredSize(new java.awt.Dimension(225, 250));

        jPanel5.setBackground(new java.awt.Color(244, 242, 255));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setPreferredSize(new java.awt.Dimension(225, 345));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Add new class");

        jLabel8.setText("Create a new class or join an existing one");

        btnCreate.setBackground(new java.awt.Color(14, 59, 118));
        btnCreate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Create");
        btnCreate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnJoin.setBackground(new java.awt.Color(244, 242, 255));
        btnJoin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnJoin.setForeground(new java.awt.Color(14, 59, 118));
        btnJoin.setText("Join");
        btnJoin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJoinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnJoin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel7))
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnJoin))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(558, Short.MAX_VALUE))
                    .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
                            .addComponent(roomCardPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roomCardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        new Dashboard(authService, currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomsActionPerformed
        loadRooms();
    }//GEN-LAST:event_btnRoomsActionPerformed

    private void btnChatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatsActionPerformed
        new ChatFrame(authService, currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnChatsActionPerformed

    private void btnFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFriendsActionPerformed
        new FriendsFrame(authService, currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnFriendsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
        new Login(authService).setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        String roomMemberId = java.util.UUID.randomUUID().toString();
        String roomName = JOptionPane.showInputDialog(this, "Enter Room Name:");
        if (roomName != null && !roomName.isBlank()) {
            String roomId = RoomIdGenerator.generateRoomId();
            Room newRoom = new Room(
                    roomId, roomName, "", currentUser.getUserId(), new Timestamp(System.currentTimeMillis()), 1, 0, 0);
            RoomMember creatorAsMember = new RoomMember(roomMemberId, currentUserId, roomId, "Creator");
            authService.createRoom(newRoom);
            authService.addRoomMember(creatorAsMember);
            Room fullRoom = authService.getRoomById(roomId);
            addRoomCard(fullRoom);
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnJoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinActionPerformed
        String roomId = JOptionPane.showInputDialog(this, "Enter Room ID:");
        if (roomId != null && !roomId.isBlank()) {
            Room room = authService.getRoomById(roomId);
            if (room != null) {
                if (isRoomAlreadyDisplayed(roomId)) {
                    JOptionPane.showMessageDialog(this, "You have already joined this room.");
                    return;
                }

                authService.joinRoom(currentUser.getUserId(), roomId);
                addRoomCard(room);
            } else {
                JOptionPane.showMessageDialog(this, "Room not found!");
            }
        }
    }//GEN-LAST:event_btnJoinActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChats;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnFriends;
    private javax.swing.JButton btnJoin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRooms;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel roomCardPanel;
    // End of variables declaration//GEN-END:variables
}
