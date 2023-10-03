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
    public String saveUserToFile(HashMap<String,Object> paramsUsername, HashMap<String,Object> paramsPassword) throws Exception {
        String pathToSaveFile, arqName, fullFilePath;
        
        pathToSaveFile = getPathToSaveFile();
        HashMap<String, Object> keyNameFile = new HashMap<>();
        String nameFile = ((String) paramsUsername.get("value")) + ((String) paramsPassword.get("value"));
        keyNameFile.put("value", nameFile);
        arqName = (String) userEncryptMode.getUsernameEncryptMode().encrypt(keyNameFile).get("value");
        
        fullFilePath = pathToSaveFile + "/" + arqName + ".txt";
        
        
        
        if(!fileExists(fullFilePath)) {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(fullFilePath))) {

                bw.write("Username: " + paramsUsername.get("value"));
                bw.newLine();
                bw.write("Password: " + paramsPassword.get("value"));
            }  
        }
        return null;
    }

    @Override
    public User findFileByUser(HashMap<String,Object> paramsUsername, HashMap<String,Object> paramsPassword) throws Exception {
        String pathToSaveFile, arqName, fullFilePath;
        
        pathToSaveFile = getPathToSaveFile();
        HashMap<String, Object> keyNameFile = new HashMap<>();
        String nameFile = ((String) userEncryptMode.getUsernameEncryptMode().encrypt(paramsUsername).get("value"))
                + ((String) userEncryptMode.getUsernameEncryptMode().encrypt(paramsPassword).get("value"));
        keyNameFile.put("value", nameFile);
        arqName = (String) userEncryptMode.getUsernameEncryptMode().encrypt(keyNameFile).get("value");
        
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
