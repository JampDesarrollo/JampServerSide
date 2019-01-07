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
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ander
 */
@Local
public interface UserManagerEJBLocal {
    /**
     * 
     * @param user
     * @throws DeleteException 
     */
    public void deleteUser(User user) throws DeleteException;
    /**
     * 
     * @param user
     * @throws UpdateException 
     */
    public void updateUser(User user) throws UpdateException;
    /**
     * 
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    public List<User> findAllTxokoUsers(Integer idTxoko) throws ReadException;
    /**
     * 
     * @param user
     * @throws CreateException
     * @throws UserLoginExistException
     * @throws TxokoNotExistException 
     */
    public void createUser(User user) throws CreateException,
            UserLoginExistException, TxokoNotExistException;
    /**
     * 
     * @param login
     * @return
     * @throws ReadException
     * @throws PasswordNotOkException
     * @throws UserNotExistException 
     */
    public User findUserByLogin(String login) throws ReadException,
            PasswordNotOkException, UserNotExistException;

}
