/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.model.dao;

import red.project.algorithms.AESCBC;
import red.project.algorithms.MD5;
import red.project.algorithms.SHA256;
import red.project.model.UserEncryptMode;

/**
 *
 * @author matheus
 */
public class DaoBuilder {
    
    public static UserDao build(UserEncryptMode uem) throws Exception {
        if ((uem.getUsernameEncryptMode() instanceof MD5) && (uem.getPasswordEncryptMode() instanceof MD5)) {
            return new UserDao_MD5_MD5(uem);
        } else if ((uem.getUsernameEncryptMode() instanceof MD5) && (uem.getPasswordEncryptMode() instanceof SHA256)) {
            return new UserDao_MD5_SHA256(uem);
        } else if ((uem.getUsernameEncryptMode() instanceof SHA256) && (uem.getPasswordEncryptMode() instanceof MD5)) {
            return new UserDao_SHA256_MD5(uem);
        } else if ((uem.getUsernameEncryptMode() instanceof SHA256) && (uem.getPasswordEncryptMode() instanceof SHA256)) {
            return new UserDao_SHA256_SHA256(uem);
        } else if ((uem.getUsernameEncryptMode() instanceof SHA256) && (uem.getPasswordEncryptMode() instanceof AESCBC)) {
            return new UserDao_SHA256_AESCBC(uem);
        } 
        throw new Exception("Esse modo de criptografia ainda não foi implementado.");
        
    }
    
    
}
