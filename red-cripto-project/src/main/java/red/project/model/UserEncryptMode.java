/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.model;

import red.project.algorithms.Algorithm;
import red.project.model.dao.UserDao;

/**
 *
 * @author matheus
 */
public class UserEncryptMode {
    
    private Algorithm usernameEncryptMode;
    
    private Algorithm passwordEncryptMode;
    
    public static UserEncryptMode getInstance(Algorithm usernameEncryptMode, Algorithm passwordEncryptMode) {
        UserEncryptMode userEncryptMode = new UserEncryptMode();
        userEncryptMode.setUsernameEncryptMode(usernameEncryptMode);
        userEncryptMode.setPasswordEncryptMode(passwordEncryptMode);
        
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
    
    public User encryptUser(User user) throws Exception {
        
        user.setUsername((String) usernameEncryptMode
                .encrypt(user.getUsername())
                .get("value"));
        user.setPassword((String) passwordEncryptMode
                .encrypt(user.getPassword())
                .get("value"));
        
        return user;
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
