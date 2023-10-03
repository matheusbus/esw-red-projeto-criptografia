/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.algorithms;

import java.util.HashMap;
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
    public HashMap<String,Object> encrypt(HashMap<String,Object> params) throws Exception {
        HashMap<String,Object> values = new HashMap<>();
        String hashValue = Hex.toHexString(JcaUtils.computeDigest("SHA-256", Strings.toByteArray((String) params.get("value"))));
        values.put("value", hashValue);
        return values;
    }
    
    @Override
    public HashMap<String,Object> decrypt(HashMap<String, Object> params) throws Exception {
        throw new Exception("SHA256 não possui algoritmo de descriptografia."); 
    }

}
