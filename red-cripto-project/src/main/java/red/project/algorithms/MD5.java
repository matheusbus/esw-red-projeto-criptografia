/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.algorithms;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import red.examples.JcaUtils;

/**
 *
 * @author matheus
 */
public class MD5 extends Algorithm {

    public static MD5 getInstance() {
        return new MD5();
    }
    
    @Override
    public String encrypt(String value) throws Exception {
        String hashValue;
        
        try {
            hashValue = Hex.toHexString(JcaUtils.computeDigest("MD5", Strings.toByteArray(value)));
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            throw new Exception("Falha ao aplicar hash MD5 no valor fornecido: " + ex.getMessage());
        }
        
        return hashValue;
    }

    @Override
    public String decrypt(String value) throws Exception {
        throw new Exception("MD5 não possui algoritmo de descriptografia."); 
    }
    
    public static void main(String[] args) {
        
        try {
            System.out.println(MD5.getInstance().encrypt("matheusbus"));
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
        
    }
    
}
