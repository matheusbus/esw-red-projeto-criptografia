/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import red.project.algorithms.AESCBC;
import red.project.algorithms.SHA256;
import red.project.model.UserEncryptMode;
import red.project.view.LoginForm;

/**
 *
 * @author Matheus
 */
public final class LoginController extends BaseController {
    
    private static final List<UserEncryptMode> modes = new ArrayList<>();
    private LoginForm form;
    
    static {
        modes.add(UserEncryptMode
                .getInstance(SHA256.getInstance(), AESCBC.getInstance(), "user-sha256_password-aescbc"));
    }
    
    public LoginController() {
        this.form = new LoginForm();
        initButtons();
        this.form.showWindow();
    }
    
    @Override
    public void initButtons() {
        this.form.addRegisterLabelClickAction(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                registerAction();
            }
        });
        this.form.setUserEncryptModes(modes);
    }
    
    public void loginAction() {
        showMessage("Working", "Working");
    }
    
    public void registerAction() {
        RegisterController registerController = new RegisterController();
        registerController.showWindow();
        this.closeWindow();
    }
    
    public void managementAction() {
        
    }

    @Override
    public void showWindow() {
        this.form.showWindow();
    }

    @Override
    public void showMessage(String message, String title) {
        this.form.showMessage(message, title);
    }

    @Override
    public void closeWindow() {
        this.form.closeWindow();
    }
    
}
