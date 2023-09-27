/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.algorithms;

import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author matheus
 */
public abstract class Algorithm {
    
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    
    public abstract String encrypt(String... params) throws Exception;
    
    public abstract String decrypt(String... params) throws Exception;
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
}
