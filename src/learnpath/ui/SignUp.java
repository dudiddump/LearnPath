package learnpath.ui;

import learnpath.ui.Login;

import learnpath.service.AuthService;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;


public class SignUp extends javax.swing.JFrame {
    private AuthService authService;
    private char defaultEchoChar;
    
    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnCreateAcc = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        chkShowPassword1 = new javax.swing.JCheckBox();
        chkShowPassword = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtConfirmPassword = new javax.swing.JPasswordField();

        jPasswordField1.setText("jPasswordField1");

        jPasswordField2.setText("jPasswordField2");

        jPasswordField3.setText("jPasswordField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(14, 59, 118));
        jLabel1.setText("SignUp");

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

        txtEmail.setBackground(new java.awt.Color(244, 242, 255));
        txtEmail.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(14, 59, 118));
        txtEmail.setCaretColor(new java.awt.Color(14, 59, 118));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnCreateAcc.setBackground(new java.awt.Color(14, 59, 118));
        btnCreateAcc.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnCreateAcc.setForeground(new java.awt.Color(244, 242, 255));
        btnCreateAcc.setText("Create Account");
        btnCreateAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAccActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(244, 242, 255));
        btnLogin.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(14, 59, 118));
        btnLogin.setText("Have Account");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        chkShowPassword1.setText("Show");
        chkShowPassword1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowPassword1ActionPerformed(evt);
            }
        });

        chkShowPassword.setText("Show");
        chkShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowPasswordActionPerformed(evt);
            }
        });

        jLabel2.setText("Email");

        jLabel3.setText("Username");

        jLabel4.setText("Password");

        jLabel5.setText("Confirm Pass");

        txtPassword.setBackground(new java.awt.Color(244, 242, 255));
        txtPassword.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(14, 59, 118));

        txtConfirmPassword.setBackground(new java.awt.Color(244, 242, 255));
        txtConfirmPassword.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        txtConfirmPassword.setForeground(new java.awt.Color(14, 59, 118));
        txtConfirmPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(180, 180, 180))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCreateAcc)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkShowPassword1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(35, 35, 35)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chkShowPassword)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(txtPassword)
                                    .addComponent(txtConfirmPassword))))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkShowPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtConfirmPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkShowPassword1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateAcc)
                    .addComponent(btnLogin))
                .addGap(30, 30, 30))
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        this.dispose();
        new Login(authService).setVisible(true);
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnCreateAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAccActionPerformed
        try {
            String username = txtUsername.getText().trim();
            String email = txtEmail.getText().trim();
            String password = new String(txtPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!email.endsWith("@gmail.com")) {
                JOptionPane.showMessageDialog(this, "Email must be a @gmail.com address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$")) {
                JOptionPane.showMessageDialog(this,
                        "Password must be at least 8 characters and include letters, numbers, and symbols.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = authService.signUp(username, email, password);
            if (success) {
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                this.dispose();
                new Login(authService).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create account. Try another username or email.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error creating account: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateAccActionPerformed

    private void chkShowPassword1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowPassword1ActionPerformed
        
    }//GEN-LAST:event_chkShowPassword1ActionPerformed

    private void chkShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowPasswordActionPerformed
        
    }//GEN-LAST:event_chkShowPasswordActionPerformed

    private void txtConfirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmPasswordActionPerformed
    
    public SignUp(AuthService authService) {
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

        chkShowPassword1.addActionListener((java.awt.event.ActionEvent evt) -> {
            if (chkShowPassword1.isSelected()) {
                txtConfirmPassword.setEchoChar((char) 0);
            } else {
                txtConfirmPassword.setEchoChar(defaultEchoChar);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateAcc;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox chkShowPassword;
    private javax.swing.JCheckBox chkShowPassword1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
