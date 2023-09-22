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
import red.project.model.User;
import red.project.model.UserEncryptMode;

/**
 *
 * @author Matheus
 */
public final class UserDao {
    
    protected static final Path CURRENT_PATH = Paths.get(System.getProperty("user.dir"));
    protected static String USERS_DIR = null;
    private String folderName;
    private UserEncryptMode userEncryptMode;
    
    
    public UserDao(UserEncryptMode userEncryptMode) throws IOException {
        if(USERS_DIR == null) {
            createUsersDirectory();
        }
        this.userEncryptMode = userEncryptMode;
        this.folderName = userEncryptMode.getDirName();
    }
            
    public String saveUserToFile(User user) {
        throw new UnsupportedOperationException("Unssuported yet.");
    }
    
    public String findFileByUser(User user) {
        throw new UnsupportedOperationException("Unssuported yet.");
    }
    
    public void createUsersDirectory() throws IOException {
        if(!usersDirectoryExists()) {
            Path path = Paths.get(CURRENT_PATH.resolve(CURRENT_PATH + "/users/").toString());
            
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
        if(!pathToSaveExists()) {
            return createPathToSaveFile();
        }
        return null;
    }
    
    public boolean pathToSaveExists() {
        return Files.exists(CURRENT_PATH.resolve(CURRENT_PATH + "/users/" + folderName), LinkOption.NOFOLLOW_LINKS);
    }
    
    public String createPathToSaveFile() throws IOException {
        Path path = Paths.get(CURRENT_PATH.resolve(CURRENT_PATH + "/users/" + folderName).toString());

        File dir = new File(path.toString());
        if(dir.mkdir()) {
            return path.toString();
        }
        throw new IOException("Não foi possível criar o diretório: " + path.toString()); 
    }
    
    
}
