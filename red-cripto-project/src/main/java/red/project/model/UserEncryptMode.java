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
    
    private UserDao userDao;
    
    private String dirName;
    
    public static UserEncryptMode getInstance(Algorithm usernameEncryptMode, Algorithm passwordEncryptMode, String dirName) {
        UserEncryptMode userEncryptMode = new UserEncryptMode();
        userEncryptMode.setUsernameEncryptMode(usernameEncryptMode);
        userEncryptMode.setPasswordEncryptMode(passwordEncryptMode);
        userEncryptMode.setDirName(dirName);
        
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

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public User encryptUser(User user) throws Exception {
        user.setUsername(usernameEncryptMode.encrypt(user.getUsername()));
        user.setPassword(passwordEncryptMode.encrypt(user.getPassword()));
        return user;
    }
    
    @Override
    public String toString() {
        return "USER = " + usernameEncryptMode.getClass().getSimpleName() + " | PASSWORD = " + passwordEncryptMode.getClass().getSimpleName();
    }
}
