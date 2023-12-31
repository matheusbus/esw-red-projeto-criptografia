/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package red.project.view;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import red.project.algorithms.Algorithm;
import red.project.model.UserEncryptMode;

/**
 *
 * @author Matheus
 */
public final class LoginForm extends BaseForm {

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        FlatArcDarkIJTheme.setup();
        initComponents();
        organizaLayout();
    }
    
    public String getUsernameInput() {
        return this.txtUsername.getText();
    }
    
    public String getPasswordInput() {
        return this.txtPassword.getText();
    }
    
    public void clearUsernameInput() {
        this.txtUsername.setText(null);
    }
    
    public void clearPasswordInput() {
        this.txtPassword.setText(null);
    }
    
    public void clearAllInputs() {
        clearUsernameInput();
        clearPasswordInput();
    }
    
    public void addLoginButtonAction(ActionListener action) {
        btnLogin.addActionListener(action);
    }
    
    public void addRegisterLabelClickAction(MouseAdapter event) {
        lblRegister.addMouseListener(event);
    }
    
    public void addManagementButtonAction(ActionListener action) {
        btnManagement.addActionListener(action);
    }
    
    public void addUernameInputFocusLost(FocusListener event) {
        lblCredentialCipher.addFocusListener(event);
    }
    
    public void setUserEncryptModes(List<Algorithm> modesUsername, List<Algorithm> modesPassword) {
        DefaultComboBoxModel defaultUsernameComboBoxModel = new DefaultComboBoxModel(modesUsername.toArray());
        cbUsernameEncMode.setModel(defaultUsernameComboBoxModel);
        DefaultComboBoxModel defaultPasswordComboBoxModel = new DefaultComboBoxModel(modesPassword.toArray());
        cbPasswordEncMode.setModel(defaultPasswordComboBoxModel);
    }
    
    public Algorithm getUsernameEncModeSelectedItem() {
        return cbUsernameEncMode.getItemAt(cbUsernameEncMode.getSelectedIndex());
    }
    
    public Algorithm getPasswordEncModeSelectedItem() {
        return cbPasswordEncMode.getItemAt(cbPasswordEncMode.getSelectedIndex());
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblCredentialCipher = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblRegister = new javax.swing.JLabel();
        btnManagement = new javax.swing.JButton();
        cbPasswordEncMode = new javax.swing.JComboBox<>();
        lblPasswordEncMode = new javax.swing.JLabel();
        cbUsernameEncMode = new javax.swing.JComboBox<>();
        lblUsernameEncMode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CredentialCipher [Login]");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));

        lblUsername.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblUsername.setText("Username:");

        lblPassword.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPassword.setText("Password:");

        lblCredentialCipher.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblCredentialCipher.setForeground(new java.awt.Color(51, 153, 255));
        lblCredentialCipher.setText("CredentialCipher");

        btnLogin.setBackground(new java.awt.Color(0, 153, 255));
        btnLogin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLogin.setText("LOGIN");
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblRegister.setBackground(new java.awt.Color(51, 153, 255));
        lblRegister.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblRegister.setForeground(new java.awt.Color(51, 153, 255));
        lblRegister.setText("Criar usuário");
        lblRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnManagement.setText("G");
        btnManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagementActionPerformed(evt);
            }
        });

        lblPasswordEncMode.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPasswordEncMode.setText("Password Encryption mode:");

        lblUsernameEncMode.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblUsernameEncMode.setText("Username Encryption mode:");

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(lblCredentialCipher))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsername)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(btnManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPassword)
                                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbPasswordEncMode, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPasswordEncMode)))))
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(lblRegister))
                            .addComponent(cbUsernameEncMode, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsernameEncMode))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblCredentialCipher)
                .addGap(18, 18, 18)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(lblUsernameEncMode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbUsernameEncMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lblPasswordEncMode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPasswordEncMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnManagement)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRegister)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManagementActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnManagement;
    private javax.swing.JComboBox<Algorithm> cbPasswordEncMode;
    private javax.swing.JComboBox<Algorithm> cbUsernameEncMode;
    private javax.swing.JLabel lblCredentialCipher;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordEncMode;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsernameEncMode;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    @Override
    public void organizaLayout() {
        this.setContentPane(pnlPrincipal);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
