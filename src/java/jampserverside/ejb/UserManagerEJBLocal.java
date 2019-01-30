/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampserverside.ejb;

import jampserverside.entity.User;
import jampserverside.exception.CreateException;
import jampserverside.exception.DeleteException;
import jampserverside.exception.PasswordNotOkException;
import jampserverside.exception.ReadException;
import jampserverside.exception.TxokoNotExistException;
import jampserverside.exception.UpdateException;
import jampserverside.exception.UserLoginExistException;
import jampserverside.exception.UserNotExistException;
import jampserverside.exception.UserPrivilegeException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ander
 */
@Local
public interface UserManagerEJBLocal {

    /**
     * Deletes an user from the aplication storage.
     *
     * @param user User to delete.
     * @throws DeleteException If there is any Exception during processing.
     */
    public void deleteUser(User user) throws DeleteException;

    /**
     * Updates an user from the aplication storage.
     *
     * @param user User to update.
     * @throws UpdateException If there is any Exception during processing.
     */
    public void updateUser(User user) throws UpdateException;

    /**
     * Finds all users of a specific txoko.
     *
     * @param idTxoko Id of the txoko to find the users from.
     * @return List of users.
     * @throws ReadException If there is any Exception during processing.
     */
    public List<User> findAllTxokoUsers(Integer idTxoko) throws ReadException;

    /**
     * Find all users from storage.
     *
     * @return List of users.
     * @throws ReadException If there is any Exception during processing.
     */
    public List<User> findAllTxokoUsers() throws ReadException;

    /**
     * Creates a User and stores it in the underlying application storage.
     *
     * @param user User to store.
     * @throws CreateException If there is any Exception during processing.
     * @throws UserLoginExistException If the login already exists.
     * @throws TxokoNotExistException If the txoko doesn't exist.
     */
    public void createUser(User user) throws CreateException,
            UserLoginExistException, TxokoNotExistException;

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
    public User findUserByLoginPasswPC(String login, String password) throws
            ReadException, PasswordNotOkException, UserPrivilegeException,
            UserNotExistException;

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
    public User findUserByLoginPasswMov(String login, String password) throws
            ReadException, PasswordNotOkException, UserNotExistException;

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
    public Boolean findUserChangePasswMov(Integer idUser, String oldpass, String newPassw)
            throws ReadException, PasswordNotOkException, UserNotExistException;

    /**
     * Finds user who forgot his password an send an email with the new
     * password.
     *
     * @param login Login of the user to find.
     * @return Boolean if all ok.
     * @throws ReadException If there is any Exception during processing.
     * @throws UserNotExistException User is not found in storage.
     */
    public Boolean findUserForgotPassword(String login) throws ReadException,
            UserNotExistException;

    /**
     * Finds user by id.
     *
     * @param idUser IdUser of the user to find.
     * @return User found.
     * @throws ReadException If there is any Exception during processing.
     */
    public User findUserById(Integer idUser) throws ReadException;

}
