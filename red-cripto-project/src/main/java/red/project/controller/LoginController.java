/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import red.project.algorithms.AESCBC;
import red.project.algorithms.Algorithm;
import red.project.algorithms.MD5;
import red.project.algorithms.SHA256;
import red.project.model.User;
import red.project.model.UserEncryptMode;
import red.project.model.dao.DaoBuilder;
import red.project.model.dao.UserDao;
import red.project.view.LoginForm;

/**
 *
 * @author Matheus
 */
public final class LoginController extends BaseController {
    
    private static final List<Algorithm> modesUsername = new ArrayList<>();
    private static final List<Algorithm> modesPassword = new ArrayList<>();
    private LoginForm form;
    
    static {
        modesUsername.add(MD5.getInstance());
        modesUsername.add(SHA256.getInstance());
        
        modesPassword.add(MD5.getInstance());
        modesPassword.add(SHA256.getInstance());
        modesPassword.add(AESCBC.getInstance());
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
        this.form.addLoginButtonAction((a) -> {
            loginAction();
        });
        this.form.setUserEncryptModes(modesUsername, modesPassword);
    }
    
    public void loginAction() {
        try {
            UserEncryptMode encryptMode = getSelectedUserEncyptMode();
            User user = new User(getUsernameInput(), getPasswordInput());
            
            HashMap<String, Object> paramsUsername = new HashMap<>();
            paramsUsername.put("value", user.getUsername());
            HashMap<String, Object> paramsPassword = new HashMap<>();
            paramsPassword.put("value", user.getPassword());
            
            
            UserDao dao = DaoBuilder.build(encryptMode);
            
            User userSearched = dao.findFileByUser(paramsUsername, paramsPassword);
            if(userSearched == null) {
                showMessage("Usuário ou senha inválidos.", "Aviso");
            } else {
                boolean usernameMatch;
                boolean passwordMatch;
                
                
                if(encryptMode.getPasswordEncryptMode() instanceof AESCBC) {
                    usernameMatch = encryptMode
                            .getUsernameEncryptMode()
                            .encrypt(paramsUsername)
                            .get("value")
                            .toString()
                            .equals(userSearched.getUsername());
                    
                    passwordMatch = paramsPassword
                            .get("value")
                            .toString()
                            .equals(userSearched.getPassword());
                } else {
                    usernameMatch = encryptMode
                            .getUsernameEncryptMode()
                            .encrypt(paramsUsername)
                            .get("value")
                            .toString()
                            .equals(userSearched.getUsername());
                    
                    passwordMatch = encryptMode
                            .getPasswordEncryptMode()
                            .encrypt(paramsPassword)
                            .get("value")
                            .toString()
                            .equals(userSearched.getPassword());
                }
                if(usernameMatch && passwordMatch) {
                    auth();
                } else {
                    showMessage("Usuário ou senha inválidos.", "Aviso");
                }
                
            }
//            UserEncryptMode userEncryptMode = getSelectedUserEncyptMode();
//            User userToSearch = new User(getUsernameInput(), getPasswordInput());
//            userToSearch = userEncryptMode.encryptUser(userToSearch);

//            UserDao dao = DaoBuilder.build(userEncryptMode);
//            HashMap<String,Object> values = new HashMap<>();
//            values.put("user", userToSearch);
//            User userSearched = dao.findFileByUser(values);
            
//            if(userSearched == null) {
//                showMessage("Usuário ou senha inválidos.", "Aviso");
//            } else {
//                if((userToSearch.getUsername().equals(userSearched.getUsername()))
//                       && (userToSearch.getPassword().equals(userSearched.getPassword()))) {
//                    auth();
//                }
//            }
        }
        catch (Exception ex) {
            showMessage("Erro: " + ex.getMessage(), "Erro");
        }
        
    }
    
    public void auth() {
        showMessage("Usuário autenticado com sucesso!", "Sucesso");
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
    
    public String getUsernameInput() {
        return this.form.getUsernameInput();
    }
    
    public String getPasswordInput() {
        return this.form.getPasswordInput();
    }
    
    public void clearInputs() {
        this.form.clearAllInputs();
    }
    
    public UserEncryptMode getSelectedUserEncyptMode() throws Exception {
        UserEncryptMode uem = new UserEncryptMode();
        uem.setUsernameEncryptMode(form.getUsernameEncModeSelectedItem());
        uem.setPasswordEncryptMode(form.getPasswordEncModeSelectedItem());
        return UserEncryptMode.getInstance(
                form.getUsernameEncModeSelectedItem(), 
                form.getPasswordEncModeSelectedItem(),
                DaoBuilder.build(uem));
    }
}
