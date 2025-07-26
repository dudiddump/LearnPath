package learnpath.ui;

import learnpath.model.*;
import learnpath.service.AuthService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class RoomDetailFrame extends javax.swing.JFrame {
    private AuthService authService;
    private Room currentRoom;
    private User currentUser;
    
    private DefaultTableModel scheduleTableModel;
    private DefaultTableModel taskTableModel;
    private DefaultTableModel materialTableModel;
    private DefaultTableModel memberTableModel;
        
    public RoomDetailFrame(AuthService authService, Room room, User user) {
        this.authService = authService;
        this.currentRoom = room;
        this.currentUser = user;
        
        initComponents();
        
        setupTableModels();
        hideIdColumns();
        loadAllData();
                
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void setupTableModels() {
        scheduleTableModel = new DefaultTableModel(new Object[]{"ID", "Day", "Subject", "Time"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableSchedule.setModel(scheduleTableModel);

        taskTableModel = new DefaultTableModel(new Object[]{"ID", "Task", "Check", "Deadline"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 2 ? Boolean.class : String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableTask.setModel(taskTableModel);

        materialTableModel = new DefaultTableModel(new Object[]{"ID", "File Name", "Detail"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableMaterials.setModel(materialTableModel);

        memberTableModel = new DefaultTableModel(new Object[]{"Username", "Role"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableMembers.setModel(memberTableModel);
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
        
    private void loadAllData() {
        String roomTitle = String.format("%s (ID: %s)", currentRoom.getRoomName(), currentRoom.getRoomId());
        lblRoomName.setText(roomTitle);
        
        loadSchedule();
        loadTasks();
        loadMaterials();
        loadMembers();
    }
    
    private void loadSchedule() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        scheduleTableModel.setRowCount(0);
        List<Schedule> schedules = authService.getSchedulesForRoom(currentRoom.getRoomId());
        for (Schedule s : schedules) {
            scheduleTableModel.addRow(new Object[]{
                s.getScheduleId(),
                s.getDay(),
                s.getSubject(),
                timeFormatter.format(s.getTime())
            });
        }
    }
    
    public void loadTasks() {
        taskTableModel.setRowCount(0);
        List<Task> tasks = authService.getTasksForRoom(currentRoom.getRoomId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Task t : tasks) {
            taskTableModel.addRow(new Object[]{
                t.getTaskId(),
                t.getTitle(),
                t.isIsCompleted(),
                sdf.format(t.getDeadline())
            });
        }
    }
    
    public void loadMaterials() {
        materialTableModel.setRowCount(0);
        List<Material> materials = authService.getMaterialsForRoom(currentRoom.getRoomId());
        for (Material m : materials) {
            materialTableModel.addRow(new Object[]{
                m.getMaterialId(),
                m.getFileName(),
                m.getMaterialDetail()
            });
        }
    }
    
    private void loadMembers() {
        memberTableModel.setRowCount(0);
        List<RoomMember> members = authService.getMembersForRoom(currentRoom.getRoomId());
        for (RoomMember member : members) {
            User memberUser = authService.getUserById(member.getUserId());
            if (memberUser != null) {
                memberTableModel.addRow(new Object[]{
                    memberUser.getUsername(),
                    member.getRole()
                });
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        lblRoomName = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        btnRooms = new javax.swing.JButton();
        btnChats = new javax.swing.JButton();
        btnFriends = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSchedule = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTask = new javax.swing.JTable();
        btnDeleteSchedule = new javax.swing.JButton();
        btnAddTask = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableMaterials = new javax.swing.JTable();
        btnAddMaterial = new javax.swing.JButton();
        btnAddSchedule = new javax.swing.JButton();
        btnEditSchedule = new javax.swing.JButton();
        btnEditTask = new javax.swing.JButton();
        btnDeleteTask = new javax.swing.JButton();
        btnEditMaterial = new javax.swing.JButton();
        btnDeleteMaterial = new javax.swing.JButton();
        txtSchedule = new javax.swing.JLabel();
        txtSchedule1 = new javax.swing.JLabel();
        txtSchedule2 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        memberPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableMembers = new javax.swing.JTable();
        btnEditMember = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblRoomName.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        lblRoomName.setText("RoomName");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRoomName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRoomName)
                .addContainerGap())
        );

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addContainerGap())
        );

        mainPanel.setBackground(new java.awt.Color(244, 242, 255));

        tableSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableSchedule);

        tableTask.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableTask);

        btnDeleteSchedule.setBackground(new java.awt.Color(169, 164, 255));
        btnDeleteSchedule.setText("Delete Schedule");
        btnDeleteSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteScheduleActionPerformed(evt);
            }
        });

        btnAddTask.setBackground(new java.awt.Color(169, 164, 255));
        btnAddTask.setText("Add Task");
        btnAddTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTaskActionPerformed(evt);
            }
        });

        tableMaterials.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tableMaterials);

        btnAddMaterial.setBackground(new java.awt.Color(169, 164, 255));
        btnAddMaterial.setText("Add Material");
        btnAddMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMaterialActionPerformed(evt);
            }
        });

        btnAddSchedule.setBackground(new java.awt.Color(169, 164, 255));
        btnAddSchedule.setText("Add Schedule");
        btnAddSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddScheduleActionPerformed(evt);
            }
        });

        btnEditSchedule.setBackground(new java.awt.Color(169, 164, 255));
        btnEditSchedule.setText("Edit Schedule");
        btnEditSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditScheduleActionPerformed(evt);
            }
        });

        btnEditTask.setBackground(new java.awt.Color(169, 164, 255));
        btnEditTask.setText("Edit Task");
        btnEditTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTaskActionPerformed(evt);
            }
        });

        btnDeleteTask.setBackground(new java.awt.Color(169, 164, 255));
        btnDeleteTask.setText("Delete Task");
        btnDeleteTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTaskActionPerformed(evt);
            }
        });

        btnEditMaterial.setBackground(new java.awt.Color(169, 164, 255));
        btnEditMaterial.setText("Edit Material");
        btnEditMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMaterialActionPerformed(evt);
            }
        });

        btnDeleteMaterial.setBackground(new java.awt.Color(169, 164, 255));
        btnDeleteMaterial.setText("Delete Material");
        btnDeleteMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMaterialActionPerformed(evt);
            }
        });

        txtSchedule.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        txtSchedule.setForeground(new java.awt.Color(14, 59, 118));
        txtSchedule.setText("Task");

        txtSchedule1.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        txtSchedule1.setForeground(new java.awt.Color(14, 59, 118));
        txtSchedule1.setText("Schedule");

        txtSchedule2.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        txtSchedule2.setForeground(new java.awt.Color(14, 59, 118));
        txtSchedule2.setText("Materials");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(btnAddTask, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEditTask, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDeleteTask, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSchedule2)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(btnAddMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEditMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDeleteMaterial))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                    .addComponent(btnAddSchedule)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnEditSchedule)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnDeleteSchedule))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSchedule)
                            .addComponent(txtSchedule1)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtSchedule1)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteSchedule)
                    .addComponent(btnAddSchedule)
                    .addComponent(btnEditSchedule))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSchedule)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddTask)
                    .addComponent(btnEditTask)
                    .addComponent(btnDeleteTask))
                .addGap(11, 11, 11)
                .addComponent(txtSchedule2)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMaterial)
                    .addComponent(btnEditMaterial)
                    .addComponent(btnDeleteMaterial))
                .addContainerGap())
        );

        jScrollPane3.setViewportView(mainPanel);

        btnBack.setBackground(new java.awt.Color(244, 242, 255));
        btnBack.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(14, 59, 118));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        memberPanel.setBackground(new java.awt.Color(169, 164, 255));
        memberPanel.setForeground(new java.awt.Color(244, 242, 255));

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 20)); // NOI18N
        jLabel2.setText("Members");

        jScrollPane5.setBackground(new java.awt.Color(244, 242, 255));

        tableMembers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tableMembers);

        btnEditMember.setBackground(new java.awt.Color(244, 242, 255));
        btnEditMember.setText("Edit Member");
        btnEditMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMemberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout memberPanelLayout = new javax.swing.GroupLayout(memberPanel);
        memberPanel.setLayout(memberPanelLayout);
        memberPanelLayout.setHorizontalGroup(
            memberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberPanelLayout.createSequentialGroup()
                .addGroup(memberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(memberPanelLayout.createSequentialGroup()
                        .addGroup(memberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(memberPanelLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel2))
                            .addGroup(memberPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memberPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEditMember)))
                .addContainerGap())
        );
        memberPanelLayout.setVerticalGroup(
            memberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditMember)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memberPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

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

    }//GEN-LAST:event_btnFriendsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        this.dispose();
        new Login(authService).setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
        new RoomFrame(authService, currentUser).setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnEditTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTaskActionPerformed
        int selectedRow = tableTask.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a task to edit.");
            return;
        }
        String taskId = (String) tableTask.getValueAt(selectedRow, 0);
        Task t = authService.getTaskById(taskId);
        
        if (t == null) {
            JOptionPane.showMessageDialog(this, "Task not found!");
            return;
        }
        JTextField titleField = new JTextField(t.getTitle());
        JCheckBox completedBox = new JCheckBox();
        completedBox.setSelected(t.isIsCompleted());

        String deadlineStr = "";
        if (t.getDeadline() != null) {
            LocalDateTime dt = t.getDeadline().toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            deadlineStr = dt.format(formatter);
        }
        
        JTextField deadlineField = new JTextField(deadlineStr);
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Completed?"));
        panel.add(completedBox);
        panel.add(new JLabel("Deadline (dd/MM/yyyy HH:mm):"));
        panel.add(deadlineField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Edit Task",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText().trim();
                boolean completed = completedBox.isSelected();
                String deadlineText = deadlineField.getText().trim();
                Timestamp deadline = null;
                
                if (!deadlineText.isEmpty()) {
                    String dateTimeRegex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$";
                    if (!deadlineText.matches(dateTimeRegex)) {
                        JOptionPane.showMessageDialog(this,
                                "Invalid deadline format. Please use 'dd/MM/yyyy HH:mm'.\nExample: 01/01/2025 15:00",
                                "Wrong Format", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    try {
                        LocalDateTime dt = LocalDateTime.parse(deadlineText, formatter);
                        deadline = Timestamp.valueOf(dt);
                    } catch (DateTimeParseException e) {
                        JOptionPane.showMessageDialog(this,
                                "The date or time is invalid. Please check your input.",
                                "Invalid Date", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                
                t.setTitle(title);
                t.setIsCompleted(completed);
                t.setDeadline(deadline);
                
                authService.updateTask(t);
                loadTasks();
                JOptionPane.showMessageDialog(this, "Task updated!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to update task: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnEditTaskActionPerformed

    private void btnEditScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditScheduleActionPerformed
        int selectedRow = tableSchedule.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a schedule to edit.");
            return;
        }
        String scheduleId = (String) tableSchedule.getValueAt(selectedRow, 0);
        Schedule s = authService.getScheduleById(scheduleId);
        
        if (s == null) {
            JOptionPane.showMessageDialog(this, "Schedule not found!");
            return;
        }
        
        JTextField fieldDay = new JTextField(s.getDay());
        JTextField fieldSubject = new JTextField(s.getSubject());
        JTextField fieldTime = new JTextField(s.getTime().toLocalTime().toString());

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Day:"));
        panel.add(fieldDay);
        panel.add(new JLabel("Subject:"));
        panel.add(fieldSubject);
        panel.add(new JLabel("Time (HH:mm):"));
        panel.add(fieldTime);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Schedule",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String day = fieldDay.getText().trim();
                String subject = fieldSubject.getText().trim();
                String timeStr = fieldTime.getText().trim();

                java.time.LocalTime time = java.time.LocalTime.parse(timeStr);

                s.setDay(day);
                s.setSubject(subject);
                s.setTime(java.sql.Time.valueOf(time));

                authService.updateSchedule(s);
                loadSchedule();

                JOptionPane.showMessageDialog(this, "Schedule updated!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to update schedule: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnEditScheduleActionPerformed

    private void btnDeleteScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteScheduleActionPerformed
        int selectedRow = tableSchedule.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a schedule to delete.");
            return;
        }
        String scheduleId = (String) tableSchedule.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this schedule?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            authService.deleteSchedule(scheduleId);
            loadSchedule();
            JOptionPane.showMessageDialog(this, "Schedule deleted!");
        }
    }//GEN-LAST:event_btnDeleteScheduleActionPerformed

    private void btnDeleteMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMaterialActionPerformed
        int selectedRow = tableMaterials.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a material to delete.");
            return;
        }
        String materialId = (String) tableMaterials.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this Material?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            authService.deleteMaterial(materialId);
            loadMaterials();
            JOptionPane.showMessageDialog(this, "Material deleted!");
        }
    }//GEN-LAST:event_btnDeleteMaterialActionPerformed

    private void btnDeleteTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTaskActionPerformed
        int selectedRow = tableTask.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Task to delete.");
            return;
        }
        String taskId = (String) tableTask.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this Task?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            authService.deleteTask(taskId);
            loadTasks();
            JOptionPane.showMessageDialog(this, "Task deleted!");
        }
    }//GEN-LAST:event_btnDeleteTaskActionPerformed

    private void btnEditMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMaterialActionPerformed
        int selectedRow = tableMaterials.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a material to edit.");
            return;
        }
        String materialId = (String) tableMaterials.getValueAt(selectedRow, 0);
        Material m = authService.getMaterialById(materialId);
        if (m == null) {
            JOptionPane.showMessageDialog(this, "Material not found!");
            return;
        }

        JTextField fileLinkField = new JTextField(m.getFileLink());
        JTextField materialDetailField = new JTextField(m.getMaterialDetail());
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("File Link:"));
        panel.add(fileLinkField);
        panel.add(new JLabel("Material Detail:"));
        panel.add(materialDetailField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Edit Material",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                String fileLink = fileLinkField.getText().trim();
                String materialDetail = materialDetailField.getText().trim();
                if (fileLink.isEmpty() || materialDetail.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                    return;
                }
                m.setFileLink(fileLink);
                m.setMaterialDetail(materialDetail);
                authService.updateMaterial(m);
                loadMaterials();
                JOptionPane.showMessageDialog(this, "Material updated!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to update material: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnEditMaterialActionPerformed

    private void btnAddScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddScheduleActionPerformed
        JTextField fieldDay = new JTextField();
        JTextField fieldSubject = new JTextField();
        JTextField fieldTime = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Day:"));
        panel.add(fieldDay);
        panel.add(new JLabel("Subject:"));
        panel.add(fieldSubject);
        panel.add(new JLabel("Time (HH:mm):"));
        panel.add(fieldTime);

        int result = JOptionPane.showConfirmDialog(
                this,panel, "Add Schedule",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        if (result == JOptionPane.OK_OPTION) {
            try {
                String day = fieldDay.getText().trim();
                String subject = fieldSubject.getText().trim();
                String timeStr = fieldTime.getText().trim();

                if (day.isEmpty() || subject.isEmpty() || timeStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.");
                    return;
                }
                java.time.LocalTime time = java.time.LocalTime.parse(timeStr);
                
                Schedule s = new Schedule();
                    s.setUserId(currentUser.getUserId());
                    s.setScheduleId(java.util.UUID.randomUUID().toString());
                    s.setRoomId(currentRoom.getRoomId());
                    s.setDay(day);
                    s.setSubject(subject);
                    s.setTime(java.sql.Time.valueOf(time));
                                
                authService.insertSchedule(s);
                loadSchedule();
                JOptionPane.showMessageDialog(this, "Schedule added successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to add schedule: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnAddScheduleActionPerformed

    private void btnAddTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTaskActionPerformed
        JTextField titleField = new JTextField();
        JCheckBox completedBox = new JCheckBox();
        JTextField deadlineField = new JTextField();

        Object[] message = {
            "Title:", titleField,
            "Completed?", completedBox,
            "Deadline (dd/MM/yyyy HH:mm):", deadlineField
        };
        int option = JOptionPane.showConfirmDialog(
            this, message, "Add Task", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            boolean completed = completedBox.isSelected();
            String deadlineStr = deadlineField.getText().trim();
            if (title.isEmpty() || deadlineStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!");
                return;
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime localDateTime = LocalDateTime.parse(deadlineStr, formatter);
                Timestamp deadline = Timestamp.valueOf(localDateTime);
                
                Task t = new Task();
                t.setTaskId(java.util.UUID.randomUUID().toString());
                t.setCreatorUserId(currentUser.getUserId());
                t.setRoomName(currentRoom.getRoomName());
                t.setSubjectId(null);
                t.setTitle(title);
                t.setIsCompleted(completed);
                t.setDeadline(deadline);

                authService.insertTask(t);
                loadTasks();
                JOptionPane.showMessageDialog(this, "Task saved!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid deadline format!");
            }
        }
    }//GEN-LAST:event_btnAddTaskActionPerformed

    private void btnAddMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMaterialActionPerformed
        JTextField fileNameField = new JTextField();
        JTextField fileLinkField = new JTextField();
        JTextField materialDetailField = new JTextField();

        Object[] message = {
            "File Name:", fileNameField,
            "File Link:", fileLinkField,
            "Material Detail:", materialDetailField
        };
        int option = JOptionPane.showConfirmDialog(
            this, message, "Add Material", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String fileName = fileNameField.getText();
            String fileLink = fileLinkField.getText();
            String materialDetail = materialDetailField.getText();
            
            Material m = new Material();
            m.setMaterialId(java.util.UUID.randomUUID().toString());
            m.setFileName(fileName);
            m.setFileLink(fileLink);
            m.setMaterialDetail(materialDetail);
            m.setRoomId(currentRoom.getRoomId());
            m.setUploadedByUserId(currentUser.getUserId());
            m.setUploadTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
            m.setSubjectId(null);
            
            authService.insertMaterial(m);
            loadMaterials();
            JOptionPane.showMessageDialog(this, "Material saved!");
        }
    }//GEN-LAST:event_btnAddMaterialActionPerformed

    private void btnEditMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMemberActionPerformed
        int selectedRow = tableMembers.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a member to edit.");
            return;
        }
        
        String username = (String) tableMembers.getValueAt(selectedRow, 0);
        User selectedUser = authService.getByUsername(username);

        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        RoomMember member = authService.getRoomMemberByUserAndRoom(selectedUser.getUserId(), currentRoom.getRoomId());
        if (member == null) {
            JOptionPane.showMessageDialog(this, "Room membership data not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String[] options = {"Set Role", "Delete Member", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this,
                "What do you want to do with " + username + "?",
                "Edit Member",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        if (choice == 0) {
            String[] roles = {"Admin", "Member", "Tutor"}; 
            JComboBox<String> roleComboBox = new JComboBox<>(roles);
            roleComboBox.setSelectedItem(member.getRole());
            int result = JOptionPane.showConfirmDialog(this, roleComboBox, "Set Role for " + username,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String newRole = (String) roleComboBox.getSelectedItem();
                member.setRole(newRole);
                authService.updateRoomMember(member);
                loadMembers();
            }
        } else if (choice == 1) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove " + username + " from this room?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                authService.deleteRoomMember(member.getRoomMemberId());
                loadMembers();
            }
        }
    }//GEN-LAST:event_btnEditMemberActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMaterial;
    private javax.swing.JButton btnAddSchedule;
    private javax.swing.JButton btnAddTask;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChats;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDeleteMaterial;
    private javax.swing.JButton btnDeleteSchedule;
    private javax.swing.JButton btnDeleteTask;
    private javax.swing.JButton btnEditMaterial;
    private javax.swing.JButton btnEditMember;
    private javax.swing.JButton btnEditSchedule;
    private javax.swing.JButton btnEditTask;
    private javax.swing.JButton btnFriends;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRooms;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblRoomName;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel memberPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JTable tableMaterials;
    private javax.swing.JTable tableMembers;
    private javax.swing.JTable tableSchedule;
    private javax.swing.JTable tableTask;
    private javax.swing.JLabel txtSchedule;
    private javax.swing.JLabel txtSchedule1;
    private javax.swing.JLabel txtSchedule2;
    // End of variables declaration//GEN-END:variables
}
