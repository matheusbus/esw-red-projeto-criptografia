/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.algorithms;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author matheus
 */
public class AESECB {
    
    public static Cipher cipher = null;
    
    public static AESECB getInstance() {
        return new AESECB();
    }
    
    static {
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        } 
        catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            Logger.getLogger(AESCBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
