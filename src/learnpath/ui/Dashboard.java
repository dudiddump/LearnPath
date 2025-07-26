package learnpath.ui;

import learnpath.service.AuthService;
import learnpath.model.Material;
import learnpath.model.Schedule;
import learnpath.model.Task;
import learnpath.model.User;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import javax.swing.event.TableModelEvent;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Dashboard extends javax.swing.JFrame {
    private AuthService authService;
    private User currentUser;
    private String currentUserId;
    
    private DefaultTableModel scheduleTableModel;
    private DefaultTableModel taskTableModel;
    private DefaultTableModel materialTableModel;

    public Dashboard(AuthService authService, User user) {
        this.authService = authService;
        this.currentUser = user;
        this.currentUserId = user.getUserId();
        initComponents();
        
        setupUI();
        loadAllData();
    }
    
    private void setupUI(){
        setLocationRelativeTo(null);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        String dateStr = today.format(formatter);
    
        setTitle("Dashboard - LearnPath");
        lblWelcome.setText("Good Morning, "+ currentUser.getUsername());
        lblDate.setText("Today is "+ dateStr);
        
        scheduleTableModel = (DefaultTableModel) tableSchedule.getModel();
        materialTableModel = (DefaultTableModel) tableMaterials.getModel();
        addMaterialTableListener();
        
        taskTableModel = new DefaultTableModel(new Object[]{"ID", "Task", "Room", "Check", "Deadline"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 3) {
                return Boolean.class;
            }
            return String.class;
        }
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };
        tableTask.setModel (taskTableModel);
        
        tableTask.getColumnModel().getColumn(0).setMaxWidth(0);
        tableTask.getColumnModel().getColumn(0).setMinWidth(0);
        tableTask.getColumnModel().getColumn(0).setPreferredWidth(0);

        taskTableModel.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 3) {
                    String taskId = (String) taskTableModel.getValueAt(row, 0);
                    boolean isCompleted = (boolean) taskTableModel.getValueAt(row, 3);

                    Task taskToUpdate = authService.getTaskById(taskId);
                    if (taskToUpdate != null) {
                        taskToUpdate.setIsCompleted(isCompleted);
                        authService.updateTask(taskToUpdate);
                    }
                }
            }
        });
    }
    
    private void loadAllData(){
        loadScheduleData();
        loadTaskData();
        loadMaterialData();        
    }
    
    private void loadScheduleData() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        
        scheduleTableModel.setRowCount(0);
        List<Schedule> schedules = authService.getUserSchedules(currentUser.getUserId());
        
        for (Schedule s : schedules){
            String formattedTime = timeFormatter.format(s.getTime()); 
            scheduleTableModel.addRow(new Object[]{ 
                s.getDay(), 
                s.getSubject(), 
                formattedTime 
            });
        }
    }

    private void loadTaskData() {
        taskTableModel.setRowCount(0);
        List<Task> tasks = authService.getUserTasks(currentUser.getUserId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        
        for (Task t : tasks) { 
            taskTableModel.addRow(new Object[]{ 
                t.getTaskId(),
                t.getTitle(), 
                t.getRoomName(), 
                t.isIsCompleted(),
                sdf.format(t.getDeadline())
            });
        }
    }

    private void loadMaterialData() {
        materialTableModel.setRowCount(0);
        List<Material> materials = authService.getUserMaterials(currentUser.getUserId());
        for (Material m : materials) {
            materialTableModel.addRow(new Object[]{
                m.getFileName(),
                m.getFileLink()
            });
        }
    }
    
    private void addMaterialTableListener() {
        tableMaterials.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMaterials.rowAtPoint(e.getPoint());
                int col = tableMaterials.columnAtPoint(e.getPoint());
                if (col == 0) {
                    String filePath = (String) tableMaterials.getValueAt(row, 1);
                    if (filePath != null && !filePath.isBlank()) {
                        try {
                            File file = new File(filePath);
                            if (file.exists()) {
                                Desktop.getDesktop().open(file);
                            } else {
                                JOptionPane.showMessageDialog(Dashboard.this,
                                    "File not found at: " + filePath,
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(
                                Dashboard.this,
                                "Failed to open file: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }   
    
    private void hideIdColumns() {
        tableSchedule.getColumnModel().getColumn(0).setMaxWidth(0);
        tableSchedule.getColumnModel().getColumn(0).setMinWidth(0);
        tableSchedule.getColumnModel().getColumn(0).setPreferredWidth(0);

        tableTask.getColumnModel().getColumn(0).setMaxWidth(0);
        tableTask.getColumnModel().getColumn(0).setMinWidth(0);
        tableTask.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        tableMaterials.getColumnModel().getColumn(0).setMaxWidth(0);
        tableMaterials.getColumnModel().getColumn(0).setMinWidth(0);
        tableMaterials.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDate = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        btnRooms = new javax.swing.JButton();
        btnChats = new javax.swing.JButton();
        btnFriends = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSchedule = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTask = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableMaterials = new javax.swing.JTable();
        btnProfile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDate.setText("Today is");

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel17.setText("Dashboard");

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

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblWelcome.setText("Good Morning");

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

        jPanel1.setBackground(new java.awt.Color(244, 242, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Berlin Sans FB", 0, 16)); // NOI18N
        jLabel11.setText("Schedule");

        tableSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Days", "Schedule", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableSchedule);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(165, 165, 165))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(244, 242, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Berlin Sans FB", 0, 16)); // NOI18N
        jLabel12.setText("Tasks");

        tableTask.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Task", "Room", "Check", "Deadline"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableTask);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(194, 194, 194))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(244, 242, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 16)); // NOI18N
        jLabel2.setText("Materials");

        tableMaterials.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "File Name", "File Path"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableMaterials);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnProfile.setBackground(new java.awt.Color(14, 59, 118));
        btnProfile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProfile.setForeground(new java.awt.Color(255, 255, 255));
        btnProfile.setText("Profile");
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWelcome)
                    .addComponent(btnProfile))
                .addGap(0, 0, 0)
                .addComponent(lblDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        new Login(authService).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        loadAllData();
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

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        new Profile(this.currentUser).setVisible(true);
    }//GEN-LAST:event_btnProfileActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChats;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnFriends;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnRooms;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JTable tableMaterials;
    private javax.swing.JTable tableSchedule;
    private javax.swing.JTable tableTask;
    // End of variables declaration//GEN-END:variables
}
