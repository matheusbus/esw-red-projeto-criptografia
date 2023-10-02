package red.examples;

import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.SecretKey;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * User: Carla
 * Date: 1 de setembro de 2015
 */

public class PBKDF2Util {

    /**
     * Gerar chave derivada da senha
     * @param key
     * @param salt
     * @param iterations
     * @return
     */
    public static SecretKey generateDerivedKey(String password, Integer iterations) throws NoSuchAlgorithmException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), getSalt().getBytes(), iterations, 128);
        SecretKeyFactory pbkdf2 = null;
        SecretKey sk = null;
        
        try {
            pbkdf2 = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            sk = pbkdf2.generateSecret(spec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sk;
    }

    public static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Hex.toHexString(salt);
    }

    public static void main(String args[]) throws NoSuchAlgorithmException {
        PBKDF2Util obj = new PBKDF2Util();

        String senha;

        Scanner input = new Scanner(System.in);
        System.out.println("Digite a senha: ");
        senha = input.nextLine();
        int it = senha.length() * 1000;

        String chaveDerivada = Arrays.toString(generateDerivedKey(senha, it).getEncoded());
        String chaveDerivada2 = Arrays.toString(generateDerivedKey(senha, it).getEncoded());

        System.out.println("Chave derivada da senha = " + chaveDerivada );
        System.out.println("Chave derivada da senha = " + chaveDerivada2 );


    }


}
