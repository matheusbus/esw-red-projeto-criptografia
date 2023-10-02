/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import red.project.model.User;
import red.project.model.UserEncryptMode;

/**
 *
 * @author matheus
 */
public class UserDao_SHA256_SHA256 extends UserDao {

    public UserDao_SHA256_SHA256(UserEncryptMode userEncryptMode) throws IOException {
        super("username-sha256_password-sha256", userEncryptMode);
    }

    @Override
    public String saveUserToFile(HashMap<String,Object> params) throws Exception {
        String pathToSaveFile, arqName, fullFilePath;
        
        pathToSaveFile = getPathToSaveFile();
        User user = (User) params.get("user");
        String[] key = {user.getUsername()+user.getPassword()};
        arqName = (String) userEncryptMode.getUsernameEncryptMode().encrypt(key).get("value");
        
        fullFilePath = pathToSaveFile + "/" + arqName + ".txt";
        
        if(!fileExists(fullFilePath)) {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(fullFilePath))) {

                bw.write("Username: " + user.getUsername());
                bw.newLine();
                bw.write("Password: " + user.getPassword());
            }  
        }
        return null;
    }

    @Override
    public User findFileByUser(HashMap<String,Object> params) throws Exception {
        String pathToSaveFile, arqName, fullFilePath;
        
        pathToSaveFile = getPathToSaveFile();
        User user = (User) params.get("user");
        String[] key = {user.getUsername()+user.getPassword()};
        arqName = (String) userEncryptMode.getUsernameEncryptMode().encrypt(key).get("value");
        
        fullFilePath = pathToSaveFile + "/" + arqName + ".txt";
        
        if(fileExists(fullFilePath)) {
            try(BufferedReader bf = new BufferedReader(new FileReader(fullFilePath))) {

                User userSearched = new User();
                userSearched.setUsername(bf.readLine().split(":")[1].trim());
                userSearched.setPassword(bf.readLine().split(":")[1].trim());
                return userSearched;
            }  
        } 
        return null;
    }    
}
