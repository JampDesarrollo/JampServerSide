/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Class that generates a random password and sends it to the user's email
 * address.
 *
 * @author Ander
 */
public class PasswordChangeAndMail {

    /**
     *
     * Logger of the class.
     */
    private static final Logger LOGGER = Logger.getLogger("jampserverside.crypto");

    /**
     * This method generates a secure random password adn sends an email.
     *
     * @param receivedMail Received email to send the mail to.
     * @return byte array.
     */
    public static byte[] passwAndSendEmail(String receivedMail) {
        byte[] hashedPassw = null;
        try {
            //Generar otra contrasena
            String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
            int length = 10;
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int indexRandom = random.nextInt(symbols.length);
                sb.append(symbols[indexRandom]);
            }
            String password = sb.toString();

            String eMail = DecrypterForEmail.decryptEmail();
            String pass = DecrypterForEmail.decryptPassw();

            //Create and send email
            //DESENCRIPTAR username y password de un archivo con clave privada
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(eMail, pass));
            email.setFrom(eMail);
            email.setSubject("Nueva contraseña para JAMP");
            email.setMsg("Se ha creado una nueva contraseña para tu cuenta."
                    + "Tu nueva contraseña es: " + password + "\n :-)");
            email.addTo(receivedMail);
            email.setStartTLSEnabled(true);
            email.send();

            hashedPassw = HashGenerator.generateHash(password.getBytes());

        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, " Logaritmo de encryptacion no encontrado. {0}",
                    e.getMessage());
        } catch (EmailException e) {
            LOGGER.log(Level.SEVERE, " Email exception. {0}", e.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, " Error actualizando contraseña {0}",
                    ex.getMessage());
        }
        return hashedPassw;
    }

    /**
     * This method sends an email to the user telling him/her the password has
     * been changed.
     *
     * @param receivedMail Users mail.
     * @return boolean if all OK.
     */
    public static boolean passwChangedEmail(String receivedMail) {
        boolean allOk = false;
        try {
            String eMail = DecrypterForEmail.decryptEmail();
            String pass = DecrypterForEmail.decryptPassw();

            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(eMail, pass));
            email.setFrom(eMail);
            email.setSubject("Contraseña de JAMP cambiada");
            email.setMsg("Se ha realizado un cambio de contraseña para tu cuenta.\n :-)");
            email.addTo(receivedMail);
            email.setStartTLSEnabled(true);
            email.send();

            allOk = true;
        } catch (EmailException ex) {
            Logger.getLogger(PasswordChangeAndMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allOk;
    }
}
