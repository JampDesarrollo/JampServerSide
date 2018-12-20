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

/**
 *
 * @author 2dam
 */
public interface UserManagerEJBLocal {
    public void deleteUser(User user) throws DeleteException;
    
    public void updateUser(User user) throws UpdateException;
    
    public List<User> findAllTxokoUsers(User user) throws ReadException;
    
    public void createUser (User user) throws CreateException,UserLoginExistException, TxokoNotExistException;
    
    public User findUserByLogin(String login) throws ReadException,PasswordNotOkException, UserNotExistException;
    
}
