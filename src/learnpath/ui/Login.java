package learnpath.ui;

import learnpath.service.AuthService;
import learnpath.model.User;
import javax.swing.*;

public class Login extends javax.swing.JFrame {
    private AuthService authService;
    private char defaultEchoChar;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chkShowPassword = new javax.swing.JCheckBox();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chkShowPassword.setText("Show");
        chkShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowPasswordActionPerformed(evt);
            }
        });

        txtPassword.setBackground(new java.awt.Color(244, 242, 255));
        txtPassword.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(14, 59, 118));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(14, 59, 118));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login");

        txtUsername.setBackground(new java.awt.Color(244, 242, 255));
        txtUsername.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(14, 59, 118));
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsername.setCaretColor(new java.awt.Color(14, 59, 118));
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(14, 59, 118));
        btnLogin.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(244, 242, 255));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnSignUp.setBackground(new java.awt.Color(244, 242, 255));
        btnSignUp.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnSignUp.setForeground(new java.awt.Color(14, 59, 118));
        btnSignUp.setText("Create Account");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        jLabel3.setText("Username");

        jLabel4.setText("Password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chkShowPassword)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPassword)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSignUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel1)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkShowPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSignUp)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowPasswordActionPerformed

    }//GEN-LAST:event_chkShowPasswordActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            boolean success = authService.login(username, password);
            if (success) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                User loggedInUser = authService.getByUsername(username);
                this.dispose();
                new Dashboard(authService, loggedInUser).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        this.dispose();
        new SignUp(authService).setVisible(true);
    }//GEN-LAST:event_btnSignUpActionPerformed

    public Login(AuthService authService) {
        this.authService = authService;
        initComponents();
        setLocationRelativeTo(null);
        
        defaultEchoChar = txtPassword.getEchoChar();
        chkShowPassword.addActionListener((java.awt.event.ActionEvent evt) -> {
            if (chkShowPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0);
            } else {
                txtPassword.setEchoChar(defaultEchoChar);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}