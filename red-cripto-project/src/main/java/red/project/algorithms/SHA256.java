/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.algorithms;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import red.examples.JcaUtils;

/**
 *
 * @author Matheus
 */
public class SHA256 {
    
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public static String getHashValue(String valueToHash) throws Exception {
        String hashValue;
        
        try {
            hashValue = Hex.toHexString(JcaUtils.computeDigest("SHA-256", Strings.toByteArray(valueToHash)));
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            throw new Exception("Falha ao aplicar hash no nome de usuário: " + ex.getMessage());
        }
        return hashValue;
    }
    
    
}
