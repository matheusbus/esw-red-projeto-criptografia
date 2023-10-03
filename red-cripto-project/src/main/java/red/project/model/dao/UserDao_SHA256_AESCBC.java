/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import org.bouncycastle.util.encoders.Hex;
import red.project.algorithms.AESCBC;
import red.project.algorithms.SHA256;
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
    public String saveUserToFile(HashMap<String,Object> paramsUsername, HashMap<String,Object> paramsPassword) throws Exception {
        String pathToSaveFile, arqName, fullFilePath;
        
        pathToSaveFile = getPathToSaveFile();
        HashMap<String, Object> keyNameFile = new HashMap<>();
        String nameFile = ((String) paramsUsername.get("value"));
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
        
        HashMap<String, Object> toNameFile = new HashMap<>();
        toNameFile.put("value", arqName);
        createParamsPasswordFile((String) SHA256
                .getInstance()
                .encrypt(toNameFile)
                .get("value"), paramsPassword);
        return null;
    }

    @Override
    public User findFileByUser(HashMap<String,Object> paramsUsername, HashMap<String,Object> paramsPassword) throws Exception {
        String originPath, fullFilePathCredential, fullFilePathParams, arqName;
        
        originPath = getPathToSaveFile();
        HashMap<String, Object> keyNameFile = new HashMap<>();
        String nameFile = ((String) paramsUsername.get("value"));
        keyNameFile.put("value", nameFile);
        arqName = (String) userEncryptMode.getUsernameEncryptMode().encrypt(keyNameFile).get("value");
        
        
        HashMap<String, Object> toNameFile = new HashMap<>();
        toNameFile.put("value", arqName);
        toNameFile.put("value", SHA256.getInstance().encrypt(toNameFile).get("value"));
        fullFilePathCredential = originPath + "/" + toNameFile.get("value") + ".txt";
        
        fullFilePathParams = originPath + "/" + (String) SHA256.getInstance().encrypt(toNameFile).get("value") + ".txt";
            
        
        // Verificar se existe o arquivo com o usuário e senha
        if(fileExists(fullFilePathParams)) {
            
            HashMap<String, Object> paramsToDescrypt = getParamsPasswordFromFile(fullFilePathParams);
            
            if(fileExists(fullFilePathCredential)) {
                
                try(BufferedReader bf = new BufferedReader(new FileReader(fullFilePathCredential))) {

                    User userSearched = new User();
                    userSearched.setUsername(bf.readLine().split(":")[1].trim());
                    
                    paramsToDescrypt.put("value", bf.readLine().split(":")[1].trim());
                    String password = (String) AESCBC.getInstance().decrypt(paramsToDescrypt).get("value");

                    userSearched.setPassword(password);
                    return userSearched;
                }  
            }
            
        }
        
        return null;
    }
    
    public void createParamsPasswordFile(String fileName, HashMap<String,Object> paramsPassword) throws IOException {
        String pathToSaveFile, arqName, fullFilePath;
        
        pathToSaveFile = getPathToSaveFile();
        arqName = fileName;
        
        fullFilePath = pathToSaveFile + "/" + arqName + ".txt";
        
        if(!fileExists(fullFilePath)) {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(fullFilePath))) {

                bw.write("IV: " + paramsPassword.get("iv"));
                bw.newLine();
                bw.write("Key: " + paramsPassword.get("key"));
            }  
        }
    }
    
    public HashMap<String,Object> getParamsPasswordFromFile(String fullPathName) throws FileNotFoundException, IOException {
        try(BufferedReader bf = new BufferedReader(new FileReader(fullPathName))) {
            HashMap<String, Object> params = new HashMap<>();

            params.put("iv", bf.readLine().split(":")[1].trim());
            params.put("key", bf.readLine().split(":")[1].trim());
            return params;
        }
    }
    
    public String findUserIVFile() {
        return null;
    }
    
    public String findUserCredentialsFile() {
        return null;
    }
}
