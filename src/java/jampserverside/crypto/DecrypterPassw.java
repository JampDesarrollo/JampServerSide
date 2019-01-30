/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.crypto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Class used for the decryption of the password that receives with the private
 * key.
 *
 * @author Ander
 */
public class DecrypterPassw {

    /**
     * Logger for the class.
     */
    private static final Logger LOGGER = Logger.getLogger("jampserverside.crypto");

    /**
     * Cipher.
     */
    private static final String CIPHERPRIV = ResourceBundle.getBundle("jampserverside.crypto.config")
            .getString("CIPHERPRIV");

    /**
     * Method that decrypts the password with the private key generated with the
     * key pair generator.
     *
     * @param password Password to decrypt.
     * @return Password decrypted.
     */
    public static byte[] decryptPassw(byte[] password) {
        byte[] decodedMessage = null;
        try {
            FileInputStream fis = new FileInputStream("private.key");
            byte[] byteA = new byte[fis.available()];
            fis.read(byteA);
            fis.close();

            KeyFactory kf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(byteA);
            PrivateKey privk = kf.generatePrivate(ks);

            // Decode the text with the private key
            Cipher cipher = Cipher.getInstance(CIPHERPRIV);
            cipher.init(Cipher.DECRYPT_MODE, privk);
            decodedMessage = cipher.doFinal(password);

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "DecrypterPasswords: File not found", e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "DecrypterPasswords: IO Exception", e.getMessage());
        } catch (InvalidKeySpecException e) {
            LOGGER.log(Level.SEVERE, "DecrypterPasswords: Invalid Key specification", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "DecrypterPasswords: No such algorithm", e.getMessage());
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "DecrypterPasswords: Invalid key", e.getMessage());
        } catch (NoSuchPaddingException e) {
            LOGGER.log(Level.SEVERE, "DecrypterPasswords: No such pading", e.getMessage());
        } catch (IllegalBlockSizeException e) {
            LOGGER.log(Level.SEVERE, "DecrypterPasswords: Illegal block size", e.getMessage());
        } catch (BadPaddingException e) {
            LOGGER.log(Level.SEVERE, "DecrypterPasswords: Bad Padding", e.getMessage());
        }

        return decodedMessage;
    }
}
