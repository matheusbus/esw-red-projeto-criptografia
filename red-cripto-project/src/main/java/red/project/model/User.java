/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.model;

import red.project.algorithms.SHA256;

/**
 *
 * @author Matheus
 */
public class User {
    
    private String username;
    
    private String password;
    
    public User(String username, String password) throws Exception {
        this.username = applyHashToUsername(username);
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setUsername(String username) throws Exception {
        this.username = applyHashToUsername(username);
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String applyHashToUsername(String username) throws Exception {
        return SHA256.getHashValue(username);
    }
    
    @Override
    public String toString() {
        return "User: " + username + "\n" + "Password: " + password;
    }
    
}
