/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that generates a hash of the password.
 * @author Ander
 */
public class HashGenerator {

    /**
     * Logger for the class.
     */
    private static final Logger LOGGER = Logger.getLogger("jampserverside.crypto");

    /**
     * This method generates a hash of the password received.
     *
     * @param myText The password to hash.
     * @return The paswword hashed.
     */
    public static byte[] generateHash(byte[] myText) {
        String algorithm = ResourceBundle.getBundle("jampserverside.crypto.config")
                .getString("ALGORITHM");
        byte[] result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] myTextinBytes = myText;
            md.update(myTextinBytes);
            result = md.digest();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "HashGenerator: No such algorithm", e.getMessage());
        }
        return result;

    }
}
