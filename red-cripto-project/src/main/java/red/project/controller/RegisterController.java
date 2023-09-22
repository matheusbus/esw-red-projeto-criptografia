/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.project.algorithms.AESCBC;
import red.project.algorithms.MD5;
import red.project.algorithms.SHA256;
import red.project.model.User;
import red.project.model.UserEncryptMode;
import red.project.view.RegisterForm;

/**
 *
 * @author Matheus
 */
public final class RegisterController extends BaseController {
    
    private static final List<UserEncryptMode> modes = new ArrayList<>();
    private RegisterForm form;

    static {
        modes.add(UserEncryptMode
                .getInstance(SHA256.getInstance(), AESCBC.getInstance(), "user-sha256_password-aescbc"));
        modes.add(UserEncryptMode
                .getInstance(MD5.getInstance(), MD5.getInstance(), "user-md5_passwrod-md5"));
    }
    
    public RegisterController() {
        this.form = new RegisterForm();
        initButtons();
        this.form.showWindow();
    }
    
    
    public User registerUser() {
        
        UserEncryptMode encryptMode = getSelectedUserEncyptMode();
        
        
        User user = new User(getUsernameInput(), getPasswordInput());
        System.out.println(user.toString());
        
        try {
            user = encryptMode.encryptUser(user);
        } catch (Exception ex) {
            showMessage(ex.getMessage(), "Erro");
        }
        
        System.out.println(user.toString());
        clearInputs();
        return user;
    }
    
    public LoginController returnLoginForm() {
        this.closeWindow();
        return new LoginController();
    }

    @Override
    public void initButtons() {
        this.form.addRegisterButtonAction((a) -> {
            registerUser();
        });
        this.form.addCancelButtonClickAction((a) -> {
            returnLoginForm();
        });
        this.form.setUserEncryptModes(modes);
        
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
    
    public String getUsernameInput() {
        return this.form.getUsernameInput();
    }
    
    public String getPasswordInput() {
        return this.form.getPasswordInput();
    }
    
    public void clearInputs() {
        this.form.clearAllInputs();
    }
    
    public UserEncryptMode getSelectedUserEncyptMode() {
        return this.form.getSelectedItem();
    }
    
}
