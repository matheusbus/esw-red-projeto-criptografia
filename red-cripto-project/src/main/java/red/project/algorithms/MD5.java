/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.algorithms;

import java.util.HashMap;
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
    public HashMap<String, Object> encrypt(String... params) throws Exception {
        HashMap<String, Object> values = new HashMap<>();
        String hashValue = Hex.toHexString(JcaUtils.computeDigest("MD5", Strings.toByteArray(params[0])));
        values.put("value", hashValue);
        
        return values;
    }

    @Override
    public HashMap<String, Object> decrypt(HashMap<String, Object> params) throws Exception {
        throw new Exception("MD5 não possui algoritmo de descriptografia."); 
    }
    
    public static void main(String[] args) {
        String[] params = {"matheusbus"};
        
        try {
            System.out.println(MD5.getInstance().encrypt(params[0]));
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
    }
    
}
