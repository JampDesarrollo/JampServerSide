package jampserverside.crypto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class decrypts the email and password for the authentification of the
 * email sender with the private key generated wih PrivKeyEncrypter.
 *
 * @author Ander
 */
public class DecrypterForEmail {

    /**
     * Logger for the class.
     */
    private static final Logger LOGGER = Logger.getLogger("jampserverside.crypto");
    /**
     * Iteration count.
     */
    private static final String ITERATIONCOUNT = ResourceBundle.getBundle("jampserverside.crypto.config")
            .getString("ITERATION");
    /**
     * SkfAlgorithm.
     */
    private static final String SKFALGORITHM = ResourceBundle.getBundle("jampserverside.crypto.config")
            .getString("SKFALGORITHM");
    /**
     * Salt.
     */
    private static final String SALTSTRING = ResourceBundle.getBundle("jampserverside.crypto.config")
            .getString("SALT");
    /**
     * Password.
     */
    private static final String PWD = ResourceBundle.getBundle("jampserverside.crypto.config")
            .getString("PWD");
    /**
     * Cipher.
     */
    private static final String CIPHER = ResourceBundle.getBundle("jampserverside.crypto.config")
            .getString("CIPHER");
    /**
     * Email.
     */
    private static final String EMAIL = ResourceBundle.getBundle("jampserverside.crypto.config")
            .getString("EMAIL");
    /**
     * Password.
     */
    private static final String PASSW = ResourceBundle.getBundle("jampserverside.crypto.config")
            .getString("PASSW");

    /**
     * This method decrypts the email address of the email sender with a private
     * key.
     *
     * @return String of th email.
     */
    public static String decryptEmail() {
        String s = null;
        ObjectInputStream ois = null;
        try {
            // Key to decrypt the file. Then, a SecretKey object must be created as we did
            // in the encryption
            byte[] salt = SALTSTRING.getBytes(); // Exactly 16 bytes
            KeySpec spec = new PBEKeySpec(PWD.toCharArray(), salt, Integer.parseInt(ITERATIONCOUNT), 128); // AES-128
            SecretKeyFactory factory = SecretKeyFactory.getInstance(SKFALGORITHM);
            byte[] key = factory.generateSecret(spec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            // The next step is to read the encoded message and IV. The IV comes first and
            // it is 8 bytes long.
            ois = new ObjectInputStream(new FileInputStream(EMAIL));

            byte[] ivRead = (byte[]) ois.readObject();
            byte[] encodedMessage = (byte[]) ois.readObject();

            // Decrypt the message
            Cipher cipher = Cipher.getInstance(CIPHER);
            IvParameterSpec ivParam = new IvParameterSpec(ivRead);
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] decodedEmail = cipher.doFinal(encodedMessage);
            s = new String(decodedEmail);

        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "DecryptEmail: Algorithm not found", e.getMessage());
        } catch (InvalidKeySpecException e) {
            LOGGER.log(Level.SEVERE, "DecryptEmail: Invalid Key specification", e.getMessage());
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "DecryptEmail: File not found", e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "DecryptEmail: IO exception", e.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "DecryptEmail: Class not found", e.getMessage());
        } catch (NoSuchPaddingException e) {
            LOGGER.log(Level.SEVERE, "DecryptEmail: No such padding", e.getMessage());
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "DecryptEmail: Invalid key", e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            LOGGER.log(Level.SEVERE, "DecryptEmail: Invalid algorithm parameter", e.getMessage());
        } catch (IllegalBlockSizeException e) {
            LOGGER.log(Level.SEVERE, "DecryptEmail: Illegal block size", e.getMessage());
        } catch (BadPaddingException e) {
            LOGGER.log(Level.SEVERE, "DecryptEmail: Bad padding", e.getMessage());
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "DecryptEmail: IO exception closing ois", e.getMessage());
            }
        }

        return s;
    }

    /**
     * This method decrypts the password of the email sender.
     *
     * @return String of the password
     */
    public static String decryptPassw() {
        String s = null;
        ObjectInputStream ois = null;
        try {
            // Key to decrypt the file. Then, a SecretKey object must be created as we did
            byte[] salt = SALTSTRING.getBytes(); // Exactly 16 bytes
            KeySpec spec = new PBEKeySpec(PWD.toCharArray(), salt, Integer.parseInt(ITERATIONCOUNT), 128); // AES-128
            SecretKeyFactory factory = SecretKeyFactory.getInstance(SKFALGORITHM);
            byte[] key = factory.generateSecret(spec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

            // The next step is to read the encoded message and IV. The IV comes first and
            // it is 8 bytes long.
            ois = new ObjectInputStream(new FileInputStream(PASSW));

            byte[] ivRead = (byte[]) ois.readObject();
            byte[] encodedMessage = (byte[]) ois.readObject();

            // Decrypt the message
            Cipher cipher = Cipher.getInstance(CIPHER);
            IvParameterSpec ivParam = new IvParameterSpec(ivRead);
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            byte[] decodedPassw = cipher.doFinal(encodedMessage);
            s = new String(decodedPassw);

        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "DecryptPassw: No such algorithm", e.getMessage());
        } catch (InvalidKeySpecException e) {
            LOGGER.log(Level.SEVERE, "DecryptPassw: Invalid key Spec", e.getMessage());
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "DecryptPassw: File not Found", e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "DecryptPassw: IO exception", e.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "DecryptPassw: Class not found", e.getMessage());
        } catch (NoSuchPaddingException e) {
            LOGGER.log(Level.SEVERE, "DecryptPassw: No such padding", e.getMessage());
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "DecryptPassw: Invalid key", e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            LOGGER.log(Level.SEVERE, "DecryptPassw: Invalid algorithm parameter", e.getMessage());
        } catch (IllegalBlockSizeException e) {
            LOGGER.log(Level.SEVERE, "DecryptPassw: Illegal block size", e.getMessage());
        } catch (BadPaddingException e) {
            LOGGER.log(Level.SEVERE, "DecryptPassw: Bad padding", e.getMessage());
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "DecryptPassw: IO exception closing ois", e.getMessage());
            }
        }

        return s;
    }
}
