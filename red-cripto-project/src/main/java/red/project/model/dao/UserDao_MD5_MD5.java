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
import red.project.algorithms.MD5;
import red.project.model.User;
import red.project.model.UserEncryptMode;

/**
 *
 * @author matheus
 */
public class UserDao_MD5_MD5 extends UserDao {

    public UserDao_MD5_MD5(UserEncryptMode userEncryptMode) throws IOException {
        super("username-md5_password-md5", userEncryptMode);
    }
    
    @Override
    public String saveUserToFile(User user) throws Exception {
        String pathToSaveFile, arqName, fullFilePath;
        
        pathToSaveFile = getPathToSaveFile();
        String[] key = {user.getUsername()+user.getPassword()};
        arqName = userEncryptMode.getUsernameEncryptMode().encrypt(key);
        
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
    public User findFileByUser(User user) throws Exception {
        String pathToSaveFile, arqName, fullFilePath;
        
        pathToSaveFile = getPathToSaveFile();
        String[] key = {user.getUsername()+user.getPassword()};
        arqName = userEncryptMode.getUsernameEncryptMode().encrypt(key);
        
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
