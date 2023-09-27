/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
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
    
    private static final List<Algorithm> modes = new ArrayList<>();
    private LoginForm form;
    
    static {
        modes.add(MD5.getInstance());
        modes.add(SHA256.getInstance());
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
        this.form.setUserEncryptModes(modes);
    }
    
    public void loginAction() {
        UserEncryptMode userEncryptMode = getSelectedUserEncyptMode();
        User userToSearch = new User(getUsernameInput(), getPasswordInput());
        
        try {
            userToSearch = userEncryptMode.encryptUser(userToSearch);

            UserDao dao = DaoBuilder.build(userEncryptMode);
            User userSearched = dao.findFileByUser(userToSearch);
            
            if(userSearched == null) {
                showMessage("Usuário ou senha inválidos.", "Aviso");
            } else {
                if((userToSearch.getUsername().equals(userSearched.getUsername()))
                        && (userToSearch.getPassword().equals(userSearched.getPassword()))) {
                    autentica();
                }
            }
        }
        catch (Exception ex) {
            showMessage("Erro: " + ex.getMessage(), "Erro");
        }
        
    }
    
    public void autentica() {
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
    
    public UserEncryptMode getSelectedUserEncyptMode() {
        return UserEncryptMode.getInstance(
                form.getUsernameEncModeSelectedItem(), 
                form.getPasswordEncModeSelectedItem());
    }
}
