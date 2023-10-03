/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.project.algorithms;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Hex;
import red.examples.PBKDF2Util;

/**
 *
 * @author matheus
 */
public class AESCBC extends Algorithm {
    
    public static Cipher cipher = null;
    
    public static AESCBC getInstance() {
        return new AESCBC();
    }
    
    static {
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
        } 
        catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            Logger.getLogger(AESCBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public HashMap<String, Object> encrypt(HashMap<String, Object> params) throws Exception {
        HashMap<String, Object> values = new HashMap<>();
        
        String password = (String) params.get("value");
        
        // Gerar chave secreta com PBKDF
        SecretKeySpec key = generateSecretKey(password);
        
        // Gerar um IV aleatório
        byte[] iv = generateIV();
        
        // Inicializar cifrador
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        
        // Aplicar criptografia
        byte[] passwordEncrypted = cipher.doFinal(password.getBytes());
        
        values.put("value", byteArrayToHexString(passwordEncrypted));
        values.put("key", byteArrayToHexString(key.getEncoded()));
        values.put("iv", byteArrayToHexString(iv));
        
        return values;
    }

    @Override
    public HashMap<String, Object> decrypt(HashMap<String, Object> params) throws Exception {
        HashMap<String, Object> values = new HashMap<>();
        
        SecretKeySpec key = new SecretKeySpec(hexStringToByteArray((String) params.get("key")), "AES");
        
        byte[] iv = hexStringToByteArray((String) params.get("iv"));
        
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        
        byte[] passwordDescrypted = cipher.doFinal(hexStringToByteArray((String) params.get("value")));
        
        String password = new String(passwordDescrypted, StandardCharsets.UTF_8);
        
        values.put("value", password);
        values.put("key", byteArrayToHexString(key.getEncoded()));
        values.put("iv", byteArrayToHexString(iv));
        
        return values;
    }
    
    public static byte[] generateIV() {
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        return iv;
    }
    
    // Gerar chave secreta utilizando PBKDF2
    public static SecretKeySpec generateSecretKey(String fromValue) throws NoSuchAlgorithmException {
        Integer iterations = fromValue.length() * 1000;
        return PBKDF2Util.generateDerivedKey(fromValue, iterations);
    }
    
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] valor = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            valor[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return valor;
    }
    
    public static void main(String[] args) throws Exception {
        
        //1- Pegar a senha fornecida pelo usuário
        //Scanner sc = new Scanner(System.in);
        String password = "matheus";
        System.out.println("Senha: " + password);
        
        //2- Derivar uma chave secreta com PBKDF2 utilizando a senha do usuário
        HashMap<String, Object> params = new HashMap<>();
        params.put("value", password);
        AESCBC cifrador = AESCBC.getInstance();
        HashMap<String, Object> retorno = cifrador.encrypt(params);
        System.out.println("Secret Key: " + retorno.get("key"));
        System.out.println("IV: " + retorno.get("iv"));
        System.out.println("Senha criptografada: " + retorno.get("value"));
        
        HashMap<String, Object> data = cifrador.decrypt(retorno);
        System.out.println("-----");
        System.out.println("Secret Key: " + data.get("key"));
        System.out.println("IV: " + data.get("iv"));
        System.out.println("Senha descriptografada: " + data.get("value"));
        
        //4- Guardar em um arquivo cifrado com AES EBC a chave secreta e o IV
        
        
        //5- Guardar em um outro arquivo o usuário cifrado com SHA256 e a senha cifrado com o AESCBC
        
        
    }
    
}
