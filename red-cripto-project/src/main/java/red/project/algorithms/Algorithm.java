/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.algorithms;

import java.security.Security;
import java.util.HashMap;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author matheus
 */
public abstract class Algorithm {
    
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public abstract HashMap<String, Object> encrypt(HashMap<String, Object> params) throws Exception;
    
    public abstract HashMap<String, Object> decrypt(HashMap<String, Object> params) throws Exception;
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
