/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.examples;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Scanner;

public class AuthenticationExample {

    private static final int ITERATIONS = 100000;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome de usuário: ");
        String username = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        try {
            // Carregar o hash do nome de usuário e a senha criptografada do arquivo
            byte[] storedData = loadHashFromFile(username);

            // Carregar o salt do arquivo protegido por AES-EBC
            byte[] salt = loadSaltFromFile();

            // Derivar chave a partir da senha usando PBKDF2
            SecretKey key = deriveKey(password, salt);

            // Criptografar a senha fornecida para comparar com o armazenado
            byte[] encryptedInput = encryptAES_CBC((username + " " + password).getBytes(StandardCharsets.UTF_8), key);

            // Comparar o hash armazenado com o hash da senha fornecida
            if (MessageDigest.isEqual(storedData, encryptedInput)) {
                System.out.println("Autenticação bem-sucedida!");
            } else {
                System.out.println("Nome de usuário ou senha incorretos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] loadHashFromFile(String username) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedUsername = digest.digest(username.getBytes(StandardCharsets.UTF_8));

        Path filePath = Paths.get("passwords.txt");
        byte[] storedData = Files.readAllBytes(filePath);

        // A primeira parte do arquivo contém o hash do nome de usuário
        byte[] storedUsername = new byte[hashedUsername.length];
        System.arraycopy(storedData, 0, storedUsername, 0, hashedUsername.length);

        // A segunda parte contém a senha criptografada
        byte[] storedEncryptedData = new byte[storedData.length - hashedUsername.length];
        System.arraycopy(storedData, hashedUsername.length, storedEncryptedData, 0, storedEncryptedData.length);

        return storedEncryptedData;
    }

    private static byte[] loadSaltFromFile() throws Exception {
        // Carregar o salt do arquivo protegido por AES-EBC
        Path filePath = Paths.get("salt.bin");
        byte[] encryptedSalt = Files.readAllBytes(filePath);

        // Decrypt salt
        SecretKey key = getKeyFromUserInput(); // Obtenha a chave do usuário (implementação específica)
        return decryptAES_ECB(encryptedSalt, key);
    }

    private static SecretKey deriveKey(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return new SecretKeySpec(factory.generateSecret(keySpec).getEncoded(), "AES");
    }

    private static SecretKey getKeyFromUserInput() {
        // Implemente a lógica para obter a chave do usuário
        // Isso pode envolver uma senha mestra ou outro meio de autenticação
        // Neste exemplo, estamos usando uma entrada fixa para fins de demonstração.
        return new SecretKeySpec("chave_do_usuario".getBytes(StandardCharsets.UTF_8), "AES");
    }

    private static byte[] encryptAES_CBC(byte[] data, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    private static byte[] decryptAES_ECB(byte[] data, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }
}
