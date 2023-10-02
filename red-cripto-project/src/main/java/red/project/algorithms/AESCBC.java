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
            cipher = Cipher.getInstance("AES/CBC/NoPadding", "BC");
        } 
        catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            Logger.getLogger(AESCBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public HashMap<String, Object> encrypt(String... params) throws Exception {
        HashMap<String, Object> values = new HashMap<>();
        
        String password = params[0];
        
        // Gerar chave secreta com PBKDF
        SecretKeySpec key = new SecretKeySpec(generateSecretKey(password).getEncoded(), "AES");
        
        // Gerar um IV aleatório
        byte[] iv = Hex.decode(generateIV());
        
        // Inicializar cifrador
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        
        // Compactar no tamanho do bloco correto
        byte[] paddedInput = padData(password.getBytes());
        byte[] encrypted = cipher.doFinal(paddedInput);
        
        values.put("value", Hex.toHexString(encrypted));
        values.put("key", key);
        values.put("iv", Arrays.toString(iv));
        
        return values;
    }

    @Override
    public HashMap<String, Object> decrypt(HashMap<String, Object> params) throws Exception {
        SecretKeySpec key = (SecretKeySpec) params.get("key");
        byte[] iv = getArrayFromString((String) params.get("iv"));
        

        // Inicializar cifrador
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            
        return null;
    }
    
    public byte[] getArrayFromString(String string) {
        String newString = string.replace("[", "").replace("]", "");
        String[] arrayString = newString.split(", ");
        byte[] array = null;
        for(int i = 0; i < arrayString.length; i++) {
            array[i] = Byte.parseByte(arrayString[i]);
        }
        return array;
    }
    
    
    public static byte[] generateIV() {
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        return Hex.encode(iv);
    }
    
    // Gerar chave secreta utilizando PBKDF2
    public static SecretKey generateSecretKey(String fromValue) throws NoSuchAlgorithmException {
        Integer iterations = fromValue.length() * 1000;
        return PBKDF2Util.generateDerivedKey(fromValue, iterations);
    }
    
    private static byte[] padData(byte[] input) {
        int blockSize = 16;
        int padding = blockSize - (input.length % blockSize);
        byte[] paddedData = new byte[input.length + padding];
        System.arraycopy(input, 0, paddedData, 0, input.length);
        
        for (int i = input.length; i < paddedData.length; i++) {
            paddedData[i] = (byte) padding;
        }
        
        return paddedData;
    }
    
    public static void main(String[] args) throws Exception {
        
        //1- Pegar a senha fornecida pelo usuário
        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();
        System.out.println(password);
        
        //2- Derivar uma chave secreta com PBKDF2 utilizando a senha do usuário
        SecretKeySpec key = new SecretKeySpec(generateSecretKey(password).getEncoded(), "AES");
        System.out.println("Secret Key: " + Arrays.toString(key.getEncoded()));
        System.out.println("Secret Key string: " + Hex.toHexString(key.getEncoded()));
        System.out.println("Secret Key retornado da string: " + Arrays.toString(Hex.decode(Hex.toHexString(key.getEncoded()))));

        
        
        //3- Gerar um IV aleatório
        byte[] iv = Hex.decode(generateIV());
        System.out.println("IV: " + Arrays.toString(iv));
        System.out.println("IV string: " + Hex.toHexString(iv));
        System.out.println("IV retornado da string: " + Arrays.toString(Hex.decode(Hex.toHexString(iv))));
        
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        
        //4- Criptografar senha
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        // Criptografar
        byte[] paddedInput = padData(password.getBytes());
        byte[] encrypted = cipher.doFinal(paddedInput);
        System.out.println("Criptografado: " + Hex.toHexString(encrypted));
        
        
        //Descriptografar
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("Descriptografado: " + Arrays.toString(decrypted));
        
        //4- Guardar em um arquivo cifrado com AES EBC a chave secreta e o IV
        
        
        //5- Guardar em um outro arquivo o usuário cifrado com SHA256 e a senha cifrado com o AESCBC
        
        
    }
    
}
