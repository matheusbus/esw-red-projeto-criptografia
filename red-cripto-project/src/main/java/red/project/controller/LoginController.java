/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.controller;

import java.awt.event.MouseAdapter;
import red.project.view.LoginForm;

/**
 *
 * @author Matheus
 */
public final class LoginController extends BaseController {
    
    private final LoginForm loginForm;
    
    public LoginController() {
        this.loginForm = new LoginForm();
        initButtons();
        showWindow();
    }
    
    public void initButtons() {
        loginForm.addLoginButtonAction((ae) -> {
            loginAction();
        });
        
        loginForm.addRegisterLabelClickAction(new MouseAdapter() {
            public void mouseClicked() {
                registerAction();
            }
        });
        loginForm.addManagementButtonAction((ae) -> {
            managementAction();
        });
    }
    
    public void loginAction() {
        showMessage("Working", "Working");
    }
    
    public void registerAction() {
        
    }
    
    public void managementAction() {
        
    }

    @Override
    public void showWindow() {
        this.loginForm.showWindow();
    }

    @Override
    public void showMessage(String message, String title) {
        this.loginForm.showMessage(message, title);
    }

    @Override
    public void closeWindow() {
        this.loginForm.closeWindow();
    }
    
}
