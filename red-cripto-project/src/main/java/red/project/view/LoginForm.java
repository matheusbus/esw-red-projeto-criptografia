/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package red.project.view;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
        lblUsername.addFocusListener(event);
    }
    
    public void setUserEncryptModes(List<UserEncryptMode> modes) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(modes.toArray());
        cbEncMode.setModel(defaultComboBoxModel);
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
        lUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblRegister = new javax.swing.JLabel();
        btnManagement = new javax.swing.JButton();
        cbEncMode = new javax.swing.JComboBox<>();
        lblPassword1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CredentialCipher [Login]");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));

        lUsername.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lUsername.setText("Username:");

        lblPassword.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPassword.setText("Password:");

        lblUsername.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(51, 153, 255));
        lblUsername.setText("CredentialCipher");

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

        lblPassword1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPassword1.setText("Encryption mode:");

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(lblUsername))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(lUsername))
                                .addComponent(lblPassword)
                                .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbEncMode, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPassword1))))))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(lblRegister)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblUsername)
                .addGap(18, 18, 18)
                .addComponent(lUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lblPassword1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEncMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnManagement)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRegister)
                .addContainerGap(20, Short.MAX_VALUE))
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
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagementActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManagementActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        FlatArcDarkIJTheme.setup();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnManagement;
    private javax.swing.JComboBox<UserEncryptMode> cbEncMode;
    private javax.swing.JLabel lUsername;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPassword1;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JLabel lblUsername;
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
