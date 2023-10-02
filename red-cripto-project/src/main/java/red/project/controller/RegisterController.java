/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.project.algorithms.Algorithm;
import red.project.algorithms.MD5;
import red.project.algorithms.SHA256;
import red.project.model.User;
import red.project.model.UserEncryptMode;
import red.project.model.dao.DaoBuilder;
import red.project.model.dao.UserDao;
import red.project.view.RegisterForm;

/**
 *
 * @author Matheus
 */
public final class RegisterController extends BaseController {
    
    private static final List<Algorithm> modes = new ArrayList<>();
    private RegisterForm form;

    static {
        modes.add(MD5.getInstance());
        modes.add(SHA256.getInstance());
    }
    
    public RegisterController() {
        this.form = new RegisterForm();
        initButtons();
        this.form.showWindow();
    }
    
    
    public User registerUser() {
        
        if(validateFields()) {
            UserEncryptMode encryptMode = getSelectedUserEncyptMode();
            User user = new User(getUsernameInput(), getPasswordInput());

            try {
                UserDao dao = DaoBuilder.build(encryptMode);
                User encryptedUser = encryptMode.encryptUser(user);

                HashMap<String,Object> values = new HashMap<>();
                values.put("user", encryptedUser);
                
                dao.saveUserToFile(values);
                
                clearInputs();
                return user;    
            } catch (Exception ex) {
                showMessage(ex.getMessage(), "Erro");
            }
            
            returnLoginForm();
            
            return user;
        } else {
            showMessage("Preencha todos os campos!", "Erro");
            return null;
        }
        
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
    
    public boolean validateFields() {
        return !(getUsernameInput().isBlank() || getPasswordInput().isBlank());
    }
    
    public UserEncryptMode getSelectedUserEncyptMode() {
        return UserEncryptMode.getInstance(
                form.getUsernameEncModeSelectedItem(), 
                form.getPasswordEncModeSelectedItem());
    }
    
    
    
}
