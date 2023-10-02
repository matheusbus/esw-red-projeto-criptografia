/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package red.examples;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import org.bouncycastle.util.encoders.Hex;

public class EncryptDecryptUserPassword {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Obter a senha do usuário
        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        // Converte a senha para bytes usando algum método apropriado (pode ser PBKDF2, etc.)
        byte[] passwordBytes = password.getBytes();

        // Chave derivada da senha
        byte[] keyBytes = getKeyFromPassword(passwordBytes);

        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");

        // Dados de entrada
        byte[] input = Hex.decode("a0a1a2a3a4a5a6a7a0a1a2a3a4a5a6a7a0a1a2a3a4a5a6a7a0a1a2a3a4a5a6a7");

        System.out.println("input    : " + Hex.toHexString(input));

        // IV (Inicialização)
        byte[] iv = Hex.decode("9f741fdb5d8845bdb48a94394e84f8a3");

        // Modo de Criptografia
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));

        // Criptografar
        byte[] encrypted = cipher.doFinal(input);

        System.out.println("encrypted: " + Hex.toHexString(encrypted));

        // Modo de Descriptografia
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

        // Descriptografar
        byte[] decrypted = cipher.doFinal(encrypted);

        System.out.println("decrypted: " + Hex.toHexString(decrypted));
        byte[] decoded = Hex.decode(decrypted);
        String original = new String(decoded, StandardCharsets.UTF_8);
        System.out.println("Original: " + original);

    }

    private static byte[] getKeyFromPassword(byte[] passwordBytes) {
        // Implemente algum método seguro de derivação de chave a partir da senha.
        // Pode ser PBKDF2, scrypt, Argon2, etc. Neste exemplo, estamos usando
        // uma simples cópia dos bytes da senha para fins de demonstração.
        byte[] key = new byte[16];
        System.arraycopy(passwordBytes, 0, key, 0, Math.min(passwordBytes.length, key.length));
        return key;
    }
}
