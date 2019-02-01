/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.ejb;

import jampserverside.crypto.DecrypterPassw;
import jampserverside.crypto.HashGenerator;
import jampserverside.crypto.PasswordChangeAndMail;
import jampserverside.ejb.UserManagerEJBLocal;
import jampserverside.entity.User;
import jampserverside.entity.UserPrivilege;
import jampserverside.exception.CreateException;
import jampserverside.exception.DeleteException;
import jampserverside.exception.PasswordNotOkException;
import jampserverside.exception.ReadException;
import jampserverside.exception.TxokoNotExistException;
import jampserverside.exception.UpdateException;
import jampserverside.exception.UserLoginExistException;
import jampserverside.exception.UserNotExistException;
import jampserverside.exception.UserPrivilegeException;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author ander
 */
@Stateless
public class UserManagerEJB implements UserManagerEJBLocal {

    /**
     * Logger for the class.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jampserverside.ejb");
    /**
     * Entity manager object.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Finds the user with a login and password for log-in into the Mobile app.
     *
     * @param login Login of the user to find.
     * @param password Password of the user to find.
     * @return The user found.
     * @throws ReadException If there is any Exception during processing.
     * @throws PasswordNotOkException Password is not ok.
     * @throws UserNotExistException User is not found in the storage.
     */
    @Override
    public User findUserByLoginPasswMov(String login, String password)
            throws ReadException, PasswordNotOkException, UserNotExistException {
        User user = null;
        byte[] p = DatatypeConverter.parseHexBinary(password);
        byte[] decryptedPassw = DecrypterPassw.decryptPassw(p);
        byte[] hashedPasww = HashGenerator.generateHash(decryptedPassw);
        try {
            LOGGER.info("UserManager: Finding user by login and passw.");
            user = (User) em.createNamedQuery("findUserByLogin")
                    .setParameter("login", login).getSingleResult();
            if (MessageDigest.isEqual(hashedPasww, DatatypeConverter.parseHexBinary(user.getPassword()))) {
                LOGGER.log(Level.INFO, "UserManager: User found {0}", user.getLogin());
                user.setLastAccess(new Timestamp(System.currentTimeMillis()));
            } else {
                throw new PasswordNotOkException();
            }
        } catch (NoResultException e) {
            LOGGER.log(Level.SEVERE, "UserManager: No result Finding user by login:",
                    e.getMessage());
            throw new UserNotExistException();
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "UserManager: Persistance expception"
                    + "finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "UserManager: RuntimeException Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return user;
    }

    /**
     * Finds the user with an idUser and password to change his current password
     * to a new one for Mobile app.
     *
     * @param idUser IdUser of the user to find.
     * @param oldpass Old password.
     * @param newPassw New password.
     * @return Boolean if all ok.
     * @throws ReadException If there is any Exception during processing.
     * @throws PasswordNotOkException Password is not ok.
     * @throws UserNotExistException User is not found in storage.
     */
    @Override
    public Boolean findUserChangePasswMov(Integer idUser, String oldpass,
            String newPassw) throws ReadException, PasswordNotOkException, UserNotExistException {
        User user = null;
        Boolean allOk = false;
        boolean passwChanged = false;

        byte[] op = DatatypeConverter.parseHexBinary(oldpass);
        byte[] decryptedoPassw = DecrypterPassw.decryptPassw(op);
        byte[] hashedoPasww = HashGenerator.generateHash(decryptedoPassw);

        byte[] np = DatatypeConverter.parseHexBinary(newPassw);
        byte[] decryptednPassw = DecrypterPassw.decryptPassw(np);
        byte[] hashednPasww = HashGenerator.generateHash(decryptednPassw);
        try {
            LOGGER.info("UserManager: Finding user by login and passw.");
            user = em.find(User.class, idUser);
            if (user == null) {
                LOGGER.log(Level.SEVERE, "UserManager: No result Finding user by idUser:");
                throw new UserNotExistException();
            } else {
                if (MessageDigest.isEqual(hashedoPasww, DatatypeConverter.parseHexBinary(user.getPassword()))) {
                    LOGGER.log(Level.INFO, "UserManager: User found {0}", user.getLogin());
                    user.setPassword(DatatypeConverter.printHexBinary(hashednPasww));
                    user.setLastPasswordChange(new Timestamp(System.currentTimeMillis()));
                    updateUser(user);
                    passwChanged = PasswordChangeAndMail.passwChangedEmail(user.getEmail());
                    if (passwChanged) {
                        allOk = true;
                    }
                } else {
                    throw new PasswordNotOkException();
                }
            }
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "UserManager: Persistance expception"
                    + "finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "UserManager: RuntimeException Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        } catch (UpdateException ex) {

        }
        return allOk;
    }

    /**
     * Finds the user with a login and password for log-in into the PC app.
     *
     * @param login Login of the user to find.
     * @param password Password of the user to find.
     * @return The user found.
     * @throws ReadException If there is any Exception during processing.
     * @throws PasswordNotOkException Password is not ok.
     * @throws jampserverside.exception.UserPrivilegeException The user does not
     * have the privilege.
     * @throws UserNotExistException User is not found in the storage.
     */
    @Override
    public User findUserByLoginPasswPC(String login, String password)
            throws ReadException, PasswordNotOkException,
            UserPrivilegeException, UserNotExistException {
        User user = null;

        byte[] p = DatatypeConverter.parseHexBinary(password);
        byte[] decryptedPassw = DecrypterPassw.decryptPassw(p);
        byte[] hashedPasww = HashGenerator.generateHash(decryptedPassw);
        try {
            LOGGER.info("UserManager: Finding user by login and passw.");
            user = (User) em.createNamedQuery("findUserByLogin")
                    .setParameter("login", login).getSingleResult();
            if (MessageDigest.isEqual(hashedPasww, DatatypeConverter.parseHexBinary(user.getPassword()))) {
                LOGGER.log(Level.INFO, "UserManager: User found {0}", user.getLogin());
                if (user.getPrivilege() == UserPrivilege.USER) {
                    throw new UserPrivilegeException("User privilege not ok");
                } else {
                    user.setLastAccess(new Timestamp(System.currentTimeMillis()));
                    updateUser(user);
                }
            } else {
                throw new PasswordNotOkException("Password not ok for " + login);
            }
        } catch (NoResultException e) {
            LOGGER.log(Level.SEVERE, "UserManager: No result Finding user by login:",
                    e.getMessage());
            throw new UserNotExistException("User not found" + login);
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "UserManager: Persistance expception"
                    + "finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "UserManager: RuntimeException Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        } catch (UpdateException ex) {
            Logger.getLogger(UserManagerEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    /**
     * Finds user who forgot his password an send an email with the new
     * password.
     *
     * @param login Login of the user to find.
     * @return Boolean if all ok.
     * @throws ReadException If there is any Exception during processing.
     * @throws UserNotExistException User is not found in storage.
     */
    @Override
    public Boolean findUserForgotPassword(String login) throws ReadException,
            UserNotExistException {
        Boolean exists = false;
        User user = null;
        byte[] hashedPassw = null;
        LOGGER.info("UserManager: Finding user forgot password by login.");
        try {
            user = (User) em.createNamedQuery("findUserByLogin")
                    .setParameter("login", login).getSingleResult();
            if (user == null) {
                throw new UserNotExistException();
            } else {
                //Send email to user with new password
                hashedPassw = PasswordChangeAndMail.passwAndSendEmail(user.getEmail());
                user.setPassword(DatatypeConverter.printHexBinary(hashedPassw));
                user.setLastPasswordChange(new Timestamp(System.currentTimeMillis()));
                updateUser(user);
                exists = true;
            }
        } catch (NoResultException e) {
            LOGGER.log(Level.SEVERE, "UserManager: No result Finding user by login:",
                    e.getMessage());
            throw new UserNotExistException();
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "UserManager: Persistance expception"
                    + "finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "UserManager: RuntimeException Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "UserManager: Updateexception updating passw:",
                    ex.getMessage());
        }
        return exists;
    }

    /**
     * Find all users from storage.
     *
     * @return List of users.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<User> findAllTxokoUsers(Integer idTxoko) throws ReadException {
        List<User> users = null;
        try {
            LOGGER.info("UserManager: Reading all users.");
            users = em.createNamedQuery("findAllTxokoUsers")
                    .setParameter("idTxoko", idTxoko).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception reading all users:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return users;
    }

    /**
     * Finds all users of a specific txoko.
     *
     * @return List of users.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<User> findAllTxokoUsers() throws ReadException {
        List<User> users = null;
        try {
            LOGGER.info("UserManager: Reading all users.");
            users = em.createNamedQuery("findAllUsers").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception reading all users:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return users;
    }

    /**
     * Finds user by id.
     *
     * @param idUser IdUser of the user to find.
     * @return User found.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public User findUserById(Integer idUser) throws ReadException {
        User user = null;
        try {
            LOGGER.info("UserManager: finding user by idUser.");
            user = em.find(User.class, idUser);
            LOGGER.log(Level.INFO, "UserManager: User found {0}", user.getEmail());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception reading all users:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return user;
    }

    /**
     * Deletes an user from the aplication storage.
     *
     * @param user User to delete.
     * @throws DeleteException If there is any Exception during processing.
     */
    @Override
    public void deleteUser(User user) throws DeleteException {
        LOGGER.info("UserManagerEJB: Deleting user.");
        try {
            if (!em.contains(user)) {
                user = em.merge(user);
            }
            em.remove(user);
            LOGGER.info("UserManagerEJB: User deleted.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception deleting user.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Updates an user from the aplication storage.
     *
     * @param user User to update.
     * @throws UpdateException If there is any Exception during processing.
     */
    @Override
    public void updateUser(User user) throws UpdateException {
        LOGGER.info("UserManagerEJB: Updating user.");
        try {
            em.merge(user);
            em.flush();
            LOGGER.info("UserManagerEJB: User updated.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception updating user.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }

    }

    /**
     * Creates a User and stores it in the underlying application storage.
     *
     * @param user User to store.
     * @throws CreateException If there is any Exception during processing.
     * @throws UserLoginExistException If the login already exists.
     * @throws TxokoNotExistException If the txoko doesn't exist.
     */
    //****************************TRATAMIENTO DE EXCEPCIONES*************
    @Override
    public void createUser(User user) throws CreateException,
            UserLoginExistException, TxokoNotExistException {
        LOGGER.info("UserManager: Creating user.");
        try {
            byte[] p = DatatypeConverter.parseHexBinary(user.getPassword());
            byte[] decryptedPassw = DecrypterPassw.decryptPassw(p);
            byte[] hashedPasww = HashGenerator.generateHash(decryptedPassw);
            //String decryptedPassw = DecrypterPassw.decryptPassw(user.getPassword());
            //String hashedPasww = HashGenerator.generateHash(decryptedPassw);
            user.setPassword(DatatypeConverter.printHexBinary(hashedPasww));
            em.persist(user);
            LOGGER.info("UsderManager: User created.");
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "UserManager: Persistance expception"
                    + "creating user:",
                    e.getMessage());
            throw new UserLoginExistException();
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "UserManager: RuntimeException creating user:",
                    e.getMessage());
            throw new CreateException();
        }
    }

}
