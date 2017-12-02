package aula1.Misc;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class Senha {

    private static final int ITERACOES = 20 * 1000;
    private static final int TAM_SALT = 32;
    private static final int TAM_CHAVE_DESEJADO = 256;

    public static String hash(String senha) {
        byte[] salt = null;
        try {
            salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(TAM_SALT);
        } catch (NoSuchAlgorithmException ex) {
        }
        return Base64.getEncoder().encodeToString(salt) + "$" + hash(senha, salt);
    }

    private static String hash(String senha, byte[] salt) {
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey chave = f.generateSecret(new PBEKeySpec(
                    senha.toCharArray(), salt, ITERACOES, TAM_CHAVE_DESEJADO)
            );
            return Base64.getEncoder().encodeToString(chave.getEncoded());

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Senha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static boolean check(String senhaCandidata, String senhaHash) {
        String[] saltESenha = senhaHash.split("\\$");
        if (saltESenha.length != 2) {
            throw new IllegalStateException(
                    "The stored password have the form 'salt$hash'");
        }
        String hashEntrada = hash(senhaCandidata, Base64.getDecoder().decode(saltESenha[0]));
        return hashEntrada.equals(saltESenha[1]);
    }
}
