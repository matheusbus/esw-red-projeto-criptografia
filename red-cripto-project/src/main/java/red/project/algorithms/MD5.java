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
    public HashMap<String, Object> encrypt(HashMap<String, Object> params) throws Exception {
        String hashValue = Hex.toHexString(JcaUtils.computeDigest("MD5", Strings.toByteArray((String) params.get("value"))));
        HashMap<String, Object> values = new HashMap<>();
        values.put("value", hashValue);
        
        return values;
    }

    @Override
    public HashMap<String, Object> decrypt(HashMap<String, Object> params) throws Exception {
        throw new Exception("MD5 não possui algoritmo de descriptografia."); 
    }
    
}
