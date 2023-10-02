/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.model.dao;

import java.io.IOException;
import java.util.HashMap;
import red.project.model.User;
import red.project.model.UserEncryptMode;

/**
 *
 * @author matheus
 */
public class UserDao_SHA256_AESCBC extends UserDao {

    public UserDao_SHA256_AESCBC(UserEncryptMode userEncryptMode) throws IOException {
        super("user-sha256_password-aescbc", userEncryptMode);
    }

    @Override
    public String saveUserToFile(HashMap<String,Object> params) throws Exception {
        return null;
    }

    @Override
    public User findFileByUser(HashMap<String,Object> params) throws Exception {
        return null;
    }
    
    public String findUserIVFile() {
        return null;
    }
    
    public String findUserCredentialsFile() {
        return null;
    }
}
