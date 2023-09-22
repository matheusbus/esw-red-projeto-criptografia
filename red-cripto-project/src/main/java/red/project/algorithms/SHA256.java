/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.algorithms;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import red.examples.JcaUtils;

/**
 *
 * @author Matheus
 */
public class SHA256 extends Algorithm {
    
    public static SHA256 getInstance() {
        return new SHA256();
    }
    
    @Override
    public String encrypt(String value) throws Exception {
        String hashValue;
        
        try {
            hashValue = Hex.toHexString(JcaUtils.computeDigest("SHA-256", Strings.toByteArray(value)));
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            throw new Exception("Falha ao aplicar hash SHA-256 no valor fornecido: " + ex.getMessage());
        }
        return hashValue;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(SHA256.getInstance().encrypt("matheusbus"));
        } catch (Exception ex) {
            Logger.getLogger(SHA256.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public String decrypt(String value) throws Exception {
        throw new Exception("SHA256 não possui algoritmo de descriptografia."); 
    }

}
