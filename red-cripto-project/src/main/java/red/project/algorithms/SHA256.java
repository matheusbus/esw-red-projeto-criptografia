/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.algorithms;

import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import red.examples.JcaUtils;
import red.project.model.dao.DaoBuilder;

/**
 *
 * @author Matheus
 */
public class SHA256 extends Algorithm {
    
    public static SHA256 getInstance() {
        return new SHA256();
    }
    
    @Override
    public String encrypt(String... params) throws Exception {
        String hashValue;
        
        hashValue = Hex.toHexString(JcaUtils.computeDigest("SHA-256", Strings.toByteArray(params[0])));
        return hashValue;
    }
    
    @Override
    public String decrypt(String... params) throws Exception {
        throw new Exception("SHA256 não possui algoritmo de descriptografia."); 
    }
    
    public static void main(String[] args) {
        String[] params = {"matheusbus"};
        
        try {
            System.out.println(SHA256.getInstance().encrypt(params[0]));
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
        try {
            System.out.println(SHA256.getInstance().decrypt(params[0]));
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

}
