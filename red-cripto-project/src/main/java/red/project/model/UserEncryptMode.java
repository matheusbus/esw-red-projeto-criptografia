/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.model;

import java.util.HashMap;
import red.project.algorithms.Algorithm;
import red.project.model.dao.UserDao;

/**
 *
 * @author matheus
 */
public class UserEncryptMode {
    
    private Algorithm usernameEncryptMode;
    
    private Algorithm passwordEncryptMode;
    
    private UserDao userDao;
    
    public static UserEncryptMode getInstance(Algorithm usernameEncryptMode, Algorithm passwordEncryptMode, UserDao dao) {
        UserEncryptMode userEncryptMode = new UserEncryptMode();
        userEncryptMode.setUsernameEncryptMode(usernameEncryptMode);
        userEncryptMode.setPasswordEncryptMode(passwordEncryptMode);
        userEncryptMode.setUserDao(dao);
        
        return userEncryptMode;
    }

    public Algorithm getUsernameEncryptMode() {
        return usernameEncryptMode;
    }

    public void setUsernameEncryptMode(Algorithm usernameEncryptMode) {
        this.usernameEncryptMode = usernameEncryptMode;
    }

    public Algorithm getPasswordEncryptMode() {
        return passwordEncryptMode;
    }

    public void setPasswordEncryptMode(Algorithm passwordEncryptMode) {
        this.passwordEncryptMode = passwordEncryptMode;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public void encryptUser(User user) throws Exception {
        HashMap<String,Object> paramsUsername = new HashMap<>();
        HashMap<String,Object> paramsPassword = new HashMap<>();
        paramsUsername.put("value", user.getUsername());
        paramsPassword.put("value", user.getPassword());

        paramsUsername = usernameEncryptMode.encrypt(paramsUsername);
        paramsPassword = passwordEncryptMode.encrypt(paramsPassword);
        
        andThenSave(paramsUsername, paramsPassword);
    }
    
    public void andThenSave(HashMap<String,Object> paramsUsername, HashMap<String,Object> paramsPassword) throws Exception {
        userDao.saveUserToFile(paramsUsername, paramsPassword);
    }
    
    @Override
    public String toString() {
        return "USERNAME = " + usernameEncryptMode
                .getClass()
                .getSimpleName() 
          + " | PASSWORD = " + passwordEncryptMode
                .getClass()
                .getSimpleName();
    }
}
