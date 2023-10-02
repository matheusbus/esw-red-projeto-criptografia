/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.model.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import red.project.model.User;
import red.project.model.UserEncryptMode;
/**
 *
 * @author Matheus
 */
public abstract class UserDao {
    
    protected static final Path CURRENT_PATH = Paths.get(System.getProperty("user.dir"));
    protected static String USERS_DIR = null;
    protected String folderNameToSave;
    protected UserEncryptMode userEncryptMode;
    
    
    public UserDao(String folderNameToSave, UserEncryptMode userEncryptMode) throws IOException {
        if(USERS_DIR == null) {
            createUsersDirectory();
        }
        this.folderNameToSave = folderNameToSave;
        this.userEncryptMode = userEncryptMode;
    }
            
    public abstract String saveUserToFile(HashMap<String,Object> params) throws Exception ;
    
    public abstract User findFileByUser(HashMap<String,Object> params) throws Exception ;
    
    public final void createUsersDirectory() throws IOException {
        if(!usersDirectoryExists()) {
            Path path = Paths.get(CURRENT_PATH.resolve(CURRENT_PATH + "/users").toString());
            
            File dir = new File(path.toString());
            if(dir.mkdir()) {
                USERS_DIR = path.toString();
            }
            throw new IOException("Não foi possível criar o diretório de usuários.");   
        }
    }
    
    public boolean usersDirectoryExists() {
        return Files.exists(CURRENT_PATH.resolve(CURRENT_PATH + "/users"), LinkOption.NOFOLLOW_LINKS);
    }
    
    public String getPathToSaveFile() throws IOException {
        if(!pathToSaveFileExists()) {
            return createPathToSaveFile();
        }
        return CURRENT_PATH.resolve(CURRENT_PATH + "/users/" + folderNameToSave).toString();
    }
    
    public boolean pathToSaveFileExists() {
        return Files.exists(CURRENT_PATH.resolve(CURRENT_PATH + "/users/" + folderNameToSave), LinkOption.NOFOLLOW_LINKS);
    }
    
    public String createPathToSaveFile() throws IOException {
        Path path = Paths.get(CURRENT_PATH.resolve(CURRENT_PATH + "/users/" + folderNameToSave).toString());

        File dir = new File(path.toString());
        if(dir.mkdir()) {
            return path.toString();
        }
        throw new IOException("Não foi possível criar o diretório: " + path.toString()); 
    }

    public static String getUSERS_DIR() {
        return USERS_DIR;
    }

    public static void setUSERS_DIR(String USERS_DIR) {
        UserDao.USERS_DIR = USERS_DIR;
    }

    public String getFolderNameToSave() {
        return folderNameToSave;
    }

    public void setFolderNameToSave(String folderNameToSave) {
        this.folderNameToSave = folderNameToSave;
    }

    public UserEncryptMode getUserEncryptMode() {
        return userEncryptMode;
    }

    public void setUserEncryptMode(UserEncryptMode userEncryptMode) {
        this.userEncryptMode = userEncryptMode;
    }
    
    public boolean fileExists(String fullFilePath) {
        File f = new File(fullFilePath);
        return f.exists() && !f.isDirectory();
    }
    
    
    
}
