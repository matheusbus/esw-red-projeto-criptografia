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
import java.util.Base64;
import java.util.Scanner;

public class PasswordManager {

    private static final int ITERATIONS = 100000;
    private static final int KEY_LENGTH = 256;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome de usuário: ");
        String username = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        try {
            // Derivar chave a partir da senha usando PBKDF2
            byte[] salt = generateSalt();
            SecretKey key = deriveKey(password, salt);

            // Criptografar o nome de usuário e a senha usando AES-CBC
            String data = username + " " + password;
            byte[] encryptedData = encryptAES_CBC(data.getBytes(StandardCharsets.UTF_8), key);

            // Salvar o hash do nome de usuário e a senha criptografada em um arquivo
            saveHashToFile(username, encryptedData);

            // Salvar o salt em um arquivo protegido por AES-EBC
            saveSaltToFile(salt, key);

            System.out.println("Senha salva com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private static SecretKey deriveKey(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return new SecretKeySpec(factory.generateSecret(keySpec).getEncoded(), "AES");
    }

    private static byte[] encryptAES_CBC(byte[] data, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    private static void saveHashToFile(String username, byte[] encryptedData) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedUsername = digest.digest(username.getBytes(StandardCharsets.UTF_8));

        Path filePath = Paths.get("passwords.txt");
        Files.write(filePath, hashedUsername);
        Files.write(filePath, encryptedData, java.nio.file.StandardOpenOption.APPEND);
    }

    private static void saveSaltToFile(byte[] salt, SecretKey key) throws Exception {
        byte[] encryptedSalt = encryptAES_ECB(salt, key);

        Path filePath = Paths.get("salt.bin");
        Files.write(filePath, encryptedSalt);
    }

    private static byte[] encryptAES_ECB(byte[] data, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }
}
