/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.ejb;

import jampserverside.ejb.UserManagerEJBLocal;
import jampserverside.entity.User;
import jampserverside.exception.CreateException;
import jampserverside.exception.DeleteException;
import jampserverside.exception.PasswordNotOkException;
import jampserverside.exception.ReadException;
import jampserverside.exception.TxokoNotExistException;
import jampserverside.exception.UpdateException;
import jampserverside.exception.UserLoginExistException;
import jampserverside.exception.UserNotExistException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ander
 */
public class UserManagerEJB implements UserManagerEJBLocal {

    /**
     * Logger for the class.
     */
    private static final Logger LOGGER
            = Logger.getLogger("javafxserverside");
    /**
     * Entity manager object.
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    public void deleteUser(User user) throws DeleteException {
        LOGGER.info("UserManagerEJB: Deleting user.");
        try {
            user = em.merge(user);
            em.remove(user);
            LOGGER.info("UserManagerEJB: User deleted.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception deleting user.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

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

    @Override
    public List<User> findAllTxokoUsers(Integer idTxoko) throws ReadException {
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

    //****************************TRATAMIENTO DE EXCEPCIONES*************
    @Override
    public void createUser(User user) throws CreateException, UserLoginExistException, TxokoNotExistException {
        LOGGER.info("UserManager: Creating user.");
        try {
            em.persist(user);
            LOGGER.info("UsderManager: User created.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception creating user.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    //****************************TRATAMIENTO DE EXCEPCIONES*************
    @Override
    public User findUserByLogin(String login) throws ReadException, PasswordNotOkException, UserNotExistException {
        User user = null;
        try {
            LOGGER.info("UserManager: Finding user by login.");
            user = em.find(User.class, login);
            LOGGER.log(Level.INFO, "UserManager: User found {0}", user.getLogin());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return user;
    }

}
